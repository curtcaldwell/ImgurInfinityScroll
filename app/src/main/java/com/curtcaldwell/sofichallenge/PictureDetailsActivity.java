package com.curtcaldwell.sofichallenge;

import android.graphics.Color;
import android.os.Bundle;

import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class PictureDetailsActivity extends AppCompatActivity {

    private TextView pictureText;
    private TextView descriptionText;
    private ImageView pictureImage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_detail);
        getWindow().getDecorView().setBackgroundColor(Color.BLACK);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        pictureText = findViewById(R.id.pic_title);
        pictureImage = findViewById(R.id.pic_image_view);

        pictureText.setText(getIntent().getStringExtra("title"));


        Picasso.get().load(getIntent().getStringExtra("link")).placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_background).into(pictureImage);


    }


}
