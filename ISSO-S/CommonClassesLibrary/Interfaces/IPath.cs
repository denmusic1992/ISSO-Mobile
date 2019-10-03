using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CommonClassesLibrary.Interfaces
{
	public interface IPath
	{
		string GetDatabasePath(string filename);
	}
}
