package com.example.hajozatuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hajozatuser.Adapter.FacilityAdapter;
import com.example.hajozatuser.Adapter.RuleAdapter;
import com.example.hajozatuser.Adapter.SlideAdapter;
import com.example.hajozatuser.Common.Common;
import com.example.hajozatuser.Interface.ApiInterafce;
import com.example.hajozatuser.Model.Facility;
import com.example.hajozatuser.Model.FavHis;
import com.example.hajozatuser.Model.Rules;
import com.example.hajozatuser.Model.SlideItem;
import com.example.hajozatuser.Remote.RetrofitCient;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Details_hotel extends AppCompatActivity {
    private TextView txtCity, txtStar, txtRoomNum, txtNameHotel;
    private RatingBar ratStar;
    private ImageView addFav,share;
    private Button btnRev, btnComments, btnMpas;
    private ApiInterafce Api;
    private List<SlideItem> slideItems;
    private SlideAdapter adapter;
    private List<Facility> facilityList;
    private FacilityAdapter adapter1;
    private RecyclerView recyclerView, recyclerView1;
    private RetrofitCient retrofitCient;
    private List<Rules> rulesList;
    private RuleAdapter adapter2;
    private List<FavHis> favHotelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_details_hotel );

        btnComments = findViewById( R.id.Comments );
        btnMpas = findViewById( R.id.maps );
        btnRev = findViewById( R.id.rev );
        share=findViewById( R.id.Share );

        recyclerView = findViewById( R.id.rec_facility );
        recyclerView1 = findViewById( R.id.rec_rule );
        //for Retrofit Api
        retrofitCient = RetrofitCient.getINSTANCE();
        //for set Detial hotel
        txtNameHotel = findViewById( R.id.name_hotel_detail11 );
        txtCity = findViewById( R.id.hotel_city_detail1 );
        txtStar = findViewById( R.id.star_detail1 );
        txtRoomNum = findViewById( R.id.room_number_detail1 );
        txtNameHotel.setText( Common.o.getHotel_name() );
        txtCity.setText( Common.o.getCity_name() );
        ratStar = findViewById( R.id.hotel_rating_detail );
        //txtBrand.setText( Common.o.get );
        txtStar.setText( Common.o.getStar() + "" );
        txtRoomNum.setText( Common.o.getCount_rooms() );
        ratStar.setRating( Common.o.getStar() );
        //for slider image
        final SliderView sliderView = findViewById( R.id.imageSlider );

        addFav = findViewById( R.id.loveOrunlove );
        //for button to back
        ImageView imgbtn = findViewById( R.id.imageButton );

        share.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "The Hotel  " + Common.o.getHotel_name() + "  is very beautiful";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        } );

        addFav.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Api = retrofitCient.Api;
                Api.addFavourite( Common.getToken(), Common.user.getId(), Common.o.getId() )
                        .enqueue( new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String mm = response.body();
                                Toast.makeText( Details_hotel.this, mm, Toast.LENGTH_SHORT ).show();

                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        } );

            }
        } );

        btnMpas.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //This code to ArcGIS Map
                Intent intentMap = new Intent( Details_hotel.this, maps.class );
                intentMap.putExtra( "Lat", Common.o.getLat() );
                intentMap.putExtra( "Lng", Common.o.getLng() );
                intentMap.putExtra( "Name", Common.o.getHotel_name() );
                startActivity( intentMap );
            }
        } );


        btnRev.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( Details_hotel.this, moreDetail.class ) );
            }
        } );

        imgbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );

        btnComments.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /****for show commnet to hotels
                 and *********/
                startActivity( new Intent( Details_hotel.this, Comments.class ) );

            }
        } );

        Api = retrofitCient.Api;
        Api.getHotelDetailsImage( Common.getToken(), Common.o.getId() ).enqueue( new Callback<List<SlideItem>>() {
            @Override
            public void onResponse(Call<List<SlideItem>> call, Response<List<SlideItem>> response) {
                slideItems = response.body();
                adapter = new SlideAdapter( Details_hotel.this, slideItems );
                sliderView.setSliderAdapter( adapter );
                sliderView.setIndicatorAnimation( IndicatorAnimationType.WORM ); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                sliderView.setSliderTransformAnimation( SliderAnimations.SIMPLETRANSFORMATION );
                sliderView.setAutoCycleDirection( SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH );
                sliderView.setIndicatorSelectedColor( Color.WHITE );
                sliderView.setIndicatorUnselectedColor( Color.GRAY );
                sliderView.setScrollTimeInSec( 5 );
                sliderView.setAutoCycle( true );
                sliderView.startAutoCycle();

            }

            @Override
            public void onFailure(Call<List<SlideItem>> call, Throwable t) {

            }
        } );

        Api = retrofitCient.Api;
        Api.getHotelDetailsFacility( Common.getToken(), Common.o.getId() ).enqueue( new Callback<List<Facility>>() {
            @Override
            public void onResponse(Call<List<Facility>> call, Response<List<Facility>> response) {

                facilityList = response.body();
                Log.d( "fac", String.valueOf( facilityList.get( 0 ).getName() ) );
                //sortIcomFacility();
                //Log.d("img1", String.valueOf( imageIcon1.get(0) ));.
                LinearLayoutManager layoutManager = new LinearLayoutManager( Details_hotel.this, LinearLayoutManager.HORIZONTAL, false );
                recyclerView.setLayoutManager( layoutManager );
                adapter1 = new FacilityAdapter( Details_hotel.this, facilityList );
                recyclerView.setAdapter( adapter1 );
            }

            @Override
            public void onFailure(Call<List<Facility>> call, Throwable t) {

            }
        } );


        Api = retrofitCient.Api;
        Api.getHotelDetailsRules( Common.getToken(), Common.o.getId() ).enqueue( new Callback<List<Rules>>() {
            @Override
            public void onResponse(Call<List<Rules>> call, Response<List<Rules>> response) {

                rulesList = response.body();
                LinearLayoutManager layoutManager = new LinearLayoutManager( Details_hotel.this, LinearLayoutManager.HORIZONTAL, false );
                recyclerView1.setLayoutManager( layoutManager );
                adapter2 = new RuleAdapter( Details_hotel.this, rulesList );
                recyclerView1.setAdapter( adapter2 );

            }

            @Override
            public void onFailure(Call<List<Rules>> call, Throwable t) {

            }
        } );

    }

    /****   private void setArrayList(ArrayList<Integer> imageIcon) {

     imageIcon.add( R.drawable.wifi );

     imageIcon.add( R.drawable.pool );

     imageIcon.add( R.drawable.restaurant );

     imageIcon.add( R.drawable.hot_water );

     imageIcon.add( R.drawable.heating );
     }****/////

//    private void sortIcomFacility(){
//        if(facilityList.get(0).getName() =="WiFi")
//            imageIcon1.add(R.drawable.wifi );
//
//    }
}