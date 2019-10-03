namespace ISSO_I.Drivers
{
	// ReSharper disable once InconsistentNaming
	public class Ais7DataTableDriver_I_PODXOD : Ais7DataTableDriver
	{
		public Ais7DataTableDriver_I_PODXOD()
		{
			IdColumn = 1;
			ValueColumn = 1;
		}

		public override string GetSql()
		{
			return "select n_podxod, n_podxod from i_podxod";
		}
	}
}
