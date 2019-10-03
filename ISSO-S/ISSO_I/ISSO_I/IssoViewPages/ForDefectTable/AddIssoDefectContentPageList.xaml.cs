using CommonClassesLibrary;
using ISSO_I.Additional_Classes;
using Rg.Plugins.Popup.Extensions;
using SQLite;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Diagnostics.CodeAnalysis;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using CommonClassesLibrary.PopupPages;
using HighEnergy.TreeView;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_I.IssoViewPages.ForDefectTable
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class AddIssoDefectContentPageList
    {
        /// <summary>
        /// Номер конструкции
        /// </summary>
        private int CIsso { get; }

		private Ais7IssoDefectsTreeNode CurrentLevel { get; }

		/// <summary>
		/// Список конструктивов
		/// </summary>
		private List<VGrConstrDef2> AdvancedSGrConstrList { get; set; }

        /// <summary>
        /// Список дефектов
        /// </summary>
        private List<CustomDefectAlterTable> AdvancedDefectsList { get; set; }

		/// <summary>
		/// Полное или неполное дерево дефектов
		/// </summary>
		private Ais7IssoDefectsTreeNode.VisibleMode VisibilityMode { get; set; }

		/// <summary>
		/// Главная нода, построенная по дефектам
		/// </summary>
		private Ais7IssoDefectsTreeNode2 RootNode { get; set; }

	    /// <summary>
	    /// Конструктор
	    /// </summary>
	    /// <param name="cIsso"></param>
	    /// <param name="rootNode"></param>
	    /// <param name="currentLevel"></param>
	    /// <param name="visibleMode"></param>
	    public AddIssoDefectContentPageList(int cIsso, Ais7IssoDefectsTreeNode2 rootNode = null,
		    Ais7IssoDefectsTreeNode currentLevel = null,
		    Ais7IssoDefectsTreeNode.VisibleMode visibleMode = Ais7IssoDefectsTreeNode.VisibleMode.ExistConstructions)
        {
            InitializeComponent();
	        Title = $"ИССО №{cIsso}: Добавление дефекта.";
            CIsso = cIsso;
			CurrentLevel = currentLevel;
			RootNode = rootNode;
			VisibilityMode = visibleMode;
			ChangeDefectView.Icon = new FileImageSource
			{
				File = CommonStaffUtils.GetFilePath(visibleMode == Ais7IssoDefectsTreeNode.VisibleMode.AllContructions
					? "elements_tree_full.png"
					: "elements_tree.png")
			};
			
		}

		protected override void OnAppearing()
		{
			base.OnAppearing();
			

			// Если у нас RootNode ещё не строился, то вызываем buildTree в отдельном потоке, иначе просто строим список
			if(RootNode != null)
			{
				InitTreeDefect();
			}
			else
			{
				var popupPage = new LoadingPopupPage("Идет построение дерева дефектов...", true);
				Navigation.PushPopupAsync(popupPage);
				Task.Factory.StartNew(() =>
				{
					RootNode = BuildTree();
					Device.BeginInvokeOnMainThread(() => { InitTreeDefect(); Navigation.PopPopupAsync(); });
				});
			}

			const string textStart = "Путь до текущей группы конструкций: ";
			// Путь в дереве дефектов
			// Если это родительская нода
			if(CurrentLevel?.ParentNode != null)
			{
				var nameBuilder = new StringBuilder(CurrentLevel.NGrConstr);
				var tempNode = CurrentLevel;
				// Пока не дошли до рутовой ноды
				while(tempNode.ParentNode != null)
				{
					tempNode = tempNode.ParentNode;
					// не записываем "Общие данные"
					if(tempNode.ParentNode != null)
						// записываем в начало
						nameBuilder.Insert(0, $"{tempNode.NGrConstr}/");
				}
				TreeNodeLevelLabel.Text = $"{textStart}/{nameBuilder}";
			}
			// Иначе мы убираем с экрана этот label
			else
			{
				TreeNodeLevelLabel.Text = $"{textStart}/";
				//TreeNodeLevelLabel.IsVisible = false;
			}
		}

	    /// <summary>
	    /// Метод, инициализирующий построение списка конструктивов и перечней дефектов
	    /// </summary>
	    private Ais7IssoDefectsTreeNode2 BuildTree()
        {
			//Класс поключения к БД
			var connection = ConnectionClass.CreateDatabase();

			// Получение наименований конструктивов, нужных для построения списков
			var tlList = connection.Query<TableList>("select n_gr_constr from v_gr_constr_def").ToList().Select(list => list.n_gr_constr).ToList();

			// Текущий тип ИССО
			var typeIsso = connection.ExecuteScalar<int>($"select CTYPEISSO from I_ISSO where C_ISSO={CIsso}");

			// Забираем значения групп конструкций для текущего типа ИССО
			var forCtypisso = connection.Query<TableList>(
				$"select C_GR_CONSTR as N_GR_CONSTR from TABLE_DELEGATES where ISSO_TYPE={typeIsso}");

			// Построение первого уровня конструктивов
			var tableNames = connection.Query<DBHelper.TABLE_NAMES>(
				$"select SYS_NAME, DESCRIPTION, PARENT_ID, C_GR_CONSTR from TABLE_NAMES where C_GR_CONSTR in ({string.Join(',', forCtypisso.Select(x => x.n_gr_constr).ToList())})")
				.ToList();
			//var table_Names = connection.Query<TABLE_NAMES>(String.Format("select n_gr_constr as SYS_NAME, text as DESCRIPTION, c_gr_constr, parent_group as PARENT_ID from S_GR_CONSTR " +
			//    "order by c_gr_constr"));
			var infoRows = new List<InfoRow>();
			 foreach (var name in tableNames)
				 infoRows.Add(new InfoRow(name.SYS_NAME, name.DESCRIPTION, name.PARENT_ID, name.C_GR_CONSTR, -1));
			 
			// Получаем весь список возможных дефектов
			AdvancedSGrConstrList = connection.Query<VGrConstrDef2>("select * from v_gr_constr_def2 order by parent_group, c_gr_constr").ToList();

			// Список конструктивов
			AdvancedDefectsList = connection.Query<CustomDefectAlterTable>(
				"select s_def4constr.c_gr_constr, is_constr, s_defect.*, item_type, lock_expert, life, ord from s_def4constr " +
				"left outer join s_gr_constr on s_def4constr.c_gr_constr = s_gr_constr.c_gr_constr " +
				"left outer join s_defect on s_defect.c_defect = s_def4constr.c_defect").ToList();

			connection.Close();

			// Строим дерево
			var rootNode = new Ais7IssoDefectsTreeNode2(infoRows.Find(x => x.CGrConstr == 10).SysName, tlList, null, infoRows, AdvancedDefectsList);

			// Добавляем в дерево элементы конструктивов
			foreach (var sRow in AdvancedSGrConstrList)
			{
				try
				{
					var node = rootNode.LocateNode(sRow.PARENT_GROUP);
					node?.ChildrenNodes.Add(new Ais7IssoDefectsTreeNode(sRow, node, AdvancedDefectsList));
				}
				catch (Exception ex)
				{
					Console.WriteLine(ex.Message);
				}
			}
			Console.WriteLine("Done!");

			// Здесь будем смотреть, есть ли элементы дочерние для корневых в дереве
			foreach (var child in rootNode.ChildrenNodes.ToList())
				 LeafCapturingMethod(child);

			return rootNode;
        }

		private void LeafCapturingMethod(Ais7IssoDefectsTreeNode parent)
		{
			parent.IsNotLeaf = AdvancedSGrConstrList.FindAll(x =>
				                   x.PARENT_GROUP == parent.CGrConstr && (Ais7IssoDefectsTreeNodeType) x.ITEM_TYPE !=
				                   Ais7IssoDefectsTreeNodeType.Type0).Count > 0;
			foreach(var child in parent.ChildrenNodes)
			{
				LeafCapturingMethod(child);
			}
		}

		private void InitTreeDefect()
		{
			// В список добавляем текущие элементы для отображения
			var selected = (CurrentLevel ?? RootNode).ChildrenNodes.ToList();

			Defect_list.ItemsSource =
				new ObservableCollection<Ais7IssoDefectsTreeNode>(selected.Where(x =>
					x.Visible(CIsso, x.CGrConstr, VisibilityMode)));
		}

        public void ListViewItemTapped(object sender, ItemTappedEventArgs e)
        {
			var selectedItem = (Ais7IssoDefectsTreeNode)e.Item;
			//var advancedDefectsForGrConstr = new List<Ais7IssoDefectsTreeNode>();
			//foreach(var child in selectedItem.ChildrenNodes)
			//{
			//	if(child.ItemType == Ais7IssoDefectsTreeNodeType.Type0)
			//	{
			//		advancedDefectsForGrConstr.Add(child);
			//	}
			//}
	        if (!selectedItem.IsDefectLeaf)
	        {
		        var advancedDefectsForGrConstr = selectedItem.ChildrenNodes
			        .Where(item => item.ItemType == Ais7IssoDefectsTreeNodeType.Type0).ToList();
		        var constrPage = new DefectForCGrConstrPage(CIsso, selectedItem, advancedDefectsForGrConstr);
		        Navigation.PushAsync(constrPage);
	        }
	        else
	        {
		        Navigation.PushPopupAsync(new CommonPopupPage(
			        new AlertPopupPage(true, "Дефекты для этой группы конструкций отсутствуют."), "Отсутствие дефектов"));
	        }
	        ((ListView)sender).SelectedItem = null;
        }

		private void ChangeDefectView_Clicked(object sender, EventArgs e)
		{
			switch (VisibilityMode)
			{
				case Ais7IssoDefectsTreeNode.VisibleMode.ExistConstructions:
					VisibilityMode = Ais7IssoDefectsTreeNode.VisibleMode.AllContructions;
					ChangeDefectView.Icon = new FileImageSource { File = CommonStaffUtils.GetFilePath("elements_tree_full.png") };
					break;
				case Ais7IssoDefectsTreeNode.VisibleMode.AllContructions:
					VisibilityMode = Ais7IssoDefectsTreeNode.VisibleMode.ExistConstructions;
					ChangeDefectView.Icon = new FileImageSource { File = CommonStaffUtils.GetFilePath("elements_tree.png") };
					break;
				case Ais7IssoDefectsTreeNode.VisibleMode.All:
					break;
			}
			InitTreeDefect();
		}

		private void ImageButton_Clicked(object sender, EventArgs e)
		{
			if (!(sender is ImageButton imageButton)) return;
			if (!(imageButton.BindingContext is Ais7IssoDefectsTreeNode defectsTreeNode2) || !defectsTreeNode2.IsNotLeaf) return;
			var addIssoDefect = new AddIssoDefectContentPageList(CIsso, RootNode, defectsTreeNode2, VisibilityMode);
			Navigation.PushAsync(addIssoDefect);
		}

	    public override void Dispose()
	    {
		    RootNode = null;
		    ChangeDefectView = null;
		    Content = null;
		    BindingContext = null;
	    }
    }

	/// <summary>
	/// Класс для дефектов, нужен для получения экземпляров из БД
	/// </summary>
	/// 
	[Table("V_GR_CONSTR_DEF2")]
	[SuppressMessage("ReSharper", "InconsistentNaming")]
	public class VGrConstrDef2
	{
		/// <summary>
		/// номер группы конструкций
		/// </summary>
		public int C_GR_CONSTR { get; set; }

		/// <summary>
		/// имя группы конструкций
		/// </summary>
		public string N_GR_CONSTR { get; set; }

		/// <summary>
		/// Название группы
		/// </summary>
		public string TEXT { get; set; }

		/// <summary>
		/// Признак конструкции а не группы конструкций
		/// </summary>
		public bool IS_CONSTR { get; set; }

		/// <summary>
		/// Родительская группа в дереве
		/// </summary>
		public int PARENT_GROUP { get; set; }

		/// <summary>
		/// Родительская группа при отображении в форме
		/// </summary>
		public int PARENT_VIEW { get; set; }

		/// <summary>
		/// Основная долговечность
		/// </summary>
		public bool LIFE { get; set; }

		/// <summary>
		/// Тип элемента
		/// </summary>
		public int ITEM_TYPE { get; set; }

		/// <summary>
		/// Порядок в дереве
		/// </summary>
		public short ORD { get; set; }

		/// <summary>
		/// Родительская группа к которой относим эту группу при определении долговечности на сооружении
		/// </summary>
		public short ID4D { get; set; }
	}

    /// <summary>
    /// Вспомогательный класс для построения списка
    /// </summary>
    [SuppressMessage("ReSharper", "InconsistentNaming")]
    public class CustomDefectAlterTable
    {
	    public int c_gr_constr { get; set; }

	    public bool is_constr { get; set; }

	    public int C_DEFECT { get; set; }

	    public string N_DEFECT { get; set; }

	    public short DEF_GRP { get; set; }

	    public string W_DEF { get; set; }

	    public short ITEM_TYPE { get; set; }

	    public bool LOCK_EXPERT { get; set; }

	    public bool life { get; set; }

	    public short ord { get; set; }
    }

    /// <summary>
    /// Вспомогательный класс для получения данных из БД
    /// </summary>
    [SuppressMessage("ReSharper", "InconsistentNaming")]
    public class TableList
    {
        public string n_gr_constr { get; set; }

        public TableList() { }

        public TableList(string nGrConstr)
        {
	        n_gr_constr = nGrConstr;
        }
    }
}