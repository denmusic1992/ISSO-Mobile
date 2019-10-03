using System;
using System.ServiceModel;
using System.ServiceModel.Channels;
using ISSO_I.Interfaces;
using static CommonClassesLibrary.DBHelper;

namespace ISSO_I.GeneratedCode
{
    /// <summary>
    /// Класс для подключения к сервису Isso-I для iOS
    /// </summary>
    public class ISSO_I_ClientBaseIOS : ISSO_I_ClientBase
    {
        public ISSO_I_ClientBaseIOS(Binding binding, EndpointAddress endpointAddress) : base(binding, endpointAddress) { }

        protected override IAis7MobileIssoIServerCoreXamarin CreateChannel()
        {
            return new ISSO_I_ClientBaseChannel(this);
        }

        /// <summary>
        /// Класс, реализующий интерфейс канала для связи
        /// </summary>
        private class ISSO_I_ClientBaseChannel : ChannelBase<IAis7MobileIssoIServerCoreXamarin>, IAis7MobileIssoIServerCoreXamarin
        {

            public ISSO_I_ClientBaseChannel(ISSO_I_ClientBaseIOS client) : base(client) { }

            public int compareCountOfRows(Guid id, int clientCount, string tableName)
            {
                return (int)Invoke("compareCountOfRows", new object[] { id, clientCount, tableName });
            }

            public int CreateIssoIUserCollection(ListCollection list)
            {
                return (int)Invoke("CreateIssoIUserCollection", new object[] { list });
            }

            public int DeleteIssoIUserCollection(int UserID, long DateCreate, string CollectionName)
            {
                return (int)Invoke("DeleteIssoIUserCollection", new object[] { UserID, DateCreate, CollectionName });
            }

            public string[][] GetActualInformation(Guid id, string table, int[] CIssos)
            {
                return (string[][])Invoke("GetActualInformation", new object[] { id, table, CIssos });
            }

            public byte[] GetDataTableStream(Guid id, string table, int[] CIssos, bool is_directory_table)
            {
                return (byte[])Invoke("GetDataTableStream", new object[] { id, table, CIssos, is_directory_table });
            }

            public byte[][] GetDictionaryTables(Guid id, string[] names)
            {
                return (byte[][])Invoke("GetDictionaryTables", new object[] { id, names });
            }

            public ListCollection GetIssoICurrentCollection(int UserID, long DateCreate, string CollectionName)
            {
                return (ListCollection)Invoke("GetIssoICurrentCollection", new object[] { UserID, DateCreate, CollectionName });
            }

            public ListCollection[] GetIssoIUserCollections(string user, string pass)
            {
                return (ListCollection[])Invoke("GetIssoIUserCollections", new object[] { user, pass });
            }

            public PrimaryInfo getPrimaryKeysValues(Guid id, string table)
            {
                return (PrimaryInfo)Invoke("getPrimaryKeysValues", new object[] { id, table });
            }

            public long getTimerFromServer(Guid id)
            {
                return (long)Invoke("getTimerFromServer", new object[] { id });
            }

            public AdvancedInfo HttpsGetAdvancedInfoForIssos(Guid id, int[] issos, string sysTableName, int C_GR_CONSTR, bool isTheLast)
            {
                return (AdvancedInfo)Invoke("HttpsGetAdvancedInfoForIssos", new object[] { id, issos, sysTableName, C_GR_CONSTR, isTheLast });
            }

            public MeanInfo HttpsGetMeanInfoForIssos(Guid id, int[] issos, int[] cTypeIssos)
            {
                return (MeanInfo)Invoke("HttpsGetMeanInfoForIssos", new object[] { id, issos, cTypeIssos });
            }

            public string[] HttpsGetSessionIdForIssoI(string user, string pass, string IssoList)
            {
                return (string[])Invoke("HttpsGetSessionIdForIssoI", new object[] { user, pass, IssoList });
            }

            public string[][] HttpsReceiveAllDatabase(Guid id, string tableName, int[] c_issos)
            {
                return (string[][])Invoke("HttpsReceiveAllDatabase", new object[] { id, tableName, c_issos });
            }

            public string[] HttpsReceiveSQLQuery(Guid id, string[] names)
            {
                return (string[])Invoke("HttpsReceiveSQLQuery", new object[] { id, names });
            }

            public int ModifyIssoIUserCollection(int UserID, long DateCreate, string CollectionName, ListCollection listModify)
            {
                return (int)Invoke("ModifyIssoIUserCollection", new object[] { UserID, DateCreate, CollectionName, listModify });
            }

            public OutputInfo NewSynchronyzeTables(Guid id, string table, string[] clientPks, int[] cIssos)
            {
                return (OutputInfo)Invoke("NewSynchronyzeTables", new object[] { id, clientPks, cIssos });
            }

            public string[][] synchronyzeTables(Guid id, string table, string[] clientPks, int[] cIssos)
            {
                return (string[][])Invoke("synchronyzeTables", new object[] { id, table, clientPks, cIssos });
            }

            public UploadPhotoForIsso[] UploadPhotoInfoForIssos(int c_isso)
            {
                return (UploadPhotoForIsso[])Invoke("UploadPhotoInfoForIssos", new object[] { c_isso });
            }

            public UploadSchemeForIsso[] UploadSchemeForIssos(int c_isso)
            {
                return (UploadSchemeForIsso[])Invoke("UploadSchemeForIssos", new object[] { c_isso });
            }
        }
    }
}
