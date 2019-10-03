namespace ISSO_I.IssoViewPages.ForDefectTable
{
    public class Ais7IssoDefect
    {
        /** Номер дефекта */
        public int NDef { get; set; }
        /** Порядковый номер в ведомости */
        public int Ord { get; set; }
        /** Наличие фотографий для дефекта */
        public bool HasPhoto { get; set; }
        /** Наличие устранения дефекта*/
        public bool IsDefCompleted { get; set; }
        /** Местоположение дефекта */
        public string Location { get; set; }
        /** Местоположение дефекта */
        public string NameConstr { get; set; }
        /** Тип и описание дефекта */
        public string NDefect { get; set; }
        /** Определяющие параметры степени развития и их значение */
        public string Params { get; set; }
        /** Категории дефекта */
        public string Category { get; set; }
        /** Характеристика объема дефекта по ремонтоспособности */
        public string RemontInfo { get; set; }
        /** Номер главной группы конструкций*/
        public int MainConstrId { get; set; }



        public override string ToString()
        {
            return $"{Ord}/{NDef}{(HasPhoto ? " (ф)" : "")}";
        }
    }
}
