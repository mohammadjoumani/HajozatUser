package com.example.hajozatuser;

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
    private List<Hotels> hotelList;
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

        if (id == 1) {
            listView = view.findViewById( R.id.list_view );
            getHotelsData( listView );
            listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    // common.o=null;
                    Common.o = hotelList.get( i );
                    startActivity( new Intent( getActivity(), Details_hotel.class ) );

                    //getActivity().finish();// for finsh this activity and go to activity home
                }
            } );

        } else if (id == 2) {
            // adapter = new ArrayAdapter<>( getActivity(), android.R.layout.simple_list_item_1, mm );
        }


    }

    private void getHotelsData(ListView listView) {

        RetrofitCient retrofitCient = RetrofitCient.getINSTANCE();
        Api = retrofitCient.Api;
        Api.getHotels( Common.getToken() ).enqueue( new Callback<List<Hotels>>() {
            @Override
            public void onResponse(Call<List<Hotels>> call, Response<List<Hotels>> response) {

                hotelList = response.body();
                adapter = new HotelsAdpter( getActivity().getApplicationContext(), hotelList );
                listView.setAdapter( adapter );
                Log.d( "second", String.valueOf( hotelList.size() ) );
            }

            @Override
            public void onFailure(Call<List<Hotels>> call, Throwable t) {

            }
        } );
    }
}