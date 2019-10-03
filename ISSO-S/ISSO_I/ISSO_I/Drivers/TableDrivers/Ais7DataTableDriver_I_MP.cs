namespace ISSO_I.Drivers
{

	// ReSharper disable once InconsistentNaming
	public class Ais7DataTableDriver_I_MP : Ais7DataTableDriver
	{
		public Ais7DataTableDriver_I_MP()
		{
			IdColumn = 1;
			ValueColumn = 1;
		}

		public override string GetSql()
		{
			return "select n_ps, n_ps from i_mp";
		}
	}
}
