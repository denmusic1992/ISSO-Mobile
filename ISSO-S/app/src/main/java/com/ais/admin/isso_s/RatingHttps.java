package com.ais.admin.isso_s;


public class RatingHttps {


    public static final String NAMESPACE = "http://schemas.datacontract.org/2004/07/Ais7UpdateServer";
    public static final String[] NamesOfAttributes = {"CIsso", "CurrentRating", "Latitude", "Longitude", "Offset", "RatingDate", "RatingDateEdit", "RatingExt", "RatingIsso"};

    public int CIsso;

    public int CurrentRating;

    public double Latitude;

    public double Longitude;

    public long RatingDate;

    public long RatingDateEdit;

    public long Offset;

    public String RatingExt;

    public int RatingIsso;

    public boolean CheckOut;

    public RatingHttps() {}

    public RatingHttps(int CIsso, int CurrentRating, int RatingIsso, long RatingDate, String RatingExt, long RatingDateEdit, long Offset, double  Latitude, double Longitude, boolean CheckOut) {
        this.CIsso = CIsso;
        this.CurrentRating = CurrentRating;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.Offset = Offset;
        this.RatingDate = RatingDate;
        this.RatingDateEdit = RatingDateEdit;
        this.RatingExt = RatingExt;
        this.RatingIsso = RatingIsso;
        this.CheckOut = CheckOut;
    }

    public int getCIsso() {return this.CIsso;}
    public int getCurrentRating() { return this.CurrentRating;}
    public String getLatitude() { return String.valueOf(this.Latitude);}
    public String getLongitude() { return String.valueOf(this.Longitude);}
    public long getOffset() { return this.Offset;}
    public int getRatingIsso() { return this.RatingIsso;}
    public long getRatingDate() { return this.RatingDate;}
    public long getRatingDateEdit() { return this.RatingDateEdit;}
    public String getRatingExt() { return this.RatingExt;}

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
        }
        return null;
    }
}
