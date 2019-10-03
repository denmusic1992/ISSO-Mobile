namespace ISSO_I.IssoViewPages.ForDefectTable
{
    /// <summary>
    /// Описание дефекта
    /// </summary>
    public class Ais7DefectItem
    {
        /// <summary>
        /// Идентификатор дефекта
        /// </summary>
        public int CDefect { get; protected set; }

        /// <summary>
        /// Название дефекта
        /// </summary>
        public string NDefect { get; protected set; }

        public string WDef { get; protected set; }

        /// <summary>
        /// Родительский элемент
        /// </summary>
        public Ais7IssoDefectsTreeNode Parent { get; protected set; }

        /// <summary>
        /// Признак блокирования возможности установки экспертных категорий дефектов
        /// </summary>
        public bool LockExpert { get; protected set; }

        public Ais7DefectItem(Ais7IssoDefectsTreeNode parent, int id, string name, string wDef, bool lockExpert)
        {
            Parent = parent;
            CDefect = id;
            NDefect = name;
            WDef = wDef;
            LockExpert = lockExpert;
        }

        public override string ToString()
        {
            return NDefect;
        }
    }
}
