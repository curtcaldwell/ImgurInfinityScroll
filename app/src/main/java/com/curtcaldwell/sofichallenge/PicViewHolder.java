package com.curtcaldwell.sofichallenge;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.curtcaldwell.sofichallenge.model.CustomDisplayItem;
import com.squareup.picasso.Picasso;


class PicViewHolder extends RecyclerView.ViewHolder {

    private ImageView picView;
    private TextView titleView;
    private View root;

    PicViewHolder(@NonNull View itemView) {
        super(itemView);

        picView = itemView.findViewById(R.id.pic);
        titleView = itemView.findViewById(R.id.title);
        root = itemView;
    }

    void SetData(CustomDisplayItem item, View.OnClickListener listener) {
        Picasso.get().load(item.getLink()).resize(1000,1000).placeholder(R.drawable.ic_image).error(R.drawable.ic_image).into(picView);
        titleView.setText(item.getTitle());
        root.setOnClickListener(listener);
    }
}
