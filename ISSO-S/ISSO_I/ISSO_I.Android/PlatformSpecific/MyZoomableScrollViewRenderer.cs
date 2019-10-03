using Android.Content;
using Android.Views;
using Android.Views.Animations;
using ISSO_I.CustomRenderes;
using ISSO_I.Droid.PlatformSpecific;
using Xamarin.Forms;
using Xamarin.Forms.Platform.Android;
using static Android.Views.ScaleGestureDetector;

[assembly: ExportRenderer(typeof(ZoomableScrollView), typeof(MyZoomableScrollView))]
namespace ISSO_I.Droid.PlatformSpecific
{
    public class MyZoomableScrollView : ScrollViewRenderer, IOnScaleGestureListener
    {
        private float _mScale = 1f;
        private ScaleGestureDetector _mScaleDetector;

        public MyZoomableScrollView(Context context) : base(context) { }

        public bool OnScale(ScaleGestureDetector detector)
        {
            var scale = 1 - detector.ScaleFactor;

            var prevScale = _mScale;
            _mScale += scale;

            if (_mScale < 0.8f) // Minimum scale condition:
                _mScale = 0.8f;

            if (_mScale > 2f) // Maximum scale condition:
                _mScale = 2f;
            var scaleAnimation = new ScaleAnimation(1f / prevScale, 1f / _mScale, 1f / prevScale, 1f / _mScale, detector.FocusX, detector.FocusY)
            {
                Duration = 0,
                FillAfter = true
            };
            StartAnimation(scaleAnimation);
            return true;
        }

        public bool OnScaleBegin(ScaleGestureDetector detector)
        {
            return true;
        }

        public void OnScaleEnd(ScaleGestureDetector detector)
        {

        }

        protected override void OnElementChanged(VisualElementChangedEventArgs e)
        {
            base.OnElementChanged(e);
            _mScaleDetector = new ScaleGestureDetector(Context, this);
        }

        public override bool DispatchTouchEvent(MotionEvent e)
        {
            base.DispatchTouchEvent(e);
            return _mScaleDetector.OnTouchEvent(e);
        }
    }
}