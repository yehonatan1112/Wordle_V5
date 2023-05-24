package com.example.wordle;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.fonts.Font;

import java.util.Calendar;

public class Square {
    protected int width;
    protected int height;
    protected int widthEnd;
    protected int heightEnd;
    protected String color;
    protected Boolean hasText;
    protected Boolean WrongL;
    protected Boolean WrongPos;
    protected Boolean CorrectLandPos;
    protected String chartoadd;
    protected int numberofgueeses;
    public Boolean isHeb;
    protected int charatplace;
    protected Boolean isIBefore;

    //constructor
    public Square(int w, int h, int we, int he){
        this.width = w;
        this.height = h;
        this.widthEnd = we;
        this.heightEnd = he;
        this.color = "white";
        this.hasText = false;
        this.WrongL = false;
        this.WrongPos = false;
        this.isHeb = false;
        this.CorrectLandPos = false;
        this.chartoadd="";
        charatplace=0;
        isIBefore=false;
        numberofgueeses=0;
    }

    //getters
    public String getColor() { return this.color; }

    //setters
    public void setColor(String color){this.color = color;}
    //bordering the cubes

    public void DrawBorder(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(width, height, widthEnd, heightEnd, paint);
        if (WrongL) {
            paint.setColor(Color.LTGRAY);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(width + 5, height + 5, widthEnd - 5, heightEnd - 5, paint);
        } else if (WrongPos) {
            paint.setColor(Color.YELLOW);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(width + 5, height + 5, widthEnd - 5, heightEnd - 5, paint);
        } else if (CorrectLandPos) {
            paint.setColor(Color.GREEN);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(width + 5, height + 5, widthEnd - 5, heightEnd - 5, paint);
        }
        paint.setColor(Color.BLACK);
        paint.setTextSize(120);
        paint.setTypeface(Typeface.create("SANS_SERIF", Typeface.NORMAL));
        if (!isHeb) {
            if (chartoadd.length() > 0)
                if (chartoadd.toLowerCase().charAt(0) == 'i') {
                    isIBefore = true;
                    canvas.drawText(chartoadd, 133 + (charatplace * 190), 750 + (numberofgueeses * 190), paint);
                } else if (isIBefore) {
                    canvas.drawText(chartoadd, 133 + (charatplace * 170), 750 + (numberofgueeses * 190), paint);
                    isIBefore = false;
                } else
                    canvas.drawText(chartoadd, 133 + (charatplace * 180), 750 + (numberofgueeses * 190), paint);
        }
        else
        {
            canvas.drawText(chartoadd, 133 + (charatplace * 180), 750 + (numberofgueeses * 190), paint);
        }
    }

}