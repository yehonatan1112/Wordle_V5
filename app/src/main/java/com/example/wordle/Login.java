package com.example.wordle;

import static android.app.PendingIntent.FLAG_IMMUTABLE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Calendar;

public class Login extends AppCompatActivity {
    EditText etpassword,etfirstname;
    ImageButton btnLogin;
    Button btnForgot,btnShowPass,google;
    private DBHandler dbHandler;
    GoogleSignInClient gsc;
    GoogleSignInOptions gso;
    private boolean isGoingToHomeScreen = false;
    String firstname="";
    public Calendar calendar= Calendar.getInstance();
    public PendingIntent pendingIntent;
    public AlarmManager alarmManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        etpassword=findViewById(R.id.editTextTextPassWord);
        etfirstname=findViewById(R.id.editTextTextPersonName);
        btnLogin=findViewById(R.id.imageView2);
        btnForgot=findViewById(R.id.textView6);
        btnShowPass=findViewById(R.id.for2);
        google=findViewById(R.id.button);
        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc= GoogleSignIn.getClient(this,gso);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(); 
            }
        });
        dbHandler = new DBHandler(Login.this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstname = etfirstname.getText().toString();
                String password = etpassword.getText().toString();

              if(dbHandler.login(firstname,password))
                {
                    Toast.makeText(Login.this, "You Have Logged In", Toast.LENGTH_SHORT).show();
                    Stats.Reset();
                    isGoingToHomeScreen=true;
                    Intent i = new Intent(Login.this, LoggedMainActivity.class);
                    i.putExtra("Name",firstname);
                   startActivity(i);
                }
                else
                {
                    Toast.makeText(Login.this, "Try Again...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isGoingToHomeScreen=true;
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });
        btnShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isGoingToHomeScreen=true;
                Intent intent = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottonnav);
        bottomNavigationView.setSelectedItemId(R.id.action_login);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.action_Main)
                {
                    isGoingToHomeScreen=true;
                    Intent intent;
                    intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                }
                if(id==R.id.action_SignUp)
                {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(Login.this, SignUp.class);
                    startActivity(intent);
                    return true;
                }
                if(id==R.id.action_Credits)
                {
                    isGoingToHomeScreen=true;
                    Intent intent =new Intent(Login.this,Credits.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }

    private void signIn() {
        isGoingToHomeScreen=true;
        Intent signInIntent=gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1000)
        {
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                finish();
                Stats.Reset();
                Intent intent=new Intent(Login.this,LoggedMainActivity.class);
                GoogleSignInAccount acct=GoogleSignIn.getLastSignedInAccount(this);
                firstname=acct.getGivenName();
                intent.putExtra("Name",firstname);
                startActivity(intent);
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
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