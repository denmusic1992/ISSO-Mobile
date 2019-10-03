using System.IO;
using Android.Graphics;
using CommonClassesLibrary.Interfaces;
using ISSO_I.Droid.PlatformSpecific;

[assembly: Xamarin.Forms.Dependency(typeof(MediaServiceAndroid))]
namespace ISSO_I.Droid.PlatformSpecific
{
	public class MediaServiceAndroid : IMediaService
	{
		public byte[] ResizeImage(byte[] imageData, float width, float height)
		{
			// Load the bitmap 
			var options =
				new BitmapFactory.Options
				{
					InPurgeable = true
				}; // Create object of bitmapfactory's option method for further option use
			// inPurgeable is used to free up memory while required
			var originalImage = BitmapFactory.DecodeByteArray(imageData, 0, imageData.Length, options);

			float newHeight;
			float newWidth;

			var originalHeight = originalImage.Height;
			var originalWidth = originalImage.Width;

			if (originalHeight > originalWidth)
			{
				newHeight = height;
				var ratio = originalHeight / height;
				newWidth = originalWidth / ratio;
			}
			else 
			{
				newWidth = width;
				var ratio = originalWidth / width;
				newHeight = originalHeight / ratio;
			}

			var resizedImage = Bitmap.CreateScaledBitmap(originalImage, (int)newWidth, (int)newHeight, true);

			originalImage.Recycle();

			using (var ms = new MemoryStream())
			{
				resizedImage.Compress(Bitmap.CompressFormat.Png, 100, ms);

				resizedImage.Recycle();

				return ms.ToArray();
			}

		}
	}
}