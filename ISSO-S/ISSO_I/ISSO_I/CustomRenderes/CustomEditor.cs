using Xamarin.Forms;

namespace ISSO_I.CustomRenderes
{
    public class CustomEditor : Editor
    {
	    public CustomEditor()
	    {
		    TextChanged += (sender, args) => { InvalidateMeasure(); };
	    }
    }
}
