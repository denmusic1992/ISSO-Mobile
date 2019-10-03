using System;
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
	        /// Наименование ИССО
	        /// </summary>
	        public string NAME_ISSO { get; set; }
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
	            this.NAME_ISSO = isso.NameIsso;
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

        #endregion

        #region ISSO_I Таблицы
        [Table("TABLE_NAMES")]
        public class TABLE_NAMES
        {
            /// <summary>
            /// Группа конструкций
            /// </summary>
            public Int32 C_GR_CONSTR { get; set; }
            /// <summary>
            /// Системное имя
            /// </summary>
            public String SYS_NAME { get; set; }
            /// <summary>
            /// Описание
            /// </summary>
            public String DESCRIPTION { get; set; }
            /// <summary>
            /// Идентификатор родителя
            /// </summary>
            public Int16 PARENT_ID { get; set; }
            /// <summary>
            /// Имя родителя
            /// </summary>
            public Int16 PARENT_VIEW { get; set; }

            public Int16 COUNT { get; set; }
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

        [Table("ADVANCED_S_TABLES")]
        public class ADVANCED_S_TABLES
        {
            /// <summary>
            /// имя таблицы
            /// </summary>
            public String TABLE_NAME { get; set; }
            /// <summary>
            /// имя колонки
            /// </summary>
            public String TABLE_COLUMN { get; set; }
            /// <summary>
            /// описание
            /// </summary>
            public String DESCRIPTION { get; set; }
            /// <summary>
            /// справочная таблица для этой колонки
            /// </summary>
            public String S_TABLE { get; set; }
            /// <summary>
            /// Параметр для группирования
            /// </summary>
            public String CATEGORY { get; set; }
            /// <summary>
            /// Только для чтения
            /// </summary>
            public bool READONLY { get; set; }
            /// <summary>
            /// Видно ли параметр
            /// </summary>
            public bool VISIBLE { get; set; }
            /// <summary>
            /// Форматирование
            /// </summary>
            public String FORMAT { get; set; }
            /// <summary>
            /// Минимальный порог
            /// </summary>
            public Int32 MIN_V { get; set; }
            /// <summary>
            /// Максимальный порог
            /// </summary>
            public Int32 MAX_V { get; set; }
            /// <summary>
            /// Тип колонки
            /// </summary>
            public string COLUMNTYPE { get; set; }
            /// <summary>
            /// Признак первичного ключа
            /// </summary>
            public bool IS_PRIMARY_KEY { get; set; }
        }

        [Table("S_INFO_TABLES")]
        public class S_INFO_TABLES
        {
            public String TABLE_NAME { get; set; }
            public Int32 ID { get; set; }
            public String VALUE { get; set; }
        }

        [System.Diagnostics.DebuggerStepThroughAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
        [System.Runtime.Serialization.DataContractAttribute(Name = "UploadPhotoForIsso", Namespace = "http://schemas.datacontract.org/2004/07/Ais7IssoIServerCore")]
        public partial class UploadPhotoForIsso : object
        {

            private int C_ISSOField;

            private string CommentsField;

            private int NField;

            private string PhotoField;

            private string PhotoDateField;

            [System.Runtime.Serialization.DataMemberAttribute()]
            public int C_ISSO
            {
                get
                {
                    return this.C_ISSOField;
                }
                set
                {
                    this.C_ISSOField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string Comments
            {
                get
                {
                    return this.CommentsField;
                }
                set
                {
                    this.CommentsField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public int N
            {
                get
                {
                    return this.NField;
                }
                set
                {
                    this.NField = value;
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
            public string PhotoDate
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
        }

        [System.Diagnostics.DebuggerStepThroughAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
        [System.Runtime.Serialization.DataContractAttribute(Name = "UploadSchemeForIsso", Namespace = "http://schemas.datacontract.org/2004/07/Ais7IssoIServerCore")]
        public partial class UploadSchemeForIsso : object
        {

            private int C_ISSOField;

            private string CommentsField;

            private int NField;

            private string SchemeField;

            private string SchemeDateField;

            private string ThumbnailField;

            [System.Runtime.Serialization.DataMemberAttribute()]
            public int C_ISSO
            {
                get
                {
                    return this.C_ISSOField;
                }
                set
                {
                    this.C_ISSOField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string Comments
            {
                get
                {
                    return this.CommentsField;
                }
                set
                {
                    this.CommentsField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public int N
            {
                get
                {
                    return this.NField;
                }
                set
                {
                    this.NField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string Scheme
            {
                get
                {
                    return this.SchemeField;
                }
                set
                {
                    this.SchemeField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string SchemeDate
            {
                get
                {
                    return this.SchemeDateField;
                }
                set
                {
                    this.SchemeDateField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string Thumbnail
            {
                get
                {
                    return this.ThumbnailField;
                }
                set
                {
                    this.ThumbnailField = value;
                }
            }
        }

        [System.Diagnostics.DebuggerStepThroughAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
        [System.Runtime.Serialization.DataContractAttribute(Name = "MeanInfo", Namespace = "http://schemas.datacontract.org/2004/07/Ais7IssoIServerCore")]
        public partial class MeanInfo : object
        {

            private HttpsTableDelegate[] tableDelegateField;

            private HttpsTableNames[] tableNamesField;

            [System.Runtime.Serialization.DataMemberAttribute()]
            public HttpsTableDelegate[] tableDelegate
            {
                get
                {
                    return this.tableDelegateField;
                }
                set
                {
                    this.tableDelegateField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public HttpsTableNames[] tableNames
            {
                get
                {
                    return this.tableNamesField;
                }
                set
                {
                    this.tableNamesField = value;
                }
            }
        }

        [System.Diagnostics.DebuggerStepThroughAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
        [System.Runtime.Serialization.DataContractAttribute(Name = "HttpsTableDelegate", Namespace = "http://schemas.datacontract.org/2004/07/Ais7IssoIServerCore")]
        public partial class HttpsTableDelegate : object
        {

            private int C_GR_CONSTRField;

            private int C_TYPEISSOField;

            [System.Runtime.Serialization.DataMemberAttribute()]
            public int C_GR_CONSTR
            {
                get
                {
                    return this.C_GR_CONSTRField;
                }
                set
                {
                    this.C_GR_CONSTRField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public int C_TYPEISSO
            {
                get
                {
                    return this.C_TYPEISSOField;
                }
                set
                {
                    this.C_TYPEISSOField = value;
                }
            }
        }

        [System.Diagnostics.DebuggerStepThroughAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
        [System.Runtime.Serialization.DataContractAttribute(Name = "HttpsTableNames", Namespace = "http://schemas.datacontract.org/2004/07/Ais7IssoIServerCore")]
        public partial class HttpsTableNames : object
        {

            private int C_GR_CONSTRField;

            private bool IsTableExportedWholeField;

            private string TableNameField;

            private int TableParentIDField;

            private string TableSysNameField;

            private int TableViewField;

            [System.Runtime.Serialization.DataMemberAttribute()]
            public int C_GR_CONSTR
            {
                get
                {
                    return this.C_GR_CONSTRField;
                }
                set
                {
                    this.C_GR_CONSTRField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public bool IsTableExportedWhole
            {
                get
                {
                    return this.IsTableExportedWholeField;
                }
                set
                {
                    this.IsTableExportedWholeField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string TableName
            {
                get
                {
                    return this.TableNameField;
                }
                set
                {
                    this.TableNameField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public int TableParentID
            {
                get
                {
                    return this.TableParentIDField;
                }
                set
                {
                    this.TableParentIDField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string TableSysName
            {
                get
                {
                    return this.TableSysNameField;
                }
                set
                {
                    this.TableSysNameField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public int TableView
            {
                get
                {
                    return this.TableViewField;
                }
                set
                {
                    this.TableViewField = value;
                }
            }
        }

        [System.Diagnostics.DebuggerStepThroughAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
        [System.Runtime.Serialization.DataContractAttribute(Name = "AdvancedInfo", Namespace = "http://schemas.datacontract.org/2004/07/Ais7IssoIServerCore")]
        public partial class AdvancedInfo : object
        {

            private HttpsTableAttributes[] tableAttributesField;

            private string[][] tableValuesField;

            [System.Runtime.Serialization.DataMemberAttribute()]
            public HttpsTableAttributes[] tableAttributes
            {
                get
                {
                    return this.tableAttributesField;
                }
                set
                {
                    this.tableAttributesField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string[][] tableValues
            {
                get
                {
                    return this.tableValuesField;
                }
                set
                {
                    this.tableValuesField = value;
                }
            }
        }

        [System.Diagnostics.DebuggerStepThroughAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
        [System.Runtime.Serialization.DataContractAttribute(Name = "HttpsTableAttributes", Namespace = "http://schemas.datacontract.org/2004/07/Ais7IssoIServerCore")]
        public partial class HttpsTableAttributes : object
        {

            private string AttributeNameField;

            private int C_GR_CONSTRField;

            private string CategoryField;

            private string DataTypeField;

            private int IDField;

            private string TableSysNameField;

            private int VisibleInGridField;

            private bool isBlobField;

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string AttributeName
            {
                get
                {
                    return this.AttributeNameField;
                }
                set
                {
                    this.AttributeNameField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public int C_GR_CONSTR
            {
                get
                {
                    return this.C_GR_CONSTRField;
                }
                set
                {
                    this.C_GR_CONSTRField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string Category
            {
                get
                {
                    return this.CategoryField;
                }
                set
                {
                    this.CategoryField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string DataType
            {
                get
                {
                    return this.DataTypeField;
                }
                set
                {
                    this.DataTypeField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public int ID
            {
                get
                {
                    return this.IDField;
                }
                set
                {
                    this.IDField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string TableSysName
            {
                get
                {
                    return this.TableSysNameField;
                }
                set
                {
                    this.TableSysNameField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public int VisibleInGrid
            {
                get
                {
                    return this.VisibleInGridField;
                }
                set
                {
                    this.VisibleInGridField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public bool isBlob
            {
                get
                {
                    return this.isBlobField;
                }
                set
                {
                    this.isBlobField = value;
                }
            }
        }

        [System.Diagnostics.DebuggerStepThroughAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
        [System.Runtime.Serialization.DataContractAttribute(Name = "PrimaryInfo", Namespace = "http://schemas.datacontract.org/2004/07/Ais7IssoIServerCore")]
        public partial class PrimaryInfo : object
        {

            private string[] pksField;

            private string[] typesField;

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string[] pks
            {
                get
                {
                    return this.pksField;
                }
                set
                {
                    this.pksField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string[] types
            {
                get
                {
                    return this.typesField;
                }
                set
                {
                    this.typesField = value;
                }
            }
        }

        [System.Diagnostics.DebuggerStepThroughAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
        [System.Runtime.Serialization.DataContractAttribute(Name = "OutputInfo", Namespace = "http://schemas.datacontract.org/2004/07/Ais7IssoIServerCore")]
        public partial class OutputInfo : object
        {

            private string[][] NeedToDeleteField;

            private string[][] resultField;

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string[][] NeedToDelete
            {
                get
                {
                    return this.NeedToDeleteField;
                }
                set
                {
                    this.NeedToDeleteField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string[][] result
            {
                get
                {
                    return this.resultField;
                }
                set
                {
                    this.resultField = value;
                }
            }
        }

        [System.Diagnostics.DebuggerStepThroughAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
        [System.Runtime.Serialization.DataContractAttribute(Name = "ListCollection", Namespace = "http://schemas.datacontract.org/2004/07/Ais7IssoIServerCore")]
        public partial class ListCollection : object
        {

            private string CollectionNameField;

            private long DateCreateField;

            private long DateModifyField;

            private string DescriptionField;

            private string IssoListField;

            private int UserIDField;

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string CollectionName
            {
                get
                {
                    return this.CollectionNameField;
                }
                set
                {
                    this.CollectionNameField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public long DateCreate
            {
                get
                {
                    return this.DateCreateField;
                }
                set
                {
                    this.DateCreateField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public long DateModify
            {
                get
                {
                    return this.DateModifyField;
                }
                set
                {
                    this.DateModifyField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string Description
            {
                get
                {
                    return this.DescriptionField;
                }
                set
                {
                    this.DescriptionField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public string IssoList
            {
                get
                {
                    return this.IssoListField;
                }
                set
                {
                    this.IssoListField = value;
                }
            }

            [System.Runtime.Serialization.DataMemberAttribute()]
            public int UserID
            {
                get
                {
                    return this.UserIDField;
                }
                set
                {
                    this.UserIDField = value;
                }
            }
        }

        #endregion
    }
}
