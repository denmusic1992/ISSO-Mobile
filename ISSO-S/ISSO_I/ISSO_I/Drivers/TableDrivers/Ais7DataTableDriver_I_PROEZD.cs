namespace ISSO_I.Drivers
{
	// ReSharper disable once InconsistentNaming
	public class Ais7DataTableDriver_I_PROEZD : Ais7DataTableDriver
	{
		public Ais7DataTableDriver_I_PROEZD()
		{
			IdColumn = 1;
			ValueColumn = 1;
		}

		public override string GetSql()
		{
			return "select w_proezd, n_whe from i_proezd left outer join s_whe on w_proezd=c_whe";
		}
	}
}
