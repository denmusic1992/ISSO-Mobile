using System;
using System.Collections.Generic;
using HighEnergy.TreeView;

namespace ISSO_I.IssoViewPages.ForDefectTable
{
	internal class DefectTreeNode
    {
	    private InfoRow Data { get; }
	    private DefectTreeNode Parent { get; set; }
        public List<DefectTreeNode> Children { get; set; }
        public List<string> Path { get; set; } = new List<string>();
	    private DefectTreeNode Result { get; set; }

        public DefectTreeNode(InfoRow data)
        {
            Data = data;
            Children = new List<DefectTreeNode>();
        }

        public DefectTreeNode AddChild(InfoRow child)
        {
            var childNode = new DefectTreeNode(child) { Parent = this };
            Children.Add(childNode);
            return childNode;
        }

        public DefectTreeNode GetParent(DefectTreeNode child)
        {
	        return child?.Parent;
        }

        public string GetConstrFullName(DefectTreeNode def, int cGrConstr, int nConstr)
        {
            Result = null;
            var fullName = SearchFullPath(def, cGrConstr);
            var mainName = SearchMainPath(Result, cGrConstr, nConstr);
            if (fullName.IndexOf(mainName.Substring(0, mainName.Length - 3), StringComparison.Ordinal) == 0)
            {
                if (!fullName.Equals(mainName))
                {
                    if (fullName.Length > mainName.Length)
                    {
                        if (mainName.Substring(mainName.Length - 2, 1).Equals("№"))
                        {
                            return fullName.Substring(mainName.Length - 1);
                        }
                        else
                        {
                            return fullName.Substring(mainName.Length + 1);
                        }
                    }
                    else return "";
                }
                else return "";
            }
            //return !fullName.Equals(mainName) ?
            //    (fullName.Length > mainName.Length ? (mainName.Substring(mainName.Length - 2, mainName.Length - 1).Equals("№") 
            //    ? fullName.Substring(mainName.Length - 1) : fullName.Substring(mainName.Length + 1)) : "") : "";}
            else return fullName;
        }

        public string GetConstrMainName(DefectTreeNode def, int cGrConstr, int nConstr)
        {
            Result = null;
            SearchFullPath(def, cGrConstr);
            return SearchMainPath(Result, cGrConstr, nConstr);
        }

        public string SearchFullPath(DefectTreeNode root, int cGrConstr)
        {
            if (GetParent(root) == null)
            {
                //path.add(root.data.DESCRIPTION);
                Result = root;
            }

            if (root.Data.CGrConstr == cGrConstr)
            {
                Result = root;
                return string.Join(". ", Path.ToArray());
            }

	        if (root.Children.Count == 0) return "";
	        foreach (var child in root.Children)
	        {
		        if (child.Data.CountOfColumns == 2 || child.Data.CountOfColumns == 3)
		        {
			        var def = GetParent(child);
			        if (def != null && GetParent(def) != null)
			        {
				        Path.Add(child.Data.Description);
			        }
			        var addPath = SearchFullPath(child, cGrConstr);
			        if (!addPath.Equals(""))
				        return addPath;
			        else if(Path.Count > 0)
			        {
				        if (def != null && GetParent(def) != null)
				        {
					        Path.RemoveAt(Path.Count - 1);
				        }
			        }
		        }
		        else
		        {
			        var addPath = SearchFullPath(child, cGrConstr);
			        if (!addPath.Equals(""))
				        return addPath;
		        }
	        }
	        return "";
        }

        public string SearchMainPath(DefectTreeNode root, int cGrConstr, int nConstr)
        {
	        if ((GetParent(GetParent(root)) != null && root.Data.CountOfColumns != 100))
		        return GetParent(root).Data.CGrConstr > 100000
			        ? SearchMainPath(GetParent(root), cGrConstr, nConstr)
			        : SearchMainPathLower100000(GetParent(root), cGrConstr, nConstr);
	        if (nConstr > 0) return root.Data.Description + " №" + nConstr;
	        return root.Data.Description;

        }

        public string SearchMainPathLower100000(DefectTreeNode root, int cGrConstr, int nConstr)
        {
	        return nConstr == -1 ? root.Data.Description : SearchMainPath(root, cGrConstr, nConstr);
        }
    }
}
