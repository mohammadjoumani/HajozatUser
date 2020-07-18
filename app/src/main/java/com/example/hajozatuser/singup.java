package com.example.hajozatuser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;

public class singup extends AppCompatActivity {

    private TextInputEditText txtName, txtEmail, txtPassword, txtNumber;
    private Button btnsingup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_singup );


        ImageView image_back1 = (ImageView) findViewById( R.id.iamgeview_back1 );

        image_back1.setOnClickListener( new View.OnClickListener() { // listener for click button singup in activity main
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                finish();
                // Start the new activity
            }
        } );

        txtName = (TextInputEditText) findViewById( R.id.editText_name_up );
        txtEmail = (TextInputEditText) findViewById( R.id.editText_email_up );
        txtPassword = (TextInputEditText) findViewById( R.id.editText_password_up );
        txtNumber = (TextInputEditText) findViewById( R.id.editText_number_up );
        btnsingup = (Button) findViewById( R.id.button_singup2 );
        btnsingup.setOnClickListener( new View.OnClickListener() { // listener for click button singup in activity main
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                singuup();
                // Start the new activity
            }
        } );
    }

    private void singuup() {
        if (TextUtils.isEmpty( (txtName.getText().toString().trim()) ) ||
                TextUtils.isEmpty( txtPassword.getText().toString().trim() ) ||
                TextUtils.isEmpty( txtEmail.getText().toString().trim() ) ||
                TextUtils.isEmpty( txtNumber.getText().toString().trim() )) {
            txtName.setError( "Faild can't be Empty" );
            txtEmail.setError( "Faild can't be Empty" );
            txtPassword.setError( "Faild can't be Empty" );
            txtNumber.setError( "Faild can't be Empty" );
        }
    }
}
