package com.curtcaldwell.sofichallenge;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.curtcaldwell.sofichallenge.model.CustomDisplayItem;
import com.squareup.picasso.Picasso;


public class PicViewHolder extends RecyclerView.ViewHolder {

    ImageView picView;
    TextView titleView;
    View root;


    public PicViewHolder(@NonNull View itemView) {
        super(itemView);

        picView = itemView.findViewById(R.id.pic);
        titleView = itemView.findViewById(R.id.title);
        root = itemView;
    }

    public void SetData(CustomDisplayItem item, View.OnClickListener listener) {
        Picasso.get().load(item.getLink()).resize(1000,1000).centerCrop().placeholder(R.drawable.ic_image).error(R.drawable.ic_image).into(picView);
        titleView.setText(item.getTitle());

        root.setOnClickListener(listener);
    }
}
