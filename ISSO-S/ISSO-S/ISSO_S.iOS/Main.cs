using System;
using System.Collections.Generic;
using System.Linq;
using Autoselect;
using Foundation;
using UIKit;
using Xamarin.Forms.Platform.iOS;
using Xamarin.Forms;
using ISSO_S;
using ISSO_S.iOS;
using MapKit;
using CoreGraphics;
using CommonClassesLibrary;

namespace ISSO_S.iOS
{
	public class Application
	{
		// This is the main entry point of the application.
		static void Main(string[] args)
		{
            // if you want to use a different Application Delegate class from "AppDelegate"
            // you can specify it here.
            UIApplication.Main(args, null, "AppDelegate");
        }
    }
}
