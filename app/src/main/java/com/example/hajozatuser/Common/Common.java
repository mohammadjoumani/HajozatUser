package com.example.hajozatuser.Common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hajozatuser.Model.Hotels;
import com.example.hajozatuser.Model.SlideItem;
import com.example.hajozatuser.Model.User;
import com.example.hajozatuser.R;

import java.util.List;

public class Common {

    public static User user;
    public static Hotels o;
    public static SlideItem slid;


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


}
