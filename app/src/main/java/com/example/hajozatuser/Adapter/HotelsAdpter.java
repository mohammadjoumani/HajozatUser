package com.example.hajozatuser.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hajozatuser.Common.Common;
import com.example.hajozatuser.Details_hotel;
import com.example.hajozatuser.Home;
import com.example.hajozatuser.Model.Hotels;
import com.example.hajozatuser.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HotelsAdpter extends BaseAdapter {

    private Context context;
    public static List<Hotels> items;
    public LayoutInflater inflater;
    AlertDialog.Builder builder;

    public HotelsAdpter(Context appcontext, List<Hotels> items) {
        this.context = appcontext;
        this.items = items;
        inflater = LayoutInflater.from( appcontext );
        builder = new AlertDialog.Builder(context);
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
            v = LayoutInflater.from( context ).inflate( R.layout.activity_list_item, parent, false );
            viewHolder = new ViewHolder( v );
            v.setTag( viewHolder );
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.hotelname.setText( items.get( position ).getHotel_name() );
        viewHolder.cityname.setText( items.get( position ).getCity_name() );
        viewHolder.roonumber.setText( items.get( position ).getCount_rooms() + " " + "Rooms" );
        viewHolder.star.setRating( items.get( position ).getStar() );
        if (items.get( position ).getImage_Path().contains( "drive" )) {

            Picasso.get().load( items.get( position ).getImage_Path() ).into( viewHolder.imghotel );

        }

        viewHolder.share.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //View view = LayoutInflater.from( context ).inflate( R.layout.dailog_share, null );

//                LinearLayout facebook = (LinearLayout)view.findViewById(R.id.select_facebook);
//                LinearLayout whatsApp = (LinearLayout)view.findViewById(R.id.select_whatsapp);
//                LinearLayout messenger = (LinearLayout)view.findViewById(R.id.select_messenger);
//                LinearLayout instagram = (LinearLayout)view.findViewById(R.id.select_instagram);

//                AlertDialog.Builder builder = new AlertDialog.Builder(context)
//                        .setTitle("Share")
//                        .setMessage("Share with :");
//                //builder.setView(view);
//                AlertDialog alertDialog=builder.create();
//                Log.d("aa", String.valueOf("mohammad"));
//                alertDialog.show();

            }
        } );

        return v;

    }
    private static class ViewHolder {

        public TextView cityname;
        public TextView hotelname;
        public TextView roonumber;
        public ImageView imghotel;
        public RatingBar star;
        public ImageView share;


        public ViewHolder(View v) {
            cityname = v.findViewById( R.id.hotel_city );
            hotelname = v.findViewById( R.id.hotel_name );
            roonumber = v.findViewById( R.id.roomNumber );
            imghotel = v.findViewById( R.id.hotel_image );
            star = v.findViewById( R.id.hotel_rating );
            share=v.findViewById( R.id.share_image );

        }
    }
}
