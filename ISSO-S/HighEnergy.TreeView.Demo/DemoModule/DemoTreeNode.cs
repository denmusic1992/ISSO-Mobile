using System.Diagnostics;
using System.Windows.Input;
using CommonClassesLibrary;
using HighEnergy.Collections.Tree;
using Xamarin.Forms;

namespace HighEnergy.TreeView.Demo.DemoModule
{
    public class TreeNode : TreeNode<TreeNode>
    {
        public ICommand ToggleIsExpandedCommand { protected set; get; }

        public ICommand GoToTableContent { get; set; }

        // normal view model properties provide the content as well as the visual state

	    private bool _isExpanded;
        public bool IsExpanded
        {
            get => _isExpanded;
	        set { Set("IsExpanded", ref _isExpanded, value); _imageExpand.File = CommonStaffUtils.GetFilePath(value ? "minus.png" : "plus.png"); }
        }

	    private bool _isNotLeaf;
        public bool IsNotLeaf
        {
            get => _isNotLeaf;
	        set => Set("IsNotLeaf", ref _isNotLeaf, value);
        }

	    private FileImageSource _imageExpand = new FileImageSource { File = CommonStaffUtils.GetFilePath("minus.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "minus.png")*/ };
        public FileImageSource ImageExpand
        {
            get => _imageExpand;
	        set => Set("ImageExpand", ref _imageExpand, value);
        }

        // we're 100% in control of the indentation level, if any, that we use in rendering our tree nodes
        public double IndentWidth => Depth * 30;


	    /// <summary>
        /// Описание ноды
        /// </summary>
        public string Description { get; set; }
        /// <summary>
        /// Системное имя ноды
        /// </summary>
        public string Sysname { get; set; }

        /// <summary>
        /// Номер ИССО
        /// </summary>
        public int CIsso { get; set; }

		/// <summary>
		/// заголовок, некликабельный
		/// </summary>
		public bool IsHeader { get; set; }

        /// <summary>
        /// Номер группы конструкций
        /// </summary>
        public int CGrConstr { get; set; }

		public object[] Args { get; set; }

		public string Bdrg { get; set; }

	    private bool selected;
		public bool Selected {
			get => selected;
			set 
			{
				if (Selected == value) return;
				selected = value; 
				OnPropertyChanged("Selected");
			}
		}


        protected override void OnPropertyChanged(string propertyName)
        {
            base.OnPropertyChanged(propertyName);

            if (propertyName == "Depth")
            {
                Debug.WriteLine("TreeNode.Depth property changed - triggering update for IndentWidth");
                base.OnPropertyChanged("IndentWidth");
            }
        }

        public TreeNode()
        {
			ToggleIsExpandedCommand = new Command(obj =>
			{
				IsExpanded = !IsExpanded;
				ImageExpand = IsExpanded ? new FileImageSource { File = CommonStaffUtils.GetFilePath("minus.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "minus.png")*/ } : new FileImageSource { File = CommonStaffUtils.GetFilePath("plus.png") /* String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "plus.png")*/ };
			});
        }

        public override string ToString()
        {
            return string.Format("TreeNode: Description={3}, SysName={4}, C_ISSO={5}, C_GR_CONSTR={6} IsExpanded={1}, IndentWidth={2} " 
                + base.ToString(), ToggleIsExpandedCommand, IsExpanded, IndentWidth, Description, Sysname, CIsso, CGrConstr);
        }
    }
}