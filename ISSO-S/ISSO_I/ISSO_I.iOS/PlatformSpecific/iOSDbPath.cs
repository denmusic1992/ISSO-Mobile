using System;
using System.IO;
using CommonClassesLibrary.Interfaces;
using Foundation;
using ISSO_I.Interfaces;
using ISSO_I.iOS.PlatformSpecific;
using Xamarin.Forms;

[assembly: Dependency(typeof(IosDbPath))]
namespace ISSO_I.iOS.PlatformSpecific
{
    public class IosDbPath : IPath
    {
	    public string GetDatabasePath(string filename)
	    {
		    return Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.MyDocuments), "..", "Library", filename);
	    }
    }
}