package com.example.hajozatuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hajozatuser.Adapter.PagerAdapter;
import com.example.hajozatuser.Common.Common;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // var for show message when click button to back
    private long backPressTime;
    private Toast backToast;

    // for set Data user on fun getDataUser
    private AppBarConfiguration mAppBarConfiguration;
    private TextView txtEmailUser, txtNameUser;
    CircularImageView imgUser;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;

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
        mAppBarConfiguration = new AppBarConfiguration.Builder( )
                .setDrawerLayout( drawer )
                .build();

        //Set Data of brand on navigation
        View headerView = navigationView.getHeaderView( 0 );
        txtNameUser = (TextView) headerView.findViewById( R.id.nameuser );
        txtEmailUser = (TextView) headerView.findViewById( R.id.emailuser );
        imgUser = (CircularImageView) headerView.findViewById( R.id.imguser );
        getDataUser( txtNameUser, txtEmailUser, imgUser );

        //for create tab and fragments
        tabLayout = (TabLayout) findViewById( R.id.tablayout ); // for tablayout
        viewPager = (ViewPager) findViewById( R.id.viewpager ); // for Viewpager
        PagerAdapter adapter = new PagerAdapter( getSupportFragmentManager() );
        adapter.addtab( new MyTab( "Home", CategoryFragment.newInstance( 1, "Home" ) ) );
        adapter.addtab( new MyTab( "Top", CategoryFragment.newInstance( 2, "Top" ) ) );
        viewPager.setAdapter( adapter );
        tabLayout.setupWithViewPager( viewPager );
        //  listener for tablayout
//        tabLayout.addOnTabSelectedListener( new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//
//               Toast.makeText( Home.this, "select", Toast.LENGTH_SHORT ).show();
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//               // Toast.makeText( Home.this, "unselected", Toast.LENGTH_SHORT ).show();
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//               // Toast.makeText( Home.this, "reselected", Toast.LENGTH_SHORT ).show();
//            }
//        } );
//        // listener for Viewpager
//        viewPager.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        } );

//       NavController navController = Navigation.findNavController( this, R.id.nav_host_fragment );
//        NavigationUI.setupActionBarWithNavController( this, navController, mAppBarConfiguration );
//        NavigationUI.setupWithNavController( navigationView, navController );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // for show icon search on toolbar
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.home, menu );
        return true;
    }

    @Override
    // onClick any item form meun for navigationDrawer
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.About)
            startActivity( new Intent( Home.this, About.class ) );
        else if (id == R.id.history)
            startActivity( new Intent( Home.this, History.class ) );
        else if (id == R.id.favourite)
            startActivity( new Intent( Home.this, favourite.class ) );
        else if (id==R.id.profile){
            startActivity( new Intent( Home.this, Profile.class  ) );
        }

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }

    // fun for get Data for user signin
    private void getDataUser(TextView txtNameUser, TextView txtEmailUser, CircularImageView imgUser) {
        txtNameUser.setText( Common.user.getName() );
        txtEmailUser.setText( Common.user.getEmail() );
        if (Common.user.getImage_Path() != null) {


            if (Common.user.getImage_Path().contains( "drive" )) {

                Picasso.get().load( Common.user.getImage_Path() ).into( imgUser );
            }
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
}

