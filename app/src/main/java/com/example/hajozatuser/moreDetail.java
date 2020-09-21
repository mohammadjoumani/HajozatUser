package com.example.hajozatuser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.braintreepayments.api.dropin.DropInActivity;
import com.braintreepayments.api.dropin.DropInRequest;
import com.braintreepayments.api.dropin.DropInResult;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.example.hajozatuser.Adapter.SlideAdapter;
import com.example.hajozatuser.Common.Common;
import com.example.hajozatuser.Interface.ApiInterafce;
import com.example.hajozatuser.Model.SlideItem;
import com.example.hajozatuser.Model.price;
import com.example.hajozatuser.Remote.RetrofitCient;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Calendar;

public class moreDetail extends AppCompatActivity {

    private static final int REQUEST_PAYMENT_CODE = 7777;
    private CheckBox checkBoxSigle, checkBoxDouble, checkBoxFamily;
    private Button btnBooking;
    private NumberPicker numberPickerSinle, numberPickerDouble, numberPickerFamily;
    private Map<String, String> params;
    private TextView txtSingle, txtDouble, txtFamily, txtNameHotle;
    private RetrofitCient retrofitCient;
    private ApiInterafce Api;
    private List<SlideItem> slideItems;
    private List<price> priceList;
    private SlideAdapter adapter;
    private ImageView imgbtn;
    private String amount;
    private Double single = 0.0, aDoublee = 0.0, family = 0.0, amounts = 0.0;
    private int a = 0, b = 0, c = 0;
    private Calendar calendar;
    private String Date1, Date2;
    CalendarView calendarView;
    private int day, month, year, day1, month1, year1;
//    private Calendar call1;
//    private Calendar call2;
    private long diffDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_more_detail );

        calendar = Calendar.getInstance();
//        call1 = Calendar.getInstance();
//        call2 = Calendar.getInstance();
        Log.d( "year", String.valueOf( calendar.get( Calendar.YEAR ) ) );
        Log.d( "mot", String.valueOf( calendar.get( Calendar.MONTH ) ) );
        Log.d( "day", String.valueOf( calendar.get( Calendar.DAY_OF_MONTH ) ) );
        day1 = calendar.get( Calendar.DAY_OF_MONTH );
        month1 = calendar.get( Calendar.MONTH );
        year1 = calendar.get( Calendar.YEAR );

        Date1 = calendar.get( Calendar.DAY_OF_MONTH ) + "/" + calendar.get( Calendar.MONTH ) + "/" + calendar.get( Calendar.YEAR );

        Log.d( "date1", Date1 );
        calendarView = findViewById( R.id.calendarView );
        calendarView.setOnDateChangeListener( new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                Date2 = i2 + "/" + i1 + "/" + i;
                day = i2;
                month = i1;
                year = i;
                Log.d( "date2", Date2 );
                Log.d( "i", i + "" );
                Log.d( "i1", i1 + "" );
                Log.d( "i2", i2 + "" );

            }
        } );

        txtNameHotle = findViewById( R.id.name_hotel_detail );
        txtNameHotle.setText( Common.o.getHotel_name() );
        //for set spinner
        //fillSpinners();
        checkBoxSigle = findViewById( R.id.checkbox_single );
        checkBoxDouble = findViewById( R.id.checkbox_double );
        checkBoxFamily = findViewById( R.id.checkbox_family );

        /*for textView for price hotles from activity moreDetail*/
        txtSingle = findViewById( R.id.txt_price_single );
        txtDouble = findViewById( R.id.txt_price_double );
        txtFamily = findViewById( R.id.txt_price_family );

        /*for NumberPicker for activity moreDetail*/

        numberPickerSinle = findViewById( R.id.numberpickerSingle );
        numberPickerSinle.setMinValue( 1 );
        numberPickerSinle.setMaxValue( 10 );

        numberPickerDouble = findViewById( R.id.numberpickerDouble );
        numberPickerDouble.setMinValue( 1 );
        numberPickerDouble.setMaxValue( 10 );

        numberPickerFamily = findViewById( R.id.numberpickerFamily );
        numberPickerFamily.setMinValue( 1 );
        numberPickerFamily.setMaxValue( 10 );

        String token = Common.Tokenization_Key_Braintree;

        final SliderView sliderView = findViewById( R.id.imageSlider1 );
        retrofitCient = RetrofitCient.getINSTANCE();

        btnBooking = findViewById( R.id.booking );


        btnBooking.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if ((month == month1 && day > day1)||(month>month1 && day<day1)) {
                    dif( year1, month1, day1, year, month, day );
                    Log.d( "dif", diffDays + " " );


                    if (checkBoxSigle.isChecked())
                        a = 1;
                    if (checkBoxDouble.isChecked())
                        b = 1;
                    if (checkBoxFamily.isChecked())
                        c = 1;

                    amounts = (a * Double.parseDouble( txtSingle.getText().toString() ) * numberPickerSinle.getValue() +
                            b * Double.parseDouble( txtDouble.getText().toString() ) * numberPickerDouble.getValue() +
                            c * Double.parseDouble( txtFamily.getText().toString() ) * numberPickerFamily.getValue()) * diffDays;


                    Toast.makeText( moreDetail.this, amounts.toString(), Toast.LENGTH_SHORT ).show();

                    //Payment
                    if (amounts <= 0) {
                        Toast.makeText( moreDetail.this, "please select from type", Toast.LENGTH_SHORT ).show();
                    } else {
                        DropInRequest dropInRequest = new DropInRequest().clientToken( token );
                        startActivityForResult( dropInRequest.getIntent( moreDetail.this ), REQUEST_PAYMENT_CODE );
                    }
                    // for unChecked
                    a = b = c = 0;
                } else {
                    Toast.makeText( moreDetail.this, "please select different date ", Toast.LENGTH_SHORT ).show();

                }
            }
        } );

        //for button to back
        imgbtn =

                findViewById( R.id.imageButton11 );

        imgbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );
        Api = retrofitCient.Api;
        Api.getHotelDetailsImage( Common.getToken(), Common.o.getId() ).

                enqueue( new Callback<List<SlideItem>>() {
                    @Override
                    public void onResponse
                            (Call<List<SlideItem>> call, Response<List<SlideItem>> response) {
                        slideItems = response.body();
                        adapter = new SlideAdapter( moreDetail.this, slideItems );
                        sliderView.setSliderAdapter( adapter );
                        sliderView.setIndicatorAnimation( IndicatorAnimationType.WORM ); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                        sliderView.setSliderTransformAnimation( SliderAnimations.SIMPLETRANSFORMATION );
                        sliderView.setAutoCycleDirection( SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH );
                        sliderView.setIndicatorSelectedColor( Color.WHITE );
                        sliderView.setIndicatorUnselectedColor( Color.GRAY );
                        sliderView.setScrollTimeInSec( 5 );
                        sliderView.setAutoCycle( true );
                        sliderView.startAutoCycle();
                    }

                    @Override
                    public void onFailure(Call<List<SlideItem>> call, Throwable t) {

                    }
                } );
        Api = retrofitCient.Api;
        Api.getPrice( Common.getToken(), Common.o.getId() )
                .

                        enqueue( new Callback<List<price>>() {
                            @Override
                            public void onResponse
                                    (Call<List<price>> call, Response<List<price>> response) {
                                priceList = response.body();
                                txtSingle.setText( priceList.get( 2 ).getPrice() );
                                txtDouble.setText( priceList.get( 0 ).getPrice() );
                                txtFamily.setText( priceList.get( 1 ).getPrice() );
                            }

                            @Override
                            public void onFailure(Call<List<price>> call, Throwable t) {

                            }
                        } );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        if (requestCode == REQUEST_PAYMENT_CODE) {
            if (resultCode == RESULT_OK) {

                DropInResult result = data.getParcelableExtra( DropInResult.EXTRA_DROP_IN_RESULT );
                PaymentMethodNonce nonce = result.getPaymentMethodNonce();
                String strNonce = nonce.getNonce();

                amount = amounts.toString();
                params = new HashMap<>();

                params.put( "amount", amount );
                params.put( "nonce", strNonce );
                Toast.makeText( this, "Payment successful", Toast.LENGTH_SHORT ).show();

                booking();
                addToHistory();
                sendPayment();

            } else if (resultCode == RESULT_CANCELED)
                Toast.makeText( this, "Payment cancelled", Toast.LENGTH_SHORT ).show();

            else {

                Exception error = (Exception) data.getSerializableExtra( DropInActivity.EXTRA_ERROR );
                Log.e( "PAYMENT_ERROR", error.getMessage() );
            }
        }
    }

    private void sendPayment() {

        Api.payment( params.get( "nonce" ), params.get( "amount" ) )
                .enqueue( new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        Toast.makeText( moreDetail.this, "Transaction successful", Toast.LENGTH_SHORT ).show();

                        if (response.isSuccessful()) {

                            Toast.makeText( moreDetail.this, "Transaction successfulllll", Toast.LENGTH_SHORT ).show();


                        } else {

                            Toast.makeText( moreDetail.this, "Transaction failed", Toast.LENGTH_SHORT ).show();
                        }

                        Log.d( "PAYMENT_INFO", response.body() );
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                        Log.d( "PAYMENT_INFO", t.getMessage() );
                    }
                } );
    }

    private void booking() {
        Api = retrofitCient.Api;
        Api.bookingRoom( Common.getToken(), Common.user.getId(), Common.o.getId(),
                Date1, Date2, amounts )
                .enqueue( new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        response.body();
                        //  Toast.makeText( moreDetail.this, response.body(), Toast.LENGTH_SHORT ).show();

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                } );

    }

    private void addToHistory() {
        Api = retrofitCient.Api;
        Api.addHistory( Common.getToken(), Common.user.getId(), Common.o.getId() )
                .enqueue( new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String nn = response.body();
                        Toast.makeText( moreDetail.this, nn, Toast.LENGTH_SHORT ).show();

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                } );

    }

    private void dif(int d1, int m1, int y1, int d2, int m2, int y2) {
        // Set the date for both of the calendar instance
//        call1.set( y1, m1, d1 );
//        call2.set( y2, m2, d2 );
//
//        // Get the represented date in milliseconds
//        long millis1 = call1.getTimeInMillis();
//        long millis2 = call2.getTimeInMillis();
//
//        // Calculate difference in milliseconds
//        long diff = millis2 - millis1;
//
//        // Calculate difference in seconds
//        long diffSeconds = diff / 1000;
//
//        // Calculate difference in minutes
//        long diffMinutes = diff / (60 * 1000);
//
//        // Calculate difference in hours
//        long diffHours = diff / (60 * 60 * 1000);
        if (month == month1) {
            diffDays = day - day1;
        }
        else {
            diffDays=30-(day1-day);
        }

        // Calculate difference in days
        //  diffDays = diff / (24 * 60 * 60 * 1000 * 365);

    }

}