using System.IO;
using System.Threading.Tasks;

namespace CommonClassesLibrary.Interfaces
{
    public interface ILocalFileProvider
    {
        Task<string> SaveFileToDisk(Stream stream, string fileName);

        string GetRootDir();

        void DeleteFilesFromDir(string pathToDir);
    }
}
