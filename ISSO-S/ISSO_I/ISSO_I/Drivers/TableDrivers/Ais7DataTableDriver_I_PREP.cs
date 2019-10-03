namespace ISSO_I.Drivers
{
	public class Ais7DataTableDriver_I_PREP : Ais7DataTableDriver
	{
		public Ais7DataTableDriver_I_PREP()
		{
			IdColumn = 1;
			ValueColumn = 1;
		}

		public override string GetSql()
		{
			return "select n_prep, n_prep from i_prep";
		}
	}
}
