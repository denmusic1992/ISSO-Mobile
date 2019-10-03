namespace ISSO_I.IssoViewPages
{
    public class PdfDocEntity
    {
        public string FileName { get; set; }
        public string Url { get; set; }

        public PdfDocEntity() { }

        public PdfDocEntity(string fileName, string url)
        {
            FileName = fileName;
            Url = url;
        }
    }
}
