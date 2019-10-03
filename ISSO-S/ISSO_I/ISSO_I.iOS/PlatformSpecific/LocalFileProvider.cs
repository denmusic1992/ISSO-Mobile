using System;
using System.IO;
using System.Threading.Tasks;
using CommonClassesLibrary.Interfaces;
using ISSO_I.iOS.PlatformSpecific;
using Xamarin.Forms;

[assembly: Dependency(typeof(LocalFileProvider))]
namespace ISSO_I.iOS.PlatformSpecific
{
    public class LocalFileProvider : ILocalFileProvider
    {
        private readonly string _rootDir = Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.Personal), "pdfjs");

        public async Task<string> SaveFileToDisk(Stream stream, string fileName)
        {
            if (!Directory.Exists(_rootDir))
                Directory.CreateDirectory(_rootDir);

            var filePath = Path.Combine(_rootDir, fileName);

            using (var memoryStream = new MemoryStream())
            {
                await stream.CopyToAsync(memoryStream);
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