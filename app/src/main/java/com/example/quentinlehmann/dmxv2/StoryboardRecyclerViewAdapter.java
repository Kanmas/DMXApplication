package com.example.quentinlehmann.dmxv2;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by quentin.lehmann on 01/06/2018.
 */

public class StoryboardRecyclerViewAdapter extends RecyclerView.Adapter<StoryboardRecyclerViewAdapter.ViewHolder>  {

    private List<Storyboard> mData;
    private LayoutInflater mInflater;
    private StoryboardRecyclerViewAdapter.ItemClickListener mClickListener;

    StoryboardRecyclerViewAdapter (Context context, List<Storyboard> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate( R.layout.recyclerview_row_storyboard, parent, false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Storyboard element = mData.get(position);
        holder.timeTextView.setText( String.valueOf( element.getDuration() ) );
        holder.nameTextView.setText( element.getName() );
        holder.countTextView.setText( String.valueOf( element.getCount() ) );
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView timeTextView;
        TextView nameTextView;
        TextView countTextView;

        ViewHolder (View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById( R.id.tvStoryboardTime );
            nameTextView = itemView.findViewById( R.id.tvStoryboardName );
            countTextView = itemView.findViewById( R.id.tvElementCount );
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick( view, getAdapterPosition() );
        }
    }

    public interface ItemClickListener {
        void onItemClick (View view, int position);
    }

    public void setmClickListener (ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public List<Storyboard> getmData () {return mData;}

    public Storyboard getItem (int id) {
        return mData.get( id );
    }
}
