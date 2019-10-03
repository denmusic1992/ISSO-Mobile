package com.development.aisisso.autoselect;

import java.util.ArrayList;


public class HttpsIsso {

    public int CIsso;

    public String Length;

    public String DorName;

    public Double WIsso;

    public String Obstacle;

    public String Name;

    public String FullName;

    public Double Latitude;

    public Double Longitude;

    public int ExpertRatingNumeric;

    public String ExpertRating;

    //public IssoRating LastRating;

    public HttpsIsso() {}

    public HttpsIsso(int CIsso, String DorName, String ExpertRating, int ExpertRatingNumeric, String FullName, Double Latitude, Double Longitude,
                     String Length, Double WIsso, String Name, String Obstacle) {
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

