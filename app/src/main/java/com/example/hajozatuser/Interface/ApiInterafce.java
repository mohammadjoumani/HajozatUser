package com.example.hajozatuser.Interface;

import com.example.hajozatuser.Model.Facility;
import com.example.hajozatuser.Model.Hotels;
import com.example.hajozatuser.Model.Rules;
import com.example.hajozatuser.Model.SlideItem;
import com.example.hajozatuser.Model.Type;
import com.example.hajozatuser.Model.User;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterafce {

    // for singin
    @FormUrlEncoded
    @POST("UserLogin.php")
    public Call<User> LoginUser(@Field("email") String email, @Field("password") String password);

    // for Register
    @FormUrlEncoded
    @POST("registerUser.php")
    public Call<User> RegiterUser(@Body HashMap<Object, Object> map);

    // for show Hotels
    @GET("getUserHotels.php")
    public Call<List<Hotels>> getHotels(@Header("Authorization") String token);

    // for show Hotels favourite
    @POST("getUserFavourite.php")
    public Call<List<Hotels>> getHotelsFav(@Header("Authorization") String token,@Field("User_Id") int id);

    // for show Hotels history
    @POST("getUserHistory.php")
    public Call<List<Hotels>> getHotelsHis(@Header("Authorization") String token , @Field("User_Id") int id);

    // for show Top star Hotels
    @GET("getUserTopRatedHotels.php")
    public Call<List<Hotels>> getTopHotels(@Header("Authorization") String token);

    // for facility hotel
    @POST("UserHotelDetailsFacility.php")
    public Call<List<Facility>> getHotelDetailsFacility(@Header("Authorization") String token, @Field("Id") String id);

     // for get image for hotel and  put it in imageSlider in activity Detail_hotel
    @FormUrlEncoded
    @POST("UserHotelDetailsImages.php")
    public Call<List<SlideItem>> getHotelDetailsImage(@Header("Authorization") String token, @Field("Id") String id);

    // for get Rules hotels
    @FormUrlEncoded
    @POST("UserHotelDetailsRules.php")
    public Call<List<Rules>> getHotelDetailsRules(@Header("Authorization") String token, @Field("Id") String id);
     // for
    @FormUrlEncoded
    @POST("UserHotelDetailsType.php")
    public Call<List<Type>> getHotelDetailsType(@Header("Authorization") String token, @Field("Id") String id);

    @FormUrlEncoded
    @POST("getUserHotelsSearch.php")
    public Call<List<Hotels>> getHotelSearch(@Header("Authorization") String token, @Field("Name") String name);

    @FormUrlEncoded
    @POST("UserSendReport.php")
    public Call<String> Sendreport(@Header("Authorization") String token, @Field("User_Id") String user_id,@Field("context") String context);
}