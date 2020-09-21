package com.example.hajozatuser.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hajozatuser.Common.Common;
import com.example.hajozatuser.Model.FavHis;
import com.example.hajozatuser.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavHisAdapter extends RecyclerView.Adapter<FavHisAdapter.ViewHolder> {
    private Context context;
    public static List<FavHis> items;
    public LayoutInflater inflater;

    public FavHisAdapter(Context context,List<FavHis>items) {
        this.context = context;
        this.items=items;
        inflater = LayoutInflater.from( context );
    }

    @NonNull
    @Override
    public FavHisAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate( R.layout.list_item, parent, false);
        FavHisAdapter.ViewHolder viewHolder = new FavHisAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavHisAdapter.ViewHolder holder, int position) {

        holder.hotelname.setText( items.get( position ).getHotel_name() );
        holder.cityname.setText( items.get( position ).getCity_name() );
        holder.roonumber.setText( items.get( position ).getCount_rooms() + " " + "Rooms" );
        holder.star.setRating( items.get( position ).getStar() );

        if (Common.user.getImage_Path() != null) {


            if (items.get( position ).getImage_Path().contains( "drive" ))
                Picasso.get().load( items.get( position ).getImage_Path() ).into( holder.imghotel );
            else
                Picasso.get().load(Common.BASE_URL + "photos/" +items.get( position ).getImage_Path()).into(holder.imghotel);
        }

      

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView cityname;
        public TextView hotelname;
        public TextView roonumber;
        public ImageView imghotel;
        public RatingBar star;

        public ViewHolder(@NonNull View v) {
            super( v );
            cityname = v.findViewById( R.id.hotel_city );
            hotelname = v.findViewById( R.id.hotel_name );
            roonumber = v.findViewById( R.id.roomNumber );
            imghotel = v.findViewById( R.id.hotel_image );
            star = v.findViewById( R.id.hotel_rating );
            Log.d( "a5", String.valueOf("mmmm" ) );
        }
    }
}
