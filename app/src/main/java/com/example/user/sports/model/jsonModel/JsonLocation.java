package com.example.user.sports.model.jsonModel;

import java.io.Serializable;

/**
 * Created by Allen on 2017/10/12.
 */
public class JsonLocation implements Serializable{

    private Double  longitude;
    private Double  latitude;


    public JsonLocation( Double latitude,Double longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "JsonLocation{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
