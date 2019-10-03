using System;
using System.ComponentModel;
using System.Runtime.Serialization;
using SQLite;

namespace CommonClassesLibrary
{
    public class DBHelper
    {
        #region Общая часть
        [Table("I_ISSO")]
        public class I_ISSO
        {
            /// <summary>
            /// номер ИССО
            /// </summary>
            [PrimaryKey]
            public Int32 C_ISSO { get; set; }
            /// <summary>
            /// Наименование ИССО
            /// </summary>
            public string NAME { get; set; }
            /// <summary>
            /// Экспертная ОТС (численная)
            /// </summary>
            public Int32 C_OTC_EXP { get; set; }
            /// <summary>
            /// Экспертная ОТС
            /// </summary>
            public string N_OTC_EXP { get; set; }
            /// <summary>
            /// Полное наименование ИССО
            /// </summary>
            public string FULLNAME { get; set; }
            /// <summary>
            /// Дорога, на которой находится ИССО
            /// </summary>
            public string DORNAME { get; set; }
            /// <summary>
            /// Номер дороги
            /// </summary>
            public Int32 W_ISSO { get; set; }
            /// <summary>
            /// Препятствие
            /// </summary>
            public string OBSTACLE { get; set; }
            /// <summary>
            /// Длина
            /// </summary>
            public double LENGTH { get; set; }
            /// <summary>
            /// Широта
            /// </summary>
            public double LATITUDE { get; set; }
            /// <summary>
            /// Долгота
            /// </summary>
            public double LONGITUDE { get; set; }
            /// <summary>
            /// Тип ИССО
            /// </summary>
            public Int32 CTYPEISSO { get; set; }

            public I_ISSO() { }
            public I_ISSO(HttpsIsso isso)
            {
                this.C_ISSO = isso.CIsso;
                this.NAME = isso.Name;
                this.C_OTC_EXP = isso.ExpertRatingNumeric;
                this.N_OTC_EXP = isso.ExpertRating;
                this.FULLNAME = isso.FullName;
                this.DORNAME = isso.DorName;
                this.W_ISSO = isso.WIsso;
                this.OBSTACLE = isso.Obstacle;
                this.LENGTH = isso.Length;
                this.LATITUDE = isso.Latitude;
                this.LONGITUDE = isso.Longitude;
                this.CTYPEISSO = isso.CTypeIsso;
            }
        }

        [System.Diagnostics.DebuggerStepThroughAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
        [System.Runtime.Serialization.DataContractAttribute(Name = "HttpsIsso", Namespace = "http://schemas.datacontract.org/2004/07/Ais7MobileCommonServerCore")]
        public partial class HttpsIsso : object
        {

            private int CIssoField;

            private int CTypeIssoField;

            private string DorNameField;

            private string ExpertRatingField;

            private int ExpertRatingNumericField;

            private string FullNameField;

            private double LatitudeField;

            private double LengthField;

            private double LongitudeField;

            private string NameField;

            private string NameIssoField;

            private string ObstacleField;

            private int WIssoField;

            [System.Runtime.Serialization.DataMemberAttribute()]
            public int CIsso
            {
                get
                {
                    return this.CIssoField;
                }
                set
                {
                    this.CIssoField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public int CTypeIsso
            {
                get
                {
                    return this.CTypeIssoField;
                }
                set
                {
                    this.CTypeIssoField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string DorName
            {
                get
                {
                    return this.DorNameField;
                }
                set
                {
                    this.DorNameField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string ExpertRating
            {
                get
                {
                    return this.ExpertRatingField;
                }
                set
                {
                    this.ExpertRatingField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public int ExpertRatingNumeric
            {
                get
                {
                    return this.ExpertRatingNumericField;
                }
                set
                {
                    this.ExpertRatingNumericField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string FullName
            {
                get
                {
                    return this.FullNameField;
                }
                set
                {
                    this.FullNameField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public double Latitude
            {
                get
                {
                    return this.LatitudeField;
                }
                set
                {
                    this.LatitudeField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public double Length
            {
                get
                {
                    return this.LengthField;
                }
                set
                {
                    this.LengthField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public double Longitude
            {
                get
                {
                    return this.LongitudeField;
                }
                set
                {
                    this.LongitudeField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string Name
            {
                get
                {
                    return this.NameField;
                }
                set
                {
                    this.NameField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string NameIsso
            {
                get
                {
                    return this.NameIssoField;
                }
                set
                {
                    this.NameIssoField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string Obstacle
            {
                get
                {
                    return this.ObstacleField;
                }
                set
                {
                    this.ObstacleField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public int WIsso
            {
                get
                {
                    return this.WIssoField;
                }
                set
                {
                    this.WIssoField = value;
                }
            }
        }

        [System.Diagnostics.DebuggerStepThroughAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
        [System.Runtime.Serialization.DataContractAttribute(Name = "PhotoIssoS", Namespace = "http://schemas.datacontract.org/2004/07/Ais7MobileCommonServerCore")]
        public partial class PhotoIssoS : object
        {

            private int CIssoField;

            private string CommentField;

            private string PhotoField;

            private long PhotoDateField;

            private string PhotoPathField;

            private long RatingDateField;

            [System.Runtime.Serialization.DataMemberAttribute()]
            public int CIsso
            {
                get
                {
                    return this.CIssoField;
                }
                set
                {
                    this.CIssoField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string Comment
            {
                get
                {
                    return this.CommentField;
                }
                set
                {
                    this.CommentField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string Photo
            {
                get
                {
                    return this.PhotoField;
                }
                set
                {
                    this.PhotoField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public long PhotoDate
            {
                get
                {
                    return this.PhotoDateField;
                }
                set
                {
                    this.PhotoDateField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string PhotoPath
            {
                get
                {
                    return this.PhotoPathField;
                }
                set
                {
                    this.PhotoPathField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public long RatingDate
            {
                get
                {
                    return this.RatingDateField;
                }
                set
                {
                    this.RatingDateField = value;
                }
            }
        }
        #endregion

        #region ISSO_S Таблицы
        [Table("RATING")]
        public class RATING
        {
            /// <summary>
            /// Номер ИССО
            /// </summary>
            [Indexed(Name = "IxPk", Order = 1, Unique = true)]
            public Int32 C_ISSO { get; set; }
            /// <summary>
            /// Дата ОТС
            /// </summary>
            [Indexed(Name = "IxPk", Order = 2, Unique = true)]
            public long RATINGDATE { get; set; }
            /// <summary>
            /// Дата редактирования ОТС
            /// </summary>
            public long RATINGDATEEDIT { get; set; }
            /// <summary>
            /// Общий рейтинг
            /// </summary>
            public Int32 RATINGS { get; set; }
            /// <summary>
            /// Широта ОТС
            /// </summary>
            public double LATITUDE_RATING { get; set; }
            /// <summary>
            /// Долгота ОТС
            /// </summary>
            public double LONGITUDE_RATING { get; set; }
            /// <summary>
            /// Комментарий к ОТС
            /// </summary>
            public string COMMENTS { get; set; }
            /// <summary>
            /// Был ли синхронизирована ОТС
            /// </summary>
            public bool SYNC { get; set; }
            /// <summary>
            /// Для добавления в дату
            /// </summary>
            public long OFFSET { get; set; }
            /// <summary>
            /// Текущий рейтинг
            /// </summary>
            public Int32 CURRENTRATING { get; set; } = 0;
            /// <summary>
            /// Необходимость внеплановой оценки ИССО
            /// </summary>
            public bool CHECKOUTOFPLAN { get; set; } = false;

            public RATING() { }
            public RATING(RatingHttps ratingHttps)
            {
                this.C_ISSO = ratingHttps.CIsso;
                this.CURRENTRATING = ratingHttps.CurrentRating;
                this.RATINGDATE = ratingHttps.RatingDate;
                this.RATINGS = ratingHttps.RatingIsso;
                this.COMMENTS = ratingHttps.RatingExt;
                this.OFFSET = ratingHttps.Offset;
                this.CHECKOUTOFPLAN = ratingHttps.CheckOut;
                this.RATINGDATEEDIT = ratingHttps.RatingDateEdit;
                this.LATITUDE_RATING = ratingHttps.Latitude;
                this.LONGITUDE_RATING = ratingHttps.Longitude;
                this.SYNC = true;
            }
        }

        [Table("PHOTOS_RATING")]
        public class PHOTOS_RATING
        {
            [Indexed(Name = "IxPk", Order = 1, Unique = true)]
            public Int32 C_ISSO { get; set; }
            [Indexed(Name = "IxPk", Order = 2, Unique = true)]
            public long RATINGDATE { get; set; }
            public string PHOTOPATH { get; set; }
            [Indexed(Name = "IxPk", Order = 3, Unique = true)]
            public long PHOTODATE { get; set; }
            public string COMMENT { get; set; }
            public bool SYNC { get; set; }
        }

        [System.Diagnostics.DebuggerStepThroughAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
        [System.Runtime.Serialization.DataContractAttribute(Name = "RatingHttps", Namespace = "http://schemas.datacontract.org/2004/07/Ais7IssoSServerCore")]
        public partial class RatingHttps : object
        {

            private int CIssoField;

            private bool CheckOutField;

            private int CurrentRatingField;

            private double LatitudeField;

            private double LongitudeField;

            private long OffsetField;

            private long RatingDateField;

            private long RatingDateEditField;

            private string RatingExtField;

            private int RatingIssoField;

            [System.Runtime.Serialization.DataMemberAttribute()]
            public int CIsso
            {
                get
                {
                    return this.CIssoField;
                }
                set
                {
                    this.CIssoField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public bool CheckOut
            {
                get
                {
                    return this.CheckOutField;
                }
                set
                {
                    this.CheckOutField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public int CurrentRating
            {
                get
                {
                    return this.CurrentRatingField;
                }
                set
                {
                    this.CurrentRatingField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public double Latitude
            {
                get
                {
                    return this.LatitudeField;
                }
                set
                {
                    this.LatitudeField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public double Longitude
            {
                get
                {
                    return this.LongitudeField;
                }
                set
                {
                    this.LongitudeField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public long Offset
            {
                get
                {
                    return this.OffsetField;
                }
                set
                {
                    this.OffsetField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public long RatingDate
            {
                get
                {
                    return this.RatingDateField;
                }
                set
                {
                    this.RatingDateField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public long RatingDateEdit
            {
                get
                {
                    return this.RatingDateEditField;
                }
                set
                {
                    this.RatingDateEditField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string RatingExt
            {
                get
                {
                    return this.RatingExtField;
                }
                set
                {
                    this.RatingExtField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public int RatingIsso
            {
                get
                {
                    return this.RatingIssoField;
                }
                set
                {
                    this.RatingIssoField = value;
                }
            }
        }

        #endregion
    }
}
