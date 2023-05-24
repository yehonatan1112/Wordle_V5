package com.example.wordle;

import static android.app.PendingIntent.FLAG_IMMUTABLE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Calendar;

public class Settings extends AppCompatActivity {
    Dialog d3,d;
    public static Boolean flag = true;
     Boolean isLogged;
     ImageButton EditUser;
    private boolean isGoingToHomeScreen = false;
    Button YesLogOut,NoLogOut,btnAudio,btnAudioChoose,harrybutton,tikatanbutton,comingbutton,billiejeanbutton,iwannabutton;
    public Calendar calendar= Calendar.getInstance();
    public PendingIntent pendingIntent;
    public AlarmManager alarmManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        d=new Dialog(this);
        d3=new Dialog(this);
        btnAudio=findViewById(R.id.Cbtnb);
        EditUser=findViewById(R.id.imageView4);
        btnAudioChoose=findViewById(R.id.Dbtnb);
        isLogged=getIntent().getBooleanExtra("Logged",false);
        EditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isGoingToHomeScreen=true;
                Intent intent = new Intent(Settings.this, SettingsLogin.class);
                startActivity(intent);
            }
        });
        btnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == btnAudio){
                    if (!flag) {
                        startService(new Intent(getApplicationContext(), MyService.class));
                        flag = true;
                        btnAudio.setText("Turn Off Music");
                    }
                    else{
                        stopService(new Intent(getApplicationContext(), MyService.class));
                        flag = false;
                        btnAudio.setText("Turn On Music");
                    }
                }
            }
        });
        btnAudioChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == btnAudioChoose){
                    stopService(new Intent(getApplicationContext(), MyService.class));
                    d3.setContentView(R.layout.music_dialog);
                    harrybutton=d3.findViewById(R.id.Harry);
                    tikatanbutton=d3.findViewById(R.id.tikatan);
                    comingbutton=d3.findViewById(R.id.coming);
                    billiejeanbutton=d3.findViewById(R.id.billiejean);
                    iwannabutton=d3.findViewById(R.id.iwannabeyours);
                    d3.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    d3.setCancelable(true);
                    harrybutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            MyService.song=R.raw.harrypotter;
                            startService(new Intent(getApplicationContext(), MyService.class));
                            d3.dismiss();
                        }
                    });
                    tikatanbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            MyService.song=R.raw.tikatan;
                            startService(new Intent(getApplicationContext(), MyService.class));
                            d3.dismiss();
                        }
                    });
                    comingbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            MyService.song=R.raw.cominghome;
                            startService(new Intent(getApplicationContext(), MyService.class));
                            d3.dismiss();
                        }
                    });
                    iwannabutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            MyService.song=R.raw.iwannabeyours;
                            startService(new Intent(getApplicationContext(), MyService.class));
                            d3.dismiss();
                        }
                    });
                    billiejeanbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            MyService.song=R.raw.billiejean;
                            startService(new Intent(getApplicationContext(), MyService.class));
                            d3.dismiss();
                        }
                    });
                    d3.show();
                }
            }
        });
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottonnav);
        bottomNavigationView.setSelectedItemId(R.id.action_Settings);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.action_Main)
                {
                    isGoingToHomeScreen=true;
                    Intent intent;
                    intent = new Intent(Settings.this, LoggedMainActivity.class);
                    startActivity(intent);
                    return true;
                }
                if(id==R.id.action_LogOut)
                {
                    d.setContentView(R.layout.logout_dialog);
                    YesLogOut=d.findViewById(R.id.yes);
                    d.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    NoLogOut=d.findViewById(R.id.no);
                    d.setCancelable(true);

                    YesLogOut.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            isGoingToHomeScreen=true;
                            Intent intent = new Intent(Settings.this,MainActivity.class);
                            startActivity(intent);
                            d.dismiss();
                        }
                    });
                    NoLogOut.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            d.cancel();
                        }
                    });
                    d.show();
                    return true;
                }
                return false;
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(!isGoingToHomeScreen)
        {
            createNotificationChannel();
            setAlarm();
        }
    }

    public void setAlarm() {
        alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(this,AlarmReceiver.class);
        pendingIntent=PendingIntent.getBroadcast(this,0,intent,FLAG_IMMUTABLE);
        alarmManager.setInexactRepeating(AlarmManager.RTC,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            CharSequence name="androidReminderChannel";
            String description="Channel For Alarm Manger";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel=new NotificationChannel("android",name,importance);
            channel.setDescription(description);
            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}