package com.example.wordle;

import static android.app.PendingIntent.FLAG_IMMUTABLE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Calendar;
import java.util.regex.Pattern;


public class SignUp extends AppCompatActivity {

    private EditText etfirstname, etemail,etpassword;
    private ImageButton btnInsert;
    private DBHandler dbHandler;
    Button Login;
    public Calendar calendar= Calendar.getInstance();
    public PendingIntent pendingIntent;
    public AlarmManager alarmManager;
    private boolean isGoingToHomeScreen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Login=findViewById(R.id.textView6);
        etfirstname = (EditText)findViewById(R.id.editTextTextPersonName);
        etemail = (EditText)findViewById(R.id.editTextTextEmail);
        etpassword = (EditText) findViewById(R.id.editTextTextPassword);
        btnInsert=(ImageButton) findViewById(R.id.imageView2);
        dbHandler = new DBHandler(SignUp.this);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstname = etfirstname.getText().toString();
                String email = etemail.getText().toString();
                String password = etpassword.getText().toString();

                if (firstname.isEmpty() || email.isEmpty() ||password.isEmpty()) {
                    Toast.makeText(SignUp.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(checkAll(firstname,email,password)) {
                    dbHandler.addNewUser(firstname.toLowerCase(), email, password);
                    isGoingToHomeScreen=true;
                    Intent i = new Intent(SignUp.this, MainActivity.class);
                    Toast.makeText(SignUp.this, "User has been added.", Toast.LENGTH_SHORT).show();

                    etfirstname.setText("");
                    etemail.setText("");
                    etpassword.setText("");
                    startActivity(i);
                }
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isGoingToHomeScreen=true;
                Intent i = new Intent(SignUp.this, Login.class);
                startActivity(i);
            }
        });
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottonnav);
        bottomNavigationView.setSelectedItemId(R.id.action_SignUp);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.action_login)
                {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(SignUp.this, Login.class);
                    startActivity(intent);
                    return true;
                }
                if(id==R.id.action_Main)
                {
                    isGoingToHomeScreen=true;
                    Intent intent;
                    intent = new Intent(SignUp.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                }
                if(id==R.id.action_Credits)
                {
                    isGoingToHomeScreen=true;
                    Intent intent =new Intent(SignUp.this,Credits.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }
    private boolean checkAll(String firstname, String email, String password) {
        Boolean firstnameflag=checkNames(firstname),emailflag=checkMail(email),passwordflag=CheckPassWord(password);
        if(firstnameflag==false)
            Toast.makeText(SignUp.this, "first name should contain only letters", Toast.LENGTH_SHORT).show();
        if(emailflag==false)
            Toast.makeText(SignUp.this, "email is in wrong format", Toast.LENGTH_SHORT).show();
        if(passwordflag==false)
            Toast.makeText(SignUp.this, "password should contain only numbers", Toast.LENGTH_SHORT).show();
        if(emailflag&&firstnameflag&&passwordflag)
            return true;
        return false;

    }
    private Boolean CheckPassWord(String password) {
        if(password.matches("[0-9]+")){
            return true;
        }
        return false;
    }

    private Boolean checkMail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    private boolean checkNames(String name) {
        boolean flag=true;
        for(int i=0;i<name.length();i++)
        {
            if(name.charAt(i)<65 || name.charAt(i)>122)
                    flag=false;
        }
        return flag;
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