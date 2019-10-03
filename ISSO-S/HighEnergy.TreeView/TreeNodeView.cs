using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Diagnostics;
using System.Linq;
using HighEnergy.Collections.Tree;
using Xamarin.Forms;

namespace HighEnergy.TreeView
{
    // analog to ITreeNode<T>
    public class TreeNodeView : StackLayout
    {
	    private Grid _mainLayoutGrid;
	    private ContentView _headerView;
	    private StackLayout _childrenStackLayout;

	    private TreeNodeView ParentTreeNodeView { get; set; }

        public static readonly BindableProperty IsExpandedProperty = BindableProperty.Create("IsExpanded", typeof(bool), typeof(TreeNodeView), true, BindingMode.TwoWay, null, 
            (bindable, oldValue, newValue) =>
            {
                var node = (TreeNodeView) bindable;

                if (oldValue == newValue || node == null)
                    return;

                node.BatchBegin();
                try
                {
                    // show or hide all children
                    node._childrenStackLayout.IsVisible = node.IsExpanded;
                }
                finally
                {
                    // ensure we commit
                    node.BatchCommit();
                }
            });

        public bool IsExpanded
        {
            get => (bool)GetValue(IsExpandedProperty);
	        set => SetValue(IsExpandedProperty, value);
        }

        public View HeaderContent
        {
            get => _headerView.Content;
	        set => _headerView.Content = value;
        }

        public IEnumerable<TreeNodeView> ChildTreeNodeViews => _childrenStackLayout.Children.Cast<TreeNodeView>();

	    protected void DetachVisualChildren()
        {
			var views = _childrenStackLayout.Children.OfType<TreeNodeView>().ToList();

			foreach (var nodeView in views)
            {
                _childrenStackLayout.Children.Remove(nodeView);
                nodeView.ParentTreeNodeView = null;
            }
        }

        protected override void OnBindingContextChanged()
        {
            // prevent exceptions for null binding contexts
            // and during startup, this node will inherit its BindingContext from its Parent - ignore this
            if (BindingContext == null || (Parent != null && BindingContext == Parent.BindingContext))
                return;

	        if (!(BindingContext is ITreeNode))
                throw new InvalidOperationException("TreeNodeView currently only supports TreeNode-derived data binding sources.");

			base.OnBindingContextChanged();

			// clear out any existing child nodes - the new data source replaces them
            // make sure we don't do this if BindingContext == null
            DetachVisualChildren();

            // build the new visual tree
            BuildVisualChildren();
        }

	    private Func<View> _headerCreationFactory;
        public Func<View> HeaderCreationFactory
        {
            // [recursive up] inherit property value from parent if null
            get => _headerCreationFactory ?? ParentTreeNodeView?.HeaderCreationFactory;
	        set
            {
                if (value == _headerCreationFactory)
                    return;

                _headerCreationFactory = value;
                OnPropertyChanged();

                // wait until both factories are assigned before constructing the visual tree
                if (_headerCreationFactory == null || _nodeCreationFactory == null)
                    return;

                BuildHeader();
                BuildVisualChildren();
            }
        }

        Func<TreeNodeView> _nodeCreationFactory;
        public Func<TreeNodeView> NodeCreationFactory
        {
            // [recursive up] inherit property value from parent if null
            get => _nodeCreationFactory ?? ParentTreeNodeView?.NodeCreationFactory;
	        set
            {
                if (value == _nodeCreationFactory)
                    return;

                _nodeCreationFactory = value;
                OnPropertyChanged();

                // wait until both factories are assigned before constructing the visual tree
                if (_headerCreationFactory == null || _nodeCreationFactory == null)
                    return;

                BuildHeader();
                BuildVisualChildren();
            }
        }

        protected void BuildHeader()
        {
            // the new HeaderContent will inherit its BindingContext from this.BindingContext [recursive down]
            if (HeaderCreationFactory != null)
                HeaderContent = HeaderCreationFactory.Invoke();
        }

        // [recursive down] create item template instances, attach and layout, and set descendents until finding overrides
        protected void BuildVisualChildren()
        {
            var bindingContextNode = (ITreeNode)BindingContext;
            if (bindingContextNode == null)
                return;

            // STEP 1: remove child visual tree nodes (TreeNodeViews) that don't correspond to an item in our data source

	        var bindingChildList = new List<ITreeNode>(bindingContextNode.ChildNodes);

            // which ChildTreeNodeViews are in the visual tree... ?
	        var nodeViewsToRemove = ChildTreeNodeViews.Where(nodeView => !bindingChildList.Contains(nodeView.BindingContext)).ToList();

	        BatchBegin();
            try
            {
                // perform removal in a batch
                foreach (var nodeView in nodeViewsToRemove)
                    _mainLayoutGrid.Children.Remove(nodeView);
            }
            finally
            {
                // ensure we commit
                BatchCommit();
            }

            // STEP 2: add visual tree nodes (TreeNodeViews) for children of the binding context not already associated with a TreeNodeView

	        if (NodeCreationFactory == null) return;
	        {
		        var nodeViewsToAdd = new Dictionary<TreeNodeView,ITreeNode>();

		        foreach (var node in bindingContextNode.ChildNodes)
		        {
			        if (ChildTreeNodeViews.Any(nodeView => nodeView.BindingContext == node)) continue;
			        {
				        var nodeView = NodeCreationFactory.Invoke();
				        nodeView.ParentTreeNodeView = this;

				        if (HeaderCreationFactory != null)
					        nodeView.HeaderContent = HeaderCreationFactory.Invoke();

				        // the order of these may be critical
				        nodeViewsToAdd.Add(nodeView, node);
			        }
		        }

		        BatchBegin();
		        try
		        {
			        // perform the additions in a batch
			        foreach (var nodeView in nodeViewsToAdd)
			        {
				        // only set BindingContext after the node has Parent != null
				        nodeView.Key.BindingContext = nodeView.Value;
				        nodeView.Key.VerticalOptions = LayoutOptions.Fill;
				        nodeView.Key.HorizontalOptions = LayoutOptions.Fill;
				        nodeView.Value.ExpandAction = () => nodeView.Key.BuildVisualChildren();
				        nodeView.Key._childrenStackLayout.IsVisible = nodeView.Key.IsExpanded;
				        _childrenStackLayout.Children.Add(nodeView.Key);

				        _childrenStackLayout.SetBinding(IsVisibleProperty, new Binding("IsExpanded", BindingMode.TwoWay));

				        // TODO: make sure to unsubscribe elsewhere
				        nodeView.Value.PropertyChanged += HandleListCountChanged;
			        }
		        }
		        finally
		        {
			        // ensure we commit
			        BatchCommit();
		        }
	        }
        }

	    private void HandleListCountChanged(object sender, PropertyChangedEventArgs e)
        {
			Device.BeginInvokeOnMainThread(() =>
			    {
				    if (e.PropertyName != "Count") return;
				    var nodeView = ChildTreeNodeViews.FirstOrDefault(nv => nv.BindingContext == sender);
				    nodeView?.BuildVisualChildren();
			    });
        }

		public void InitializeComponent()
        {
            IsExpanded = true;

            _mainLayoutGrid = new Grid
                {
                    VerticalOptions = LayoutOptions.StartAndExpand,
                    HorizontalOptions = LayoutOptions.FillAndExpand
                };
            _mainLayoutGrid.ColumnDefinitions.Add(new ColumnDefinition { Width = new GridLength(1, GridUnitType.Star)});
            //MainLayoutGrid.RowDefinitions.Add(new RowDefinition { Height = GridLength.Auto });
            _mainLayoutGrid.RowDefinitions.Add(new RowDefinition { Height = GridLength.Auto });

            _headerView = new ContentView
                {
                    HorizontalOptions = LayoutOptions.FillAndExpand,
                };
            _mainLayoutGrid.Children.Add(_headerView);

            _childrenStackLayout = new StackLayout
            {
                Orientation = Orientation,
                Spacing = 0
            };
            _mainLayoutGrid.Children.Add(_childrenStackLayout, 0, 1);

            //Добавляем в SCROLL
	        var scrollView = new ScrollView
	        {
		        VerticalOptions = LayoutOptions.FillAndExpand,
		        HorizontalOptions = LayoutOptions.FillAndExpand,
		        Content = _mainLayoutGrid
	        };
	        Children.Add(scrollView);

            Spacing = 0;
            Padding = new Thickness(0);
            HorizontalOptions = LayoutOptions.FillAndExpand;
            VerticalOptions = LayoutOptions.Start;
        }

        public TreeNodeView()
        {
            InitializeComponent();

            Debug.WriteLine("new TreeNodeView");
        }
    }

    /// <summary>
    /// Класс для отображения листьев дерева
    /// </summary>
    public class InfoRow
    {
        /// <summary>
        /// Системное имя таблицы
        /// </summary>
        public string SysName;
        /// <summary>
        /// Описание
        /// </summary>
        public string Description;
        /// <summary>
        /// Кто родитель (C_GR_CONSTR; если -100, то ROOT
        /// </summary>
        public int ParentId;
        /// <summary>
        /// Группа конструкций
        /// </summary>
        public int CGrConstr;
        /// <summary>
        /// Количество колонок
        /// </summary>
        public int CountOfColumns;


        public InfoRow() { }
        public InfoRow(string sysName, string description, int parentId, int cGrConstr, int countOfColumns)
        {
            SysName = sysName;
            Description = description;
            ParentId = parentId;
            CGrConstr = cGrConstr;
            CountOfColumns = countOfColumns;
        }
    }
}