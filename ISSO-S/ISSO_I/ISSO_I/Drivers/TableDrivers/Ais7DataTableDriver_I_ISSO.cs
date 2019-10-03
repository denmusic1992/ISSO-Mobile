namespace ISSO_I.Drivers
{
	// ReSharper disable once InconsistentNaming
	public class Ais7DataTableDriver_I_ISSO : Ais7DataTableDriver
	{
		public Ais7DataTableDriver_I_ISSO()
		{
			IdColumn = 0;
			ValueColumn = 0;
		}

		public override string GetSql()
		{
			return "select c_isso, c_isso from i_isso";
		}
	}
}
