namespace CommonClassesLibrary.Interfaces
{
    public interface ITextMeter
    {
        double MeasureTextSize(string text, double width, double fontSize, string fontName = null);
    }
}
