using UIKit;
using Xamarin.Forms.Platform.iOS;
using Xamarin.Forms;
using ISSO_S.iOS;
using CoreGraphics;
using ISSO_S.Droid.CustomRenderers;

[assembly: ExportRenderer(typeof(MyContentPage), typeof(MyCustomPageRenderer))]
namespace ISSO_S.iOS
{
	internal class MyCustomPageRenderer : TabbedRenderer
    {
        public override void ViewWillAppear(bool animated)
        {
            base.ViewWillAppear(animated);

            if (((MyContentPage)Element).EnableBackButtonOverride)
            {
                SetCustomBackButton();
            }
        }

        private void SetCustomBackButton()
        {
            // Load the Back arrow Image
            var backBtnImage = UIImage.FromBundle("Icons/iosbackarrow.png");

            backBtnImage =
                backBtnImage.ImageWithRenderingMode(UIImageRenderingMode.AlwaysTemplate);

            // Create our Button and set Edge Insets for Title and Image
            var backBtn = new UIButton(UIButtonType.Custom)
            {
                TitleEdgeInsets = new UIEdgeInsets(11.5f, 15f, 10f, 0f),
                //ImageEdgeInsets = new UIEdgeInsets(1f, 8f, 0f, 0f)
            };

            // Set the styling for Title
            // You could set any Text as you wish here
            //backBtn.SetTitle("Back", UIControlState.Normal);
            // use the default blue color in ios back button text
            //backBtn.SetTitleColor(UIColor.Blue, UIControlState.Normal);
            //backBtn.SetTitleColor(UIColor., UIControlState.Highlighted);
            //backBtn.Font = UIFont.FromName("HelveticaNeue", (nfloat)17);

            // Set the Image to the button
            backBtn.SetImage(backBtnImage, UIControlState.Normal);

            // Allow the button to Size itself
            backBtn.SizeToFit();

            // Add the Custom Click event you would like to 
            // execute upon the Back button click
            backBtn.TouchDown += (sender, e) =>
            {
	            // Whatever your custom back button click handling

	            ((MyContentPage) Element)?.CustomBackButtonAction?.Invoke();
            };

            //Set the frame of the button
            //Set the frame of the button
            backBtn.Frame = new CGRect(
                0,
                0,
                UIScreen.MainScreen.Bounds.Width / 12,
                NavigationController.NavigationBar.Frame.Height);

            // Add our button to a container
            var btnContainer = new UIView(
                new CGRect(0, 0, backBtn.Frame.Width, backBtn.Frame.Height));
            btnContainer.AddSubview(backBtn);

            // A dummy button item to push our custom  back button to
            // the edge of screen (sort of a hack)
            var fixedSpace = new UIBarButtonItem(UIBarButtonSystemItem.FixedSpace)
            {
                Width = -16f
            };
            // wrap our custom back button with a UIBarButtonItem
            var backButtonItem = new UIBarButtonItem("", UIBarButtonItemStyle.Bordered, null)
            {
                CustomView = backBtn
            };

            // Add it to the ViewController
            NavigationController.TopViewController.NavigationItem.LeftBarButtonItems
            = new[] { fixedSpace, backButtonItem };
        }
    }
}