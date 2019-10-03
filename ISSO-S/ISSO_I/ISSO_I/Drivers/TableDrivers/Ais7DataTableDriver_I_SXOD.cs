namespace ISSO_I.Drivers
{
	// ReSharper disable once InconsistentNaming
	public class Ais7DataTableDriver_I_SXOD : Ais7DataTableDriver
	{
		public Ais7DataTableDriver_I_SXOD()
		{
			IdColumn = 1;
			ValueColumn = 1;
		}

		public override string GetSql()
		{
			return "select n_sxod, n_sxod from i_sxod";
		}
	}
}
