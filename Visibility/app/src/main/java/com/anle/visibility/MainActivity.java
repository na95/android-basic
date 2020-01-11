package com.anle.visibility;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textview2;

    public void gone(View view)
    {
        // View.GONE doesn't take any space in layout -  except kind of constraint layout
        textview2.setVisibility(View.GONE);
    }

    public void invisible(View view)
    {
        // View.INVISIBLE will take up spaces in layout - except kind of constraint layout
        textview2.setVisibility(View.INVISIBLE);
    }

    public void visible(View view)
    {
        textview2.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview2 = findViewById(R.id.textView2);
    }
}
