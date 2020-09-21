package com.example.hajozatuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hajozatuser.Common.Common;
import com.example.hajozatuser.Interface.ApiInterafce;
import com.example.hajozatuser.Model.User;
import com.example.hajozatuser.Remote.RetrofitCient;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {

    private TextInputEditText txtName, txtEmail, txtpassword, txtPhone;
    private CircleImageView imgUser;
    private Button btnSave;
    private RetrofitCient retrofitCient;
    private ApiInterafce Api;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile );

        retrofitCient = RetrofitCient.getINSTANCE();
        Api = retrofitCient.Api;

        //Set toolbar for profile Activity
        toolbar = findViewById( R.id.toolbar_profile );
        setSupportActionBar( toolbar );
        //for Show Icon BackWord next to name Activity
        getSupportActionBar().setHomeAsUpIndicator( R.drawable.back );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        btnSave = findViewById( R.id.save );
        txtName = findViewById( R.id.name_user );
        txtEmail = findViewById( R.id.user_email );
        txtpassword = findViewById( R.id.user_password );
        imgUser = findViewById( R.id.img_user );
        txtPhone = findViewById( R.id.user_phone );
        txtName.setText( Common.user.getName() );
        txtEmail.setText( Common.user.getEmail() );
        txtpassword.setText( Common.user.getPassword() );
        txtPhone.setText( Common.user.getPhoneNumber() );
        if (Common.user.getImage_Path() != null) {


            if (Common.user.getImage_Path().contains( "drive" ))
                Picasso.get().load( Common.user.getImage_Path() ).into( imgUser );
            else
                Picasso.get().load( Common.BASE_URL + "photos/" + Common.user.getImage_Path() ).into( imgUser );
        } else {
            imgUser.setImageResource( R.drawable.user1 );
        }

        imgUser.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        } );

        btnSave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Api.uploadprofile( Common.getToken(), Common.user.getId(), txtName.getText().toString(), txtEmail.getText().toString(), txtpassword.getText().toString(), txtPhone.getText().toString(), "12345" ).enqueue( new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        response.body();

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                } );
            }

        } );
    }

    private void updateUser() {


    }

    private void updateUserImage() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // for show icon search on toolbar
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.profil, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            txtName.setEnabled( true );
            txtEmail.setEnabled( true );
            txtpassword.setEnabled( true );
            txtPhone.setEnabled( true );
            return true;
        } else {
            finish();
        }

        return super.onOptionsItemSelected( item );
    }

}