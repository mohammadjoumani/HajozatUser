package com.example.hajozatuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hajozatuser.Common.Common;
import com.example.hajozatuser.Interface.ApiInterafce;
import com.example.hajozatuser.Model.User;
import com.example.hajozatuser.Remote.RetrofitCient;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class singup extends AppCompatActivity {

    private TextInputEditText txtName, txtEmail, txtPassword, txtNumber;
    private Button btnsingup;
    private RetrofitCient retrofitCient;
    private ApiInterafce Api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_singup );

        txtName = (TextInputEditText) findViewById( R.id.editText_name_up );
        txtEmail = (TextInputEditText) findViewById( R.id.editText_email_up );
        txtPassword = (TextInputEditText) findViewById( R.id.editText_password_up );
        txtNumber = (TextInputEditText) findViewById( R.id.editText_number_up );
        btnsingup = (Button) findViewById( R.id.button_singup2 );

        //for backward to activity main
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


        btnsingup.setOnClickListener( new View.OnClickListener() { // listener for click button singup in activity main
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                missField();
                if (Common.isConnectionToInternet( singup.this ))
                    register( txtName.getText().toString(), txtEmail.getText().toString(), txtPassword.getText().toString(), txtNumber.getText().toString() );
                else {
                    Snackbar snackbar = Snackbar.make( findViewById( R.id.layout_singin ), "No Internet Connection", Snackbar.LENGTH_LONG );
                    snackbar.setAction( "RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // write my code
                        }
                    } );
                    snackbar.show();
                }

                // Start the new activity
            }
        } );
    }

    private void register(String name, String emil, String password, String number) {
        retrofitCient = RetrofitCient.getINSTANCE();
        Api.RegiterUser( Common.getToken(), name, emil, number, password, "123435" )
                .enqueue( new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Common.user = response.body();

                        // Log.d("user", String.valueOf( Common.user.getEmail() ));
                        if (Common.user.getName() == null || Common.user.getEmail() == null || Common.user.getPassword() == null || Common.user.getPhoneNumber() == null) {

                            //Log.d( "user", String.valueOf( true ) );
                            Toast.makeText( singup.this, "Name or Email or Password or Phone Number  is wronge ..! ", Toast.LENGTH_SHORT ).show();

                        } else {

                            startActivity( new Intent( singup.this, verify.class ) );

                            finish();// for finsh this activity and go to activity home
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                } );

    }


    private void missField() {
        if (TextUtils.isEmpty( (txtName.getText().toString().trim()) )) {
            txtName.setError( "Faild can't be Empty" );
            return;
        }

        if (TextUtils.isEmpty( txtPassword.getText().toString().trim() )) {
            txtPassword.setError( "Faild can't be Empty" );
            return;
        }

        if (TextUtils.isEmpty( txtEmail.getText().toString().trim() )) {
            txtEmail.setError( "Faild can't be Empty" );
            return;
        }

        if (TextUtils.isEmpty( txtNumber.getText().toString().trim() )) {
            txtNumber.setError( "Faild can't be Empty" );
            return;
        }
    }
}
