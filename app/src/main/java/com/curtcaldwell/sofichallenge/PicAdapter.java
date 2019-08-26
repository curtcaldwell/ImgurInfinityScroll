package com.curtcaldwell.sofichallenge;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;

import androidx.paging.PagedListAdapter;

import androidx.recyclerview.widget.DiffUtil;

import com.curtcaldwell.sofichallenge.model.CustomDisplayItem;


public class PicAdapter extends PagedListAdapter<CustomDisplayItem, PicViewHolder> {


    MainActivity.PictureClickListener listener;

    protected PicAdapter(@NonNull DiffUtil.ItemCallback<CustomDisplayItem> diffCallback, MainActivity.PictureClickListener pictureClickListener) {
        super(diffCallback);
        listener = pictureClickListener;
    }


    @NonNull
    @Override
    public PicViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.pic_item, viewGroup, false);
        PicViewHolder vh = new PicViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final PicViewHolder picViewHolder, final int i) {
        picViewHolder.SetData(getItem(i), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPictureClicked(getItem(i));
            }
        });


    }


}
