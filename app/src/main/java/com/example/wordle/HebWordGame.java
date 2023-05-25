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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Calendar;

public class HebWordGame extends AppCompatActivity {

    private Board boardGame;
    private FrameLayout frm;
    EditText editText;
    ImageButton btn;
    Button YesLogOut,NoLogOut;
    Dialog d;
    private boolean isGoingToHomeScreen = false;
    public int numberofGuesses;
    HebWords Word;
    public Calendar calendar= Calendar.getInstance();
    public PendingIntent pendingIntent;
    public AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heb_word_game);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        boardGame = new Board(this,false,true);
        frm =findViewById(R.id.frm);
        frm.addView(boardGame);
        numberofGuesses=0;
        editText=findViewById(R.id.Guess);
        Word = new HebWords();
        String CorrectWord = Word.GetChosenWord();
        btn=findViewById(R.id.btnGuess);
        d=new Dialog(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Guess = editText.getText().toString().toUpperCase();
                    if (Guess.isEmpty() || Guess.length() != 5) {
                        Toast.makeText(HebWordGame.this, "Only 5 letters long", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        boardGame.onSubmitGuess(Guess, numberofGuesses, CorrectWord.toLowerCase());
                        if (numberofGuesses <=5) {
                            if (boardGame.isCorrect()||Guess.equals(CorrectWord.toLowerCase())) {
                                Stats.wordle_number_of_wins++;
                                Stats.wordle_tries+=numberofGuesses;
                                Stats.wordle_avg_tries= Stats.wordle_tries/Stats.wordle_number_of_wins;
                                isGoingToHomeScreen=true;
                                Intent intent = new Intent(HebWordGame.this, Win.class);
                                intent.putExtra("CorrectWord", CorrectWord);
                                startActivity(intent);
                            } else if (numberofGuesses==5) {
                                isGoingToHomeScreen=true;
                                Intent intent = new Intent(HebWordGame.this, Lose.class);
                                intent.putExtra("CorrectWord", CorrectWord);
                                startActivity(intent);
                            }
                            numberofGuesses++;
                            editText.setText("");
                            closeKeyboard();
                        }
                        else {
                            isGoingToHomeScreen=true;
                            Intent intent = new Intent(HebWordGame.this, Lose.class);
                            intent.putExtra("CorrectWord", CorrectWord);
                            startActivity(intent);
                        }
                }
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
                    Intent intent = new Intent(HebWordGame.this, Settings.class);
                    startActivity(intent);
                    return true;
                }
                if(id==R.id.action_Main)
                {
                    isGoingToHomeScreen=true;
                    Intent intent;
                    intent = new Intent(HebWordGame.this, LoggedMainActivity.class);
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
                            Intent intent = new Intent(HebWordGame.this,MainActivity.class);
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