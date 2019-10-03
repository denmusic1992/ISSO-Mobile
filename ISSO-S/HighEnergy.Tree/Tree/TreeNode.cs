using System;
using System.Collections.Generic;
using System.Linq;
using HighEnergy.Collections.Helpers;

namespace HighEnergy.Collections.Tree
{
    public class TreeNode<T> : ObservableObject, ITreeNode<T>, IDisposable
        where T : new()
    {
        public TreeNode()
        {
            // call property setters to trigger setup and event notifications
            _parent = null;
            Children = new TreeNodeList<T>(this);
        }

        public TreeNode(T value)
        {
            // call property setters to trigger setup and event notifications
            Value = value;
            _parent = null;
            Children = new TreeNodeList<T>(this);
        }

        public TreeNode(T value, ITreeNode<T> parent)
        {
            // call property setters to trigger setup and event notifications
            Value = value;
            _parent = parent;
            Children = new TreeNodeList<T>(this);
        }

        public ITreeNode ParentNode => _parent;

	    private ITreeNode<T> _parent;
        public ITreeNode<T> Parent
        {
            get => _parent;
	        set => SetParent(value);
        }

        public void SetParent(ITreeNode<T> node, bool updateChildNodes = true)
        {
            if (node == Parent)
                return;

            var oldParent = Parent;
            var oldParentHeight = Parent?.Height ?? 0;
            var oldDepth = Depth;

            // if oldParent isn't null
            // remove this node from its newly ex-parent's children
            if (oldParent != null && oldParent.Children.Contains(this))
                oldParent.Children.Remove(this, false);

            // update the backing field
            _parent = node;

            // add this node to its new parent's children
            if (_parent != null && updateChildNodes)
                _parent.Children.Add(this, updateParent: false);

            // signal the old parent that it has lost this child
	        oldParent?.OnDescendantChanged(NodeChangeType.NodeRemoved, this);

	        if (oldDepth != Depth)
                OnDepthChanged();

            // if this operation has changed the height of any parent, initiate the bubble-up height changed event
            if (Parent != null)
            {
                var newParentHeight = Parent?.Height;
                if (newParentHeight != oldParentHeight)
                    Parent.OnHeightChanged();
                
                Parent.OnDescendantChanged(NodeChangeType.NodeAdded, this);
            }

            OnParentChanged(oldParent, Parent);
        }

        protected virtual void OnParentChanged(ITreeNode<T> oldValue, ITreeNode<T> newValue)
        {
            OnPropertyChanged("Parent");
        }

        // TODO: add property and event notifications that are missing from this set: DescendentsChanged, AnscestorsChanged, ChildrenChanged, ParentChanged

        public ITreeNode<T> Root => (Parent == null) ? this : Parent.Root;

	    public TreeNodeList<T> Children { get; }

	    // non-generic iterator for interface-based support (From TreeNodeView, for example)
        public IEnumerable<ITreeNode> ChildNodes
        {
            get
            {
                foreach (var node in Children)
                    yield return node;
            }
        }

        public IEnumerable<ITreeNode> Descendants
        {
            get
            {
                foreach (var node in ChildNodes)
                {
                    yield return node;

                    foreach (var descendant in node.Descendants)
                        yield return descendant;
                }
            }
        }

        public IEnumerable<ITreeNode> Subtree
        {
            get
            {
                yield return this;

                foreach (var node in Descendants)
                    yield return node;
            }
        }

        public IEnumerable<ITreeNode> Ancestors
        {
            get
            {
                if (Parent == null)
                    yield break;

                yield return Parent;

                foreach (var node in Parent.Ancestors)
                    yield return node;
            }
        }

        public event Action<NodeChangeType, ITreeNode> AncestorChanged;
        public virtual void OnAncestorChanged(NodeChangeType changeType, ITreeNode node)
        {
	        AncestorChanged?.Invoke(changeType, node);

	        foreach (var child in Children)
                child.OnAncestorChanged(changeType, node);
        }

        public event Action<NodeChangeType, ITreeNode> DescendantChanged;
        public virtual void OnDescendantChanged(NodeChangeType changeType, ITreeNode node)
        {
	        DescendantChanged?.Invoke(changeType, node);

	        Parent?.OnDescendantChanged(changeType, node);
        }

        // [recurse up] descending aggregate property
        public int Height
        {
            get { return Children.Count == 0 ? 0 : Children.Max(n => n.Height) + 1; }
        }

        // [recurse down] descending-broadcasting event
        public virtual void OnHeightChanged()
        {
            OnPropertyChanged("Height");

            foreach (var child in Children)
                child.OnHeightChanged();
        }

        private T _value;
        public T Value
        {
            get => _value;
	        set
            {
                if (value == null && _value == null)
                    return;

                if (value != null && _value != null && value.Equals(_value))
                    return;

                _value = value;
                OnPropertyChanged("Value");

                // set Node if it's ITreeNodeAware
                if (_value != null && _value is ITreeNodeAware<T>)
                    ((ITreeNodeAware<T>) _value).Node = this;
            }
        }

        // [recurse up] bubble up aggregate property
        public int Depth => Parent?.Depth + 1 ?? 0;

	    // [recurse up] bubble up event
        public virtual void OnDepthChanged()
        {
            OnPropertyChanged("Depth");

	        Parent?.OnDepthChanged();
        }

	    public UpDownTraversalType DisposeTraversal { get; set; } = UpDownTraversalType.BottomUp;

	    public bool IsDisposed { get; private set; }

	    public Action ExpandAction { get; set; }

		public virtual void Dispose()
        {
            CheckDisposed();
            OnDisposing();

            // clean up contained objects (in Value property)
            if (Value is IDisposable disposable)
            {
                if (DisposeTraversal == UpDownTraversalType.BottomUp)
                    foreach (var treeNode in Children)
	                    {
		                    var node = (TreeNode<T>) treeNode;
		                    node.Dispose();
	                    }

	            disposable.Dispose();

                if (DisposeTraversal == UpDownTraversalType.TopDown)
                    foreach (var treeNode in Children)
	                    {
		                    var node = (TreeNode<T>) treeNode;
		                    node.Dispose();
	                    }
            }

            IsDisposed = true;
        }

        public event EventHandler Disposing;

        protected void OnDisposing()
        {
	        Disposing?.Invoke(this, EventArgs.Empty);
        }

        public void CheckDisposed()
        {
            if (IsDisposed)
                throw new ObjectDisposedException(GetType().Name);
        }

        public override string ToString()
        {
            return "Depth=" + Depth + ", Height=" + Height + ", Children=" + Children.Count;
        }
    }
}