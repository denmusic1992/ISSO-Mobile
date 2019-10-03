namespace ISSO_I.Drivers
{
	public class Ais7DataTableDriver_I_PS : Ais7DataTableDriver
	{
		public Ais7DataTableDriver_I_PS()
		{
			IdColumn = 1;
			ValueColumn = 1;
		}

		public override string GetSql()
		{
			return "select n_ps, n_ps from i_ps";
		}
	}
}
