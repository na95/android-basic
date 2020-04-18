package com.anle.whatstheweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    TextView textViewInformation;

    public class Downloader extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... strings) {

            try{

                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream in = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();
                String result = "";
                while (data != -1){

                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }

                return result;

            }catch (Exception e)
            {
                e.printStackTrace();
                return "";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            System.out.println("String: "+s);

            try {
                JSONObject object = new JSONObject(s);
                String weatherInfo = object.getString("weather");
                JSONArray array = new JSONArray(weatherInfo);

                String message = "";

                for (int i = 0; i < array.length(); i++)
                {
                    JSONObject jobject = array.getJSONObject(i);

                    String main = jobject.getString("main");
                    String description = jobject.getString("description");

                    if (!main.equals("") && !description.equals("")) {
                        message += main + ": " + description + "\n";
                    }
                }

                textViewInformation.setText(message);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "It's not a city, bro!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void getWeather(View v)
    {
        EditText editTextCity = findViewById(R.id.editTextCity);
        String city = editTextCity.getText().toString();
        textViewInformation.setText("");

        try {

            Downloader downloader = new Downloader();
            downloader.execute("http://api.openweathermap.org/data/2.5/weather?q="+city+"&APPID=7c8f683d4acf0bbc5e1d06733b397d70");

            //  Hide keyboard
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(editTextCity.getWindowToken(), 0);

        }catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewInformation = findViewById(R.id.textViewInformation);

    }
}
