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
import android.widget.Toast;

import com.google.android.material.transition.Hold;

import java.util.Calendar;

public class GlobalGame extends AppCompatActivity {

    Button Higherbtn,Lowerbtn;
    ImageView TopImage,BottomImage;
    TextView TopName,BottomName,TopSize,CurrentStreak;
    Countries countries=new Countries();
    Country firstCountry,secondCountry;
    private boolean isGoingToHomeScreen = false;

    Dialog d;
    Boolean isHigher,isLower;
    int Streak=0;
    public Calendar calendar= Calendar.getInstance();
    public PendingIntent pendingIntent;
    public AlarmManager alarmManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_global_game);
        Stats.areaglobal_number_of_plays++;
        Higherbtn=findViewById(R.id.Higher);
        Lowerbtn=findViewById(R.id.Lower);
        TopImage=findViewById(R.id.TopImage);
        BottomImage=findViewById(R.id.BottomImage);
        TopName=findViewById(R.id.FirstCountry);
        BottomName=findViewById(R.id.SecondCountry);
        TopSize=findViewById(R.id.FCountrySize);
        CurrentStreak=findViewById(R.id.Streak);
        firstCountry=countries.RandomCountry();
        secondCountry=countries.RandomCountry();
        TopName.setText(firstCountry.Name);
        BottomName.setText(secondCountry.Name);
        TopSize.setText(firstCountry.GetArea()+" Km\u00B2");
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
            if (firstCountry.size <= secondCountry.size) {
                Streak++;
                firstCountry=secondCountry;
                secondCountry=countries.RandomCountry();
                TopName.setText(firstCountry.Name);
                BottomName.setText(secondCountry.Name);
                TopSize.setText(firstCountry.GetArea()+" Km\u00B2");
                TopImage.setImageResource(firstCountry.imgUrl);
                BottomImage.setImageResource(secondCountry.imgUrl);
            } else {
                if(Streak>Stats.areaglobal_high)
                    Stats.areaglobal_high=Streak;
                Streak=0;
                isGoingToHomeScreen=true;
                Intent intent = new Intent(GlobalGame.this, Lose.class);
                intent.putExtra("CorrectWord","Lower");
                startActivity(intent);
            }
        }
        else if(isLower) {
            if (firstCountry.size >= secondCountry.size) {
                Streak++;
                firstCountry=secondCountry;
                secondCountry=countries.RandomCountry();
                TopName.setText(firstCountry.Name);
                BottomName.setText(secondCountry.Name);
                TopSize.setText(firstCountry.GetArea()+" Km\u00B2");
                TopImage.setImageResource(firstCountry.imgUrl);
                BottomImage.setImageResource(secondCountry.imgUrl);
            } else {
                if(Streak>Stats.areaglobal_high)
                    Stats.areaglobal_high=Streak;
                Streak=0;
                isGoingToHomeScreen=true;
                Intent intent = new Intent(GlobalGame.this, Lose.class);
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