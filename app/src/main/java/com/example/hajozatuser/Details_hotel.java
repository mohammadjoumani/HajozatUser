package com.example.hajozatuser;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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

    private ApiInterafce Api;
    private List<SlideItem> slideItems;
    private SlideAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_details_hotel );

        final SliderView sliderView = findViewById( R.id.imageSlider );

        ImageButton imgbtn=findViewById( R.id.imageButton );
        imgbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        } );

        RetrofitCient retrofitCient = RetrofitCient.getINSTANCE();
        Api = retrofitCient.Api;
        Api.getHotelDetailsImage(Common.getToken() , Common.o.getId()).enqueue( new Callback<List<SlideItem>>() {
            @Override
            public void onResponse(Call<List<SlideItem>> call, Response<List<SlideItem>> response) {
                slideItems = response.body();
                adapter = new SlideAdapter( Details_hotel.this,slideItems );
                //Log.d("jjjj", String.valueOf(slideItems.size()));
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

                Log.d("nn", String.valueOf("mm"));
            }
        } );
    }
}