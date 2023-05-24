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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Calendar;

public class ScoreBoard extends AppCompatActivity {
    ImageButton reset;
    Dialog d;
    Button YesLogOut,NoLogOut;
    private boolean isGoingToHomeScreen = false;
    TextView wordlestat1,wordlestat2,hebstat1,hebstat2,flagstat1,flagstat2,globstat1,globstat2,usstat1,usstat2,countstat1,countstat2,nbastat1,nbastat2;
    public Calendar calendar= Calendar.getInstance();
    public PendingIntent pendingIntent;
    public AlarmManager alarmManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        reset=findViewById(R.id.reset);
        d=new Dialog(this);
        wordlestat1=findViewById(R.id.textView59);
        wordlestat2=findViewById(R.id.textView10);
        hebstat1=findViewById(R.id.textView79);
        hebstat2=findViewById(R.id.textView80);
        flagstat1=findViewById(R.id.textView159);
        flagstat2=findViewById(R.id.textView110);
        globstat1=findViewById(R.id.textView89);
        globstat2=findViewById(R.id.textView50);
        usstat1=findViewById(R.id.textView189);
        usstat2=findViewById(R.id.textView120);
        countstat1=findViewById(R.id.textView780);
        countstat2=findViewById(R.id.textView11);
        nbastat1=findViewById(R.id.textView289);
        nbastat2=findViewById(R.id.textView1870);

        wordlestat1.setText("Wins: "+Stats.wordle_number_of_wins);
        wordlestat2.setText("Avg Tries: "+Stats.wordle_avg_tries);
        hebstat1.setText("Wins: "+Stats.hebwordle_number_of_wins);
        hebstat2.setText("Avg Tries: "+Stats.hebwordle_avg_tries);
        flagstat1.setText("Plays: "+Stats.flagle_number_of_plays);
        flagstat2.setText("Wins: " + Stats.flagle_high);
        globstat1.setText("Pop. High: "+Stats.popglobal_high);
        globstat2.setText("Area High: "+Stats.areaglobal_high);
        countstat1.setText("Plays: "+Stats.countries_number_of_plays);
        countstat2.setText("High: "+Stats.countries_high);
        usstat1.setText("Plays: "+Stats.usstates_number_of_plays);
        usstat2.setText("High: "+Stats.usstates_high);
        nbastat1.setText("High: "+Stats.NBA_high);
        nbastat2.setText("Avg Tries: "+Stats.NBA_avg);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.setContentView(R.layout.reset_dialog);
                YesLogOut=d.findViewById(R.id.yes);
                d.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                NoLogOut=d.findViewById(R.id.no);
                d.setCancelable(true);

                YesLogOut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Stats.Reset();
                        d.setContentView(R.layout.after_reset_dialog);
                        isGoingToHomeScreen=true;
                        Intent intent=new Intent(ScoreBoard.this,ScoreBoard.class);
                        startActivity(intent);
                    }
                });
                NoLogOut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        d.cancel();
                    }
                });
                d.show();
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
                    Intent intent = new Intent(ScoreBoard.this, Settings.class);
                    startActivity(intent);
                    return true;
                }
                if(id==R.id.action_Main)
                {
                    isGoingToHomeScreen=true;
                    Intent intent;
                    intent = new Intent(ScoreBoard.this, LoggedMainActivity.class);
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
                            Intent intent = new Intent(ScoreBoard.this,MainActivity.class);
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