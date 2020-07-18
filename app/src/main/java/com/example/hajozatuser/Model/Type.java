package com.example.hajozatuser.Model;

public class Type {

    public String city;
    public String hotel;
    public int Star;
    public double Lat;
    public double Lng;
    public String type;
    public String host;

    public Type(String city, String hotel, int star, double lat, double lng, String type, String host) {
        this.city = city;
        this.hotel = hotel;
        Star = star;
        Lat = lat;
        Lng = lng;
        this.type = type;
        this.host = host;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public int getStar() {
        return Star;
    }

    public void setStar(int star) {
        Star = star;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public double getLng() {
        return Lng;
    }

    public void setLng(double lng) {
        Lng = lng;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
