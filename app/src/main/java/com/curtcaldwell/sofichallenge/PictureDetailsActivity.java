package com.curtcaldwell.sofichallenge;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;

public class PictureDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.picture_detail);

       getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        ImageView arrowView = findViewById(R.id.back_arrow);

       arrowView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               onBackPressed();
           }
       });

        getWindow().getDecorView().setBackgroundColor(Color.BLACK);

        TextView pictureText = findViewById(R.id.pic_title);

        ImageView pictureImage = findViewById(R.id.pic_image_view);

        pictureText.setText(getIntent().getStringExtra("title"));

        Picasso.get().load(getIntent().getStringExtra("link")).placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_background).into(pictureImage);

        arrowView.bringToFront();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

}
