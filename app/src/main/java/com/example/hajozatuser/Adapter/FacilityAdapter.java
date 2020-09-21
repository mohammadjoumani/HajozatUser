package com.example.hajozatuser.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hajozatuser.Model.Facility;
import com.example.hajozatuser.Model.Rules;
import com.example.hajozatuser.R;

import java.util.ArrayList;
import java.util.List;

public class FacilityAdapter  extends RecyclerView.Adapter<FacilityAdapter.ViewHolder> {

    private Context context;
    public static List<Facility> items;
    public LayoutInflater inflater;

    public FacilityAdapter(Context appcontext, List<Facility> items ) {
        this.context = appcontext;
        this.items = items;
        inflater = LayoutInflater.from( appcontext );
    }

    @NonNull
    @Override
    public FacilityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate( R.layout.list_item_rec_rule, parent, false);
        FacilityAdapter.ViewHolder viewHolder = new FacilityAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FacilityAdapter.ViewHolder holder, int position) {
        holder.txt.setText( items.get( position ).getName() );

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        TextView txt;


        public ViewHolder(View v) {
            super(v);
            txt=v.findViewById( R.id.txt_rule );

            Log.d( "a3", String.valueOf("mmmmmm" ) );

        }
    }
}
