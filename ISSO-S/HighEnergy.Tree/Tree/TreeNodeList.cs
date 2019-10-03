using System;
using System.Collections.Generic;
using System.ComponentModel;

namespace HighEnergy.Collections.Tree
{
    public class TreeNodeList<T> : List<ITreeNode<T>>, ITreeNodeList<T>
    {
        public ITreeNode<T> Parent { get; set; }

        public TreeNodeList(ITreeNode<T> parent)
        {
            // call property setters to trigger setup and event notifications
            Parent = parent;
        }

        public new ITreeNode<T> Add(ITreeNode<T> node)
        {
            return Add(node, true);
        }

        protected internal ITreeNode<T> Add(ITreeNode<T> node, bool updateParent)
        {
            if (updateParent)
            {
                // force Node.SetParent to coordinate the update
                node.SetParent(Parent);
                return node;
            }

            base.Add(node);
            OnPropertyChanged("Count");
            return node;
        }

        public new bool Remove(ITreeNode<T> node)
        {
            return Remove(node, true);
        }

        protected internal bool Remove(ITreeNode<T> node, bool updateParent)
        {
            if (node == null)
                throw new ArgumentNullException(nameof(node));

            // if we don't have it, we can't remove it
            if (!Contains(node))
                return false;

            if (updateParent)
            {
                // force Node.SetParent to coordinate the update
                node.SetParent(null, false);

                // we're successful if the node is no longer in the collection
                return !Contains(node);
            }

            var result = base.Remove(node);
            OnPropertyChanged("Count");
            return result;
        }

        public event PropertyChangedEventHandler PropertyChanged;
        protected void OnPropertyChanged(string propertyName)
        {
	        PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }

        public override string ToString()
        {
            return "Count=" + Count;
        }
    }
}