package com.example.hajozatuser.Model;

public class Hotels {

    // dfault city
    public String city_name;
    // dfault name hotel
    public String hotel_name;
    // dfault Rating
    public float Star;
    // dfault id for hotel
    public int Id;
    // dfault image
    public String Image_Path;
    // dfault Number of Rooms
    public String count_rooms;
    public String Check_in;
    public String Check_out;
    public double Lat;
    public double Lng;

    public Hotels(String city_name, String hotel_name, float star, int id, String image_Path, String count_rooms, String check_in, String check_out, double lat, double lng) {
        this.city_name = city_name;
        this.hotel_name = hotel_name;
        Star = star;
        Id = id;
        Image_Path = image_Path;
        this.count_rooms = count_rooms;
        Check_in = check_in;
        Check_out = check_out;
        Lat = lat;
        Lng = lng;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public float getStar() {
        return Star;
    }

    public void setStar(float star) {
        Star = star;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getImage_Path() {
        return Image_Path;
    }

    public void setImage_Path(String image_Path) {
        Image_Path = image_Path;
    }

    public String getCount_rooms() {
        return count_rooms;
    }

    public void setCount_rooms(String count_rooms) {
        this.count_rooms = count_rooms;
    }

    public String getCheck_in() {
        return Check_in;
    }

    public void setCheck_in(String check_in) {
        Check_in = check_in;
    }

    public String getCheck_out() {
        return Check_out;
    }

    public void setCheck_out(String check_out) {
        Check_out = check_out;
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
}
