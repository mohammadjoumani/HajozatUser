package com.example.hajozatuser.Model;

import androidx.annotation.Nullable;

public class User {

    @Nullable
    public int Id;
    @Nullable
    public String Name;
    @Nullable
    public String Email;
    @Nullable
    public String PhoneNumber;
    @Nullable
    public String Password;
    @Nullable
    public String Image_Path;
    @Nullable
    public String Date;
    @Nullable
    public int Verified;
    @Nullable
    public String Token;

    public User(int id, @Nullable String name, @Nullable String email, @Nullable String phoneNumber, @Nullable String password, @Nullable String image_Path, @Nullable String date, int verified, @Nullable String token) {
        Id = id;
        Name = name;
        Email = email;
        PhoneNumber = phoneNumber;
        Password = password;
        Image_Path = image_Path;
        Date = date;
        Verified = verified;
        Token = token;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public int getVerified() {
        return Verified;
    }

    public void setVerified(int verified) {
        Verified = verified;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Nullable
    public String getImage_Path() {
        return Image_Path;
    }

    public void setImage_Path(@Nullable String image_Path) {
        Image_Path = image_Path;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

}
