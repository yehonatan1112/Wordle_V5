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
import android.net.Uri;
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

public class StatesTrivia extends AppCompatActivity {
    US_States us_states=new US_States();
    String[] Guesses;

    EditText guess;
    TextView numberofguesses,List;
    ImageButton  btnGuess,btnGiveUp;
    Button YesLogOut,NoLogOut,btnShowGuessed;
    Dialog d,d2,d3,d4;
    private boolean isGoingToHomeScreen = false;
    public Calendar calendar= Calendar.getInstance();
    public PendingIntent pendingIntent;
    public AlarmManager alarmManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_states_trivia);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Guesses=new String[50];
        Stats.usstates_number_of_plays++;
        numberofguesses=findViewById(R.id.numberofguesses);
        btnShowGuessed=findViewById(R.id.showGuessed);
        guess=findViewById(R.id.GuessState);
        btnGuess=findViewById(R.id.btnGuessState);
        btnGiveUp=findViewById(R.id.btnGiveUpState);
        d=new Dialog(this);
        d2=new Dialog(this);
        d3=new Dialog(this);
        d4=new Dialog(this);
        d4.setContentView(R.layout.words_dialog);
        d4.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        d4.setCancelable(true);
        numberofguesses.setText(0+"/50");
        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Guess = guess.getText().toString().toLowerCase();
                if(!Guess.isEmpty()) {
                    if(us_states.isWordReal(Guess))
                    {
                        for(int i=0;i<Guesses.length;i++)
                        {
                            if(Guesses[i]!=null)
                            {
                                if(Guesses[i].equals(Guess))
                                {
                                    guess.setText("");
                                    closeKeyboard();
                                    Toast.makeText(StatesTrivia.this, "You Already Guessed this state", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                            else{
                                numberofguesses.setText(NotNullLength(Guesses)+"/50");
                                Guesses[i]=Guess;
                                if(NotNullLength(Guesses)==51)
                                {
                                    isGoingToHomeScreen=true;
                                    Intent intent = new Intent(StatesTrivia.this,Win.class);
                                    intent.putExtra("CorrectWord","The 50 states");
                                    startActivity(intent);
                                    d2.dismiss();
                                }
                                guess.setText("");
                                Toast.makeText(StatesTrivia.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    }
                    else
                    {
                        Toast.makeText(StatesTrivia.this, "State Doesn't Exist", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                else
                {
                    Toast.makeText(StatesTrivia.this, "Please Write An Answer", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        btnGiveUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NotNullLength(Guesses)-1>Stats.usstates_high)
                    Stats.usstates_high=NotNullLength(Guesses)-1;
                d2.setContentView(R.layout.giveup_dialog);
                YesLogOut=d2.findViewById(R.id.yes);
                d2.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                NoLogOut=d2.findViewById(R.id.no);
                d2.setCancelable(true);
                YesLogOut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        isGoingToHomeScreen=true;
                        Intent intent = new Intent(StatesTrivia.this,Lose.class);
                        intent.putExtra("CorrectWord",(51-NotNullLength(Guesses))+" Other States");
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
                    Intent intent = new Intent(StatesTrivia.this, Settings.class);
                    startActivity(intent);
                    return true;
                }
                if(id==R.id.action_Main)
                {
                    isGoingToHomeScreen=true;
                    Intent intent;
                    intent = new Intent(StatesTrivia.this, LoggedMainActivity.class);
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
                            Intent intent = new Intent(StatesTrivia.this,MainActivity.class);
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