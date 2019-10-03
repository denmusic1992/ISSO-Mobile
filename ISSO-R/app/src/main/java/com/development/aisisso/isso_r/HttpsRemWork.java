package com.development.aisisso.isso_r;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;


public class HttpsRemWork implements KvmSerializable {

    public static final String NAMESPACE = "http://schemas.datacontract.org/2004/07/Ais7UpdateServer";
    public static final String[] NamesOfAttributes = {"CIsso", "CRem", "Photo", "PhotoPath", "RatingRem", "RatingRemExt", "RemMonth"};

    public int CIsso;

    public int CRem;

    public String PhotoPath;

    public String RatingRemExt;

    public int RatingRem;

    public long RemMonth;

    public String Photo;



    public HttpsRemWork() {}

    public HttpsRemWork(int CIsso, int CRem, String PhotoPath, long RemMonth, String RatingRemExt, int RatingRem, String Photo) {
        this.CIsso = CIsso;
        this.CRem = CRem;
        this.Photo = Photo;
        this.PhotoPath = PhotoPath;
        this.RatingRemExt = RatingRemExt;
        this.RatingRem = RatingRem;
        this.RemMonth = RemMonth;
    }

    public int getCIsso() { return this.CIsso; }
    public int getCRem() { return this.CRem; }
    public String getPhoto() { return this.Photo; }
    public String getPhotoPath() {return this.PhotoPath; }
    public int getRatingRem() { return this.RatingRem; }
    public String getRatingRemExt() { return this.RatingRemExt; }
    public long getRemMonth() { return this.RemMonth; }

    /** Функция, возвращающая значение имени аттрибута */
    public String getAttributeName(int i) {
        switch (i) {
            case 0:
                return NamesOfAttributes[i];
            case 1:
                return NamesOfAttributes[i];
            case 2:
                return NamesOfAttributes[i];
            case 3:
                return NamesOfAttributes[i];
            case 4:
                return NamesOfAttributes[i];
            case 5:
                return NamesOfAttributes[i];
            case 6:
                return NamesOfAttributes[i];
        }
        return null;
    }

    public Object getAttributeType(int i) {
        switch (i) {
            case 0:
                return PropertyInfo.INTEGER_CLASS;
            case 1:
                return PropertyInfo.INTEGER_CLASS;
            case 2:
                return PropertyInfo.STRING_CLASS;
            case 3:
                return PropertyInfo.STRING_CLASS;
            case 4:
                return PropertyInfo.INTEGER_CLASS;
            case 5:
                return PropertyInfo.STRING_CLASS;
            case 6:
                return PropertyInfo.LONG_CLASS;
        }
        return null;
    }

    @Override
    public Object getProperty(int i) {
        Object property = null;
        switch (i) {
            case 0:
                property = this.CIsso;
                break;
            case 1:
                property = this.CRem;
                break;
            case 2:
                property = this.Photo;
                break;
            case 3:
                property = this.PhotoPath;
                break;
            case 4:
                property = this.RatingRem;
                break;
            case 5:
                property = this.RatingRemExt;
                break;
            case 6:
                property = this.RemMonth;
                break;
        }
        return property;
    }

    @Override
    public int getPropertyCount() {
        return 7;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i) {
            case 0:
                this.CIsso = Integer.parseInt(o.toString());
                break;
            case 1:
                this.CRem = Integer.parseInt(o.toString());
                break;
            case 2:
                this.Photo = o.toString();
                break;
            case 3:
                this.PhotoPath = o.toString();
                break;
            case 4:
                this.RatingRem = Integer.parseInt(o.toString());
                break;
            case 5:
                this.RatingRemExt = o.toString();
                break;
            case 6:
                this.RemMonth= Long.parseLong(o.toString());
                break;
        }
    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch (i) {
            case 0:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "CIsso";
                break;
            case 1:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "CRem";
                break;
            case 2:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "Photo";
                break;
            case 3:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "PhotoPath";
                break;
            case 4:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "RatingRem";
                break;
            case 5:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "RatingRemExt";
                break;
            case 6:
                propertyInfo.type = PropertyInfo.LONG_CLASS;
                propertyInfo.name = "RemMonth";
                break;
            default: break;
        }
    }
}


class HttpsRemCur implements KvmSerializable {

    public int CIsso;

    public double Latitude;

    public double Longitude;

    public String NPerformer;

    public long Offset;

    public long RatingDateRem;

    public long RatingDateRemEdit;

    public long RemMonth;



    public static final String NAMESPACE = "http://schemas.datacontract.org/2004/07/Ais7UpdateServer";
    public static final String[] NamesOfAttributes = { "CIsso", "Latitude", "Longitude",
            "NPerformer", "Offset", "RatingDateRem", "RatingDateRemEdit", "RemMonth"};

    public HttpsRemCur() {}

    public HttpsRemCur( int CIsso, double Latitude, double Longitude,
                        String NPerformer, long Offset, long RatingDateRem, long RatingDateRemEdit, long RemMonth) {
        this.CIsso = CIsso;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.NPerformer = NPerformer;
        this.Offset = Offset;
        this.RatingDateRem = RatingDateRem;
        this.RatingDateRemEdit = RatingDateRemEdit;
        this.RemMonth = RemMonth;
    }


    public int getCIsso() { return  this.CIsso; }
    public String getLatitude() { return String.valueOf(this.Latitude); }
    public String getLongitude() { return String.valueOf(this.Longitude); }
    public String getNPerformer() { return  this.NPerformer; }
    public long getOffset() { return this.Offset; }
    public long getRatingDateRem() { return this.RatingDateRem; }
    public long getRatingDateRemEdit() { return this.RatingDateRemEdit; }
    public long getRemMonth() { return this.RemMonth; }


    /** Функция, возвращающая значение имени аттрибута */
    public String getAttributeName(int i) {
        switch (i) {
            case 0:
                return NamesOfAttributes[i];
            case 1:
                return NamesOfAttributes[i];
            case 2:
                return NamesOfAttributes[i];
            case 3:
                return NamesOfAttributes[i];
            case 4:
                return NamesOfAttributes[i];
            case 5:
                return NamesOfAttributes[i];
            case 6:
                return NamesOfAttributes[i];
            case 7:
                return NamesOfAttributes[i];
        }
        return null;
    }


    public Object getAttributeType(int i) {
        switch (i) {
            case 0:
                return PropertyInfo.INTEGER_CLASS;
            case 1:
                return PropertyInfo.STRING_CLASS;
            case 2:
                return PropertyInfo.STRING_CLASS;
            case 3:
                return PropertyInfo.STRING_CLASS;
            case 4:
                return PropertyInfo.LONG_CLASS;
            case 5:
                return PropertyInfo.LONG_CLASS;
            case 6:
                return PropertyInfo.LONG_CLASS;
            case 7:
                return PropertyInfo.LONG_CLASS;
        }
        return null;
    }

    @Override
    public Object getProperty(int i) {
        Object property = null;
        switch (i) {
            case 0:
                property = this.CIsso;
                break;
            case 1:
                property = this.Latitude;
                break;
            case 2:
                property = this.Longitude;
                break;
            case 3:
                property = this.NPerformer;
                break;
            case 4:
                property = this.Offset;
                break;
            case 5:
                property = this.RatingDateRem;
                break;
            case 6:
                property = this.RatingDateRemEdit;
                break;
            case 7:
                property = this.RemMonth;
                break;
        }
        return property;
    }

    @Override
    public int getPropertyCount() {
        return 9;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i) {
            case 0:
                this.CIsso = Integer.parseInt(o.toString());
                break;
            case 1:
                this.Latitude = Double.parseDouble(o.toString());
                break;
            case 2:
                this.Longitude = Double.parseDouble(o.toString());
                break;
            case 3:
                this.NPerformer= o.toString();
                break;
            case 4:
                this.Offset= Long.parseLong(o.toString());
                break;
            case 5:
                this.RatingDateRem= Long.parseLong(o.toString());
                break;
            case 6:
                this.RatingDateRemEdit= Long.parseLong(o.toString());
                break;
            case 7:
                this.RemMonth= Integer.parseInt(o.toString());
                break;
        }
    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch (i) {
            case 0:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "CIsso";
                break;
            case 1:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "Latitude";
                break;
            case 2:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "Longitude";
                break;
            case 3:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "NPerformer";
                break;
            case 4:
                propertyInfo.type = PropertyInfo.LONG_CLASS;
                propertyInfo.name = "Offset";
                break;
            case 5:
                propertyInfo.type = PropertyInfo.LONG_CLASS;
                propertyInfo.name = "RatingDateRem";
                break;
            case 6:
                propertyInfo.type = PropertyInfo.LONG_CLASS;
                propertyInfo.name = "RatingDateRemEdit";
                break;
            case 7:
                propertyInfo.type = PropertyInfo.LONG_CLASS;
                propertyInfo.name = "RemMonth";
                break;
            default: break;
        }
    }
}

class HttpsRemInfo implements KvmSerializable {

    public static final String NAMESPACE = "http://schemas.datacontract.org/2004/07/Ais7UpdateServer";
    public static final String[] NamesOfAttributes = { "CIsso", "n1", "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9", "n10", "n11", "n12", "nRem" };

    public int CIsso;

    public int CRem;

    public int n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12;

    public String nRem;

    public HttpsRemInfo() {}

    public HttpsRemInfo(int CIsso, int CRem, int n1, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, int n10, int n11, int n12, String nRem) {
        this.CIsso = CIsso;
        this.CRem = CRem;
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
        this.n4 = n4;
        this.n5 = n5;
        this.n6 = n6;
        this.n7 = n7;
        this.n8 = n8;
        this.n9 = n9;
        this.n10 = n10;
        this.n11 = n11;
        this.n12 = n12;
        this.nRem = nRem;
    }

    public int getCIsso() { return this.CIsso; }
    public int getCRem() { return this.CRem; }
    public int getN1() { return this.n1; }
    public int getN2() { return this.n2; }
    public int getN3() { return this.n3; }
    public int getN4() { return this.n4; }
    public int getN5() { return this.n5; }
    public int getN6() { return this.n6; }
    public int getN7() { return this.n7; }
    public int getN8() { return this.n8; }
    public int getN9() { return this.n9; }
    public int getN10() { return this.n10; }
    public int getN11() { return this.n11; }
    public int getN12() { return this.n12; }
    public String getnRem() { return this.nRem; }

    /** Функция, возвращающая значение имени аттрибута */
    public String getAttributeName(int i) {
        switch (i) {
            case 0:
                return NamesOfAttributes[i];
            case 1:
                return NamesOfAttributes[i];
            case 2:
                return NamesOfAttributes[i];
            case 3:
                return NamesOfAttributes[i];
            case 4:
                return NamesOfAttributes[i];
            case 5:
                return NamesOfAttributes[i];
            case 6:
                return NamesOfAttributes[i];
            case 7:
                return NamesOfAttributes[i];
            case 8:
                return NamesOfAttributes[i];
            case 9:
                return NamesOfAttributes[i];
            case 10:
                return NamesOfAttributes[i];
            case 11:
                return NamesOfAttributes[i];
            case 12:
                return NamesOfAttributes[i];
            case 13:
                return NamesOfAttributes[i];
            case 14:
                return NamesOfAttributes[i];
        }
        return null;
    }

    public Object getAttributeType(int i) {
        switch (i) {
            case 0:
                return PropertyInfo.INTEGER_CLASS;
            case 1:
                return PropertyInfo.INTEGER_CLASS;
            case 2:
                return PropertyInfo.INTEGER_CLASS;
            case 3:
                return PropertyInfo.INTEGER_CLASS;
            case 4:
                return PropertyInfo.INTEGER_CLASS;
            case 5:
                return PropertyInfo.INTEGER_CLASS;
            case 6:
                return PropertyInfo.INTEGER_CLASS;
            case 7:
                return PropertyInfo.INTEGER_CLASS;
            case 8:
                return PropertyInfo.INTEGER_CLASS;
            case 9:
                return PropertyInfo.INTEGER_CLASS;
            case 10:
                return PropertyInfo.INTEGER_CLASS;
            case 11:
                return PropertyInfo.INTEGER_CLASS;
            case 12:
                return PropertyInfo.INTEGER_CLASS;
            case 13:
                return PropertyInfo.INTEGER_CLASS;
            case 14:
                return PropertyInfo.STRING_CLASS;
        }
        return null;
    }

    @Override
    public Object getProperty(int i) {
        Object property = null;
        switch (i) {
            case 0:
                property = this.CIsso;
                break;
            case 1:
                property = this.CRem;
                break;
            case 2:
                property = this.n1;
                break;
            case 3:
                property = this.n2;
                break;
            case 4:
                property = this.n3;
                break;
            case 5:
                property = this.n4;
                break;
            case 6:
                property = this.n5;
                break;
            case 7:
                property = this.n6;
                break;
            case 8:
                property = this.n7;
                break;
            case 9:
                property = this.n8;
                break;
            case 10:
                property = this.n9;
                break;
            case 11:
                property = this.n10;
                break;
            case 12:
                property = this.n11;
                break;
            case 13:
                property = this.n12;
                break;
            case 14:
                property = this.nRem;
                break;
        }
        return property;
    }

    @Override
    public int getPropertyCount() {
        return 15;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i) {
            case 0:
                this.CIsso = Integer.parseInt(o.toString());
                break;
            case 1:
                this.CRem = Integer.parseInt(o.toString());
                break;
            case 2:
                this.n1 = Integer.parseInt(o.toString());
                break;
            case 3:
                this.n2 = Integer.parseInt(o.toString());
                break;
            case 4:
                this.n3 = Integer.parseInt(o.toString());
                break;
            case 5:
                this.n4 = Integer.parseInt(o.toString());
                break;
            case 6:
                this.n5 = Integer.parseInt(o.toString());
                break;
            case 7:
                this.n6 = Integer.parseInt(o.toString());
                break;
            case 8:
                this.n7 = Integer.parseInt(o.toString());
                break;
            case 9:
                this.n8 = Integer.parseInt(o.toString());
                break;
            case 10:
                this.n9 = Integer.parseInt(o.toString());
                break;
            case 11:
                this.n10 = Integer.parseInt(o.toString());
                break;
            case 12:
                this.n11 = Integer.parseInt(o.toString());
                break;
            case 13:
                this.n12 = Integer.parseInt(o.toString());
                break;
            case 14:
                this.nRem = o.toString();
                break;
        }
    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch (i) {
            case 0:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "CIsso";
                break;
            case 1:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "CRem";
                break;
            case 2:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "n1";
                break;
            case 3:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "n2";
                break;
            case 4:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "n3";
                break;
            case 5:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "n4";
                break;
            case 6:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "n5";
                break;
            case 7:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "n6";
                break;
            case 8:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "n7";
                break;
            case 9:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "n8";
                break;
            case 10:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "n9";
                break;
            case 11:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "n10";
                break;
            case 12:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "n11";
                break;
            case 13:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "n12";
                break;
            case 14:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "nRem";
                break;
            default: break;
        }
    }
}

class Photo implements KvmSerializable {

    public static final String NAMESPACE = "http://schemas.datacontract.org/2004/07/Ais7UpdateServer";
    public static final String[] NamesOfAttributes = { "PhotoName"};

    public String PhotoName;

    public Photo() {}

    public Photo(String PhotoName) {
        this.PhotoName = PhotoName;
    }

    public String getPhotoName() { return this.PhotoName; }

    @Override
    public Object getProperty(int arg0) {
        return this.PhotoName;
    }

    public String getAttributeName() {
        return NamesOfAttributes[0];
    }

    public Object getAttributeType() {
        return PropertyInfo.STRING_CLASS;
    }

    @Override
    public int getPropertyCount() {
        return 1;
    }

    @Override
    public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
        arg2.name = "PhotoName";
        arg2.type = PropertyInfo.STRING_CLASS;
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
        this.PhotoName = arg1.toString();
    }

}

class C_REMS implements KvmSerializable {

    public static final String NAMESPACE = "http://schemas.datacontract.org/2004/07/Ais7UpdateServer";
    public static final String[] NamesOfAttributes = {"c_rem"};

    public int c_rem;

    public C_REMS() {
    }

    public C_REMS(int c_rem) {
        this.c_rem = c_rem;
    }

    public int getC_rem() {
        return this.c_rem;
    }

    @Override
    public Object getProperty(int arg0) {
        return this.c_rem;
    }

    public String getAttributeName() {
        return NamesOfAttributes[0];
    }

    public Object getAttributeType() {
        return PropertyInfo.INTEGER_CLASS;
    }

    @Override
    public int getPropertyCount() {
        return 1;
    }

    @Override
    public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
        arg2.name = "c_rem";
        arg2.type = PropertyInfo.INTEGER_CLASS;
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
        this.c_rem = Integer.parseInt(arg1.toString());
    }
}
