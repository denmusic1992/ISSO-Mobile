namespace ISSO_I.IssoViewPages.ForDefectTable
{
    /// <summary>
    /// Элемент конструкции
    /// </summary>
    public class Ais7ConstrItem
    {
        /// <summary>
        /// Идентификатор конструкции
        /// </summary>
        public short ItemId { get; protected set; }
        /// <summary>
        /// Наименование конструкции
        /// </summary>
        public string ItemName { get; protected set; }

        /// <summary>
        /// Прризнак того, что нужно клонировать дефект для этого конструктива
        /// </summary>
        public bool NeedCloneDefects { get; protected set; }

        /// <summary>
        /// Признак того, что это реальная конструкция
        /// </summary>
        public bool RealConstruction => !NeedCloneDefects;


	    public override string ToString()
        {
            return ItemName;
        }

        public static Ais7ConstrItem Create(string constrName, short itemId)
        {
	        var item = new Ais7ConstrItem
	        {
		        ItemId = itemId,
		        ItemName = $"{constrName} №{itemId}",
		        NeedCloneDefects = false
	        };
	        // а этот не клонируется
	        return item;
        }
    }
}
