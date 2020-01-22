package com.anle.countdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView textViewTimer;
    private int actualSeconds;
    private CountDownTimer timer;
    final private String text = "Set your time by the green ball above and" +
            "\n press below button to start your countdown.";

    public void start(View view)
    {
        final Button btnStart = findViewById(R.id.buttonStart);
        btnStart.setVisibility(View.GONE);
        final Button btnCancel = findViewById(R.id.buttonCancel);
        btnCancel.setVisibility(View.VISIBLE);
        final SeekBar seekBarTime = findViewById(R.id.seekBarTime);
        seekBarTime.setEnabled(false);

        /// Second way, it's has more steps, but it saves a little bit memory
        /*String [] time = textViewTimer.getText().toString().split(":");
        int min = Integer.parseInt(time[0]);
        int second = Integer.parseInt((time[1]));
        int miliseconds = min*60*1000 + second*1000;*/
        int miliseconds = actualSeconds*1000;
        setNotificationText("");

        timer = new CountDownTimer(miliseconds, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished) {
                setTime((int) millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                btnStart.setVisibility(View.VISIBLE);
                btnCancel.setVisibility(View.GONE);
                setNotificationText("Finish!!!");
                seekBarTime.setEnabled(true);
                MediaPlayer.create(MainActivity.this, R.raw.done).start();
            }

        }.start();



    }

    public void cancel(View view)
    {
        timer.cancel();

        Button btnStart = findViewById(R.id.buttonStart);
        Button btnCancel = findViewById(R.id.buttonCancel);
        btnStart.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.GONE);
        setTime(actualSeconds);
        setNotificationText(text);
        SeekBar seekBarTime = findViewById(R.id.seekBarTime);
        seekBarTime.setEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBarTime = findViewById(R.id.seekBarTime);
        textViewTimer = findViewById(R.id.textViewTimer);
        actualSeconds = 0;
        setNotificationText(text);

        seekBarTime.setMax(300); //5 mins = 60x5 seconds
        seekBarTime.setProgress(0);
        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                actualSeconds = progress;
                setTime(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void setTime(int progress)
    {
        int min = progress / 60;
        int second = progress % 60;
        String minFormat = "0" + min;
        String secondFormat = second + "";

        if (second < 10)
        {
            secondFormat = "0" + second;
        }

        textViewTimer.setText(minFormat + ":" + secondFormat);
    }

    public void setNotificationText (String text)
    {
        TextView textViewNotification = findViewById(R.id.textViewNotification);
        textViewNotification.setText(text);
    }
}
