using System;

namespace CommonClassesLibrary
{
    /// <summary>
    /// Оценка текущего состояния
    /// </summary>
    public enum Otc
    {
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
        PreEmergency = 1,
        [EnumDescription("Неудовлетворительное")]
        NonSatisfied = 2,
        [EnumDescription("Удовлетворительное")]
        Satisfied = 3,
        [EnumDescription("Хорошее")]
        WellDone = 4,
        [EnumDescription("Отличное")]
        Excellent = 5,
        [EnumDescription("Не оценивалось")]
        NotActual = 6,
        [EnumDescription("Оценке не подлежит")]
        UnableToEstimate = 10,
        [EnumDescription("Оценка отсрочена")]
        OutOfDate = 11,
        [EnumDescription("Актуализация отсутствует")]
        ActualNotExist = 100,
        [EnumDescription("Аварийное (не актуально)")]
        EmergencyNotActual = 200,
        [EnumDescription("Предварийное (не актуально)")]
        PreEmergencyNotActual = 201,
        [EnumDescription("Неудовлетворительное (не актуально)")]
        NonSatisfiedNotActual = 202,
        [EnumDescription("Удовлетворительное (не актуально)")]
        SatisfiedNotActual = 203,
        [EnumDescription("Хорошее (не актуально)")]
        WellDoneNotActual = 204,
        [EnumDescription("Отличное (не актуально)")]
        ExcellentNotActual = 205
    }

    [AttributeUsage(AttributeTargets.Field)]
    public class EnumDescriptionAttribute : Attribute
    {
	    public string Description { get; }

	    public EnumDescriptionAttribute(string description)
        {
            Description = description;
        }
    }


    public enum ApplicationType
    {
        [EnumDescription("АИС ИССО-SX")]
        IssoS = 1,
        [EnumDescription("АИС ИССО-RX")]
        IssoR = 2,
        [EnumDescription("АИС ИССО-IX")]
        IssoI = 3
    }
}
