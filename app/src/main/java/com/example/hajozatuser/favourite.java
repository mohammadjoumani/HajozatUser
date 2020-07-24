package com.example.hajozatuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.hajozatuser.Adapter.HotelsAdpter;
import com.example.hajozatuser.Common.Common;
import com.example.hajozatuser.Interface.ApiInterafce;
import com.example.hajozatuser.Model.Hotels;
import com.example.hajozatuser.Remote.RetrofitCient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class favourite extends AppCompatActivity {
    private ApiInterafce Api;
    private ListView listView;
    private List<Hotels> hotelList;
    private HotelsAdpter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_favourite );

        //for backWord
        ImageView image_back = (ImageView) findViewById( R.id.imgeview_backfav );

        image_back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // finsh activity on click  backWard
                finish();
            }
        } );

        listView = findViewById( R.id.listfav );
        RetrofitCient retrofitCient = RetrofitCient.getINSTANCE();
        Api = retrofitCient.Api;
        Api.getHotelsFav( Common.getToken(),Common.user.getId() ).enqueue( new Callback<List<Hotels>>() {
            @Override
            public void onResponse(Call<List<Hotels>> call, Response<List<Hotels>> response) {
                hotelList = response.body();
                adapter = new HotelsAdpter( getApplicationContext(), hotelList );
                listView.setAdapter( adapter );
            }

            @Override
            public void onFailure(Call<List<Hotels>> call, Throwable t) {

            }
        } );

    }
}