package com.example.hajozatuser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.hajozatuser.Adapter.SlideAdapter;
import com.example.hajozatuser.Common.Common;
import com.example.hajozatuser.Interface.ApiInterafce;
import com.example.hajozatuser.Model.Hotels;
import com.example.hajozatuser.Model.SlideItem;
import com.example.hajozatuser.Remote.RetrofitCient;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Details_hotel extends AppCompatActivity {
    private TextView txtCity ,txtBrand,txtStar,txtLocation,txtRoomNum ,txtNameHotel;
    private RatingBar ratStar;
    private ApiInterafce Api;
    private List<SlideItem> slideItems;
    private SlideAdapter adapter;
    private  RetrofitCient retrofitCient;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_details_hotel );
        //for set Detial hotel
        txtNameHotel=findViewById( R.id.name_hotel_detail );
        txtCity=findViewById( R.id.hotel_city_detail1 );
        txtBrand=findViewById( R.id.brand_name_detail1 );
        txtStar=findViewById( R.id.star_detail1 );
        txtLocation=findViewById( R.id.location_detail1 );
        txtRoomNum=findViewById( R.id.room_number_detail1 );
        txtNameHotel.setText( Common.o.getHotel_name() );
        txtCity.setText( Common.o.getCity_name() );
        ratStar=findViewById( R.id.hotel_rating_detail );
        //txtBrand.setText( Common.o.get );
        txtStar.setText( Common.o.getStar() +"");
        txtRoomNum.setText( Common.o.getCount_rooms() );
        ratStar.setRating( Common.o.getStar() );


        final SliderView sliderView = findViewById( R.id.imageSlider );

        //for button to back
        ImageView imgbtn=findViewById( R.id.imageButton );
        imgbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        } );

        retrofitCient = RetrofitCient.getINSTANCE();
        Api = retrofitCient.Api;
        Api.getHotelDetailsImage(Common.getToken() , Common.o.getId()).enqueue( new Callback<List<SlideItem>>() {
            @Override
            public void onResponse(Call<List<SlideItem>> call, Response<List<SlideItem>> response) {
                slideItems = response.body();
                adapter = new SlideAdapter( Details_hotel.this,slideItems );
                sliderView.setSliderAdapter(adapter);
                sliderView.setIndicatorAnimation( IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                sliderView.setSliderTransformAnimation( SliderAnimations.SIMPLETRANSFORMATION);
                sliderView.setAutoCycleDirection( SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                sliderView.setIndicatorSelectedColor( Color.WHITE);
                sliderView.setIndicatorUnselectedColor(Color.GRAY);
                sliderView.setScrollTimeInSec(5);
                sliderView.setAutoCycle(true);
                sliderView.startAutoCycle();

            }

            @Override
            public void onFailure(Call<List<SlideItem>> call, Throwable t) {

            }
        } );
    }
}