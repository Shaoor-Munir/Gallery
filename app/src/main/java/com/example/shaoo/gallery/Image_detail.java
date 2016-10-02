package com.example.shaoo.gallery;

import android.app.WallpaperManager;
import android.content.res.AssetManager;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.IOException;

public class Image_detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        String path = getIntent().getStringExtra("path");
        AssetManager mgr = getAssets();
        ImageView image = (ImageView) findViewById(R.id.imageDisplay);
        try {
            image.setImageDrawable(Drawable.createFromStream(mgr.open(path), null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        else if(item.getItemId() == R.id.app_bar_set_wallpaper)
        {
            WallpaperManager wmgr = WallpaperManager.getInstance(getApplicationContext());
            String path = getIntent().getStringExtra("path");
            AssetManager mgr = getAssets();
            ImageView image = (ImageView) findViewById(R.id.imageDisplay);
            try {
                Drawable drw = Drawable.createFromStream(mgr.open(path), null);

                wmgr.setBitmap(((BitmapDrawable)drw).getBitmap());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
