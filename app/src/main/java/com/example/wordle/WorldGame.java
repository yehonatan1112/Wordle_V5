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
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Calendar;

public class WorldGame extends AppCompatActivity {

    Countries countries=new Countries();
    String[] Guesses;
    ImageView photo;
    EditText guess;
    TextView numberofguesses,List;
    ImageButton btnGuess,btnGiveUp;
    Button YesLogOut,NoLogOut,btnShowGuessed;
    private boolean isGoingToHomeScreen = false;
    Dialog d,d2,d4;
    public Calendar calendar= Calendar.getInstance();
    public PendingIntent pendingIntent;
    public AlarmManager alarmManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_game);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Stats.countries_number_of_plays++;
        photo=findViewById(R.id.FlagPhoto);
        Guesses=new String[countries.countries.length];
        numberofguesses=findViewById(R.id.numberofguesses);
        btnShowGuessed=findViewById(R.id.showGuessed);
        guess=findViewById(R.id.GuessState);
        btnGuess=findViewById(R.id.btnGuessState);
        btnGiveUp=findViewById(R.id.btnGiveUpState);
        d2=new Dialog(this);
        d=new Dialog(this);
        d4=new Dialog(this);
        d4.setContentView(R.layout.words_dialog);
        d4.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        d4.setCancelable(true);
        numberofguesses.setText(0+"/220");
        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Guess = guess.getText().toString().toLowerCase();
                if(!Guess.isEmpty()) {
                    if(countries.isWordReal(Guess))
                    {
                        for(int i=0;i<Guesses.length;i++)
                        {
                            if(Guesses[i]!=null)
                            {
                                if(Guesses[i].equals(Guess))
                                {
                                    guess.setText("");
                                    Toast.makeText(WorldGame.this, "You Already Guessed this Country", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                            else{
                                numberofguesses.setText(NotNullLength(Guesses)+"/220");
                                Guesses[i]=Guess;
                                photo.setImageResource(countries.GetImgUrl(Guess));
                                if(NotNullLength(Guesses)==220)
                                {
                                    Stats.countries_high=220;
                                    isGoingToHomeScreen=true;
                                    Intent intent = new Intent(WorldGame.this,Win.class);
                                    intent.putExtra("CorrectWord","The 220 Countries");
                                    startActivity(intent);
                                    d2.dismiss();
                                }
                                guess.setText("");
                                Toast.makeText(WorldGame.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    }
                    else
                    {
                        Toast.makeText(WorldGame.this, "Country Doesn't Exist", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                else
                {
                    Toast.makeText(WorldGame.this, "Please Write An Answer", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        btnGiveUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NotNullLength(Guesses)-1>Stats.countries_high)
                    Stats.countries_high=NotNullLength(Guesses)-1;
                d2.setContentView(R.layout.giveup_dialog);
                YesLogOut=d2.findViewById(R.id.yes);
                d2.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                NoLogOut=d2.findViewById(R.id.no);
                d2.setCancelable(true);
                YesLogOut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        isGoingToHomeScreen=true;
                        Intent intent = new Intent(WorldGame.this,Lose.class);
                        intent.putExtra("CorrectWord",(221-NotNullLength(Guesses))+" Other Countries");
                        startActivity(intent);
                        d2.dismiss();
                    }
                });
                NoLogOut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        d2.cancel();
                    }
                });
                d2.show();
            }
        });
        btnShowGuessed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str=ToList(Guesses);
                List=d4.findViewById(R.id.List);
                List.setText(str);
                d4.show();
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
                    Intent intent = new Intent(WorldGame.this, Settings.class);
                    startActivity(intent);
                    return true;
                }
                if(id==R.id.action_Main)
                {
                    isGoingToHomeScreen=true;
                    Intent intent;
                    intent = new Intent(WorldGame.this, LoggedMainActivity.class);
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
                            Intent intent = new Intent(WorldGame.this,MainActivity.class);
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
    private void closeKeyboard()
    {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager manager
                    = (InputMethodManager)
                    getSystemService(
                            Context.INPUT_METHOD_SERVICE);
            manager
                    .hideSoftInputFromWindow(
                            view.getWindowToken(), 0);
        }
    }
    public int NotNullLength(String[] Gueeses)
    {
        int length=1;
        for(int i=0;i<Gueeses.length&&Gueeses[i]!=null;i++)
            length++;
        return length;
    }
    public String ToList(String[] strs)
    {
        String str="";
        for(int i=0;i<strs.length&&strs[i]!=null;i++)
        {
            str+=strs[i];
            str+=", ";
        }
        return str;
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