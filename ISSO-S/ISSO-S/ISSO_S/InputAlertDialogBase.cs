using Rg.Plugins.Popup.Pages;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace ISSO_S
{
    public class InputAlertDialogBase<T> : PopupPage
    {
        // the awaitable task
        public Task<T> PageClosedTask => PageClosedTaskCompletionSource.Task;

	    // the task completion source
        public TaskCompletionSource<T> PageClosedTaskCompletionSource { get; set; }

        public InputAlertDialogBase(View contentBody)
        {
            Content = contentBody;

            // init the task completion source
            PageClosedTaskCompletionSource = new TaskCompletionSource<T>();

            BackgroundColor = new Color(0, 0, 0, 0.4);
        }

        // Method for animation child in PopupPage
        // Invoced after custom animation end
        //protected override Task OnAppearingAnimationEnd()
        //{
        //    return Content.FadeTo(1);
        //}

        // Method for animation child in PopupPage
        // Invoked before custom animation begin
        //protected override Task OnDisappearingAnimationBegin()
        //{
        //    return Content.FadeTo(0);
        //}

        protected override bool OnBackButtonPressed()
        {
            // Prevent back button pressed action on android
            //return base.OnBackButtonPressed();
            return true;
        }

        // Invoced when background is clicked
        protected override bool OnBackgroundClicked()
        {
            // Prevent background clicked action
            //return base.OnBackgroundClicked();
            return false;
        }
    }
}
