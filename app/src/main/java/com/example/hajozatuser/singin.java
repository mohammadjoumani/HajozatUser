package com.example.hajozatuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
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

public class singin extends AppCompatActivity {
    private TextInputEditText txtEmail, txtPassword;
    private ApiInterafce Api;
    private RetrofitCient retrofitCient;
    MainActivity mainActivity = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_singin );


        ImageView image_back2 = (ImageView) findViewById( R.id.iamgeview_back2 );
        image_back2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // finsh activity on click  backWard
                finish();
            }
        } );
        Button btnsingin = (Button) findViewById( R.id.button_singin2 );
        txtEmail = (TextInputEditText) findViewById( R.id.editText_email_in );
        txtPassword = (TextInputEditText) findViewById( R.id.editText_password_in );

        btnsingin.setOnClickListener( new View.OnClickListener() { // listener for click button singup in activity main
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                missField();
                if (Common.isConnectionToInternet( singin.this ))
                    signin( txtEmail.getText().toString(), txtPassword.getText().toString() );
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

            }
        } );
    }

    private void signin(String email, String password) {
        retrofitCient = RetrofitCient.getINSTANCE();
        Api = retrofitCient.Api;
        Api.LoginUser( email, password )
                .enqueue( new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Common.user = response.body();

                        // Log.d("user", String.valueOf( Common.user.getEmail() ));
                        if (Common.user.getEmail() == null || Common.user.getPassword() == null) {

                            //Log.d( "user", String.valueOf( true ) );
                            Toast.makeText( singin.this, "Email or Password is wronge ..! ", Toast.LENGTH_SHORT ).show();

                        } else {

                            startActivity( new Intent( singin.this, Home.class ) );

                            finish();// for finsh this activity and go to activity home
                            mainActivity.finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                        // Toast.makeText( singin.this, "Email or Password is wronge ..!", Toast.LENGTH_SHORT ).show();
                    }
                } );


    }

    private void missField() {
        if (TextUtils.isEmpty( (txtEmail.getText().toString().trim()) )) {
            txtEmail.setError( "Faild can't be Empty" );
            return;
        }
        if (TextUtils.isEmpty( txtPassword.getText().toString().trim() )) {
            txtPassword.setError( "Faild can't be Empty" );
            return;
        }
    }

}
