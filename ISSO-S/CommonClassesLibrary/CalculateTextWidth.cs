namespace CommonClassesLibrary
{
    public interface ICalculateTextWidthHeight
    {
        double CalculateWidth(string text, float textSize);

        double CalculateHeight(string text, float textSize);
    }
}
