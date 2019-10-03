using System.IO;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_S
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class FullScreenImage
    {

        public FullScreenImage (string filePath, int cIsso)
		{
            Title = $"ИССО №{cIsso}. Просмотр фотографии";
            InitializeComponent ();
            var pathToFile = Path.Combine(App.PathToPhoto, filePath);
            fullScreenImage.Source = new FileImageSource() { File = pathToFile };
		}
    }
}