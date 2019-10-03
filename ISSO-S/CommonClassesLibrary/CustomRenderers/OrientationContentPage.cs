using System;
using Xamarin.Forms;

namespace CommonClassesLibrary.CustomRenderers
{
    public class OrientationContentView : ContentView
    {
        private double _width;
        private double _height;

        public event EventHandler<PageOrientationEventArgs> OnOrientationChanged = (e, a) => { };

        public OrientationContentView() { Init(); }

        private void Init()
        {
            _width = Width;
            _height = Height;
        }

        protected override void OnSizeAllocated(double width, double height)
        {
            var oldWidth = _width;
            const double sizenotallocated = -1;

            base.OnSizeAllocated(width, height);
            if (Equals(_width, width) && Equals(_height, height)) return;

            _width = width;
            _height = height;

            // ignore if the previous height was size unallocated
            if (Equals(oldWidth, sizenotallocated)) return;

            // Has the device been rotated ?
            if (!Equals(width, oldWidth))
                OnOrientationChanged.Invoke(this, new PageOrientationEventArgs((width < height) ? PageOrientation.Vertical : PageOrientation.Horizontal));
        }
    }

    public class PageOrientationEventArgs : EventArgs
    {
        public PageOrientationEventArgs(PageOrientation orientation)
        {
            Orientation = orientation;
        }

        public PageOrientation Orientation { get; }
    }

    public enum PageOrientation
    {
        Horizontal = 0,
        Vertical = 1,
    }
}