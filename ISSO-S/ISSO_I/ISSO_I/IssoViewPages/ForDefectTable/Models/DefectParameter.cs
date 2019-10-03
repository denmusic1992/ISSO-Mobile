namespace ISSO_I.IssoViewPages.ForDefectTable.Models
{
    public class DefectParameter
    {
		/// <summary>
		/// Наименование параметра
		/// </summary>
		public string Name { get; set; }

		/// <summary>
		/// Категория параметра
		/// </summary>
		public short Category { get; set; }

		/// <summary>
		/// Начальное значение диапазона (для количественных)
		/// </summary>
		public double ValueStart { get; set; }
		/// <summary>
		/// Конеченое значение диапазона (для количественных)
		/// </summary>
		public double ValueEnd { get; set; }

		/// <summary>
		/// Минимальное значение параметра
		/// </summary>
		public double MinParamValue { get; set; }

		/// <summary>
		/// Максимальное значение параметра
		/// </summary>
		public double MaxParamValue { get; set; }

		/// <summary>
		/// Параметр дефекта
		/// </summary>
		public short CDefParam { get; set; }
		/// <summary>
		/// Категория по безопасности
		/// </summary>
		public short B { get; set; }

		/// <summary>
		/// Категория по долговечности
		/// </summary>
		public short D { get; set; }

		/// <summary>
		/// Категория по ремонтопригодности
		/// </summary>
		public short R { get; set; }

		/// <summary>
		/// Влияние на грузоподьемность
		/// </summary>
		public bool G { get; set; }

	    /// <summary>
	    /// Категория по безопасности (экспертная)
	    /// </summary>
	    public short B1 { get; set; }

	    /// <summary>
	    /// Категория по долговечности (экспертная)
	    /// </summary>
	    public short D1 { get; set; }

	    /// <summary>
	    /// Категория по ремонтопригодности (экспертная)
	    /// </summary>
	    public short R1 { get; set; }

	    /// <summary>
	    /// Влияние на грузоподъемность (экспертная)
	    /// </summary>
	    public bool G1 { get; set; }

		/// <summary>
		/// Признак того, что параметр количесвтенный (иначе качественный)
		/// </summary>
		public bool IsQual { get; set; }

		/////<inheritdoc/>
		///// <summary>
		///// Собсна конструктор
		///// </summary>
		///// <param name="name"></param>
		///// <param name="b"></param>
		///// <param name="d"></param>
		///// <param name="r"></param>
		///// <param name="g"></param>
		///// <param name="valueStart"></param>
		///// <param name="valueEnd"></param>
	 //   public DefectParameter(string name, int b, int d, int r, bool g, double valueStart = 0, double valueEnd = 0)
	 //   {
		//    Name = name;
		//    ValueStart = valueStart;
		//    ValueEnd = valueEnd;
		//    B = b;
		//    D = d;
		//    R = r;
		//    G = g;
	 //   }

	    public string GetValueRange()
	    {
		    return $"От {ValueStart:F2} до {ValueEnd:F2}";
	    }

	    public string GetBdrg()
	    {
			return $"Б{B},Д{D},Р{R}{(G ? ",Г" : "")}";
	    }
    }
}
