package com.development.aisisso.isso_r;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.ArrayList;
import java.util.Hashtable;


public class HttpsIsso implements KvmSerializable {

    public int CIsso;

    public String Length;

    public String DorName;

    public int WIsso;

    public String Obstacle;

    public String Name;

    public String FullName;

    public Double Latitude;

    public Double Longitude;

    public int ExpertRatingNumeric;

    public String ExpertRating;


    public HttpsIsso() {}

    public HttpsIsso(int CIsso, String DorName, String ExpertRating, int ExpertRatingNumeric, String FullName, Double Latitude, Double Longitude,
                     String Length, int WIsso, String Name, String Obstacle) {
        this.CIsso = CIsso;
        this.DorName = DorName;
        this.ExpertRating = ExpertRating;
        this.ExpertRatingNumeric = ExpertRatingNumeric;
        this.FullName = FullName;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.Length = Length;
        this.WIsso = WIsso;
        this.Name = Name;
        this.Obstacle = Obstacle;
    }

    @Override
    public Object getProperty(int i) {
        switch (i) {
            case 0:
                return this.CIsso;
            case 1:
                return this.DorName;
            case 2:
                return this.ExpertRating;
            case 3:
                return this.ExpertRatingNumeric;
            case 4:
                return this.FullName;
            case 5:
                return this.Latitude;
            case 6:
                return this.Longitude;
            case 7:
                return this.Length;
            case 8:
                return this.WIsso;
            case 9:
                return this.Name;
            case 10:
                return this.Obstacle;
        }
        return null;
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
                this.DorName = o.toString();
                break;
            case 2:
                this.ExpertRating = o.toString();
                break;
            case 3:
                this.ExpertRatingNumeric = Integer.parseInt(o.toString());
                break;
            case 4:
                this.FullName = o.toString();
                break;
            case 5:
                this.Latitude = Double.parseDouble(o.toString());
                break;
            case 6:
                this.Longitude = Double.parseDouble(o.toString());
                break;
            case 7:
                this.Length = o.toString();
                break;
            case 8:
                this.WIsso = Integer.parseInt(o.toString());
                break;
            case 9:
                this.Name = o.toString();
                break;
            case 10:
                this.Obstacle = o.toString();
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
                propertyInfo.name = "DorName";
                break;
            case 2:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "ExpertRating";
                break;
            case 3:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "ExpertRatingNumeric";
                break;
            case 4:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "FullName";
                break;
            case 5:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "Latitude";
                break;
            case 6:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "Longitude";
                break;
            case 7:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "Length";
                break;
            case 8:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "WIsso";
                break;
            case 9:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "Name";
                break;
            case 10:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "Obstacle";
                break;
            default: break;
        }
    }
}

class ArrayVector {
    public ArrayList<Double> distances;
    public ArrayList<Integer> indexes;

    ArrayVector() {
        distances = new ArrayList<>();
        indexes = new ArrayList<>();
    }

    public double getDistance (int index) {
        return distances.get(index);
    }
    public int getIndex (int index) {
        return indexes.get(index);
    }
}

class Vector2D {
    public double x = 0.0, y = 0.0;

    Vector2D(double _x, double _y)
    {
        x = _x;
        y = _y;
    }

    Vector2D minus(Vector2D right) { return new Vector2D(x - right.x, y - right.y); }

    double dotProduct(Vector2D right)
    {
        return  x*right.x + y*right.y;
    }
    double lengthVector(Vector2D second) { return Math.sqrt(Math.pow(x - second.x, 2) + Math.pow(y - second.y, 2));}
}

