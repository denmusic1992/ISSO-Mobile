using System;
using System.ServiceModel;
using CommonClassesLibrary;

namespace ISSO_I.Interfaces
{
    [ServiceContract]
    public interface IAis7MobileIssoIServerCoreXamarin
    {
        /// <summary>
        /// Получение сессии для зарегистрированных пользователей в приложении ISSO-I
        /// </summary>
        /// <param name="user"></param>
        /// <param name="pass"></param>
        /// <param name="IssoList"></param>
        /// <returns></returns>
        [OperationContract]
        string[] HttpsGetSessionIdForIssoI(string user, string pass, string IssoList);

        /// <summary>
        /// Загрузка фотографий по ИССО
        /// </summary>
        /// <param name="c_isso"></param>
        /// <returns></returns>
        [OperationContract]
        DBHelper.UploadPhotoForIsso[] UploadPhotoInfoForIssos(int c_isso);


        /// <summary>
        /// Загрузка схем по ИССО
        /// </summary>
        /// <param name="c_isso"></param>
        /// <returns></returns>
        [OperationContract]
        DBHelper.UploadSchemeForIsso[] UploadSchemeForIssos(int c_isso);

        /// <summary>
        /// Передача Sql запросов на клиента по данным таблиц дефектов
        /// </summary>
        /// <param name="id"></param>
        /// <param name="names"></param>
        /// <returns></returns>
        [OperationContract]
        string[] HttpsReceiveSQLQuery(Guid id, string[] names);

        /// <summary>
        /// 
        /// </summary>
        /// <param name="id"></param>
        /// <param name="tableName"></param>
        /// <returns></returns>
        [OperationContract]
        string[][] HttpsReceiveAllDatabase(Guid id, string tableName, int[] c_issos);

        /// <summary>
        /// Получение основной информации по таблицам для конкретных ИССО
        /// </summary>
        /// <param name="id"></param>
        /// <param name="issos"></param>
        /// <param name="cTypeIssos"></param>
        /// <returns></returns>
        [OperationContract]
        DBHelper.MeanInfo HttpsGetMeanInfoForIssos(Guid id, int[] issos, int[] cTypeIssos);

        /// <summary>
        /// Получение дополнительной информации по таблицам для конкретных ИССО
        /// </summary>
        /// <param name="id"></param>
        /// <param name="issos"></param>
        /// <param name="sysTableName"></param>
        /// <param name="C_GR_CONSTR"></param>
        /// <param name="isTheLast"></param>
        /// <returns></returns>
        [OperationContract]
        DBHelper.AdvancedInfo HttpsGetAdvancedInfoForIssos(Guid id, int[] issos, string sysTableName, int C_GR_CONSTR, bool isTheLast);

        /// <summary>
        /// Получение информации о работе на сервере
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [OperationContract]
        long getTimerFromServer(Guid id);

        /// <summary>
        /// Сравнение записей на сервере и на клиенте
        /// </summary>
        /// <param name="id"></param>
        /// <param name="clientCount"></param>
        /// <param name="tableName"></param>
        /// <returns></returns>
        [OperationContract]
        int compareCountOfRows(Guid id, int clientCount, string tableName);

        /// <summary>
        /// Получение первичных ключей с таблицы на сервере
        /// </summary>
        /// <param name="id"></param>
        /// <param name="table"></param>
        /// <returns></returns>
        [OperationContract]
        DBHelper.PrimaryInfo getPrimaryKeysValues(Guid id, string table);

        /// <summary>
        /// Сравнение таблиц и передача недостающих записей
        /// </summary>
        /// <param name="id"></param>
        /// <param name="table"></param>
        /// <param name="clientPks"></param>
        /// <param name="cIssos"></param>
        /// <returns></returns>
        [OperationContract]
        string[][] synchronyzeTables(Guid id, string table, string[] clientPks, int[] cIssos);

        [OperationContract]
        DBHelper.OutputInfo NewSynchronyzeTables(Guid id, string table, string[] clientPks, int[] cIssos);

        [OperationContract]
        string[][] GetActualInformation(Guid id, string table, int[] CIssos);

        [OperationContract]
        byte[] GetDataTableStream(Guid id, string table, int[] CIssos, bool is_directory_table);

        [OperationContract]
        byte[][] GetDictionaryTables(Guid id, string[] names);

        #region Для работы с выгрузкой данных
        /// <summary>
        /// Создание списка ИССО для конкретного пользователя
        /// </summary>
        /// <param name="list"></param>
        /// <returns></returns>
        [OperationContract]
        int CreateIssoIUserCollection(DBHelper.ListCollection list);

        /// <summary>
        /// Получение всех списков ИССО для конкретного пользователя
        /// </summary>
        /// <param name="UserID"></param>
        /// <returns></returns>
        [OperationContract]
        DBHelper.ListCollection[] GetIssoIUserCollections(string user, string pass);

        /// <summary>
        /// Получение списка ИССО по выбранной выгрузке
        /// </summary>
        /// <param name="UserID"></param>
        /// <param name="DateCreate"></param>
        /// <param name="CollectionName"></param>
        /// <returns></returns>
        [OperationContract]
        DBHelper.ListCollection GetIssoICurrentCollection(int UserID, long DateCreate, string CollectionName);

        /// <summary>
        /// Изменение выбранной записи
        /// </summary>
        /// <param name="UserID"></param>
        /// <param name="DateCreate"></param>
        /// <param name="CollectionName"></param>
        /// <param name="listModify"></param>
        /// <returns></returns>
        [OperationContract]
        int ModifyIssoIUserCollection(int UserID, long DateCreate, string CollectionName, DBHelper.ListCollection listModify);

        /// <summary>
        /// Удаление выбранной записи
        /// </summary>
        /// <param name="UserID"></param>
        /// <param name="DateCreate"></param>
        /// <param name="CollectionName"></param>
        /// <returns></returns>
        [OperationContract]
        int DeleteIssoIUserCollection(int UserID, long DateCreate, string CollectionName);
        #endregion
    }
}
