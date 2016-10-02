package com.example.shaoo.gallery;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
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

class ImageAdapter extends BaseAdapter implements java.io.Serializable{


    private Context mContext;
    private AssetManager mgr;
    private ArrayList<String> imagePaths;


    ImageAdapter(Context c) throws IOException {
        mContext = c;
        imagePaths = new ArrayList<String>();
        mgr = mContext.getAssets();
        String [] files;
        files = mgr.list("");
        check_for_images(files);
    }

    @Override
    public int getCount() {
        return imagePaths.size();
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

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.image_view, parent, false);
            //mImageView = new ImageView(mContext);

            //mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //mImageView.setPadding(0, 0, 0, 0);
        }
        try {
            ((ImageView) convertView).setImageDrawable(Drawable.createFromStream(mgr.open(imagePaths.get(position)), null));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertView;

    }

    String return_path(int position){
        return imagePaths.get(position);
    }

    void check_for_images(String [] files){
        String[] okFileExtensions =  new String[] {"jpg", "png", "gif","jpeg"};

        int j = 0;

        for(int i=0; i< files.length;i++) {
            boolean isImage = false;
            for (String extension : okFileExtensions) {
                if (files[i].toLowerCase().endsWith(extension)) {
                    isImage = true;
                }
            }

            if (isImage)
                imagePaths.add(j++, files[i]);
        }
    }

    void remove_path(String in_path)
    {
        boolean removedpath = false;
        for (int i =0;i<imagePaths.size() && !removedpath;i++)
        {
            if(in_path.compareTo(imagePaths.get(i)) == 0) {
                imagePaths.remove(i);
                removedpath = true;
            }
        }
    }
}
