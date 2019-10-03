using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Foundation;
using UIKit;
using Autoselect;
using Xamarin.Forms.Platform.iOS;
using Xamarin.Forms;
using MapKit;
using CoreGraphics;
using ISSO_S.iOS;

//[assembly: ExportRenderer(typeof(CustomMap), typeof(MyCustomMap))]
namespace ISSO_S.iOS
{
    //public class MyCustomMap : MapRenderer
    //{
    //    UIView customPinView;
    //    List<CustomPin> customPins;

    //    protected override void OnElementChanged(ElementChangedEventArgs<View> e)
    //    {
    //        base.OnElementChanged(e);

    //        if (e.OldElement != null)
    //        {
    //            var nativeMap = Control as MKMapView;
    //            if (nativeMap != null)
    //            {
    //                nativeMap.RemoveAnnotations(nativeMap.Annotations);
    //                nativeMap.GetViewForAnnotation = null;
    //                nativeMap.CalloutAccessoryControlTapped -= OnCalloutAccessoryControlTapped;
    //                nativeMap.DidSelectAnnotationView -= OnDidSelectAnnotationView;
    //                nativeMap.DidDeselectAnnotationView -= OnDidDeselectAnnotationView;
    //            }
    //        }

    //        if (e.NewElement != null)
    //        {
    //            //var formsMap = (CustomMap)e.NewElement;
    //            var nativeMap = Control as MKMapView;
    //            //customPins = formsMap.CustomPins;

    //            nativeMap.GetViewForAnnotation = GetViewForAnnotation;
    //            nativeMap.CalloutAccessoryControlTapped += OnCalloutAccessoryControlTapped;
    //            nativeMap.DidSelectAnnotationView += OnDidSelectAnnotationView;
    //            nativeMap.DidDeselectAnnotationView += OnDidDeselectAnnotationView;
    //            nativeMap.ShowsUserLocation = true;
    //            nativeMap.SetUserTrackingMode(MKUserTrackingMode.Follow, true);
    //        }
    //    }

    //    MKAnnotationView GetViewForAnnotation(MKMapView mapView, IMKAnnotation annotation)
    //    {
    //        MKAnnotationView annotationView = null;

    //        if (annotation is MKUserLocation)
    //            return null;

    //        var customPin = GetCustomPin(annotation as MKPointAnnotation);
    //        if (customPin == null)
    //        {
    //            throw new Exception("Custom pin not found");
    //        }

    //        annotationView = mapView.DequeueReusableAnnotation(customPin.Id);
    //        if (annotationView == null)
    //        {
    //            annotationView = new CustomMKAnnotationView(annotation, customPin.Id);
    //            switch (customPin.position)
    //            {
    //                case IssoPosition.Ahead:
    //                    annotationView.Image = UIImage.FromFile("Icons/marker_ahead_light.png");
    //                    break;
    //                case IssoPosition.Far:
    //                    annotationView.Image = UIImage.FromFile("Icons/marker_after_light.png");
    //                    break;
    //                case IssoPosition.Behind:
    //                    annotationView.Image = UIImage.FromFile("Icons/marker_begind_light.png");
    //                    break;
    //            }
    //            annotationView.CalloutOffset = new CGPoint(0, 0);
    //            //annotationView.LeftCalloutAccessoryView = new UIImageView(UIImage.FromFile("monkey.png"));
    //            annotationView.RightCalloutAccessoryView = UIButton.FromType(UIButtonType.DetailDisclosure);
    //            ((CustomMKAnnotationView)annotationView).Id = customPin.Id;
    //            ((CustomMKAnnotationView)annotationView).Url = customPin.Url;
    //        }
    //        annotationView.CanShowCallout = true;

    //        return annotationView;
    //    }

    //    void OnCalloutAccessoryControlTapped(object sender, MKMapViewAccessoryTappedEventArgs e)
    //    {
    //        var customView = e.View as CustomMKAnnotationView;
    //        if (!string.IsNullOrWhiteSpace(customView.Url))
    //        {
    //            UIApplication.SharedApplication.OpenUrl(new Foundation.NSUrl(customView.Url));
    //        }
    //    }

    //    void OnDidSelectAnnotationView(object sender, MKAnnotationViewEventArgs e)
    //    {
    //        var customView = e.View as CustomMKAnnotationView;
    //        customPinView = new UIView();

    //        if (customView.Id == "Xamarin")
    //        {
    //            customPinView.Frame = new CGRect(0, 0, 200, 84);
    //            var image = new UIImageView(new CGRect(0, 0, 200, 84));
    //            image.Image = UIImage.FromFile("xamarin.png");
    //            customPinView.AddSubview(image);
    //            customPinView.Center = new CGPoint(0, -(e.View.Frame.Height + 75));
    //            e.View.AddSubview(customPinView);
    //        }
    //    }

    //    void OnDidDeselectAnnotationView(object sender, MKAnnotationViewEventArgs e)
    //    {
    //        if (!e.View.Selected)
    //        {
    //            customPinView.RemoveFromSuperview();
    //            customPinView.Dispose();
    //            customPinView = null;
    //        }
    //    }

    //    CustomPin GetCustomPin(MKPointAnnotation annotation)
    //    {
    //        var position = new Position(annotation.Coordinate.Latitude, annotation.Coordinate.Longitude);
    //        foreach (var pin in customPins)
    //        {
    //            if (pin.Position == position)
    //            {
    //                return pin;
    //            }
    //        }
    //        return null;
    //    }
    //}

    //public class CustomMKAnnotationView : MKAnnotationView
    //{
    //    public string Id { get; set; }

    //    public string Url { get; set; }

    //    public CustomMKAnnotationView(IMKAnnotation annotation, string id)
    //        : base(annotation, id)
    //    {
    //    }
    //}
}