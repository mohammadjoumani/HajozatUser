package com.example.hajozatuser.Common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.example.hajozatuser.Interface.ApiInterafce;
import com.example.hajozatuser.Model.Hotels;
import com.example.hajozatuser.Model.SlideItem;
import com.example.hajozatuser.Model.User;
import com.example.hajozatuser.R;
import com.example.hajozatuser.Remote.RetrofitCient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Common {

    public static int Day;
    public static int Month;
    public static int Year;

    public static int Day1;
    public static int Month1;
    public static int Year1;
    public static int Day2;
    public static int Month2;
    public static int Year2;

    public static User user;
    public static Hotels o;
    public static final String BASE_URL = "http://192.168.43.32/Hajozat/User/";
    public static final String Tokenization_Key_Braintree="sandbox_5r4vg2mv_z2gj4jrq4wqkfz2z";

    public static boolean isConnectionToInternet(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService( Context.CONNECTIVITY_SERVICE );
        if (connectivityManager != null) {

            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null) {

                for (int i = 0; i < info.length; i++) {

                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                        return true;
                }
            }
        }
        return false;
    }

    public static String getToken() {

        if (user.getToken() != null)
            return "Bearer " + user.getToken();
        else {
            Log.d( "Token Error: ", "Token is null !!!!!!!" );
            return null;
        }

    }

    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {

        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

}
