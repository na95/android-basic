package com.anle.gridlayoutdynamicbuttons;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        Field[] fields = R.raw.class.getFields();
        for(int count = 0; count < fields.length; count ++){

            Log.i("Raw Asset: ", fields[count].getName());

            final String name = fields[count].getName();

            // Create button attributes inside gridlayout
            GridLayout.LayoutParams param = new GridLayout.LayoutParams();
	    // Use % 2 because this layout accepts only 2 columns as declaration in .xml file
            param.columnSpec = GridLayout.spec(count % 2, 1.0f);
	    // Use / 2 to make sure 1 row has 2 items
            param.rowSpec = GridLayout.spec(count / 2, 1.0f);
            param.setMargins(5,5,5,5);

            // Create buttons and apply attributes' settings
            Button btn = new Button(this);
            btn.setLayoutParams(param);
            btn.setText(name);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    MediaPlayer media = MediaPlayer.create(MainActivity.this, getResources().getIdentifier(name, "raw", getPackageName()));
                    media.start();
                }
            });

            gridLayout.addView(btn);
        }
    }
}
