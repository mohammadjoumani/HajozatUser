package com.example.hajozatuser.Interface;

import com.example.hajozatuser.Model.Facility;
import com.example.hajozatuser.Model.FavHis;
import com.example.hajozatuser.Model.Hotels;
import com.example.hajozatuser.Model.Rules;
import com.example.hajozatuser.Model.SlideItem;
import com.example.hajozatuser.Model.Type;
import com.example.hajozatuser.Model.User;
import com.example.hajozatuser.Model.Verify;
import com.example.hajozatuser.Model.comments;
import com.example.hajozatuser.Model.price;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterafce {

    // for singin
    @FormUrlEncoded
    @POST("UserLogin.php")
    public Call<User> LoginUser(@Field("email") String email,
                                @Field("password") String password);

    // for Register
    @FormUrlEncoded
    @POST("registerUser.php")
    public Call<User> RegiterUser(@Header("Authorization") String token,
                                  @Field("Name") String name,
                                  @Field("Email") String email,
                                  @Field("Phone_Number") String phone,
                                  @Field("Password") String password,
                                  @Field("Secure_code") String secure_code);

    // for Register
    @FormUrlEncoded
    @POST("UserVerify.php")
    public Call<Verify> verify(@Header("Authorization") String token,
                               @Field("Code") String code,
                               @Field("User_Id") int User_id);


    // for show HotelsTop
    @GET("getUserTopRatedHotels.php")
    public Call<List<Hotels>> getHotelsTop(@Header("Authorization") String token);

    // for show Hotels
    @GET("getUserHotels.php")
    public Call<List<Hotels>> getHotels(@Header("Authorization") String token);

    // for show Hotels favourite
    @FormUrlEncoded
    @POST("getUserFavourite.php")
    public Call<List<FavHis>> getHotelsFav(@Header("Authorization") String token,
                                           @Field("User_Id") int User_Id);


    // for show Hotels history
    @FormUrlEncoded
    @POST("getUserHistory.php")
    public Call<List<FavHis>> getHotelsHis(@Header("Authorization") String token,
                                           @Field("User_Id") int User_Id);


    // for show Top star Hotels
    @GET("getUserTopRatedHotels.php")
    public Call<List<Hotels>> getTopHotels(@Header("Authorization") String token);


    // for get image for hotel and  put it in imageSlider in activity Detail_hotel
    @FormUrlEncoded
    @POST("UserHotelDetailsImages.php")
    public Call<List<SlideItem>> getHotelDetailsImage(@Header("Authorization") String token,
                                                      @Field("Id") int id);


    // for facility hotel
    @FormUrlEncoded
    @POST("UserHotelDetailsFacility.php")
    public Call<List<Facility>> getHotelDetailsFacility(@Header("Authorization") String token,
                                                        @Field("Id") int id);

    // for get Rules hotels
    @FormUrlEncoded
    @POST("UserHotelDetailsRules.php")
    public Call<List<Rules>> getHotelDetailsRules(@Header("Authorization") String token,
                                                  @Field("Id") int id);

    // for
    @FormUrlEncoded
    @POST("UserHotelDetailsType.php")
    public Call<List<Type>> getHotelDetailsType(@Header("Authorization") String token,
                                                @Field("Id") String id);


    @FormUrlEncoded
    @POST("getUserHotelsSearch.php")
    public Call<List<Hotels>> getHotelSearch(@Header("Authorization") String token,
                                             @Field("Name") String name);


    @FormUrlEncoded
    @POST("UserSendReport.php")
    public Call<String> Sendreport(@Header("Authorization") String token,
                                   @Field("User_Id") String user_id,
                                   @Field("context") String context);


    @FormUrlEncoded
    @POST("updateUserProfile.php")
    public Call<User> uploadprofile(@Header("Authorization") String token,
                                    @Field("Id") int user_id,
                                    @Field("Name") String name,
                                    @Field("Email") String email,
                                    @Field("Password") String passowrd,
                                    @Field("PhoneNumber") String phone,
                                    @Field("Secure_Code") String code);

    //for show comments for hotel selected
    @FormUrlEncoded
    @POST("UserHotelDetailsComments.php")
    public Call<List<comments>> showcomments(@Header("Authorization") String token,
                                             @Field("Id") int id);

    @FormUrlEncoded
    @POST("bookingRoom.php")
    public Call<String> bookingRoom(@Header("Authorization") String token,
                                    @Field("User_Id") int id_user,
                                    @Field("Room_Id") int id_room,
                                    @Field("Check_In") String in,
                                    @Field("Check_Out") String out,
                                    @Field("Cost") double cost);

    @FormUrlEncoded
    @POST("addFavourite.php")
    public Call<String> addFavourite(@Header("Authorization") String token,
                                     @Field("User_Id") int User_Id,
                                     @Field("Hotel_Id") int Hotel_Id);

    @FormUrlEncoded
    @POST("addHistory.php")
    public Call<String> addHistory(@Header("Authorization") String token,
                                     @Field("User_Id") int User_Id,
                                     @Field("Hotel_Id") int Hotel_Id);

    @FormUrlEncoded
    @POST("braintree/checkout.php")
    Call<String> payment(@Field("nonce") String nonce, @Field("amount") String amount);

    @FormUrlEncoded
    @POST("getPriceTypeRoom.php")
    public Call<List<price>> getPrice(@Header("Authorization") String token,
                                      @Field("Hotel_Id") int id);
}