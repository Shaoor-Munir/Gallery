package com.example.shaoo.gallery;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static android.R.id.list;

public class ImageAdapter extends BaseAdapter {


    private Context mContext;
    private AssetManager mgr;
    private String [] imagePaths;


    ImageAdapter(Context c) throws IOException {
        mContext = c;
        mgr = mContext.getAssets();
        imagePaths = mgr.list("");
    }

    @Override
    public int getCount() {
        return imagePaths.length;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView mImageView;

        if(convertView == null)
        {
            mImageView = new ImageView(mContext);
            mImageView.setLayoutParams(new GridView.LayoutParams(500, 400));
            mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mImageView.setPadding(0, 0, 0, 0);
        }
        else
        {
            mImageView = (ImageView) convertView;
        }
        try {
            mImageView.setImageDrawable(Drawable.createFromStream(mgr.open(imagePaths[position]), null));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mImageView;

    }

    public String return_path(int position){
        return imagePaths[position];
    }
}
