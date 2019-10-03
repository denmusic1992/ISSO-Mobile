using System;
using Xamarin.Forms;

namespace CommonClassesLibrary
{

    public class MasterDetailPage1MenuItem
    {
	    public int Id { get; set; }
        public string Title { get; set; }
        public ImageSource Img { get; set; }

        public Type TargetType { get; set; }
    }
}