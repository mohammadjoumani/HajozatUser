package com.example.hajozatuser;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hajozatuser.Adapter.HotelsAdpter;
import com.example.hajozatuser.Common.Common;
import com.example.hajozatuser.Interface.ApiInterafce;
import com.example.hajozatuser.Model.Hotels;
import com.example.hajozatuser.Remote.RetrofitCient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragment extends Fragment {
    private ApiInterafce Api;
    private ListView listView;
    private List<Hotels> hotelList1;
    private List<Hotels> hotelList2;
    private HotelsAdpter adapter;

    private static final String ARG_ID = "id";
    private static final String ARG_NAME = "name";

    // TODO: Rename and change types of parameters
    private int id;
    private String name;

    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance(int id, String name) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putInt( ARG_ID, id );
        args.putString( ARG_NAME, name );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
            id = getArguments().getInt( ARG_ID );
            name = getArguments().getString( ARG_NAME );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_category, container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );

        listView = view.findViewById( R.id.list_view );

        if (id == 1) {

            getHotelsData( listView );

            listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    // common.o=null;
                    Common.o = hotelList1.get( i );
                    startActivity( new Intent( getActivity(), Details_hotel.class ) );

                    //getActivity().finish();// for finsh this activity and go to activity home
                }
            } );

        } else if (id == 2) {
            getHotelsData( listView );
            hotelList2=hotelList1;
            adapter = new HotelsAdpter( getActivity().getApplicationContext(), hotelList2 );
        }


    }

    private void getHotelsData(ListView listView) {

        RetrofitCient retrofitCient = RetrofitCient.getINSTANCE();
        Api = retrofitCient.Api;
        Api.getHotels( Common.getToken() ).enqueue( new Callback<List<Hotels>>() {
            @Override
            public void onResponse(Call<List<Hotels>> call, Response<List<Hotels>> response) {

                hotelList1 = response.body();
                adapter = new HotelsAdpter( getActivity().getApplicationContext(), hotelList1 );
                listView.setAdapter( adapter );
                Log.d( "second", String.valueOf( hotelList1.size() ) );
            }

            @Override
            public void onFailure(Call<List<Hotels>> call, Throwable t) {

            }
        } );

    }
}