using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_S
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class Testik : ContentPage
	{
		public Testik ()
		{
			InitializeComponent ();
            var MapType = new ToolbarItem
            {
                Command = new Command((sender) => TypeEpt()),
                Text = "Тип",
                Priority = 0,
                Order = ToolbarItemOrder.Primary
            };
            ToolbarItems.Add(MapType);
        }

        protected override void OnSizeAllocated(double width, double height)
        {
            base.OnSizeAllocated(width, height);
            this.WidthRequest = width;
            this.HeightRequest = height;
            if (width > height)
            {
                Stack.Orientation = StackOrientation.Horizontal;
            }
            else
            {
                Stack.Orientation = StackOrientation.Vertical;
            }
        }

        public async void TypeEpt()
        {
            var type = await DisplayActionSheet(null, "Отмена", null, "Синий", "Зеленый", "Оба");
            switch (type)
            {
                case "Синий":
                    BlueBox.HeightRequest = Content.Height;
                    GreenBox.HeightRequest = 0;
                    break;
                case "Зеленый":
                    BlueBox.HeightRequest = 0;
                    GreenBox.HeightRequest = Content.Height;
                    break;
                case "Оба":
                    BlueBox.HeightRequest = Content.Height / 2;
                    GreenBox.HeightRequest = Content.Height / 2;
                    break;
            }
        }
	}
}