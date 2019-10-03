using CommonClassesLibrary;
using System;
using Xamarin.Forms;

namespace ISSO_I.MasterPage
{
    public class MainSettingsActivity : SettingsActivity
    {
	    public override void InitSettings()
        {
            if (Application.Current.Properties.TryGetValue("address", out var address))
            {
                MyEntryAddress.Text = Convert.ToString(address);
            }
            if (Application.Current.Properties.TryGetValue("port", out var port))
            {
                MyEntryPort.Text = Convert.ToString(port);
            }
            if (Application.Current.Properties.TryGetValue("support_port", out var support))
            {
                MyEntrySupport.Text = Convert.ToString(support);
            }
        }

        public override async void SaveSettings()
        {
            if (!MyEntryAddress.Text.Equals(""))
                Application.Current.Properties["address"] = MyEntryAddress.Text;
            if (!MyEntryPort.Text.Equals(""))
                Application.Current.Properties["port"] = Convert.ToInt32(MyEntryPort.Text);
            if (!MyEntrySupport.Text.Equals(""))
                Application.Current.Properties["support_port"] = Convert.ToInt32(MyEntrySupport.Text);
            await Application.Current.SavePropertiesAsync();
        }

        public override void Dispose()
        {
            MyEntryAddress = null; MyEntryPort = null; MyEntrySupport = null;
            BindingContext = null;
            Content = null;
            GC.Collect();


            //Navigation.RemovePage(Navigation.NavigationStack.LastOrDefault());
            //Navigation.PopAsync();
        }
    }
}
