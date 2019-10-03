namespace ISSO_I.PopupTypes
{
    /// <summary>
    /// Модель для отображения нажатого элемента
    /// </summary>
    /// [ImplementPropertyChanged]
    public class ModelForSelect
    {
        public string Content { get; set; }
        public bool Selected { get; set; }

        public ModelForSelect() { }

        public ModelForSelect(string content, bool selected)
        {
            Content = content;
            Selected = selected;
        }
    }
}
