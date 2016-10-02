package com.example.shaoo.gallery;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class Image_detail extends AppCompatActivity {

    private final int MY_PERMISSIONS_REQUEST_SET_WALLPAPER = 1;
    boolean permission_granted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        String path = getIntent().getStringExtra("path");
        setTitle(path);
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
        } else {
            if (item.getItemId() == R.id.app_bar_set_wallpaper) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Image_detail.this);
                builder.setTitle("Are you sure you want to set this image as Wallpaper?");
                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (checkSelfPermission(Manifest.permission.SET_WALLPAPER) == PackageManager.PERMISSION_GRANTED) {
                            set_wallpaper();
                        } else {

                            requestPermissions(new String[]{Manifest.permission.SET_WALLPAPER}, 10);

                            if (checkSelfPermission(Manifest.permission.SET_WALLPAPER) == PackageManager.PERMISSION_GRANTED) {
                                set_wallpaper();
                            } else
                                Toast.makeText(Image_detail.this, "Permission to set wallpaper denied by user.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();


            } else if (item.getItemId() == R.id.app_bar_delete) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Image_detail.this);
                builder.setTitle("Are you sure you want to delete this image?");
                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String path = getIntent().getStringExtra("path");
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("path", path);
                        setResult(MainGridWindow.RESULT_OK, returnIntent);
                        finish();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        }
        return super.onOptionsItemSelected(item);
    }


    void set_wallpaper() {
        WallpaperManager wmgr = WallpaperManager.getInstance(getApplicationContext());
        String path = getIntent().getStringExtra("path");
        AssetManager mgr = getAssets();
        ImageView image = (ImageView) findViewById(R.id.imageDisplay);
        try {
            Drawable drw = Drawable.createFromStream(mgr.open(path), null);

            wmgr.setBitmap(((BitmapDrawable) drw).getBitmap());
            Toast.makeText(Image_detail.this, path + " has been successfully set as your home screen wallpaper.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SET_WALLPAPER: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    permission_granted = true;
                    // permission was granted, yay! Do the
                    set_wallpaper();
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(Image_detail.this, "Permission denied to change your wallpaper", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}
