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
import android.graphics.Color;
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

public class NBATrivia extends AppCompatActivity {

    NBA_Teams nba_teams=new NBA_Teams();
    String[] Guesses;
    ImageView photo,first,second,third,fourth,fifth,sixth,seventh,eighth,ninth,tenth,eleventh,twelveth,thirteenth,fourteenth,fifteenth,sixteenth,seventeenth,eighteenth,nineteenth,twentyth,twentyOne,twentyTwo,TwentyThree,TwentyFour,TwentyFive,TwentySix,TwentySeven,TwentyEight,TwentyNine,Thirty;
    EditText guess;
    TextView numberofguesses;
    ImageButton btnGuess,btnGiveUp;
    Button YesLogOut,NoLogOut;
    private boolean isGoingToHomeScreen = false;
    Dialog d,d2,d3,d4;
    public Calendar calendar= Calendar.getInstance();
    public PendingIntent pendingIntent;
    public AlarmManager alarmManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nbatrivia);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        photo=findViewById(R.id.MainPhoto);
        Stats.NBA_number_of_plays++;
        Guesses=new String[nba_teams.teams.length];
        numberofguesses=findViewById(R.id.numberofguesses);
        guess=findViewById(R.id.GuessState);
        btnGuess=findViewById(R.id.btnGuessState);
        btnGiveUp=findViewById(R.id.btnGiveUpState);
        first=findViewById(R.id.OnePhoto);
        second=findViewById(R.id.TwoPhoto);
        third=findViewById(R.id.ThreePhoto);
        fourth=findViewById(R.id.FourPhoto);
        fifth=findViewById(R.id.FivePhoto);
        sixth=findViewById(R.id.SixPhoto);
        seventh=findViewById(R.id.SevenPhoto);
        eighth=findViewById(R.id.EightPhoto);
        ninth=findViewById(R.id.NinePhoto);
        tenth=findViewById(R.id.TenPhoto);
        eleventh=findViewById(R.id.ElevenPhoto);
        twelveth=findViewById(R.id.TwelvePhoto);
        thirteenth=findViewById(R.id.ThirteenPhoto);
        fourteenth=findViewById(R.id.FourteenPhoto);
        fifteenth=findViewById(R.id.FifteenPhoto);
        sixteenth=findViewById(R.id.SixteenPhoto);
        seventeenth=findViewById(R.id.SeventeenPhoto);
        eighteenth=findViewById(R.id.EighteenPhoto);
        nineteenth=findViewById(R.id.NineteenPhoto);
        twentyth=findViewById(R.id.TwentyPhoto);
        twentyOne=findViewById(R.id.TwentyOnePhoto);
        twentyTwo=findViewById(R.id.TwentyTwoPhoto);
        TwentyThree=findViewById(R.id.TwentyThreePhoto);
        TwentyFour=findViewById(R.id.TwentyFourPhoto);
        TwentyFive=findViewById(R.id.TwentyFivePhoto);
        TwentySix=findViewById(R.id.TwentySixPhoto);
        TwentySeven=findViewById(R.id.TwentySevenPhoto);
        TwentyEight=findViewById(R.id.TwentyEightPhoto);
        TwentyNine=findViewById(R.id.TwentyNinePhoto);
        Thirty=findViewById(R.id.ThirtyPhoto);
        PaintWhite();
        photo.setImageResource(R.drawable.white);
        d=new Dialog(this);
        d2=new Dialog(this);
        d3=new Dialog(this);
        d4=new Dialog(this);
        d4.setContentView(R.layout.words_dialog);
        d4.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        d4.setCancelable(true);
        numberofguesses.setText(0+"/30");
        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Guess = guess.getText().toString().toLowerCase();
                if(!Guess.isEmpty()) {
                    if(nba_teams.isWordReal(Guess))
                    {
                        for(int i=0;i<Guesses.length;i++)
                        {
                            if(Guesses[i]!=null)
                            {
                                if(Guesses[i].equals(Guess))
                                {
                                    guess.setText("");
                                    Toast.makeText(NBATrivia.this, "You Already Guessed this Team", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                            else{
                                numberofguesses.setText(NotNullLength(Guesses)+"/30");
                                Guesses[i]=Guess;
                                Paint(i,Guess);
                                if(NotNullLength(Guesses)==31)
                                {
                                    Stats.NBA_high=30;
                                    Stats.NBA_totals+=30;
                                    Stats.NBA_avg=Stats.NBA_totals/Stats.NBA_number_of_plays;
                                    isGoingToHomeScreen=true;
                                    Intent intent = new Intent(NBATrivia.this,Win.class);
                                    intent.putExtra("CorrectWord","The 30 Teams");
                                    startActivity(intent);
                                    d2.dismiss();
                                }
                                guess.setText("");
                                Toast.makeText(NBATrivia.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    }
                    else
                    {
                        Toast.makeText(NBATrivia.this, "Team Doesn't Exist", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                else
                {
                    Toast.makeText(NBATrivia.this, "Please Write An Answer", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        btnGiveUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NotNullLength(Guesses)-1>Stats.NBA_high)
                    Stats.NBA_high=NotNullLength(Guesses)-1;
                Stats.NBA_totals+=NotNullLength(Guesses)-1;
                Stats.NBA_avg=Stats.NBA_totals/Stats.NBA_number_of_plays;
                d2.setContentView(R.layout.giveup_dialog);
                YesLogOut=d2.findViewById(R.id.yes);
                d2.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                NoLogOut=d2.findViewById(R.id.no);
                d2.setCancelable(true);
                YesLogOut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        isGoingToHomeScreen=true;
                        Intent intent = new Intent(NBATrivia.this,Lose.class);
                        intent.putExtra("CorrectWord",(31-NotNullLength(Guesses))+" Other Teams");
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
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottonnav);
        bottomNavigationView.setSelectedItemId(R.id.action_Main);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.action_Settings)
                {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(NBATrivia.this, Settings.class);
                    startActivity(intent);
                    return true;
                }
                if(id==R.id.action_Main)
                {
                    isGoingToHomeScreen=true;
                    Intent intent;
                    intent = new Intent(NBATrivia.this, LoggedMainActivity.class);
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
                            Intent intent = new Intent(NBATrivia.this,MainActivity.class);
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
    public void Paint(int i,String Guess)
    {
        if(i==0)
            first.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==1)
            second.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==2)
            third.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==3)
            fourth.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==4)
            fifth.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==5)
            sixth.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==6)
            seventh.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==7)
            eighth.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==8)
            ninth.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==9)
            tenth.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==10)
            eleventh.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==11)
            twelveth.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==12)
            thirteenth.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==13)
            fourteenth.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==14)
            fifteenth.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==15)
            sixteenth.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==16)
            seventeenth.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==17)
            eighteenth.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==18)
            nineteenth.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==19)
            twentyth.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==20)
            twentyOne.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==21)
            twentyTwo.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==22)
            TwentyThree.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==23)
            TwentyFour.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==24)
            TwentyFive.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==25)
            TwentySix.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==26)
            TwentySeven.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==27)
            TwentyEight.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==28)
            TwentyNine.setImageResource(nba_teams.GetImgUrl(Guess));
        if(i==29)
            Thirty.setImageResource(nba_teams.GetImgUrl(Guess));

    }
    public void PaintWhite()
    {
            first.setImageResource(R.drawable.white);
            second.setImageResource(R.drawable.white);
            third.setImageResource(R.drawable.white);
            fourth.setImageResource(R.drawable.white);
            fifth.setImageResource(R.drawable.white);
            sixth.setImageResource(R.drawable.white);
            seventh.setImageResource(R.drawable.white);
            eighth.setImageResource(R.drawable.white);
            ninth.setImageResource(R.drawable.white);
            tenth.setImageResource(R.drawable.white);
            eleventh.setImageResource(R.drawable.white);
            twelveth.setImageResource(R.drawable.white);
            thirteenth.setImageResource(R.drawable.white);
            fourteenth.setImageResource(R.drawable.white);
            fifteenth.setImageResource(R.drawable.white);
            sixteenth.setImageResource(R.drawable.white);
            seventeenth.setImageResource(R.drawable.white);
            eighteenth.setImageResource(R.drawable.white);
            nineteenth.setImageResource(R.drawable.white);
            twentyth.setImageResource(R.drawable.white);
            twentyOne.setImageResource(R.drawable.white);
            twentyTwo.setImageResource(R.drawable.white);
            TwentyThree.setImageResource(R.drawable.white);
            TwentyFour.setImageResource(R.drawable.white);
            TwentyFive.setImageResource(R.drawable.white);
            TwentySix.setImageResource(R.drawable.white);
            TwentySeven.setImageResource(R.drawable.white);
            TwentyEight.setImageResource(R.drawable.white);
            TwentyNine.setImageResource(R.drawable.white);
            Thirty.setImageResource(R.drawable.white);

    }
    public int NotNullLength(String[] Gueeses)
    {
        int length=1;
        for(int i=0;i<Gueeses.length&&Gueeses[i]!=null;i++)
            length++;
        return length;
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