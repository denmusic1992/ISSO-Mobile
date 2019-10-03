using System.Collections.Generic;

namespace ISSO_I
{
    public class MyTreeNode
    {
        /// <summary>
        /// Родительский элемент
        /// </summary>
        private MyTreeNode Parent { get; set; }
        /// <summary>
        /// Список дочерних элементов
        /// </summary>
        private List<MyTreeNode> Children { get; }

	    /// <summary>
        /// Данные в строке
        /// </summary>
        public List<object> TableRow { get; set; }

        public MyTreeNode(List<object> tableRow)
        {
            Parent = null;
            Children = new List<MyTreeNode>();
            TableRow = tableRow;
        }

        public void AddChild(MyTreeNode child)
        {
            child.Parent = this;
            Children.Add(child);
        }

        public List<MyTreeNode> GetChildren() { return Children; }

        public void SetParent(MyTreeNode parent) { Parent = parent; }

        public MyTreeNode GetParent() { return Parent; }

        public int GetLevel()
        {
            var level = 0;
            var root = this;
            while (root.GetParent() != null)
            {
                root = root.GetParent();
                level++;
            }
            return level;
        }
    }
}
