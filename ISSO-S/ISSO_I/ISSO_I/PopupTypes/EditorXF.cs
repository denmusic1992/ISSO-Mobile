using Xamarin.Forms;

namespace ISSO_I.PopupTypes
{
	internal class EditorXf : Editor
    {
        public EditorXf()
        {
            TextChanged += (sender, e) =>
            {
                InvalidateMeasure();
            };
        }
    }
}
