using CommonClassesLibrary;
using ISSO_I.Additional_Classes;
using ISSO_I.CustomRenderes;
using Rg.Plugins.Popup.Extensions;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Input;
using HighEnergy.Collections.Helpers;
using HighEnergy.TreeView;
using HighEnergy.TreeView.Demo.DemoModule;
using Xamarin.Forms;
using DemoTreeCardView = HighEnergy.TreeView.Demo.DemoModule.DemoTreeCardView;

namespace ISSO_I.IssoViewPages
{
    public class TreeView : HighEnergy.TreeView.TreeView, IDisposable
    {
        TreeViewModel _viewModel;

        public void InitTreeView(int cIsso)
        {
            var page = new LoadingPopupPage("Идет построение дерева...", true);
            Navigation.PushPopupAsync(page, false);
            Task.Factory.StartNew(() =>
            {
                var infoRows = DoSqlCount(cIsso);
                _viewModel = new TreeViewModel(infoRows, cIsso);
                //TreeViewModel.navigation = this.Navigation;
                NodeCreationFactory = () => new TreeNodeView
                {
                    HorizontalOptions = LayoutOptions.FillAndExpand,
                    VerticalOptions = LayoutOptions.Fill,
                };
                Device.BeginInvokeOnMainThread(() =>
                {
                    HeaderCreationFactory = () => new DemoTreeCardView
                    {
                        HorizontalOptions = LayoutOptions.FillAndExpand,
                        VerticalOptions = LayoutOptions.Start
                    };
                    BindingContext = _viewModel.Tree;
                    Navigation.PopPopupAsync();
                });
            });
        }

        public List<InfoRow> DoSqlCount(int cIsso)
        {
            try
            {
	            var connection = ConnectionClass.CreateDatabase();
                var copy = new List<InfoRow>();
                var result = connection.Query<DBHelper.TABLE_NAMES>("select SYS_NAME, DESCRIPTION, PARENT_ID, C_GR_CONSTR from TABLE_NAMES where PARENT_VIEW != -1").ToList();
                var sql = string.Join(" union ", result.ConvertAll(item => string.Format("select '{0}' as TableName, count(*) as Count from {0} where C_ISSO={1}", item.SYS_NAME, cIsso)));
                var counts = connection.CreateCommand(sql).ExecuteQuery<CountIsso>();
                var issoType = connection.Table<DBHelper.I_ISSO>().Where(isso => isso.C_ISSO == cIsso).ToList()[0].CTYPEISSO;
                var realCnct = connection.CreateCommand(
	                $"select * from S_CNCT where C_NMD1 in (select C_GR_Constr from s_typisso where C_TYPISSO={issoType})").ExecuteQuery<SCnct>();
                var i = 0;
                foreach (var name in result)
                {
                    var count = counts.Find(x => x.TableName == name.SYS_NAME).Count;
                    int? cnct = realCnct.Where(item => item.C_NMD2 == name.C_GR_CONSTR).ToList()[0].CNCT;
                    if (cnct == 1)
                    {
                        copy.Add(new InfoRow(name.SYS_NAME, name.DESCRIPTION, name.PARENT_ID, name.C_GR_CONSTR, -1));
                    }
                    else if (count > 0)
                        copy.Add(new InfoRow(name.SYS_NAME, name.DESCRIPTION, name.PARENT_ID, name.C_GR_CONSTR, count));
                    i++;
                }
                for (i = 0; i < copy.Count; i++)
                {
                    var subCopy = copy[i];
                    try
                    {
                        var res = CommonStaffUtils.TableStates.FirstOrDefault(table => table.Key.Substring(0, table.Key.LastIndexOf('.')).Equals(subCopy.SysName));

                        if (res.Key != null && res.Value == TableState.Invisible)
                        {
                            copy.Remove(subCopy);
                            i--;
                        }
                    }
                    catch (Exception ex)
                    {
                        Console.WriteLine(ex.Message);
                    }
                }
	            connection.Close();
                return copy;
            }
            catch(Exception ex)
            {
                Console.WriteLine(ex.Message);
                return new List<InfoRow>();
            }
        }

        public void Dispose()
        {
            _viewModel?.Dispose();
        }
    }

    class CountIsso
    {
        public string TableName { get; set; }
        public int Count { get; set; }
    }

    class SCnct
    {
	    public Int32 C_NMD1 { get; set; }
	    public Int32 C_NMD2 { get; set; }
	    public Int16 CNCT { get; set; }
    }

    public class TreeViewModel : ObservableObject, IDisposable
    {
	    private TreeNode _tree;
        public TreeNode Tree
        {
            get => _tree;
	        set => Set("Tree", ref _tree, value);
        }

	    private static bool? NodeTouched { get; set; } = false;

        //public static INavigation navigation { get; set; }

        public ICommand GoToTableContent { get; set; } = new Command<TreeNode>( async(treeNode) =>
         {
             if (NodeTouched ?? false) return;
             NodeTouched = true;
             var tablepage = new TableTreeView(treeNode.CIsso, treeNode.Sysname, treeNode.CGrConstr, treeNode.Description.Contains('[') ?
                 treeNode.Description.Substring(0, treeNode.Description.IndexOf('[')) : treeNode.Description);
             await ((MyNavigationPage) ((MasterDetailPage1) Application.Current.MainPage).Detail).PushAsync(tablepage);
             NodeTouched = false;
         });


        public TreeViewModel(List<InfoRow> infoRows, int cIsso)
        {
            var parentId = -1;
            foreach (var row in infoRows)
            {
	            if (row.ParentId != -100) continue;
	            Tree = new TreeNode
	            {
		            Description = row.Description,
		            IsExpanded = true,
		            CIsso = cIsso,
		            CGrConstr = row.CGrConstr,
		            Sysname = row.SysName,
		            IsNotLeaf = true,
		            GoToTableContent = GoToTableContent
	            };
	            parentId = row.CGrConstr;
	            break;
            }

            foreach (var row in infoRows)
            {
	            if (row.ParentId != parentId) continue;
	            var node = new TreeNode
	            {
		            Description =
			            $"{row.Description}{(row.CountOfColumns != -1 ? $" [{row.CountOfColumns}]" : "")}",
		            CGrConstr = row.CGrConstr,
		            CIsso = cIsso,
		            Sysname = row.SysName,
		            GoToTableContent = GoToTableContent
	            };
	            AddNodeToTree(row.CGrConstr, node, infoRows, cIsso);
	            node.IsNotLeaf = node.Children.Count > 0;
	            Tree.Children.Add(node);
            }
        }

        private void AddNodeToTree(int parentId, TreeNode parent, List<InfoRow> infoRows, int cIsso)
        {
            foreach (var row in infoRows)
            {
	            if (row.ParentId != parentId) continue;
	            var node = new TreeNode
	            {
		            Description =
			            $"{row.Description}{(row.CountOfColumns != -1 ? $" [{row.CountOfColumns}]" : "")}",
		            CGrConstr = row.CGrConstr,
		            CIsso = cIsso,
		            Sysname = row.SysName,
		            GoToTableContent = GoToTableContent
	            };
	            AddNodeToTree(row.CGrConstr, node, infoRows, cIsso);
	            node.IsNotLeaf = node.Children.Count > 0;
	            parent.Children.Add(node);
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
