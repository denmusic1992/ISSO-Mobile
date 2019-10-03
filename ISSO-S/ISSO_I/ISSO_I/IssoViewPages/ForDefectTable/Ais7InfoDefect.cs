namespace ISSO_I.IssoViewPages.ForDefectTable
{
    /// <summary>
    /// Класс для отображения в ListView
    /// </summary>
    internal class Ais7InfoDefect
    {
        public string Info { get; set; }
        public string BDRG { get; set; }

        public Ais7InfoDefect() { }
        public Ais7InfoDefect(string info, string bdrg)
        {
            Info = info;
            BDRG = bdrg;
        }
    }
}
