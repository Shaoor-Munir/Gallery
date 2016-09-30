package com.example.shaoo.gallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.IOException;

public class MainGridWindow extends AppCompatActivity {

    private GridView mGridView;
    private ImageAdapter mImageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_grid_window);
        mGridView = (GridView) findViewById(R.id.gridView);

        try {
            mImageAdapter = new ImageAdapter(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mGridView.setAdapter(mImageAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String path = mImageAdapter.return_path(position);
                

            }
        });
    }
}

