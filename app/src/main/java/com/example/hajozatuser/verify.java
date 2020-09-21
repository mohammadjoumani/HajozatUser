package com.example.hajozatuser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.example.hajozatuser.Common.Common;
import com.example.hajozatuser.Interface.ApiInterafce;
import com.example.hajozatuser.Model.Verify;
import com.example.hajozatuser.Remote.RetrofitCient;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class verify extends AppCompatActivity {

    private TextInputEditText txtVerify;
    private ApiInterafce Api;
    private RetrofitCient retrofitCient;
    Button btn_forWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_verify );

        btn_forWord = findViewById( R.id.goto_main );
        txtVerify = findViewById( R.id.verify );
        btn_forWord.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                missField();
                verify( txtVerify.getText().toString(), Common.user.getId() );
            }
        } );
    }

    private void verify(String code, int userid) {
        retrofitCient = RetrofitCient.getINSTANCE();
        Api.verify( Common.getToken(), code, userid )
                .enqueue( new Callback<Verify>() {
                    @Override
                    public void onResponse(Call<Verify> call, Response<Verify> response) {
                        //Common.user=response.body();

                    }

                    @Override
                    public void onFailure(Call<Verify> call, Throwable t) {

                    }
                } );

    }

    private void missField() {
        if (TextUtils.isEmpty( (txtVerify.getText().toString().trim()) )) {
            txtVerify.setError( "Faild can't be Empty" );
            return;
        }
    }
}