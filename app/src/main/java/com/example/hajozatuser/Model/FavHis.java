package com.example.hajozatuser.Model;

public class FavHis {

    private String Name;
    private int Id;
    private String hotel_name;
    private float Star;
    private String city_name;
    private String Image_Path;
    private String count_rooms;

    public FavHis(String name, int id, String hotel_name, float star, String city_name, String image_Path, String count_rooms) {
        Name = name;
        Id = id;
        this.hotel_name = hotel_name;
        Star = star;
        this.city_name = city_name;
        Image_Path = image_Path;
        this.count_rooms = count_rooms;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
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
