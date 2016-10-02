package com.example.shaoo.gallery;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.PersistableBundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainGridWindow extends AppCompatActivity {

    private static final int DELETION_REQUEST_SENT = 1;
    private GridView mGridView;
    private ImageAdapter mImageAdapter;
    private ArrayList<String> deletedItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main_grid_window);
        mGridView = (GridView) findViewById(R.id.gridView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        try {
            mImageAdapter = new ImageAdapter(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mGridView.setAdapter(mImageAdapter);

        if(savedInstanceState == null) {

            deletedItems = new ArrayList<String>();
        }
        else
        {
            deletedItems = (ArrayList<String>) savedInstanceState.getSerializable("deletedItems");

            for(int i=0;i<deletedItems.size();i++)
            {
                mImageAdapter.remove_path(deletedItems.get(i));
            }
            mImageAdapter.notifyDataSetChanged();
            mGridView.setAdapter(mImageAdapter);
        }


        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String path = mImageAdapter.return_path(position);
                load_image(path);

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putSerializable("deletedItems", deletedItems);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void load_image(String path){
        Intent mIntent = new Intent(this, Image_detail.class);
        mIntent.putExtra("path", path);

        startActivityForResult(mIntent, DELETION_REQUEST_SENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DELETION_REQUEST_SENT && resultCode == RESULT_OK && data != null) {
            String path = data.getStringExtra("path");
            deletedItems.add(path);
            mImageAdapter.remove_path(path);
            mImageAdapter.notifyDataSetChanged();
            mGridView.setAdapter(mImageAdapter);

            Toast.makeText(MainGridWindow.this, path+ "has been successfully deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}

