package com.development.aisisso.isso_i;

import java.io.Serializable;

class HttpsTableNames {
    public int C_GR_CONSTR;
    public String TableSysName;
    public String TableName;
    public int TableParentID;
    public int TableView;
    public boolean IsTableExportedWhole;
}

class UserCollection implements Serializable{
    public int UserID;
    public long DateCreate;
    public String CollectionName;
    public long DateModify;
    public String IssoList;
    public String Description;
}


class HttpsTableAttributes {
    public int ID;
    public int C_GR_CONSTR;
    public String TableSysName;
    public String DataType;
    public String AttributeName;
    public boolean isBlob;
    public String Category;
    public int VisibleInGrid;
}


class HttpsTableValues {
    public int AttributeID;
    public int C_ISSO;
    public String TableValue;
}


class HttpsTableDelegate {
    public int C_TYPEISSO;
    public int C_GR_CONSTR;
}


class ALLInfo {
    public HttpsTableNames[] tableNames;
    public HttpsTableAttributes[] tableAttributes;
    public HttpsTableValues[] tableValues;
    public HttpsTableDelegate[] tableDelegate;
}

class MeanInfo {
    public HttpsTableNames[] tableNames;
    public HttpsTableDelegate[] tableDelegate;
}

class AdvancedInfo {
    public HttpsTableAttributes[] tableAttributes;
    public Object[][] tableValues;
}

class PrimaryInfo {
    public String[] pks;
    public String[] types;
}

class UploadPhotoForIsso {
    public int C_ISSO;
    public int N;
    public String Comments;
    public String Photo;
    public String PhotoDate;
}

class UploadSchemeForIsso {
    public int C_ISSO;
    public int N;
    public String Comments;
    public String Scheme;
    public String Thumbnail;
    public String SchemeDate;
}

