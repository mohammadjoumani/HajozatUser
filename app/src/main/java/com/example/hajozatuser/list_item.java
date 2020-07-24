package com.example.hajozatuser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class list_item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_list_item );

        //Share button
        ImageView imgShare=findViewById( R.id.share_image );
        imgShare.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(list_item.this,"mohammad",Toast.LENGTH_LONG ).show();
            }
        } );

    }
}