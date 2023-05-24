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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Calendar;
import java.util.Random;

public class Flagle extends AppCompatActivity{
    Button YesLogOut,NoLogOut,btnPlayAgain,btnGoogleMaps,btnA,btnB,btnC,btnD;
    ImageView flag;
    Countries countries;
    Country country;
    Dialog d,d3;
    private boolean isGoingToHomeScreen = false;
    String CorrectAnswer;
    int CorrectAnswerNum;
    TextView winningText;
    String firstCountry,SecondCountry,ThirdCountry,fourthCountry;
    public Calendar calendar= Calendar.getInstance();
    public PendingIntent pendingIntent;
    public AlarmManager alarmManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_flagle);
        Stats.flagle_number_of_plays++;
        flag=findViewById(R.id.FlagPhoto);
        btnA=findViewById(R.id.Abtn);
        btnB=findViewById(R.id.Bbtn);
        btnC=findViewById(R.id.Cbtn);
        btnD=findViewById(R.id.Dbtn);
        countries=new Countries();
        d=new Dialog(this);
        d3=new Dialog(this);
        country=countries.RandomCountry();
        flag.setImageResource(country.flagUrl);
        CorrectAnswer=country.Name;
        Random rnd=new Random();
        CorrectAnswerNum=rnd.nextInt(5);
        firstCountry=country.Name;
        SecondCountry=country.Name;
        ThirdCountry=country.Name;
        fourthCountry=country.Name;
        if(CorrectAnswerNum==1)
            {
                firstCountry=country.Name;
                SecondCountry=countries.getRandomCountry(firstCountry,ThirdCountry,fourthCountry);
                ThirdCountry=countries.getRandomCountry(firstCountry,SecondCountry,fourthCountry);
                fourthCountry=countries.getRandomCountry(firstCountry,SecondCountry,ThirdCountry);
                if(ThirdCountry.equals(firstCountry))
                {
                    SecondCountry=countries.getRandomCountry(firstCountry,ThirdCountry,fourthCountry);
                    ThirdCountry=countries.getRandomCountry(firstCountry,SecondCountry,fourthCountry);
                    fourthCountry=countries.getRandomCountry(firstCountry,SecondCountry,ThirdCountry);
                }

            }
        else if(CorrectAnswerNum==2)
            {
                SecondCountry=country.Name;
                firstCountry=countries.getRandomCountry(SecondCountry,ThirdCountry,fourthCountry);
                ThirdCountry=countries.getRandomCountry(firstCountry,SecondCountry,fourthCountry);
                fourthCountry=countries.getRandomCountry(firstCountry,SecondCountry,ThirdCountry);
                if(ThirdCountry.equals(firstCountry))
                {
                    firstCountry=countries.getRandomCountry(SecondCountry,ThirdCountry,fourthCountry);
                    ThirdCountry=countries.getRandomCountry(firstCountry,SecondCountry,fourthCountry);
                    fourthCountry=countries.getRandomCountry(firstCountry,SecondCountry,ThirdCountry);
                }
            }
        else if(CorrectAnswerNum==3)
        {
            ThirdCountry=country.Name;
            firstCountry=countries.getRandomCountry(SecondCountry,ThirdCountry,fourthCountry);
            SecondCountry=countries.getRandomCountry(firstCountry,ThirdCountry,fourthCountry);
            fourthCountry=countries.getRandomCountry(firstCountry,SecondCountry,ThirdCountry);
            if(ThirdCountry.equals(firstCountry))
            {
                firstCountry=countries.getRandomCountry(SecondCountry,ThirdCountry,fourthCountry);
                SecondCountry=countries.getRandomCountry(firstCountry,ThirdCountry,fourthCountry);
                fourthCountry=countries.getRandomCountry(firstCountry,SecondCountry,ThirdCountry);
            }
        }
        else if(CorrectAnswerNum==4)
        {
            fourthCountry=country.Name;
            firstCountry=countries.getRandomCountry(SecondCountry,ThirdCountry,fourthCountry);
            SecondCountry=countries.getRandomCountry(firstCountry,ThirdCountry,fourthCountry);
            ThirdCountry=countries.getRandomCountry(firstCountry,SecondCountry,fourthCountry);
            if(fourthCountry.equals(firstCountry))
            {
                firstCountry=countries.getRandomCountry(SecondCountry,ThirdCountry,fourthCountry);
                SecondCountry=countries.getRandomCountry(firstCountry,ThirdCountry,fourthCountry);
                ThirdCountry=countries.getRandomCountry(firstCountry,SecondCountry,fourthCountry);
            }
        }
        if(firstCountry.length()>7)
            btnA.setTextSize(20);
        if(SecondCountry.length()>7)
            btnB.setTextSize(20);
        if(ThirdCountry.length()>7)
            btnC.setTextSize(20);
        if(fourthCountry.length()>7)
            btnD.setTextSize(20);
        if(firstCountry.length()>14)
            btnA.setTextSize(13);
        if(SecondCountry.length()>14)
            btnB.setTextSize(13);
        if(ThirdCountry.length()>14)
            btnC.setTextSize(13);
        if(fourthCountry.length()>14)
            btnD.setTextSize(13);
        if(firstCountry.length()>18)
            btnA.setTextSize(12);
        if(SecondCountry.length()>18)
            btnB.setTextSize(12);
        if(ThirdCountry.length()>18)
            btnC.setTextSize(12);
        if(fourthCountry.length()>18)
            btnD.setTextSize(12);
        btnA.setText(firstCountry);
        btnB.setText(SecondCountry);
        btnC.setText(ThirdCountry);
        btnD.setText(fourthCountry);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottonnav);
        bottomNavigationView.setSelectedItemId(R.id.action_Main);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.action_Settings)
                {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Flagle.this, Settings.class);
                    startActivity(intent);
                    return true;
                }
                if(id==R.id.action_Main)
                {
                    isGoingToHomeScreen=true;
                    Intent intent;
                    intent = new Intent(Flagle.this, LoggedMainActivity.class);
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
                            Intent intent = new Intent(Flagle.this,MainActivity.class);
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
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CorrectAnswerNum==1)
                {
                    Stats.flagle_high++;
                    d3.setContentView(R.layout.winflagle_dialog);
                    btnPlayAgain=d3.findViewById(R.id.playagainwinbtn);
                    btnGoogleMaps=d3.findViewById(R.id.GoogleMaps);
                    winningText=d3.findViewById(R.id.WinMassage);
                    winningText.setText("Good Job! \n The Correct Answer Was '"+country.Name+"' "+"\n See "+country.Name+" in Google Maps:");
                    d3.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    d3.setCancelable(false);
                    btnPlayAgain.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            isGoingToHomeScreen=true;
                            Intent intent = new Intent(Flagle.this,Flagle.class);
                            startActivity(intent);
                            d3.dismiss();
                        }
                    });
                    btnGoogleMaps.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            String uri=("geo:<" + country.coordinates.latitude  + ">,<" + country.coordinates.longitude + ">?q=<" + country.coordinates.latitude  + ">,<" + country.coordinates.longitude + ">(" +country.Name + ")");
                            intent.setData(Uri.parse(uri));
                            Intent chooser = Intent.createChooser(intent, "Launch Map");
                            startActivity(chooser);
                            d3.dismiss();
                        }
                    });
                        d3.show();
                }
                else
                {
                    Stats.flagle_Low++;
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Flagle.this,Lose.class);
                    intent.putExtra("CorrectWord",CorrectAnswer);
                    startActivity(intent);
                }
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CorrectAnswerNum==2)
                {
                    Stats.flagle_high++;
                    d3.setContentView(R.layout.winflagle_dialog);
                    btnPlayAgain=d3.findViewById(R.id.playagainwinbtn);
                    btnGoogleMaps=d3.findViewById(R.id.GoogleMaps);
                    winningText=d3.findViewById(R.id.WinMassage);
                    winningText.setText("Good Job! \n The Correct Answer Was '"+country.Name+"' "+"\n See "+country.Name+" in Google Maps:");
                    d3.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    d3.setCancelable(false);
                    btnPlayAgain.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            isGoingToHomeScreen=true;
                            Intent intent = new Intent(Flagle.this,Flagle.class);
                            startActivity(intent);
                            d3.dismiss();
                        }
                    });
                    btnGoogleMaps.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            String uri=("geo:<" + country.coordinates.latitude  + ">,<" + country.coordinates.longitude + ">?q=<" + country.coordinates.latitude  + ">,<" + country.coordinates.longitude + ">(" +country.Name + ")");
                            intent.setData(Uri.parse(uri));
                            Intent chooser = Intent.createChooser(intent, "Launch Map");
                            startActivity(chooser);
                            d3.dismiss();
                        }
                    });
                    d3.show();
                }
                else
                {
                    Stats.flagle_Low++;
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Flagle.this,Lose.class);
                    intent.putExtra("CorrectWord",CorrectAnswer);
                    startActivity(intent);
                }
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CorrectAnswerNum==3)
                {
                    Stats.flagle_high++;
                    d3.setContentView(R.layout.winflagle_dialog);
                    btnPlayAgain=d3.findViewById(R.id.playagainwinbtn);
                    btnGoogleMaps=d3.findViewById(R.id.GoogleMaps);
                    winningText=d3.findViewById(R.id.WinMassage);
                    winningText.setText("Good Job! \n The Correct Answer Was '"+country.Name+"' "+"\n See "+country.Name+" in Google Maps:");
                    d3.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    d3.setCancelable(false);
                    btnPlayAgain.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            isGoingToHomeScreen=true;
                            Intent intent = new Intent(Flagle.this,Flagle.class);
                            startActivity(intent);
                            d3.dismiss();
                        }
                    });
                    btnGoogleMaps.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            String uri=("geo:<" + country.coordinates.latitude  + ">,<" + country.coordinates.longitude + ">?q=<" + country.coordinates.latitude  + ">,<" + country.coordinates.longitude + ">(" +country.Name + ")");
                            intent.setData(Uri.parse(uri));
                            Intent chooser = Intent.createChooser(intent, "Launch Map");
                            startActivity(chooser);
                            d3.dismiss();
                        }
                    });
                    d3.show();
                }
                else
                {
                    Stats.flagle_Low++;
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Flagle.this,Lose.class);
                    intent.putExtra("CorrectWord",CorrectAnswer);
                    startActivity(intent);
                }
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CorrectAnswerNum==4)
                {
                    Stats.flagle_high++;
                    d3.setContentView(R.layout.winflagle_dialog);
                    btnPlayAgain=d3.findViewById(R.id.playagainwinbtn);
                    btnGoogleMaps=d3.findViewById(R.id.GoogleMaps);
                    winningText=d3.findViewById(R.id.WinMassage);
                    winningText.setText("Good Job! \n The Correct Answer Was '"+country.Name+"' "+"\n See "+country.Name+" in Google Maps:");
                    d3.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    d3.setCancelable(false);
                    btnPlayAgain.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            isGoingToHomeScreen=true;
                            Intent intent = new Intent(Flagle.this,Flagle.class);
                            startActivity(intent);
                            d3.dismiss();
                        }
                    });
                    btnGoogleMaps.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            String uri=("geo:<" + country.coordinates.latitude  + ">,<" + country.coordinates.longitude + ">?q=<" + country.coordinates.latitude  + ">,<" + country.coordinates.longitude + ">(" +country.Name + ")");
                            intent.setData(Uri.parse(uri));
                            Intent chooser = Intent.createChooser(intent, "Launch Map");
                            startActivity(chooser);
                            d3.dismiss();
                        }
                    });
                    d3.show();
                }
                else
                {
                    Stats.flagle_Low++;
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Flagle.this,Lose.class);
                    intent.putExtra("CorrectWord",CorrectAnswer);
                    startActivity(intent);
                }
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

