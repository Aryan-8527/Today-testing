package com.example.sqldatastore;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.VIEWHOLDER> {

    private Context mContext;
    private List<Model_class> mData ;

    public CustomAdapter(Context mContext, List<Model_class> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public CustomAdapter.VIEWHOLDER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.sample_layout , parent , false);
        return new VIEWHOLDER(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.VIEWHOLDER holder, int position) {
        Model_class model = mData.get(position);
        holder.title.setText(model.getTitle());
        holder.details.setText(model.getDetails());

                  // for image through on Internet //

        Glide
                .with(mContext)
                .load(mData.get(position).getImage())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class VIEWHOLDER extends RecyclerView.ViewHolder {
        TextView title , details ;
        ImageView image ;
        CardView cardView;
        public VIEWHOLDER(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_view_title);
            details = itemView.findViewById(R.id.text_view_details);
            image = itemView.findViewById(R.id.image_view);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
