using ISSO_I.Interfaces;
using System;
using System.ServiceModel;
using System.ServiceModel.Channels;
using static CommonClassesLibrary.DBHelper;

namespace ISSO_I.GeneratedCode
{
    /// <summary>
    /// Класс клиента для подключения к сервису Isso-I
    /// </summary>
    public class ISSO_I_ClientBase : ClientBase<IAis7MobileIssoIServerCoreXamarin>, IAis7MobileIssoIServerCoreXamarin
    {
        /// <summary>
        /// Конструктор
        /// </summary>
        /// <param name="binding"></param>
        /// <param name="endpointAddress"></param>
        public ISSO_I_ClientBase(Binding binding, EndpointAddress endpointAddress) : base(binding, endpointAddress) { }

        public int compareCountOfRows(Guid id, int clientCount, string tableName)
        {
            return Channel.compareCountOfRows(id, clientCount, tableName);
        }

        public int CreateIssoIUserCollection(ListCollection list)
        {
            return Channel.CreateIssoIUserCollection(list);
        }

        public int DeleteIssoIUserCollection(int UserID, long DateCreate, string CollectionName)
        {
            return Channel.DeleteIssoIUserCollection(UserID, DateCreate, CollectionName);
        }

        public string[][] GetActualInformation(Guid id, string table, int[] CIssos)
        {
            return Channel.GetActualInformation(id, table, CIssos);
        }

        public byte[] GetDataTableStream(Guid id, string table, int[] CIssos, bool is_directory_table)
        {
            return Channel.GetDataTableStream(id, table, CIssos, is_directory_table);
        }

        public byte[][] GetDictionaryTables(Guid id, string[] names)
        {
            return Channel.GetDictionaryTables(id, names);
        }

        public ListCollection GetIssoICurrentCollection(int UserID, long DateCreate, string CollectionName)
        {
            return Channel.GetIssoICurrentCollection(UserID, DateCreate, CollectionName);
        }

        public ListCollection[] GetIssoIUserCollections(string user, string pass)
        {
            return Channel.GetIssoIUserCollections(user, pass);
        }

        public PrimaryInfo getPrimaryKeysValues(Guid id, string table)
        {
            return Channel.getPrimaryKeysValues(id, table);
        }

        public long getTimerFromServer(Guid id)
        {
            return Channel.getTimerFromServer(id);
        }

        public AdvancedInfo HttpsGetAdvancedInfoForIssos(Guid id, int[] issos, string sysTableName, int C_GR_CONSTR, bool isTheLast)
        {
            return Channel.HttpsGetAdvancedInfoForIssos(id, issos, sysTableName, C_GR_CONSTR, isTheLast);
        }

        public MeanInfo HttpsGetMeanInfoForIssos(Guid id, int[] issos, int[] cTypeIssos)
        {
            return Channel.HttpsGetMeanInfoForIssos(id, issos, cTypeIssos);
        }

        public string[] HttpsGetSessionIdForIssoI(string user, string pass, string IssoList)
        {
            return Channel.HttpsGetSessionIdForIssoI(user, pass, IssoList);
        }

        public string[][] HttpsReceiveAllDatabase(Guid id, string tableName, int[] c_issos)
        {
            return Channel.HttpsReceiveAllDatabase(id, tableName, c_issos);
        }

        public string[] HttpsReceiveSQLQuery(Guid id, string[] names)
        {
            return Channel.HttpsReceiveSQLQuery(id, names);
        }

        public int ModifyIssoIUserCollection(int UserID, long DateCreate, string CollectionName, ListCollection listModify)
        {
            return Channel.ModifyIssoIUserCollection(UserID, DateCreate, CollectionName, listModify);
        }

        public OutputInfo NewSynchronyzeTables(Guid id, string table, string[] clientPks, int[] cIssos)
        {
            return Channel.NewSynchronyzeTables(id, table, clientPks, cIssos);
        }

        public string[][] synchronyzeTables(Guid id, string table, string[] clientPks, int[] cIssos)
        {
            return Channel.synchronyzeTables(id, table, clientPks, cIssos);
        }

        public UploadPhotoForIsso[] UploadPhotoInfoForIssos(int c_isso)
        {
            return Channel.UploadPhotoInfoForIssos(c_isso);
        }

        public UploadSchemeForIsso[] UploadSchemeForIssos(int c_isso)
        {
            return Channel.UploadSchemeForIssos(c_isso);
        }
    }
}
