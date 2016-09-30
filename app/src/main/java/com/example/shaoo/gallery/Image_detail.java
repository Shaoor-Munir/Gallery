package com.example.shaoo.gallery;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;

public class Image_detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        String path = getIntent().getStringExtra("path");
        AssetManager mgr = getAssets();
        ImageView image = (ImageView) findViewById(R.id.imageDisplay);
        try {
            image.setImageDrawable(Drawable.createFromStream(mgr.open(path), null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
