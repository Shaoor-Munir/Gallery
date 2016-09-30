package com.example.shaoo.gallery;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    private Bitmap[]assetsImages;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        try {
            load_images();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return assetsImages.length;
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
            mImageView.setLayoutParams(new GridView.LayoutParams(450, 400));
            mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mImageView.setPadding(0, 0, 0, 0);
        }
        else
        {
            mImageView = (ImageView) convertView;
        }
        mImageView.setImageBitmap(assetsImages[position]);
        return mImageView;

    }

    private void load_images() throws IOException {
        AssetManager mgr = mContext.getAssets();

        String[] imagePaths = mgr.list("");
        assetsImages= new Bitmap[imagePaths.length];

        for(int i=0;i<imagePaths.length;i++)
        {
            assetsImages[i] = BitmapFactory.decodeStream(mgr.open(imagePaths[i]));
        }

    }
}
