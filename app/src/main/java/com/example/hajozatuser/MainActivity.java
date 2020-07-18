package com.example.hajozatuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // var for show message when click button to back
    private long backPressTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Button btnsingin = (Button) findViewById( R.id.button_singin1 );

        // Set a click listener on that View
        btnsingin.setOnClickListener( new View.OnClickListener() { // listener for clic button sing in in activity main
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent singinIntent = new Intent( MainActivity.this, singin.class );

                // Start the new activity
                startActivity( singinIntent );
            }
        } );
        Button btnsingup = (Button) findViewById( R.id.button_singup1 );

        btnsingup.setOnClickListener( new View.OnClickListener() { // listener for click button singup in activity main
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent singupIntent = new Intent( MainActivity.this, singup.class );

                // Start the new activity
                startActivity( singupIntent );
            }
        } );
    }// end onCreate fun


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