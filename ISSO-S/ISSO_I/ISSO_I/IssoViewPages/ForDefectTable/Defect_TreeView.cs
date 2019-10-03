using System;
using System.Linq;
using System.Windows.Input;
using HighEnergy.Collections.Helpers;
using HighEnergy.TreeView;
using HighEnergy.TreeView.Demo.DemoModule;
using ISSO_I.CustomRenderes;
using ISSO_I.IssoViewPages.ForDefectTable.Models;
using ISSO_I.IssoViewPages.ForDefectTable.ViewModels;
using Xamarin.Forms;
using DemoTreeCardView = HighEnergy.TreeView.Demo.DemoModule.DemoTreeCardView;

namespace ISSO_I.IssoViewPages.ForDefectTable
{
	public class DefectTreeView : HighEnergy.TreeView.TreeView, IDisposable
	{

		/// <summary>
		/// Модель дерева дефектов
		/// </summary>
		private DefectTreeModel ViewModel { get; }

		public DefectTreeView (int cIsso, Ais7IssoDefectsTreeNode parent, bool isMainDefect)
		{
			ViewModel = new DefectTreeModel(cIsso, parent, isMainDefect);
			//TreeViewModel.navigation = this.Navigation;
			NodeCreationFactory = () => new TreeNodeView
			{
				HorizontalOptions = LayoutOptions.FillAndExpand,
				VerticalOptions = LayoutOptions.Start,
			};
			HeaderCreationFactory = () => new DemoTreeCardView
			{
				HorizontalOptions = LayoutOptions.FillAndExpand,
				VerticalOptions = LayoutOptions.Start
			};
			BindingContext = ViewModel.Tree;
		}

		public void Dispose()
		{
			ViewModel?.Dispose();
		}
	}

	public class ParamTreeView : HighEnergy.TreeView.TreeView, IDisposable
	{
		/// <summary>
		/// Модель дерева дефектов
		/// </summary>
		private ParamsTreeModel ViewModel { get; }


		public ParamTreeView(CreateDefectModel vm)
		{
			ViewModel = new ParamsTreeModel(vm);

			NodeCreationFactory = () => new TreeNodeView
			{
				HorizontalOptions = LayoutOptions.FillAndExpand,
				VerticalOptions = LayoutOptions.Start,
			};
			HeaderCreationFactory = () => new ParamDefectItemView()
			{
				HorizontalOptions = LayoutOptions.FillAndExpand,
				VerticalOptions = LayoutOptions.Start
			};
			BindingContext = ViewModel.Tree;
		}

		public void Dispose()
		{
		}
	}

	public class ParamsTreeModel : ObservableObject, IDisposable
	{
		public const string Descr = "Качественные параметры";

		/// <summary>
		///  Элемент дерева дефектов
		/// </summary>
		private TreeNode _tree;
		public TreeNode Tree
		{
			get => _tree;
			set => Set("Tree", ref _tree, value);
		}

		/// <summary>
		/// индентификатор нажатия элемента
		/// </summary>
		private static bool? NodeTouched { get; set; } = false;

		//public static INavigation navigation { get; set; }

		/// <summary>
		/// обработка нажатия элемента
		/// </summary>
		public ICommand GoToTableContent { get; set; }

		public ParamsTreeModel(CreateDefectModel parent)
		{
			Tree = new TreeNode
			{
				Description = Descr,
				IsExpanded = true,
				IsHeader = true,
				IsNotLeaf = true
			};
			// инициализируем обработку нажатия на ноду
			GoToTableContent = new Command<TreeNode>((treeNode) =>
			{
				if (NodeTouched ?? false) return;
				NodeTouched = true;
				// do stuff
				foreach (var treeNode1 in treeNode.Parent.Children)
				{
					var node = (TreeNode) treeNode1;
					node.Selected = false;

				}
				treeNode.Selected = true;

				parent.SelectedQualDefectParameter = parent.DefectParameters.Where(item =>
					item.CDefParam == (short) treeNode.Args[0] && item.Category == (short) treeNode.Args[1]).ToArray()[0];
				NodeTouched = false;
			});
			var index = 0;
			foreach (var child in parent.DefectParameters.Where(item => item.IsQual))
			{
				var treeNode = new TreeNode
				{
					CGrConstr = index,
					Description = child.Name,
					Args = new object[] { child.CDefParam, child.Category },
					IsNotLeaf = false,
					IsHeader = false,
					Bdrg = child.GetBdrg,
					GoToTableContent = GoToTableContent
				};
				index++;
				Tree.Children.Add(treeNode);
			}
		}

		public void Dispose()
		{
		}
	}


	public class DefectTreeModel : ObservableObject, IDisposable
	{
		/// <summary>
		/// Описание дефектов по умолчанию
		/// </summary>
		public const string ParentDescription = "Характерные дефекты";

		/// <summary>
		///  Элемент дерева дефектов
		/// </summary>
		private TreeNode _tree;
		public TreeNode Tree
		{
			get => _tree;
			set => Set("Tree", ref _tree, value);
		}

		/// <summary>
		/// индентификатор нажатия элемента
		/// </summary>
		private static bool? NodeTouched { get; set; } = false;

		//public static INavigation navigation { get; set; }

		/// <summary>
		/// обработка нажатия элемента
		/// </summary>
		public ICommand GoToTableContent { get; set; } = new Command<TreeNode>((treeNode) =>
		{
			if (NodeTouched ?? false) return;
			NodeTouched = true;
			var createDefectContentPage = new CreateDefectContentPage(treeNode.CIsso, treeNode.CGrConstr, treeNode.Description, treeNode.Args);
			((MyNavigationPage) ((MasterDetailPage1) Application.Current.MainPage).Detail).Navigation.PushAsync(createDefectContentPage);
			// do stuff

			NodeTouched = false;
		});

		public DefectTreeModel(int cIsso, Ais7IssoDefectsTreeNode parent, bool isMainDefect)
		{
			Tree = new TreeNode
			{
				CIsso = cIsso,
				CGrConstr = parent.CGrConstr,
				Description = isMainDefect ? ParentDescription : parent.NGrConstr,
				IsExpanded = isMainDefect,
				IsHeader = true,
				IsNotLeaf = true
			};

			foreach (var child in parent.DefectList)
			{
				var treeNode = new TreeNode
				{
					CIsso = cIsso,
					CGrConstr = parent.CGrConstr,
					Description = child.NDefect,
					IsNotLeaf = false,
					IsHeader = false,
					Args = new object[]{child.CDefect, child.NDefect, child},
					GoToTableContent = GoToTableContent
				};
				Tree.Children.Add(treeNode);
			}
		}


		public void Dispose()
		{
			_tree.Children.Clear();
			_tree.Dispose();
			NodeTouched = null;
			GC.Collect();
		}
	}
}