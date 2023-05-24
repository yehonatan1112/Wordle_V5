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
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Calendar;

public class LoggedMainActivity extends AppCompatActivity {

    ImageButton  GlobGame,ScoreBoard,Trivle,Flagle,Modes,Settings,Credits,SignUp,Search;
    SharedPreferences sp;
    EditText searchBar;
    Button YesLogOut,NoLogOut;
    Dialog d;
    TextView UserName;
    private boolean isGoingToHomeScreen = false;
    String name="",name2="";
    GoogleSignInClient gsc;
    GoogleSignInOptions gso;
    public Calendar calendar= Calendar.getInstance();
    public PendingIntent pendingIntent;
    public AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc= GoogleSignIn.getClient(this,gso);
        sp=getSharedPreferences("deatails1",0);
        SignUp = (ImageButton) findViewById(R.id.imageView4);
        Trivle=(ImageButton) findViewById(R.id.imageView51);
        Modes=(ImageButton) findViewById(R.id.imageView52);
        Flagle=(ImageButton) findViewById(R.id.imageView53);
        ScoreBoard=(ImageButton) findViewById(R.id.imageView54);
        GlobGame=(ImageButton) findViewById(R.id.imageView55);
        Search=(ImageButton) findViewById(R.id.imageView56);
        Settings=(ImageButton) findViewById(R.id.imageView57);
        UserName=findViewById(R.id.UserName);
        Credits=(ImageButton) findViewById(R.id.imageView58);
        searchBar=findViewById(R.id.editTextTextPersonName2);
        name=sp.getString("name",null);
        if(name==null)
            name="Logged User";
        else
            if(getIntent().getStringExtra("Name")!=null)
                name=getIntent().getStringExtra("Name");
            else
                name=sp.getString("name",null);
        UserName.setText("Hello "+name);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("name",name);
        editor.apply();
        d=new Dialog(this);
        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name2=searchBar.getText().toString();
                isGoingToHomeScreen=true;
                Intent intent = new Intent(LoggedMainActivity.this, Search.class);
                intent.putExtra("SearchValue",name2);
                startActivity(intent);
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
                    Intent intent = new Intent(LoggedMainActivity.this, Settings.class);
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
                            gsc.signOut();
                            isGoingToHomeScreen=true;
                            Intent intent = new Intent(LoggedMainActivity.this,MainActivity.class);
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
        Trivle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isGoingToHomeScreen=true;
                Intent intent =new Intent(LoggedMainActivity.this,Trivle.class);
                startActivity(intent);                }
        });
        Modes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isGoingToHomeScreen=true;
                Intent intent =new Intent(LoggedMainActivity.this,Modes.class);
                startActivity(intent);                }
        });
        Flagle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isGoingToHomeScreen=true;
                Intent intent =new Intent(LoggedMainActivity.this,Flagle.class);
                startActivity(intent);                }
        });
        ScoreBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isGoingToHomeScreen=true;
                Intent intent =new Intent(LoggedMainActivity.this,ScoreBoard.class);
                startActivity(intent);                }
        });
        GlobGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isGoingToHomeScreen=true;
                Intent intent =new Intent(LoggedMainActivity.this,GlobalChoose.class);
                startActivity(intent);                }
        });
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isGoingToHomeScreen=true;
                Intent intent =new Intent(LoggedMainActivity.this,Search.class);
                startActivity(intent);                }
        });
        Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isGoingToHomeScreen=true;
                Intent intent =new Intent(LoggedMainActivity.this,Settings.class);
                startActivity(intent);                }
        });
        Credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isGoingToHomeScreen=true;
                Intent intent =new Intent(LoggedMainActivity.this,Credits.class);
                startActivity(intent);                }
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