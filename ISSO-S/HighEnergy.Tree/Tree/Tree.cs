namespace HighEnergy.Collections.Tree
{
    public class Tree<T> : TreeNode<T>
        where T : new()
    {
        public Tree() { }

        public Tree(T rootValue)
        {
            Value = rootValue;
        }
    }
}