package com.example.hajozatuser.Model;

public class price {

    public String Type;

    public String Price;

    public price(String type, String price) {
        Type = type;
        Price = price;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
