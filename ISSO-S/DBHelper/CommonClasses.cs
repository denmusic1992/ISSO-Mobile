using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DBHelper
{
    public class CommonClasses
    {

        /// <summary>
        /// Оценка текущего состояния
        /// </summary>
        public enum OTC
        {
            // Для ИССО-S
            [EnumDescription("Без изменений")]
            NotChanged = 20,
            [EnumDescription("Незначительное ухужшение")]
            SlightlyWorse = 30,
            [EnumDescription("Ухудшение")]
            Worse = 40,
            [EnumDescription("Значительное ухудшение")]
            SignificantlyWorse = 50,
            [EnumDescription("Авария")]
            Crash = 70,
            [EnumDescription("Улучшение")]
            Improved = 60,

            // Для ИССО-I
            [EnumDescription("Аварийное")]
            Emergency = 0,
            [EnumDescription("Предварийное")]
            Pre_emergency = 1,
            [EnumDescription("Неудовлетворительное")]
            Non_satisfied = 2,
            [EnumDescription("Удовлетворительное")]
            Satisfied = 3,
            [EnumDescription("Хорошее")]
            Well_done = 4,
            [EnumDescription("Отличное")]
            Excellent = 5,
            [EnumDescription("Не оценивалось")]
            Not_actual = 6,
            [EnumDescription("Оценке не подлежит")]
            Unable_To_Estimate = 10,
            [EnumDescription("Оценка отсрочена")]
            Out_Of_Date = 11,
            [EnumDescription("Актуализация отсутствует")]
            Actual_Not_Exist = 100,
            [EnumDescription("Аварийное (не актуально)")]
            Emergency_not_actual = 200,
            [EnumDescription("Предварийное (не актуально)")]
            Pre_emergency_not_actual = 201,
            [EnumDescription("Неудовлетворительное (не актуально)")]
            Non_satisfied_not_actual = 202,
            [EnumDescription("Удовлетворительное (не актуально)")]
            Satisfied_not_actual = 203,
            [EnumDescription("Хорошее (не актуально)")]
            Well_done_not_actual = 204,
            [EnumDescription("Отличное (не актуально)")]
            Excellent_not_actual = 205
        }

        public enum ApplicationType
        {
            ISSO_S = 1,
            ISSO_R = 2,
            ISSO_I = 3
        }



        [AttributeUsage(AttributeTargets.Field, AllowMultiple = false)]
        public class EnumDescriptionAttribute : Attribute
        {
            private readonly string description;
            public string Description { get { return description; } }
            public EnumDescriptionAttribute(string description)
            {
                this.description = description;
            }
        }
    }
}
