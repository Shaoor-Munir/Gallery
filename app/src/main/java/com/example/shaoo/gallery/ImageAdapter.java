package com.example.shaoo.gallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {


    private Context mContext;
    private Bitmap[]assetsImages;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        load_images();
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
            mImageView.setLayoutParams(new GridView.LayoutParams(200, 150));
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

    private void load_images(){


    }
}
