namespace ISSO_I.IssoViewPages.ForPhotos
{
    public class DataInformation
    {
        public string Info { get; set; }
        public long Date { get; set; }
        public byte[] Array { get; set; }
        public string ImageSource { get; set; }
        public int N { get; set; }

        public DataInformation() { }

        public DataInformation(string info, long date, byte[] array, string imageSource)
        {
            Info = info;
            Date = date;
            Array = array;
            ImageSource = imageSource;
        }

        public DataInformation(string info, long date, byte[] array, string imageSource, int n)
        {
            Info = info;
            Date = date;
            Array = array;
            ImageSource = imageSource;
            N = n;
        }
    }
}
