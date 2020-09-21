package com.example.hajozatuser.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;

import com.example.hajozatuser.Model.comments;
import com.example.hajozatuser.R;
import java.util.List;

public class CommentsAdapter extends BaseAdapter {

    private Context context;
    public static List<comments> items;
    public LayoutInflater inflater;

    public CommentsAdapter(Context appcontext, List<comments> items) {
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
        CommentsAdapter.ViewHolder viewHolder = null;
        if (v == null) {
            v = LayoutInflater.from( context ).inflate( R.layout.list_item_comments, parent, false );
            viewHolder = new CommentsAdapter.ViewHolder( v );
            v.setTag( viewHolder );
        } else {
            viewHolder = (CommentsAdapter.ViewHolder) v.getTag();
        }
        viewHolder.nameComments.setText( items.get( position ).getName() );
        viewHolder.comments.setText( items.get( position ).getComment() );
        return v;

    }
    private static class ViewHolder {

        public TextView nameComments;
        public TextView comments;


        public ViewHolder(View v) {
            nameComments = v.findViewById( R.id.name_com );
            comments = v.findViewById( R.id.txt_comments );


        }
    }
}