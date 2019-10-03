using System.IO;
using System.Threading.Tasks;
using CommonClassesLibrary.Interfaces;
using ISSO_I.Droid.PlatformSpecific;
using Xamarin.Forms;

[assembly: Dependency(typeof(LocalFileProvider))]
namespace ISSO_I.Droid.PlatformSpecific
{
    public class LocalFileProvider : ILocalFileProvider
    {
        private readonly string _rootDir = Path.Combine(Android.OS.Environment.ExternalStorageDirectory.Path, "pdfjs");

        public async Task<string> SaveFileToDisk(Stream pdfStream, string fileName)
        {
            if (!Directory.Exists(_rootDir))
                Directory.CreateDirectory(_rootDir);

            var filePath = Path.Combine(_rootDir, fileName);

            using (var memoryStream = new MemoryStream())
            {
                await pdfStream.CopyToAsync(memoryStream);
                File.WriteAllBytes(filePath, memoryStream.ToArray());
            }

            return filePath;
        }

        public string GetRootDir() { return _rootDir; }

        public void DeleteFilesFromDir(string pathToDir)
        {
            var list = Directory.GetFiles(pathToDir, "*");
	        if (list.Length <= 0) return;
	        foreach (var l in list)
	        {
		        File.Delete(l);
	        }
        }
    }
}