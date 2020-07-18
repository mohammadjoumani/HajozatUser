package com.example.hajozatuser.Model;

public class Hotels {
    // dfault city
    public String city_name;
    // dfault name hotel
    public String hotel_name;
    // dfault Rating
    public float Star;
    // dfault id for hotel
    public String Id;
    // dfault image
    public String Image_Path;
    // dfault Number of Rooms
    public String count_rooms;

    public Hotels(String city_name, String hotel_name, float star, String id, String image_Path, String count_rooms) {
        this.city_name = city_name;
        this.hotel_name = hotel_name;
        Star = star;
        Id = id;
        Image_Path = image_Path;
        this.count_rooms = count_rooms;
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

    public String getId() {
        return Id;
    }

    public void setId(String id) {
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
}
