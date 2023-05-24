package com.example.wordle;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service {
    private MediaPlayer mediaPlayer;
    public static int song=R.raw.iwannabeyours;

    @Nullable
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    public void onCreate(){
        mediaPlayer = MediaPlayer.create(getApplicationContext(), song);
        mediaPlayer.setLooping(true);
    }

    public void onStart(Intent intent, int start){
        mediaPlayer.start();
    }

    public void onDestroy(){
        mediaPlayer.stop();
    }
}