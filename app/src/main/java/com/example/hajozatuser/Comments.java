package com.example.hajozatuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hajozatuser.Adapter.CommentsAdapter;
import com.example.hajozatuser.Adapter.FavHisAdapter;
import com.example.hajozatuser.Adapter.HotelsAdpter;
import com.example.hajozatuser.Common.Common;
import com.example.hajozatuser.Interface.ApiInterafce;
import com.example.hajozatuser.Model.FavHis;
import com.example.hajozatuser.Model.comments;
import com.example.hajozatuser.Remote.RetrofitCient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Comments extends AppCompatActivity {

    private ApiInterafce Api;
    ListView listView;
    private RetrofitCient retrofitCient;
    private List<comments> commentList;
    private CommentsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_comments );

        listView = findViewById( R.id.list_view_comments );
        retrofitCient = RetrofitCient.getINSTANCE();
        Api = retrofitCient.Api;
        Api.showcomments( Common.getToken(), Common.o.getId() ).enqueue( new Callback<List<comments>>() {
            @Override
            public void onResponse(Call<List<comments>> call, Response<List<comments>> response) {
                commentList = response.body();
                adapter = new CommentsAdapter( getApplicationContext(), commentList );
                listView.setAdapter( adapter );

            }

            @Override
            public void onFailure(Call<List<comments>> call, Throwable t) {

            }
        } );

        //for backWord
        ImageView image_back = (ImageView) findViewById( R.id.imgeview_back_com );

        image_back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // finsh activity on click  backWard
                finish();
            }
        } );
    }
}