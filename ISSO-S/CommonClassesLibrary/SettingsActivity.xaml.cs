using CommonClassesLibrary.CustomRenderers;
using System;
using Xamarin.Forms.Xaml;

namespace CommonClassesLibrary
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public abstract partial class SettingsActivity
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

		protected SettingsActivity ()
		{
			InitializeComponent ();
            // Инициализация компонентов ввода
            MyEntryAddress = EntryAddress;
            MyEntryPort = EntryPort;
            MyEntrySupport = EntrySupport;
			// ReSharper disable once VirtualMemberCallInConstructor
            InitSettings();
        }

        protected override void OnDisappearing()
        {
            base.OnDisappearing();
            SaveSettings();
        }

        public override void Dispose()
        {
            MyEntryAddress = null; MyEntryPort = null; MyEntrySupport = null;
            BindingContext = null;
            Content = null;
            GC.Collect();


            //Navigation.RemovePage(Navigation.NavigationStack.LastOrDefault());
            Navigation.PopAsync();
        }

        public abstract void InitSettings();
        public abstract void SaveSettings();
    }
}