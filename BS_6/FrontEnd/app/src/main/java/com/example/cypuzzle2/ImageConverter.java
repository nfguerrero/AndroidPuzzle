package com.example.cypuzzle2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class ImageConverter extends BaseAdapter { //much of this class was implemented by following a great youtube tutorial on Android Studio bmp icon creation on channel "thenewboston". Another great tutorial on youtube by Dave Park also was followed for some parts
    private Context mContext;
    private AssetManager am;
    private String[] files;


    public ImageConverter(Context c) {
        mContext = c;
        am = mContext.getAssets();
        try {
            files  = am.list("img");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCount() {
        return files.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new background ImageView for each item
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.img_grid, null);
        }

        final ImageView imageView = convertView.findViewById(R.id.gridImageview);
        imageView.setImageBitmap(null);
        // run image related code after the view was laid out
        imageView.post(new Runnable() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void run() {
                new AsyncTask<Void, Void, Void>() {
                    private Bitmap bitmap;

                    @Override
                    protected Void doInBackground(Void... voids) {
                        bitmap = getPicFromAsset(imageView, files[position]);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        imageView.setImageBitmap(bitmap);
                    }
                }.execute();
            }
        });

        return convertView;
    }

    private Bitmap getPicFromAsset(ImageView imageView, String assetName) { //this passes the image to the mainpuzzleactivity to create a new puzzle
        // Get the dimensions of the view
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        if(targetW == 0 || targetH == 0) { //no dimensions for view
            return null;
        }

        try {
            InputStream is = am.open("img/" + assetName);
            // Get the dimensions to make bmp
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(is, new Rect(-1, -1, -1, -1), bmOptions);
            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;

            // scale img
            int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

            is.reset();

            // decode img to bmp
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;
            bmOptions.inPurgeable = true;

            return BitmapFactory.decodeStream(is, new Rect(-1, -1, -1, -1), bmOptions);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}


