using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Runtime.CompilerServices;

namespace CommonClassesLibrary.ViewModels
{
	public class BaseViewModel : INotifyPropertyChanged
	{
		private string _title = string.Empty;
		public const string TitlePropertyName = "Title";

		/// <summary>
		/// Gets or sets the "Title" property
		/// </summary>
		/// <value>The title.</value>
		public string Title
		{
			get => _title;
			set => SetProperty(ref _title, value);
		}

		private string _subtitle = string.Empty;
		/// <summary>
		/// Gets or sets the "Subtitle" property
		/// </summary>
		public const string SubtitlePropertyName = "Subtitle";
		public string Subtitle
		{
			get => _subtitle;
			set => SetProperty(ref _subtitle, value);
		}

		private string _icon;
		/// <summary>
		/// Gets or sets the "Icon" of the viewmodel
		/// </summary>
		public const string IconPropertyName = "Icon";
		public string Icon
		{
			get => _icon;
			set => SetProperty(ref _icon, value);
		}

		bool _isBusy;

		/// <summary>
		/// Gets or sets a value indicating whether this instance is busy.
		/// </summary>
		/// <value><c>true</c> if this instance is busy; otherwise, <c>false</c>.</value>
		public bool IsBusy
		{
			get => _isBusy;
			set
			{
				if (SetProperty(ref _isBusy, value))
					IsNotBusy = !_isBusy;
			}
		}

		bool _isNotBusy = true;

		/// <summary>
		/// Gets or sets a value indicating whether this instance is not busy.
		/// </summary>
		/// <value><c>true</c> if this instance is not busy; otherwise, <c>false</c>.</value>
		public bool IsNotBusy
		{
			get => _isNotBusy;
			private set => SetProperty(ref _isNotBusy, value);
		}

		private bool _canLoadMore = true;
		/// <summary>
		/// Gets or sets if we can load more.
		/// </summary>
		public const string CanLoadMorePropertyName = "CanLoadMore";
		public bool CanLoadMore
		{
			get => _canLoadMore;
			set => SetProperty(ref _canLoadMore, value);
		}

		protected bool SetProperty<T>(
			ref T backingStore, T value,
			[CallerMemberName]string propertyName = "",
			Action onChanged = null)
		{


			if (EqualityComparer<T>.Default.Equals(backingStore, value))
				return false;

			backingStore = value;

			onChanged?.Invoke();

			OnPropertyChanged(propertyName);
			return true;
		}

		#region INotifyPropertyChanged implementation
		public event PropertyChangedEventHandler PropertyChanged;
		#endregion

		public void OnPropertyChanged([CallerMemberName] string propertyName = "")
		{
			var changed = PropertyChanged;

			changed?.Invoke(this, new PropertyChangedEventArgs(propertyName));
}
	}
}
