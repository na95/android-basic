package com.anle.audio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.audio);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        SeekBar volumeController = findViewById(R.id.seekBarVolume);
        volumeController.setMax(maxVolume);
        volumeController.setProgress(maxVolume);
        volumeController.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Info", "Progress: "+ progress);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        final SeekBar scrubController = findViewById(R.id.seekBarScrub);
        scrubController.setMax(mediaPlayer.getDuration());
        scrubController.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                // if one didn't check it's from user or not, it would create horrendous sound
                // due to the synchronized thread will use this listener to change progress also
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    public void play(View view)
    {
        mediaPlayer.start();

/*        new Thread(new Runnable() {

            @Override
            public void run() {
                while(mediaPlayer != null && mediaPlayer.getCurrentPosition() < mediaPlayer.getDuration())
                {
                    int mediaPos_new = mediaPlayer.getCurrentPosition();
                    SeekBar scrubController = findViewById(R.id.seekBarScrub);
                    scrubController.setProgress(mediaPos_new);
                    Log.i("INFO", "Thread running - progress: "+ mediaPos_new);
                    try {
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();*/

        // Timer handle this process better than thread
	// because Thread uses higher CPU usage
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SeekBar scrubController = findViewById(R.id.seekBarScrub);
                scrubController.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 300);
    }

    public void pause(View view) throws InterruptedException {
        mediaPlayer.pause();
    }
}
