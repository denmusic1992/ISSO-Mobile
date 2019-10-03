using System;

namespace ISSO_I.IssoViewPages.ForDefectTable
{
    /// <summary>
    /// Подробная ниформация о дефекте
    /// </summary>
    public class Ais7AdvancedDefect
    {
        public string L_DEF { get; set; }
        public string W_DEF { get; set; }
        public string N_REM { get; set; }
        public string REM_DIMENSION { get; set; }
        public string REM_SIZE { get; set; }
        public long Date { get; set; }
        public string DateStr { get; set; }
        public string BDRG { get; set; }
        public string Param { get; set; }
        public string DefParam { get; set; }
        public bool PhotoDef { get; set; }

        public Ais7AdvancedDefect() { }

        public Ais7AdvancedDefect(string L_DEF, string W_DEF, string N_REM, string REM_DIMENSION, string REM_SIZE, long Date,
            string DateStr, string BDRG, string Param, string DefParam, bool PhotoDef)
        {
            this.L_DEF = L_DEF;
            this.W_DEF = W_DEF;
            this.N_REM = N_REM;
            this.REM_DIMENSION = REM_DIMENSION;
            this.REM_SIZE = REM_SIZE;
            this.Date = Date;
            this.DateStr = DateStr;
            this.BDRG = BDRG;
            this.Param = Param;
            this.DefParam = DefParam;
            this.PhotoDef = PhotoDef;
        }
    }
}
