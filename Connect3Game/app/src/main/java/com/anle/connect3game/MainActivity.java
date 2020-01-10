package com.anle.connect3game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int player = 1; //red
    boolean gameActive = true;
    int counter = 0;
    int [] gameState = {0,0,0,0,0,0,0,0,0}; //0: empty, 1: player 1, 2: player 2;
    int [][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropIn(View view)
    {
        ImageView img = (ImageView) view;

        int tag = Integer.parseInt(img.getTag().toString());
        if (gameState[tag] != 0 || gameActive == false)
        {
            return;
        }

        gameState[tag] = player;
        counter++;
        img.setTranslationY(-1500);

        if (player == 1) {
            img.setImageResource(R.drawable.red);
            img.animate().translationYBy(1500).setDuration(300);
            player = 2;
        }
        else
        {
            img.setImageResource(R.drawable.yellow);
            img.animate().translationYBy(1500).setDuration(300);
            player = 1;
        }

        String winner = "No one";
        for (int[] winPos : winPositions) {

            if (gameState[winPos[0]] == gameState[winPos[1]] &&
                    gameState[winPos[0]] == gameState[winPos[2]] &&
                    gameState[winPos[0]] != 0) {

                if (gameState[winPos[0]] == 1) {

                    winner = "RED";
                } else {

                    winner = "YELLOW";
                }

                break;
            }
        }

        if (counter == 9 || !winner.equals("No one"))
        {
            TextView text = findViewById(R.id.textView);
            text.setText(winner + " has won!");
            Button btn = findViewById(R.id.button);
            btn.setVisibility(View.VISIBLE);
            text.setVisibility(View.VISIBLE);
            gameActive = false;
        }

    }

    public void playAgain(View view)
    {
        TextView text = findViewById(R.id.textView);
        Button btn = findViewById(R.id.button);
        btn.setVisibility(View.INVISIBLE);
        text.setVisibility(View.INVISIBLE);
        gameActive = true;

        GridLayout layout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i = 0; i < layout.getChildCount(); i++) {
            ImageView child = (ImageView) layout.getChildAt(i);
            child.setImageDrawable(null);
        }

        gameState = new int[] {0,0,0,0,0,0,0,0,0};
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
