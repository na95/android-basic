package com.example.downloadingimages;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public void download(View view)
    {
        Log.i("Click", "OK");
        ImageDownloader task = new ImageDownloader();
        Bitmap myImage;
        try {
            String link = "https://pluspng.com/img-png/png-doraemon-doraemon-new-png-images-411.png";
            if(view.getTag().equals("1"))
            {
                link = "https://i.imgur.com/u6qevEK.png";
            }
            myImage = task.execute(link).get();
            ImageView image = findViewById(R.id.imageView);
            image.setImageBitmap(myImage);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream in = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);

                return myBitmap;

            }catch (Exception e)
            {
                e.printStackTrace();
            }

            return null;
        }
    }
}
