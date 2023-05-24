package com.example.wordle;

import static android.app.PendingIntent.FLAG_IMMUTABLE;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class PopulationGame extends AppCompatActivity {

    Button Higherbtn,Lowerbtn,YesLogOut,NoLogOut;
    ImageView TopImage,BottomImage;
    TextView TopName,BottomName,TopPop,CurrentStreak;
    Countries countries=new Countries();
    Country firstCountry,secondCountry;
    Boolean isHigher,isLower;
    private boolean isGoingToHomeScreen = false;
    int Streak=0;
    Dialog d;
    public Calendar calendar= Calendar.getInstance();
    public PendingIntent pendingIntent;
    public AlarmManager alarmManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_population_game);
        Stats.popglobal_number_of_plays++;
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Higherbtn=findViewById(R.id.Higher);
        Lowerbtn=findViewById(R.id.Lower);
        TopImage=findViewById(R.id.TopImage);
        BottomImage=findViewById(R.id.BottomImage);
        TopName=findViewById(R.id.FirstCountry);
        BottomName=findViewById(R.id.SecondCountry);
        TopPop=findViewById(R.id.FCountryPop);
        CurrentStreak=findViewById(R.id.Streak);
        firstCountry=countries.RandomCountry();
        secondCountry=countries.RandomCountry();
        TopName.setText(firstCountry.Name);
        BottomName.setText(secondCountry.Name);
        TopPop.setText(firstCountry.GetPopulation());
        TopImage.setImageResource(firstCountry.imgUrl);
        BottomImage.setImageResource(secondCountry.imgUrl);
        isHigher=false;
        isLower=false;
        d=new Dialog(this);
        Higherbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHigher=true;
                isLower=false;
                CheckAnswer();
                CurrentStreak.setText("Current Streak: "+Streak);
            }
        });
        Lowerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHigher=false;
                isLower=true;
                CheckAnswer();
                CurrentStreak.setText("Current Streak: "+Streak);
            }
        });
    }
    public void CheckAnswer() {
        if (isHigher) {
            if (firstCountry.population <= secondCountry.population) {
                Streak++;
                firstCountry=secondCountry;
                secondCountry=countries.RandomCountry();
                TopName.setText(firstCountry.Name);
                BottomName.setText(secondCountry.Name);
                TopPop.setText(firstCountry.GetPopulation());
                TopImage.setImageResource(firstCountry.imgUrl);
                BottomImage.setImageResource(secondCountry.imgUrl);
            } else {
                if(Streak>Stats.popglobal_high)
                    Stats.popglobal_high=Streak;
                Streak=0;
                isGoingToHomeScreen=true;
                Intent intent = new Intent(PopulationGame.this, Lose.class);
                intent.putExtra("CorrectWord","Lower");
                startActivity(intent);
            }
        }
        else if(isLower) {
            if (firstCountry.population >= secondCountry.population) {
                Streak++;
                firstCountry=secondCountry;
                secondCountry=countries.RandomCountry();
                TopName.setText(firstCountry.Name);
                BottomName.setText(secondCountry.Name);
                TopPop.setText(firstCountry.GetPopulation());
                TopImage.setImageResource(firstCountry.imgUrl);
                BottomImage.setImageResource(secondCountry.imgUrl);
            } else {
                if(Streak>Stats.popglobal_high)
                    Stats.popglobal_high=Streak;
                Streak=0;
                isGoingToHomeScreen=true;
                Intent intent = new Intent(PopulationGame.this, Lose.class);
                intent.putExtra("CorrectWord","Higher");
                startActivity(intent);
            }
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