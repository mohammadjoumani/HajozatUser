package com.example.hajozatuser;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.hajozatuser.Common.Common;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    private TextInputEditText txtName , txtEmail, txtpassword,txtDate;
    private CircleImageView imgUser;
    private ImageView imgEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile );

        txtName= findViewById( R.id.name_user );
        txtEmail=findViewById( R.id.user_email );
        txtpassword=findViewById( R.id.user_password );
        imgUser=findViewById( R.id.img_user );
        txtDate=findViewById( R.id.user_date );
        imgEdit=findViewById( R.id.img_edit );
        txtName.setText( Common.user.getName() );
        txtEmail.setText( Common.user.getEmail() );
        txtpassword.setText( Common.user.getPassword() );
        txtDate.setText( Common.user.getDate() );
        if (Common.user.getImage_Path() != null) {


            if (Common.user.getImage_Path().contains( "drive" )) {

                Picasso.get().load( Common.user.getImage_Path() ).into( imgUser );
            }
        }
        imgEdit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtName.setEnabled( true );
                txtEmail.setEnabled( true );
                txtpassword.setEnabled( true );
            }
        } );
    }

}