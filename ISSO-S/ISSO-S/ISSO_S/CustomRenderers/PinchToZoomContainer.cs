using System;
using CommonClassesLibrary;
using ISSO_S.iOS.Extensions;
using Xamarin.Forms;

namespace ISSO_S.iOS.CustomRenderers
{
	internal class PinchToZoomContainer : ContentView
    {
	    private double _currentScale = 1;
	    private double _startScale = 1;
	    private double _xOffset;
	    private double _yOffset;
	    private bool _initialLoad;

        public double ScreenWidth { get; private set; }
        public double ScreenHeight { get; private set; }

        public PinchToZoomContainer()
        {
            var panGesture = new PanGestureRecognizer();
            panGesture.PanUpdated += OnPanUpdated;
            GestureRecognizers.Add(panGesture);

            var pinchGesture = new PinchGestureRecognizer();
            pinchGesture.PinchUpdated += OnPinchUpdated;
            GestureRecognizers.Add(pinchGesture);
        }

	    private void OnPanUpdated(object sender, PanUpdatedEventArgs e)
        {
            var s = (ContentView)sender;

            // do not allow pan if the image is in its intial size
            if (Math.Abs(_currentScale - 1) < CommonStaffUtils.DoubleTolerance)
                return;

            switch (e.StatusType)
            {
                case GestureStatus.Started:
                    _xOffset = s.Content.TranslationX;
                    _yOffset = s.Content.TranslationY;
                    break;
                case GestureStatus.Running:
                    Content.TranslationX = _xOffset + e.TotalX;
                    Content.TranslationY = _yOffset + e.TotalY;
                    break;

                //case GestureStatus.Completed:
                //    // Store the translation applied during the pan
                //    xOffset = s.Content.TranslationX;
                //    yOffset = s.Content.TranslationY;
                //    break;
	            case GestureStatus.Completed:
		            break;
	            case GestureStatus.Canceled:
		            break;
	            default:
		            throw new ArgumentOutOfRangeException();
            }
        }

        protected override void OnSizeAllocated(double width, double height)
        {
            base.OnSizeAllocated(width, height); //must be called

	        if (!(Math.Abs(width - -1) > CommonStaffUtils.DoubleTolerance) ||
	            !(Math.Abs(ScreenWidth - width) > CommonStaffUtils.DoubleTolerance) &&
	            !(Math.Abs(ScreenHeight - height) > CommonStaffUtils.DoubleTolerance)) return;
	        ResetLayout();

	        ScreenWidth = width;
	        ScreenHeight = height;

	        _xOffset = TranslationX;
	        _yOffset = TranslationY;

	        _currentScale = Scale;

	        if (_initialLoad)
		        _initialLoad = false;
        }

	    private void ResetLayout()
        {
            TranslationY = 0;
            TranslationX = 0;
            Scale = 1;
            AnchorX = 0.5;
            AnchorY = 0.5;
            _currentScale = 1;
            _xOffset = 0;
            _yOffset = 0;
        }

	    private void OnPinchUpdated(object sender, PinchGestureUpdatedEventArgs e)
        {
            switch (e.Status)
            {
	            case GestureStatus.Started:
		            // Store the current scale factor applied to the wrapped user interface element,
		            // and zero the components for the center point of the translate transform.
		            _startScale = Content.Scale;
		            Content.AnchorX = 0;
		            Content.AnchorY = 0;
		            break;
	            case GestureStatus.Running:
		            // Calculate the scale factor to be applied.
		            _currentScale += (e.Scale - 1) * _startScale;
		            _currentScale = Math.Max(1, _currentScale);

		            // The ScaleOrigin is in relative coordinates to the wrapped user interface element,
		            // so get the X pixel coordinate.
		            var renderedX = Content.X + _xOffset;
		            var deltaX = renderedX / Width;
		            var deltaWidth = Width / (Content.Width * _startScale);
		            var originX = (e.ScaleOrigin.X - deltaX) * deltaWidth;

		            // The ScaleOrigin is in relative coordinates to the wrapped user interface element,
		            // so get the Y pixel coordinate.
		            var renderedY = Content.Y + _yOffset;
		            var deltaY = renderedY / Height;
		            var deltaHeight = Height / (Content.Height * _startScale);
		            var originY = (e.ScaleOrigin.Y - deltaY) * deltaHeight;

		            // Calculate the transformed element pixel coordinates.
		            var targetX = _xOffset - (originX * Content.Width) * (_currentScale - _startScale);
		            var targetY = _yOffset - (originY * Content.Height) * (_currentScale - _startScale);

		            // Apply translation based on the change in origin.
		            Content.TranslationX = targetX.Clamp(-Content.Width * (_currentScale - 1), 0);
		            Content.TranslationY = targetY.Clamp(-Content.Height * (_currentScale - 1), 0);

		            // Apply scale factor
		            Content.Scale = _currentScale;
		            AnchorX = e.ScaleOrigin.X;
		            AnchorY = e.ScaleOrigin.Y;
		            break;
	            case GestureStatus.Completed:
		            // Store the translation delta's of the wrapped user interface element.
		            _xOffset = Content.TranslationX;
		            _yOffset = Content.TranslationY;
		            break;
            }
        }
    }
}
