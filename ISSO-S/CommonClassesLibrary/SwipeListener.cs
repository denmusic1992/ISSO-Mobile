using System;
using CommonClassesLibrary.Interfaces;
using Xamarin.Forms;

namespace CommonClassesLibrary
{
    public class SwipeListener : PanGestureRecognizer
    {
        private readonly ISwipeCallback iSwipeCallBack;
        private double _translateX, _translateY;

        public SwipeListener(View view, ISwipeCallback iSwipeCallBack)
        {
            this.iSwipeCallBack = iSwipeCallBack;
            var panGesture = new PanGestureRecognizer();
            panGesture.PanUpdated += PanGesture_PanUpdated;
            view.GestureRecognizers.Add(panGesture);
        }

        private void PanGesture_PanUpdated(object sender, PanUpdatedEventArgs e)
        {
            switch (e.StatusType)
            {

                case GestureStatus.Running:

                    try
                    {
                        _translateX = e.TotalX;
                        _translateY = e.TotalY;
                    }
                    catch (Exception err)
                    {
                        System.Diagnostics.Debug.WriteLine("" + err.Message);
                    }
                    break;

                case GestureStatus.Completed:

                    System.Diagnostics.Debug.WriteLine("translatedX : " + _translateX);
                    System.Diagnostics.Debug.WriteLine("translatedY : " + _translateY);

                    if (_translateX < 0 && Math.Abs(_translateX) > Math.Abs(_translateY))
                    {
                        iSwipeCallBack.OnLeftSwipe();
                    }
                    else if (_translateX > 0 && _translateX > Math.Abs(_translateY))
                    {
                        iSwipeCallBack.OnRightSwipe();
                    }
                    //else
                    //{
                    //    iSwipeCallBack.OnNothingSwiped(Content);
                    //}

                    break;

            }
        }
    }
}
