package com.example.downloadingwebcontent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    public class DownloadTask extends AsyncTask<String, Void, String>{

        ProgressDialog progDailog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progDailog = new ProgressDialog(MainActivity.this);
            progDailog.setMessage("Wait a minute, babe...");
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.setCancelable(false);
            progDailog.show();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progDailog.dismiss();
            TextView textView = findViewById(R.id.textView);
            textView.setText(result);
        }

        @Override
        protected String doInBackground(String... strings) {

            Log.i("URL", strings[0]);
            try {
                // Convert string to URL object
                URL url = new URL(strings[0]);

                // To make the website becomes available. The header fields and the contents of the website can be accessed
                URLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                String result = null;

                while (data != -1)
                {
                    char current = (char) data;
                    result += current;
                    data = reader.read();

                }

                return result;

            }catch (Exception e)
            {
                e.printStackTrace();
                return "Fail";
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new DownloadTask().execute("https://www.geeksforgeeks.org/");
    }
}
