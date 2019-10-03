using System;
using System.IO;
using CommonClassesLibrary.Interfaces;
using ISSO_I.Droid.PlatformSpecific;
using Xamarin.Forms;

[assembly: Dependency(typeof(AndroidDbPath))]
namespace ISSO_I.Droid.PlatformSpecific
{
	public class AndroidDbPath : IPath
	{
		public string GetDatabasePath(string filename)
		{
			var path = Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.Personal), filename);
			return path;
		}
	}
}