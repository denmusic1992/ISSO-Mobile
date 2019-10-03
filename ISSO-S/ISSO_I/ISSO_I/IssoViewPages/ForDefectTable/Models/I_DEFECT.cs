using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using System.Text;

namespace ISSO_I.IssoViewPages.ForDefectTable.Models
{
    [SuppressMessage("ReSharper", "InconsistentNaming")]
    public class defects
    {
		public int c_isso { get; set; }
		public short n_def { get; set; }
		public int c_defect { get; set; }
		public int c_gr_constr { get; set; }
		public short? n_constr { get; set; }
		public short? b { get; set; }
		public short? b1 { get; set; }
		public short? d { get; set; }
		public short? d1 { get; set; }
		public short? r { get; set; }
	    public short? r1 { get; set; }
	    public bool? g { get; set; }
	    public bool? g1 { get; set; }
		public DateTime? date { get; set; }
		public long? datef { get; set; }
    }
}
