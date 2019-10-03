using System;
using System.Threading.Tasks;
using CommonClassesLibrary.Interfaces;
using CommonClassesLibrary.PopupPages;
using Plugin.Media;
using Plugin.Media.Abstractions;
using Plugin.Permissions;
using Plugin.Permissions.Abstractions;
using Rg.Plugins.Popup.Extensions;
using Xamarin.Forms;

namespace CommonClassesLibrary
{
	public class CommonPhotoUtils
	{
		public static async Task<MediaFile> TakePhoto()
		{
			await CrossMedia.Current.Initialize();
			/** Просмотр разрешений на использование камеры */
			if (!CrossMedia.Current.IsCameraAvailable || !CrossMedia.Current.IsTakePhotoSupported)
			{
				await Application.Current.MainPage.Navigation.PushPopupAsync(
					new CommonPopupPage(new AlertPopupPage(true, "Нет камеры в телефоне."), "Ошибка"));
				//await Application.Current.MainPage.DisplayAlert(null, "Нет камеры", "OK");
				return null;
			}

			var storageStatus = await CrossPermissions.Current.CheckPermissionStatusAsync(Permission.Storage);

            if (storageStatus != PermissionStatus.Granted)
            {
                if (await CrossPermissions.Current.ShouldShowRequestPermissionRationaleAsync(Permission.Storage))
                {
	                await Application.Current.MainPage.Navigation.PushPopupAsync(
			                new CommonPopupPage(new AlertPopupPage(true, "Разрешите приложению доступ к камере."),
				                "Разрешение на камеру"));
                    //await Application.Current.MainPage.DisplayAlert("Разрешение на камеру", "Разрешите приложению доступ к камере.", "OK");
                }

                var results = await CrossPermissions.Current.RequestPermissionsAsync(Permission.Storage);
                storageStatus = results[Permission.Storage];
            }
            /** Если все доступно, то используем камеру */
            if (storageStatus == PermissionStatus.Granted)
            {
                await CrossMedia.Current.Initialize();
                /** Просмотр разрешений на использование камеры */
                if (!CrossMedia.Current.IsCameraAvailable || !CrossMedia.Current.IsTakePhotoSupported)
                {
	                await Application.Current.MainPage.Navigation.PushPopupAsync(
		                new CommonPopupPage(new AlertPopupPage(true, "Нет камеры в телефоне."), "Ошибка"));
                    //await Application.Current.MainPage.DisplayAlert(null, "Нет доступной камеры", "OK");
                    return null;
                }

                var mediaFile = await CrossMedia.Current.TakePhotoAsync(new StoreCameraMediaOptions
                {
                    Directory = "CommonPhotos",
                    DefaultCamera = CameraDevice.Rear,
                    MaxWidthHeight = 1280,
                    PhotoSize = PhotoSize.MaxWidthHeight,
                    Name = $"IMG_{DateTime.Now:yyyyMMdd_HHmmss}.jpg",
                });

                // Добавление даты в фотографию
	            if (mediaFile != null)
	            {
		            DependencyService.Get<IDateImageInterface>().AddDateToImageSpecific(mediaFile.Path);
	            }

	            return mediaFile;

                // Добавление даты в фотографию
                //DependencyService.Get<IDateImageInterface>().AddDateToImageSpecific(newPath);
                //
            }
            /** Иначе нет разрешений на камеру */

			await Application.Current.MainPage.Navigation.PushPopupAsync(new CommonPopupPage(
				new AlertPopupPage(true, "Невозможно сделать фотографию, т.к. нет разрешения на это действие"), "Нет разрешений"));
	        //await Application.Current.MainPage.DisplayAlert("Нет разрешений", "Невозможно сделать фотографию, т.к. нет разрешения на это действие", "OK");
	        return null;
		}
	}
}
