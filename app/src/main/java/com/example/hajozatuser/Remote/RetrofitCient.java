package com.example.hajozatuser.Remote;

import android.app.RemoteAction;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hajozatuser.Interface.ApiInterafce;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCient {//10.0.2.2 //192.168.1.106
    private static final String BASE_URL = "http://192.168.1.106/User/";
    public static ApiInterafce Api;
    private static RetrofitCient INSTANCE;

    public RetrofitCient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        Api = retrofit.create( ApiInterafce.class );
    }

    public static RetrofitCient getINSTANCE() {
        if (null == INSTANCE) {
            INSTANCE = new RetrofitCient();
        }
        return INSTANCE;
    }

}
