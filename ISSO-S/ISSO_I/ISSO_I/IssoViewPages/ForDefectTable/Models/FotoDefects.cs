using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using System.Text;

namespace ISSO_I.IssoViewPages.ForDefectTable.Models
{
    [SuppressMessage("ReSharper", "InconsistentNaming")]
    public class FotoDefects
    {
		public int c_isso { get; set; }
		public short n_def { get; set; }
		public DateTime? date { get; set; }
		public byte[] foto { get; set; } 
    }
}
