package com.example.wordle;

import static android.app.PendingIntent.FLAG_IMMUTABLE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Calendar;

public class Trivle extends AppCompatActivity {
    Button CountriesGame,OtherTriv,StatesTriv,YesLogOut,NoLogOut;
    Dialog d;
    public Calendar calendar= Calendar.getInstance();
    public PendingIntent pendingIntent;
    public AlarmManager alarmManager;
    private boolean isGoingToHomeScreen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivle);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        CountriesGame = (Button) findViewById(R.id.CountriesTriv);
        OtherTriv = (Button) findViewById(R.id.OtherTriv);
        StatesTriv = (Button) findViewById(R.id.StatesTriv);
        d=new Dialog(this);
        CountriesGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isGoingToHomeScreen=true;
                Intent intent = new Intent(Trivle.this, WorldGame.class);
                startActivity(intent);
            }
        });
        OtherTriv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isGoingToHomeScreen=true;
                Intent intent = new Intent(Trivle.this, NBATrivia.class);
                startActivity(intent);
            }
        });
        StatesTriv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isGoingToHomeScreen=true;
                Intent intent = new Intent(Trivle.this, StatesTrivia.class);
                startActivity(intent);
            }
        });
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottonnav);
        bottomNavigationView.setSelectedItemId(R.id.action_Main);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.action_Settings)
                {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Trivle.this, Settings.class);
                    startActivity(intent);
                    return true;
                }
                if(id==R.id.action_Main)
                {
                    isGoingToHomeScreen=true;
                    Intent intent;
                    intent = new Intent(Trivle.this, LoggedMainActivity.class);
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
                            Intent intent = new Intent(Trivle.this,MainActivity.class);
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
        pendingIntent= PendingIntent.getBroadcast(this,0,intent,FLAG_IMMUTABLE);
        alarmManager.setInexactRepeating(AlarmManager.RTC,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O)
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
