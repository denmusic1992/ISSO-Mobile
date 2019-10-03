using System;
using System.Collections.Generic;
using System.Text;
using Xamarin.Forms;
using Xamarin.Forms.Internals;

namespace ISSO_S
{
    class MyZoomImage : Image
    {
        /// <summary>
        /// координаты нажатия на экран
        /// </summary>
        private double _currentScale = 1, _startScale = 1, _xOffset, _yOffset;
        private readonly int MIN_SCALE = 1, MAX_SCALE = 4;
        //private readonly double OVERSHOOT = 0.15;

        public MyZoomImage()
        {
            // обработчик нажатия на изображение
            var tap = new TapGestureRecognizer { NumberOfTapsRequired = 2 };
            tap.Tapped += OnTapped;
            GestureRecognizers.Add(tap);

            // обработчик перемещения изображения
            var panGesture = new PanGestureRecognizer();
            panGesture.PanUpdated += OnPanUpdated;
            GestureRecognizers.Add(panGesture);

            // обработчик масштабирования изображения
            var pinchGesture = new PinchGestureRecognizer();
            pinchGesture.PinchUpdated += OnPinchUpdated;
            GestureRecognizers.Add(pinchGesture);
        }

        private void OnTapped(object sender, EventArgs e)
        {
            if (Scale > MIN_SCALE)
            {
                this.ScaleTo(MIN_SCALE, 250, Easing.CubicInOut);
                this.TranslateTo(0, 0, 250, Easing.CubicInOut);
            }
            else
            {
                AnchorX = AnchorY = 0.5; //TODO tapped position
                this.ScaleTo(MAX_SCALE, 250, Easing.CubicInOut);
            }
        }

        private void OnPanUpdated(object sender, PanUpdatedEventArgs e)
        {
            if (Scale > MIN_SCALE)
                switch (e.StatusType)
                {
                    case GestureStatus.Running:
                        TranslationX = _xOffset + e.TotalX;
                        TranslationY = _yOffset + e.TotalY;
                        break;

                    case GestureStatus.Completed:
                        _xOffset = TranslationX;
                        _yOffset = TranslationY;
                        break;
                }
        }

        private void OnPinchUpdated(object sender, PinchGestureUpdatedEventArgs e)
        {
            switch (e.Status)
            {
                case GestureStatus.Started:
                    _startScale = Scale;
                    AnchorX = 0;
                    AnchorY = 0;

                    break;

                case GestureStatus.Running:
                    _currentScale += (e.Scale - 1) * _startScale;
                    _currentScale = Math.Max(1, _currentScale);

                    var renderedX = X + _xOffset;
                    var deltaX = renderedX / Width;
                    var deltaWidth = Width / (Width * _startScale);
                    var originX = (e.ScaleOrigin.X - deltaX) * deltaWidth;

                    var renderedY = Y + _yOffset;
                    var deltaY = renderedY / Height;
                    var deltaHeight = Height / (Height * _startScale);
                    var originY = (e.ScaleOrigin.Y - deltaY) * deltaHeight;

                    var targetX = _xOffset - (originX * Width) * (_currentScale - _startScale);
                    var targetY = _yOffset - (originY * Height) * (_currentScale - _startScale);

                    TranslationX = targetX.Clamp(-Width * (_currentScale - 1), 0);
                    TranslationY = targetY.Clamp(-Height * (_currentScale - 1), 0);

                    Scale = _currentScale;

                    break;

                case GestureStatus.Completed:
                    _xOffset = TranslationX;
                    _yOffset = TranslationY;

                    break;
            }
        }

        //private void PinchGesture_PinchUpdated(object sender, PinchGestureUpdatedEventArgs e)
        //{
        //    if (e.Status == GestureStatus.Started)
        //    {
        //        startScale = Content.Scale;
        //        Content.AnchorX = 0;
        //        Content.AnchorY = 0;
        //    }
        //    if (e.Status == GestureStatus.Running)
        //    {

        //        switch (e.Status)
        //        {
        //            case GestureStatus.Started:
        //                startScale = Content.Scale;
        //                Content.AnchorX = e.ScaleOrigin.X;
        //                Content.AnchorY = e.ScaleOrigin.Y;
        //                break;
        //            case GestureStatus.Running:
        //                double current = Scale + (e.Scale - 1) * startScale;
        //                Scale = Clamp(current, MIN_SCALE * (1 - OVERSHOOT), MAX_SCALE * (1 - OVERSHOOT));
        //                break;
        //            case GestureStatus.Completed:
        //                if (Scale > MAX_SCALE)
        //                    this.ScaleTo(MAX_SCALE, 250, Easing.SpringOut);
        //                else if (Scale < MIN_SCALE)
        //                    this.ScaleTo(MIN_SCALE, 250, Easing.SpringOut);
        //                break;
        //        }

        //        //// Calculate the scale factor to be applied.
        //        //currentScale += (e.Scale - 1) * startScale;
        //        //currentScale = Math.Max(1, currentScale);

        //        //// The ScaleOrigin is in relative coordinates to the wrapped user interface element,
        //        //// so get the X pixel coordinate.
        //        //double renderedX = Content.X + xOffset;
        //        //double deltaX = renderedX / Width;
        //        //double deltaWidth = Width / (Content.Width * startScale);
        //        //double originX = (e.ScaleOrigin.X - deltaX) * deltaWidth;

        //        //// The ScaleOrigin is in relative coordinates to the wrapped user interface element,
        //        //// so get the Y pixel coordinate.
        //        //double renderedY = Content.Y + yOffset;
        //        //double deltaY = renderedY / Height;
        //        //double deltaHeight = Height / (Content.Height * startScale);
        //        //double originY = (e.ScaleOrigin.Y - deltaY) * deltaHeight;

        //        //// Calculate the transformed element pixel coordinates.
        //        //double targetX = xOffset - (originX * Content.Width) * (currentScale - startScale);
        //        //double targetY = yOffset - (originY * Content.Height) * (currentScale - startScale);

        //        //// Apply translation based on the change in origin.
        //        //Content.TranslationX = targetX.Clamp(-Content.Width * (currentScale - 1), 0);
        //        //Content.TranslationY = targetY.Clamp(-Content.Height * (currentScale - 1), 0);

        //        //// Apply scale factor.
        //        //Content.Scale = currentScale;
        //    }
        //    if (e.Status == GestureStatus.Completed)
        //    {
        //        // Store the translation delta's of the wrapped user interface element.
        //        xOffset = Content.TranslationX;
        //        yOffset = Content.TranslationY;
        //        x = xOffset;
        //        y = yOffset;
        //    }
        //}

        //private void PanGesture_PanUpdated(object sender, PanUpdatedEventArgs e)
        //{
        //    switch(e.StatusType)
        //    {
        //        case GestureStatus.Running:
        //            Content.TranslationX = x + e.TotalX;
        //            Content.TranslationY = y + e.TotalY;
        //            break;
        //        case GestureStatus.Completed:
        //            x = Content.TranslationX;
        //            y = Content.TranslationY;
        //            break;
        //    }
        //}




        private T Clamp<T>(T value, T minimum, T maximum) where T : IComparable
        {
            if (value.CompareTo(minimum) < 0)
                return minimum;
            else if (value.CompareTo(maximum) > 0)
                return maximum;
            else
                return value;
        }
    }
}
