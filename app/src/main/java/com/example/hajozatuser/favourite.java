package com.example.hajozatuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.hajozatuser.Adapter.FavHisAdapter;
import com.example.hajozatuser.Adapter.HotelsAdpter;
import com.example.hajozatuser.Common.Common;
import com.example.hajozatuser.Interface.ApiInterafce;
import com.example.hajozatuser.Model.FavHis;
import com.example.hajozatuser.Model.Hotels;
import com.example.hajozatuser.Remote.RetrofitCient;

import java.security.acl.Group;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class favourite extends AppCompatActivity {
    private ApiInterafce Api;
    private RetrofitCient retrofitCient;
    private RecyclerView recyclerView;
    private List<FavHis> favHotelList;
    private FavHisAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_favourite );

        recyclerView = findViewById( R.id.listfav );

        //for backWord
        ImageView image_back = (ImageView) findViewById( R.id.imgeview_backfav );

        image_back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // finsh activity on click  backWard
                finish();
            }
        } );

        retrofitCient = RetrofitCient.getINSTANCE();
        Api = retrofitCient.Api;
        Api.getHotelsFav( Common.getToken(), Common.user.getId() ).enqueue( new Callback<List<FavHis>>() {
            @Override
            public void onResponse(Call<List<FavHis>> call, Response<List<FavHis>> response) {
                favHotelList = response.body();
                LinearLayoutManager layoutManager = new LinearLayoutManager( favourite.this, LinearLayoutManager.VERTICAL, false );
                recyclerView.setLayoutManager( layoutManager );
                adapter = new FavHisAdapter( favourite.this, favHotelList );
                recyclerView.setAdapter( adapter );
                Log.d( "ttt", String.valueOf( favHotelList.size() ) );

            }

            @Override
            public void onFailure(Call<List<FavHis>> call, Throwable t) {

            }
        } );

    }
}