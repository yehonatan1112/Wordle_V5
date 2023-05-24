package com.example.wordle;

import static android.app.PendingIntent.FLAG_IMMUTABLE;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Calendar;

public class EditUser extends AppCompatActivity {

    private EditText etUpdateEmail, etDeleteEmail, etOldPassword, etNewPassword,etDeletePassWord;
    private Button updateUserBtn, deleteUserBtn,YesLogOut,NoLogOut,NoDelete,YesDelete;
    private DBHandler dbHandler;
    public Calendar calendar= Calendar.getInstance();
    public PendingIntent pendingIntent;
    public AlarmManager alarmManager;
    private boolean isGoingToHomeScreen = false;

    Dialog d,dd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        etUpdateEmail = findViewById(R.id.emailupdate);
        etDeleteEmail = findViewById(R.id.emailDelete);
        etDeletePassWord = findViewById(R.id.passwordDelete);
        etOldPassword = findViewById(R.id.oldpassword);
        etNewPassword = findViewById(R.id.newpassword);
        updateUserBtn = findViewById(R.id.btnUpdate);
        deleteUserBtn = findViewById(R.id.btnDelete);
        d=new Dialog(this);
        dd=new Dialog(this);
        dbHandler = new DBHandler(EditUser.this);

        String updateEmail = etUpdateEmail.getText().toString();
        String deleteEmail = etDeleteEmail.getText().toString();
        String deletePassword = etDeletePassWord.getText().toString();
        String oldPassword = etOldPassword.getText().toString();
        String newPassword = etNewPassword.getText().toString();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        updateUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbHandler.updateUser(oldPassword,updateEmail,newPassword);

                Toast.makeText(EditUser.this, "User Updated..", Toast.LENGTH_SHORT).show();
                isGoingToHomeScreen=true;
                Intent i = new Intent(EditUser.this, LoggedMainActivity.class);
                startActivity(i);
            }
        });

        deleteUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dd.setContentView(R.layout.deleteuser_dialog);
                YesDelete=dd.findViewById(R.id.yes);
                dd.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                NoDelete=dd.findViewById(R.id.no);
                dd.setCancelable(true);

                YesDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dbHandler.deleteUser(deleteEmail,deletePassword);
                        isGoingToHomeScreen=true;
                        Toast.makeText(EditUser.this, "User Deleted", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(EditUser.this, MainActivity.class);
                        startActivity(i);
                    }
                });
                NoDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dd.cancel();
                    }
                });
                dd.show();
            }
        });
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottonnav);
        bottomNavigationView.setSelectedItemId(R.id.action_Settings);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.action_Settings)
                {
                    isGoingToHomeScreen=true;
                    Intent intent = new Intent(EditUser.this, Settings.class);
                    startActivity(intent);
                    return true;
                }
                if(id==R.id.action_Main)
                {
                    isGoingToHomeScreen=true;
                    Intent intent;
                    intent = new Intent(EditUser.this, LoggedMainActivity.class);
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
                            Intent intent = new Intent(EditUser.this,MainActivity.class);
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