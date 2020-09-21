package com.example.hajozatuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.hajozatuser.Adapter.FavHisAdapter;
import com.example.hajozatuser.Adapter.HotelsAdpter;
import com.example.hajozatuser.Common.Common;
import com.example.hajozatuser.Interface.ApiInterafce;
import com.example.hajozatuser.Model.FavHis;
import com.example.hajozatuser.Model.Hotels;
import com.example.hajozatuser.Remote.RetrofitCient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class History extends AppCompatActivity {

    private ApiInterafce Api;
    private RetrofitCient retrofitCient;
    private RecyclerView recyclerView;
    private List<FavHis> hisHotelList;
    private FavHisAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_history );


        recyclerView = findViewById( R.id.lishis );
        //button for backword
        ImageView image_back = (ImageView) findViewById( R.id.imgeview_backhis );

        image_back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // finsh activity on click  backWard
                finish();
            }
        } );

        retrofitCient = RetrofitCient.getINSTANCE();
        Api = retrofitCient.Api;
        Api.getHotelsHis( Common.getToken(), Common.user.getId() ).enqueue( new Callback<List<FavHis>>() {
            @Override
            public void onResponse(Call<List<FavHis>> call, Response<List<FavHis>> response) {
                hisHotelList = response.body();
                LinearLayoutManager layoutManager = new LinearLayoutManager( History.this, LinearLayoutManager.VERTICAL, false );
                recyclerView.setLayoutManager( layoutManager );
                adapter = new FavHisAdapter( History.this, hisHotelList );
                recyclerView.setAdapter( adapter );
                Log.d( "ttt", String.valueOf( hisHotelList.size() ) );
            }

            @Override
            public void onFailure(Call<List<FavHis>> call, Throwable t) {

            }
        } );

    }
}