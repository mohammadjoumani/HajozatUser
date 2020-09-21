package com.example.hajozatuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class topStar extends AppCompatActivity {

    private ApiInterafce Api;
    private RetrofitCient retrofitCient;

    private ListView listView;
    private List<Hotels> hotelsList1;
    private HotelsAdpter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_top_star );

        ImageView imgbtn = findViewById( R.id.imgeview_back_home );

        imgbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );


        listView = findViewById( R.id.list_view_top );
        getTopHotells( listView );

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // common.o=null;
                Common.o = hotelsList1.get( i );
                startActivity( new Intent( topStar.this, Details_hotel.class ) );

                //getActivity().finish();// for finsh this activity and go to activity home
            }
        } );

    }

    private void getTopHotells(ListView listView) {
        retrofitCient = RetrofitCient.getINSTANCE();
        Api = retrofitCient.Api;
        Api.getHotelsTop( Common.getToken() ).enqueue( new Callback<List<Hotels>>() {
            @Override
            public void onResponse(Call<List<Hotels>> call, Response<List<Hotels>> response) {

                hotelsList1 = response.body();
                adapter = new HotelsAdpter( getApplicationContext(), hotelsList1 );
                listView.setAdapter( adapter );
            }

            @Override
            public void onFailure(Call<List<Hotels>> call, Throwable t) {

            }
        } );
    }
}