package com.example.hajozatuser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class About extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_about );

        ImageView image_back = (ImageView) findViewById( R.id.imgeview_backabout );

        image_back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // finsh activity on click  backWard
                finish();
            }
        } );
    }

}