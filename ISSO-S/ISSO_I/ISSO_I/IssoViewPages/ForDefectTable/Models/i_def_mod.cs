using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using System.Text;

namespace ISSO_I.IssoViewPages.ForDefectTable.Models
{
    [SuppressMessage("ReSharper", "InconsistentNaming")]
    public class defMods
    {
		public int c_isso { get; set; }
		public short n_def { get; set; }
		public DateTime date { get; set; }
		public string l_def { get; set; }
		public string w_def { get; set; }
		public int? c_rem { get; set; }
		public double? v_rem { get; set; }
    }
}
