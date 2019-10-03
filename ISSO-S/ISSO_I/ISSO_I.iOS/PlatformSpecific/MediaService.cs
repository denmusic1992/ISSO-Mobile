using System;
using System.Drawing;
using CommonClassesLibrary.Interfaces;
using ISSO_I.iOS.PlatformSpecific;
using UIKit;

[assembly:  Xamarin.Forms.Dependency(typeof(MediaServiceiOs))]
namespace ISSO_I.iOS.PlatformSpecific
{
    public class MediaServiceiOs : IMediaService
    {
	    public byte[] ResizeImage(byte[] imageData, float width, float height)
	    {
		    var originalImage = new UIImage(Foundation.NSData.FromArray(imageData));

			
		    var originalHeight = originalImage.Size.Height;
		    var originalWidth = originalImage.Size.Width;

		    nfloat newHeight;
		    nfloat newWidth;

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

		    width = (float)newWidth;
		    height = (float)newHeight;

		    UIGraphics.BeginImageContext(new SizeF(width, height));
		    originalImage.Draw(new RectangleF(0, 0, width, height));
		    var resizedImage = UIGraphics.GetImageFromCurrentImageContext();
		    UIGraphics.EndImageContext();

		    var bytesImagen = resizedImage.AsJPEG().ToArray();
		    resizedImage.Dispose();
		    return bytesImagen;
	    }
    }
}