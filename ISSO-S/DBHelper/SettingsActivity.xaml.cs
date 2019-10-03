using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace CommonStaffLibrary
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public abstract partial class SettingsActivity : ContentPage
	{
        /// <summary>
        /// Экземпляр ввода адреса для доступа извне абстрактного класса
        /// </summary>
        public NoHelperEntry MyEntryAddress;
        /// <summary>
        /// Экземпляр ввода порта для доступа извне абстрактного класса
        /// </summary>
        public NoHelperEntry MyEntryPort;
        /// <summary>
        /// Экземпляр ввода порта службы поддержки для доступа извне абстрактного класса
        /// </summary>
        public NoHelperEntry MyEntrySupport;

		public SettingsActivity ()
		{
			InitializeComponent ();
            // Инициализация компонентов ввода
            MyEntryAddress = EntryAddress;
            MyEntryPort = EntryPort;
            MyEntrySupport = EntrySupport;
            InitSettings();
        }

        protected override void OnDisappearing()
        {
            base.OnDisappearing();
            SaveSettings();
        }

        public abstract void InitSettings();
        public abstract void SaveSettings();
    }

    public class NoHelperEntry : Entry
    {
        public NoHelperEntry() { }
    }
}