package com.example.hajozatuser.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hajozatuser.BuildConfig;
import com.example.hajozatuser.Common.Common;
import com.example.hajozatuser.Details_hotel;
import com.example.hajozatuser.Home;
import com.example.hajozatuser.Model.FavHis;
import com.example.hajozatuser.Model.Hotels;
import com.example.hajozatuser.R;
import com.example.hajozatuser.moreDetail;
import com.example.hajozatuser.singin;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.hajozatuser.Common.Common.o;

public class HotelsAdpter extends BaseAdapter {

    private Context context;
    public static List<Hotels> items;
    public LayoutInflater inflater;

    public HotelsAdpter(Context appcontext, List<Hotels> items) {
        this.context = appcontext;
        this.items = items;
        inflater = LayoutInflater.from( appcontext );
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder viewHolder = null;
        if (v == null) {
            v = LayoutInflater.from( context ).inflate( R.layout.list_item, parent, false );
            viewHolder = new ViewHolder( v );
            v.setTag( viewHolder );
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.hotelname.setText( items.get( position ).getHotel_name() );
        viewHolder.cityname.setText( items.get( position ).getCity_name() );
        viewHolder.roonumber.setText( items.get( position ).getCount_rooms() + " " + "Rooms" );
        viewHolder.star.setRating( items.get( position ).getStar() );

        if (Common.user.getImage_Path() != null) {


            if (items.get( position ).getImage_Path().contains( "drive" ))
                Picasso.get().load( items.get( position ).getImage_Path() ).into( viewHolder.imghotel );
            else
                Picasso.get().load(Common.BASE_URL + "photos/" +items.get( position ).getImage_Path()).into(viewHolder.imghotel);
        }

        return v;

    }
    private static class ViewHolder {

        public TextView cityname;
        public TextView hotelname;
        public TextView roonumber;
        public ImageView imghotel;
        public RatingBar star;

        public ViewHolder(View v) {
            cityname = v.findViewById( R.id.hotel_city );
            hotelname = v.findViewById( R.id.hotel_name );
            roonumber = v.findViewById( R.id.roomNumber );
            imghotel = v.findViewById( R.id.hotel_image );
            star = v.findViewById( R.id.hotel_rating );

        }
    }
}