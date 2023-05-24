package com.example.wordle;

import com.google.android.gms.maps.model.LatLng;

import java.text.DecimalFormat;

public class Country {
    public String Name;
    public int size;
    public int population;
    public int imgUrl;
    public int flagUrl;
    public LatLng coordinates;
    DecimalFormat decFormat = new DecimalFormat("###,###");

    public Country(String name,int size,int imgUrl,int population,int flagUrl,LatLng coordinates)
    {
        this.Name=name;
        this.size=size;
        this.imgUrl=imgUrl;
        this.population=population;
        this.flagUrl=flagUrl;
        this.coordinates=coordinates;
    }
    public String GetArea()
    {
        String area=decFormat.format(this.size);
        return area;
    }

    public String GetPopulation()
    {
        String pop=decFormat.format(this.population);
        return pop;
    }
}
