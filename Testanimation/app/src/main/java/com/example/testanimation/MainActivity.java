package com.example.testanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    boolean catIsShowing = true;

    public void fade(View view)
    {
        Log.i ("Info", "Pressed fade button");
        if (catIsShowing) {
            ImageView imgCat = findViewById(R.id.imgCat);
            imgCat.animate().alpha(0).setDuration(1000);
            ImageView imgDog = findViewById(R.id.imgDog);
            imgDog.animate().alpha(1).setDuration(1000);
            catIsShowing = false;
        }
        else
        {
            ImageView imgCat = findViewById(R.id.imgCat);
            imgCat.animate().alpha(1).setDuration(1000);
            ImageView imgDog = findViewById(R.id.imgDog);
            imgDog.animate().alpha(0).setDuration(1000);
            catIsShowing = true;
        }
    }

    public void scale(View view)
    {
        Log.i ("Info", "Pressed scale button");
        if (catIsShowing) {
            ImageView imgCat = findViewById(R.id.imgCat);
            imgCat.animate().scaleX(0.5f).scaleY(0.5f).alpha(0).setDuration(1000);
            ImageView imgDog = findViewById(R.id.imgDog);
            imgDog.animate().scaleX(1f).scaleY(1f).alpha(1).setDuration(1000);
            catIsShowing = false;
        }
        else {
            ImageView imgCat = findViewById(R.id.imgCat);
            imgCat.animate().scaleX(1f).scaleY(1f).alpha(1).setDuration(1000);
            ImageView imgDog = findViewById(R.id.imgDog);
            imgDog.animate().scaleX(0.5f).scaleY(0.5f).alpha(0).setDuration(1000);
            catIsShowing = true;
        }
    }

    public void rotate(View view)
    {
        Log.i ("Info", "Pressed rotate button");
        if (catIsShowing) {
            ImageView imgCat = findViewById(R.id.imgCat);
            imgCat.animate().rotation(360*3).alpha(0).setDuration(1000);
            ImageView imgDog = findViewById(R.id.imgDog);
            imgDog.animate().rotation(360*5).alpha(1).setDuration(1000);
            catIsShowing = false;
        }
        else {
            ImageView imgCat = findViewById(R.id.imgCat);
            imgCat.animate().rotation(360*5).alpha(1).setDuration(1000);
            ImageView imgDog = findViewById(R.id.imgDog);
            imgDog.animate().rotation(360*3).alpha(0).setDuration(1000);
            catIsShowing = true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
