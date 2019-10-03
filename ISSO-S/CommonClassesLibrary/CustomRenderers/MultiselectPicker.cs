using CommonClassesLibrary.Interfaces;
using Xamarin.Forms;

namespace CommonClassesLibrary.CustomRenderers
{
	public class MultiselectPicker : Picker
	{
		public MultiselectPicker()
		{
			Focused += MultiselectPicker_Focused;
		}

		private void MultiselectPicker_Focused(object sender, FocusEventArgs e)
		{
			DependencyService.Get<IMessage>().ShortAlert("Click!");
		}
	}
}
