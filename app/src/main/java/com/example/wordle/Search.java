package com.example.wordle;

import static android.app.PendingIntent.FLAG_IMMUTABLE;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class Search extends AppCompatActivity {

    String search="";
    EditText searchBar;
    String first="",second="",third="";
    Button firstbtn,secondbtn,thirdbtn;
    private boolean isGoingToHomeScreen = false;
    public Calendar calendar= Calendar.getInstance();
    public PendingIntent pendingIntent;
    public AlarmManager alarmManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        searchBar=findViewById(R.id.editTextTextPersonName2);
        firstbtn=findViewById(R.id.firstresult);
        secondbtn=findViewById(R.id.Secondresult);
        thirdbtn=findViewById(R.id.Thirdresult);
        if(getIntent().getStringExtra("SearchValue")!=null) {
            search = getIntent().getStringExtra("SearchValue");
            searchResult(search);
        }
        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search=searchBar.getText().toString();
                searchResult(search);
            }
        });
    }

    private void searchResult(String search) {
        switch (search.toLowerCase()) {
            case "s":
                first = "settings";
                second = "statistics";
                third = "states";
                updateBtn(first, second, third);
                break;
            case "st":
            case "sta":
            case "stat":
                first = "states";
                second = "statistics";
                third = "null";
                updateBtn(first, second, third);
                break;
            case "state":
            case "states":
                first = "states";
                second = "null";
                third = "null";
                updateBtn(first, second, third);
                break;
            case "stati":
            case "statis":
            case "statist":
            case "statisti":
            case "statistic":
            case "statistics":
                first = "statistics";
                second = "null";
                third = "null";
                updateBtn(first, second, third);
                break;
            case "se":
            case "set":
            case "sett":
            case "setti":
            case "settin":
            case "setting":
            case "settings":
                first = "settings";
                second = "null";
                third = "null";
                updateBtn(first, second, third);
                break;
            case "c":
                first = "countries";
                second = "credits";
                third = "null";
                updateBtn(first, second, third);
                break;
            case "co":
            case "cou":
            case "coun":
            case "count":
            case "countr":
            case "countri":
            case "countrie":
            case "countries":
                first = "countries";
                second = "null";
                third = "null";
                updateBtn(first, second, third);
                break;
            case "cr":
            case "cre":
            case "cred":
            case "credi":
            case "credit":
            case "credits":
                first = "credits";
                second = "null";
                third = "null";
                updateBtn(first, second, third);
                break;
            case "g":
            case "gl":
            case "glo":
            case "glob":
            case "globa":
            case "global":
                first = "global";
                second = "null";
                third = "null";
                updateBtn(first, second, third);
                break;
            case "f":
            case "fl":
            case "fla":
            case "flag":
            case "flagl":
            case "flagle":
                first = "flagle";
                second = "null";
                third = "null";
                updateBtn(first, second, third);
                break;
            case "w":
            case "wo":
            case "wor":
            case "word":
            case "wordl":
            case "wordle":
                first = "wordle";
                second = "null";
                third = "null";
                updateBtn(first, second, third);
                break;
            case "m":
            case "ma":
            case "mai":
            case "main":
            case "h":
            case "ho":
            case "hom":
            case "home":
                first = "main";
                second = "null";
                third = "null";
                updateBtn(first, second, third);
                break;
            case "t":
            case "tr":
            case "tri":
            case "triv":
            case "trivi":
            case "trivia":
                first = "states";
                second = "nba";
                third = "countries";
                updateBtn(first, second, third);
                break;
            case "n":
            case "nb":
            case "nba":
                first = "nba";
                second = "null";
                third = "null";
                updateBtn(first, second, third);
                break;
            default:
                first = "null";
                second = "null";
                third = "null";
                updateBtn(first, second, third);
                break;
        }
    }

    private void updateBtn(String first, String second, String third) {
        if(first.equals("settings"))
        {
            firstbtn.setText("Settings");
            firstbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,Settings.class);
                    startActivity(intent);
                }
            });
        }
        if(first.equals("states"))
        {
            firstbtn.setText("States Trivia");
            firstbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,StatesTrivia.class);
                    startActivity(intent);
                }
            });
        }
        if(first.equals("nba"))
        {
            firstbtn.setText("NBA Trivia");
            firstbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,NBATrivia.class);
                    startActivity(intent);
                }
            });
        }
        if(first.equals("statistics"))
        {
            firstbtn.setText("Statistics");
            firstbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,ScoreBoard.class);
                    startActivity(intent);
                }
            });
        }
        if(first.equals("main"))
        {
            firstbtn.setText("Home Page");
            firstbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,LoggedMainActivity.class);
                    startActivity(intent);
                }
            });
        }
        if(first.equals("wordle"))
        {
            firstbtn.setText("Wordle");
            firstbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,Modes.class);
                    startActivity(intent);
                }
            });
        }
        if(first.equals("global"))
        {
            firstbtn.setText("Global");
            firstbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,GlobalChoose.class);
                    startActivity(intent);
                }
            });
        }
        if(first.equals("flagle"))
        {
            firstbtn.setText("Wordle");
            firstbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,Modes.class);
                    startActivity(intent);
                }
            });
        }
        if(first.equals("credits"))
        {
            firstbtn.setText("Credits");
            firstbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,Credits.class);
                    startActivity(intent);
                }
            });
        }
        if(first.equals("countries"))
        {
            firstbtn.setText("Countries Trivia");
            firstbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,WorldGame.class);
                    startActivity(intent);
                }
            });
        }
        if(second.equals("settings"))
        {
            secondbtn.setText("Settings");
            secondbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,Settings.class);
                    startActivity(intent);
                }
            });
        }
        if(second.equals("states"))
        {
            secondbtn.setText("States Trivia");
            secondbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,StatesTrivia.class);
                    startActivity(intent);
                }
            });
        }
        if(second.equals("nba"))
        {
            secondbtn.setText("NBA Trivia");
            secondbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,NBATrivia.class);
                    startActivity(intent);
                }
            });
        }
        if(second.equals("statistics"))
        {
            secondbtn.setText("Statistics");
            secondbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,ScoreBoard.class);
                    startActivity(intent);
                }
            });
        }
        if(second.equals("main"))
        {
            secondbtn.setText("Home Page");
            secondbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,LoggedMainActivity.class);
                    startActivity(intent);
                }
            });
        }
        if(second.equals("wordle"))
        {
            secondbtn.setText("Wordle");
            secondbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,Modes.class);
                    startActivity(intent);
                }
            });
        }
        if(second.equals("global"))
        {
            secondbtn.setText("Global");
            secondbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,GlobalChoose.class);
                    startActivity(intent);
                }
            });
        }
        if(second.equals("flagle"))
        {
            secondbtn.setText("Wordle");
            secondbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,Modes.class);
                    startActivity(intent);
                }
            });
        }
        if(second.equals("credits"))
        {
            secondbtn.setText("Credits");
            secondbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,Credits.class);
                    startActivity(intent);
                }
            });
        }
        if(second.equals("countries"))
        {
            secondbtn.setText("Countries Trivia");
            secondbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,WorldGame.class);
                    startActivity(intent);
                }
            });
        }
        if(third.equals("settings"))
        {
            thirdbtn.setText("Settings");
            thirdbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,Settings.class);
                    startActivity(intent);
                }
            });
        }
        if(third.equals("states"))
        {
            thirdbtn.setText("States Trivia");
            thirdbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,StatesTrivia.class);
                    startActivity(intent);
                }
            });
        }
        if(third.equals("nba"))
        {
            thirdbtn.setText("NBA Trivia");
            thirdbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,NBATrivia.class);
                    startActivity(intent);
                }
            });
        }
        if(third.equals("statistics"))
        {
            thirdbtn.setText("Statistics");
            thirdbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,ScoreBoard.class);
                    startActivity(intent);
                }
            });
        }
        if(third.equals("main"))
        {
            thirdbtn.setText("Home Page");
            thirdbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,LoggedMainActivity.class);
                    startActivity(intent);
                }
            });
        }
        if(third.equals("wordle"))
        {
            thirdbtn.setText("Wordle");
            thirdbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,Modes.class);
                    startActivity(intent);
                }
            });
        }
        if(third.equals("global"))
        {
            thirdbtn.setText("Global");
            thirdbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,GlobalChoose.class);
                    startActivity(intent);
                }
            });
        }
        if(third.equals("flagle"))
        {
            thirdbtn.setText("Wordle");
            thirdbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,Modes.class);
                    startActivity(intent);
                }
            });
        }
        if(third.equals("credits"))
        {
            thirdbtn.setText("Credits");
            thirdbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,Credits.class);
                    startActivity(intent);
                }
            });
        }
        if(third.equals("countries"))
        {
            thirdbtn.setText("Countries Trivia");
            thirdbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Search.this,WorldGame.class);
                    startActivity(intent);
                }
            });
        }
        if(third.equals("null"))
        {
            thirdbtn.setText(" ");
        }
        if(second.equals("null"))
        {
            secondbtn.setText(" ");
        }
        if(first.equals("null"))
        {
            firstbtn.setText(" ");
        }
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