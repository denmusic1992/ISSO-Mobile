namespace ISSO_I.Drivers
{
	// ReSharper disable once InconsistentNaming
	public class Ais7DataTableDriver_I_OPORA : Ais7DataTableDriver
	{
		public Ais7DataTableDriver_I_OPORA()
		{
			IdColumn = 1;
			ValueColumn = 1;
		}

		public override string GetSql()
		{
			return "select n, n from i_opora";
		}
	}
}
