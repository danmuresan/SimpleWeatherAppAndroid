package com.example.muresand.simpleweatherapp.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by muresand on 9/7/2017.
 */

public class DownloadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

    private ImageView imageViewControl;

    public DownloadImageAsyncTask(ImageView imageViewControl) {
        this.imageViewControl = imageViewControl;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String imageUrl = strings[0];
        Bitmap icon = null;
        try {
            InputStream in = new URL(imageUrl).openStream();
            icon = BitmapFactory.decodeStream(in);
        } catch (Exception ex) {
            Log.e("Error", ex.getMessage());
        }

        return icon;
    }

    protected void onPostExecute(Bitmap result) {
        imageViewControl.setImageBitmap(result);
    }
}
