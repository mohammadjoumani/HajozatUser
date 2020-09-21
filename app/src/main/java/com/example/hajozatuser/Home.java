package com.example.hajozatuser;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hajozatuser.Adapter.FavHisAdapter;
import com.example.hajozatuser.Adapter.HotelsAdpter;
import com.example.hajozatuser.Common.Common;
import com.example.hajozatuser.Interface.ApiInterafce;
import com.example.hajozatuser.Model.FavHis;
import com.example.hajozatuser.Model.Hotels;
import com.example.hajozatuser.Remote.RetrofitCient;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.hajozatuser.Common.Common;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ApiInterafce Api;
    private RetrofitCient retrofitCient;
    private List<Hotels> hotelSearch;

    private ListView listView;
    private List<Hotels> hotelsList;
    private HotelsAdpter adapter;
    // var for show message when click button to back
    private long backPressTime;
    private Toast backToast;

    // for set Data user on fun getDataUser
    private AppBarConfiguration mAppBarConfiguration;
    private TextView txtEmailUser, txtNameUser;
    private CircleImageView imgUser;
    //    private TabLayout tabLayout;
//    private ViewPager viewPager;
//    private PagerAdapter adapter;
    private Toolbar toolbar;


    Dialog myDailog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );

        toolbar = (Toolbar) findViewById( R.id.toolbar ); // for toolbar
        setSupportActionBar( toolbar );

        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        // for open navigation
        NavigationView navigationView = findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder()
                .setDrawerLayout( drawer )
                .build();


        //Set Data of brand on navigation
        View headerView = navigationView.getHeaderView( 0 );
        txtNameUser = (TextView) headerView.findViewById( R.id.nameuser );
        txtEmailUser = (TextView) headerView.findViewById( R.id.emailuser );
        imgUser = (CircleImageView) headerView.findViewById( R.id.imguser );
        getDataUser( txtNameUser, txtEmailUser, imgUser );


        listView = findViewById( R.id.list_view_home );
        getHotells( listView );

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // common.o=null;
                Common.o = hotelsList.get( i );
                startActivity( new Intent( Home.this, Details_hotel.class ) );

                //getActivity().finish();// for finsh this activity and go to activity home
            }
        } );
        /*for create tab and fragments
        start for tablayout and viewPager setting
         */
//        tabLayout = (TabLayout) findViewById( R.id.tablayout ); // for tablayout
//        viewPager = (ViewPager) findViewById( R.id.viewpager ); // for Viewpager
//        adapter = new PagerAdapter( getSupportFragmentManager() );

//        //Add  fragment
//        adapter.AddFragment( new home_fragment(), "Home" );
//        adapter.AddFragment( new top_fragment(), "Top" );

//        viewPager.setAdapter( adapter );
//        tabLayout.setupWithViewPager( viewPager );


        /*for create tab and fragments
        End for tablayout and viewPager setting
         */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // for show icon search on toolbar
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.home, menu );
        SearchView searchView = (SearchView) menu.findItem( R.id.search_menu ).getActionView();
        searchView.setSubmitButtonEnabled( true );
        searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override

            public boolean onQueryTextSubmit(String query) {
                searchHotel( query.toLowerCase() );
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        } );

        searchView.setOnCloseListener( new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                adapter = new HotelsAdpter( Home.this, hotelsList );
                listView.setAdapter( adapter );
                return true;
            }
        } );


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected( item );
    }

    @Override
    // onClick any item form meun for navigationDrawer
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.About)
            startActivity( new Intent( Home.this, About.class ) );
        if (id == R.id.topHotels)
            startActivity( new Intent( Home.this, topStar.class ) );
        else if (id == R.id.history)
            startActivity( new Intent( Home.this, History.class ) );
        else if (id == R.id.favourite)
            startActivity( new Intent( Home.this, favourite.class ) );
        else if (id == R.id.profile) {
            startActivity( new Intent( Home.this, Profile.class ) );
        }

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }

    // fun for get Data for user signin
    public void getDataUser(TextView txtNameUser, TextView txtEmailUser, CircleImageView imgUser) {
        txtNameUser.setText( Common.user.getName() );
        Log.d( "nameUser", Common.user.getName() );
        txtEmailUser.setText( Common.user.getEmail() );
        if (Common.user.getImage_Path() != null) {

            if (Common.user.getImage_Path() != null) {

                if (Common.user.getImage_Path().contains( "drive" ))
                    Picasso.get().load( Common.user.getImage_Path() ).into( imgUser );
                else
                    Picasso.get().load( Common.BASE_URL + "photos/" + Common.user.getImage_Path() ).into( imgUser );
            } else if (Common.user.getImage_Path() == null)

                imgUser.setImageResource( R.drawable.user1 );

        }
    }

    @Override
    public void onBackPressed() { // function for show message when click button to back

        if (backPressTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText( getBaseContext(), "Press back again to exite", Toast.LENGTH_SHORT );
            backToast.show();
        }
        backPressTime = System.currentTimeMillis();
    }


    private void searchHotel(String query) {
        Api = retrofitCient.Api;
        Api.getHotelSearch( Common.getToken(), query ).enqueue( new Callback<List<Hotels>>() {
            @Override
            public void onResponse(Call<List<Hotels>> call, Response<List<Hotels>> response) {
                hotelSearch = response.body();
                adapter = new HotelsAdpter( Home.this, hotelSearch );
                listView.setAdapter( adapter );
//                listView.getAdapter().notify();
                //   Toast.makeText( Home.this, hotelSearch.get(0).getHotel_name(), Toast.LENGTH_SHORT ).show();

            }

            @Override
            public void onFailure(Call<List<Hotels>> call, Throwable t) {

            }
        } );
        //                Toast.makeText( Home.this, "Sorry .... !", Toast.LENGTH_SHORT ).show();
    }

    private void getHotells(ListView listView) {
        retrofitCient = RetrofitCient.getINSTANCE();
        Api = retrofitCient.Api;
        Api.getHotels( Common.getToken() ).enqueue( new Callback<List<Hotels>>() {
            @Override
            public void onResponse(Call<List<Hotels>> call, Response<List<Hotels>> response) {

                hotelsList = response.body();
                adapter = new HotelsAdpter( getApplicationContext(), hotelsList );
                listView.setAdapter( adapter );
            }

            @Override
            public void onFailure(Call<List<Hotels>> call, Throwable t) {

            }
        } );
    }

}

