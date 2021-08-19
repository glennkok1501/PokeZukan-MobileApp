package com.gmail.pokedex.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageLoader {

    public static Bitmap getImageFromURL(String link) throws IOException {
        URL url = new URL(link);
        Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        return bmp;
    }
}
