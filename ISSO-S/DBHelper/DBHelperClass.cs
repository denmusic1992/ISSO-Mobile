using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Ais7IssoSServerCore;
using ais7MobileCommonServerCore;
using SQLite;

namespace CommonStaffLibrary
{
    public class DBHelper
    {

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
        #endregion

        #region ISSO_I Таблицы
        [Table("TABLE_NAMES")]
        public class TABLE_NAMES
        {
            [PrimaryKey]
            public Int32 C_GR_CONSTR { get; set; }
            public String SYS_NAME { get; set; }
            public String DESCRIPTION { get; set; }
            public Int32 PARENT_ID { get; set; }
            public Int32 PARENT_VIEW { get; set; }
        }

        [Table("TABLE_ATTRIBUTES")]
        public class TABLE_ATTRIBUTES
        {
            [PrimaryKey]
            public Int32 ID { get; set; }
            public Int32 C_GR_CONSTR { get; set; }
            public String SYS_NAME { get; set; }
            public String DATA_TYPE { get; set; }
            public String DESCRIPTION { get; set; }
            public bool IS_BLOB { get; set; }
            public String CATEGORY { get; set; }
            public Int32 VISIBLEINGRID { get; set; }

        }

        [Table("TABLE_VALUES")]
        public class TABLE_VALUES
        {
            public Int32 ATTRIBUTE_ID { get; set; }
            public Int32 ISSO { get; set; }
            public String VALUE { get; set; }
        }

        [Table("TABLE_DELEGATES")]
        public class TABLE_DELEGATES
        {
            public Int32 ISSO_TYPE { get; set; }
            public Int32 C_GR_CONSTR { get; set; }
        }

        [Table("UPLOAD_PHOTOS")]
        public class UPLOAD_PHOTOS
        {
            public Int32 C_ISSO { get; set; }
            public Int32 N { get; set; }
            public String COMMENTS { get; set; }
            public byte[] PHOTO { get; set; }
            public Int64 PHOTO_DATE { get; set; }
        }

        [Table("UPLOAD_SCHEMES")]
        public class UPLOAD_SCHEMES
        {
            public Int32 C_ISSO { get; set; }
            public Int32 N { get; set; }
            public String COMMENTS { get; set; }
            public byte[] SCHEME { get; set; }
            public byte[] THUMBNAIL { get; set; }
            public Int64 SCHEME_DATE { get; set; }
        }
        #endregion
    }
}
