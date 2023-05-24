package com.example.wordle;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "Users";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "myusers1";


    private static final String FNAME_COL = "firstname";


    private static final String EMAIL_COL = "email";


    private static final String PASSWORD_COL = "password";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + FNAME_COL + " TEXT,"
                + EMAIL_COL + " TEXT,"
                + PASSWORD_COL + " TEXT)";
        db.execSQL(query);
    }

    public void addNewUser(String firstname, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FNAME_COL, firstname);
        values.put(EMAIL_COL, email.toLowerCase());
        values.put(PASSWORD_COL, password);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }
    @SuppressLint("Range")
    public String ForgotPassword(String email,String firstname)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String password="";
        String selection = EMAIL_COL+" = ? AND "+FNAME_COL+" = ?";
        String[] selectionArgs = {email,firstname};
        String[] columns = {PASSWORD_COL};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            password = cursor.getString(cursor.getColumnIndex(PASSWORD_COL));
        }
        cursor.close();
        db.close();
        return password;
    }
    public void deleteUser(String email,String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        String[] selections={email,password};
        String where="email= ? AND password= ?";
        db.delete(TABLE_NAME,where,selections);
        db.close();
    }
    public void updateUser( String originalUserPassword,String email, String password)  {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PASSWORD_COL, password);
        db.update(TABLE_NAME,values,"password= ? AND email= ?",new String[]{password,email});
        db.close();
    }

    @SuppressLint("Range")
    public boolean login(String tryfirstname, String trypassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] selections={tryfirstname.toLowerCase(),trypassword};
        Cursor cursor=db.rawQuery("SELECT "+ FNAME_COL+" FROM "+TABLE_NAME+" WHERE firstname= ? AND password= ?",selections);
        cursor.moveToFirst();
        if(cursor.getCount()>0)
        {
            String name=cursor.getString(cursor.getColumnIndex(FNAME_COL));
            if(name.length()==tryfirstname.length())
            {
                cursor.close();
                db.close();
                return true;
            }
        }
            cursor.close();
            db.close();
            return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
