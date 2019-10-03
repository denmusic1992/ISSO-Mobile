using System;
using System.Diagnostics;
using System.IO;
using System.Net.Http;
using System.Threading.Tasks;

namespace ISSO_I.IssoViewPages.ForPDFView
{
    public static class RestApiHelper
    {
        public static async Task<MemoryStream> DownloadFileAsync(string url)
        {
            try
            {
                var stream = new MemoryStream();
                using (new HttpClient())
                {
	                //var downloadStream = await httpClient.GetStreamAsync(new Uri(url));
	                var downloadStream = File.Open(url, FileMode.Open);
	                await downloadStream.CopyToAsync(stream);
                }

                return stream;
            }
            catch (Exception exception)
            {
                Debug.WriteLine(exception);
                return null;
            }
        }
    }
}
