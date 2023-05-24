package com.example.wordle;

import static android.app.PendingIntent.FLAG_IMMUTABLE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public Calendar calendar= Calendar.getInstance();
    public PendingIntent pendingIntent;
    public AlarmManager alarmManager;
    private boolean isGoingToHomeScreen = false;
    ImageButton  GlobGame,ScoreBoard,Trivle,Flagle,Modes,Settings,Credits,SignUp,Search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SignUp = (ImageButton) findViewById(R.id.imageView4);
        Trivle=(ImageButton) findViewById(R.id.imageView51);
        Modes=(ImageButton) findViewById(R.id.imageView52);
        Flagle=(ImageButton) findViewById(R.id.imageView53);
        ScoreBoard=(ImageButton) findViewById(R.id.imageView54);
        GlobGame=(ImageButton) findViewById(R.id.imageView55);
        Search=(ImageButton) findViewById(R.id.imageView56);
        Settings=(ImageButton) findViewById(R.id.imageView57);
        Credits=(ImageButton) findViewById(R.id.imageView58);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        startService(new Intent(getApplicationContext(), MyService.class));
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottonnav);
        bottomNavigationView.setSelectedItemId(R.id.action_Main);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.action_login)
                {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                    return true;
                }
                if(id==R.id.action_Credits)
                {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(MainActivity.this, Credits.class);
                    startActivity(intent);
                    return true;
                }
                if(id==R.id.action_SignUp)
                {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(MainActivity.this, SignUp.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
        Trivle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Log In First", Toast.LENGTH_SHORT).show();
        }
            });
        Modes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Log In First", Toast.LENGTH_SHORT).show();
            }
        });
        Flagle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Log In First", Toast.LENGTH_SHORT).show();
            }
        });
        ScoreBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Log In First", Toast.LENGTH_SHORT).show();
            }
        });
        GlobGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Log In First", Toast.LENGTH_SHORT).show();
            }
        });
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Log In First", Toast.LENGTH_SHORT).show();
            }
        });
        Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Log In First", Toast.LENGTH_SHORT).show();
            }
        });
        Credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isGoingToHomeScreen=true;
                Intent intent =new Intent(MainActivity.this,Credits.class);
                startActivity(intent);            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isGoingToHomeScreen=true;
                Intent intent =new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
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
