package com.example.wordle;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;


public class Board extends View {

    private final Square[][] allSquares;
    private final Paint paint;
    private final Paint textPaint;
    private final Paint backgroundPaint;
    Boolean isWordle;
    Boolean isHebWordle;
    public int NumberofGreens;
    public Board(Context context,Boolean IsWordle,Boolean IsHebWordle) {
        super(context);
        this.paint = new Paint();
        isWordle=IsWordle;
        isHebWordle=IsHebWordle;
        this.textPaint = new Paint();
        this.backgroundPaint = new Paint();
        this.allSquares = new Square[5][6];
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(9);
        NumberofGreens=0;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setTextSize(160);
        textPaint.setColor(Color.BLACK);
        backgroundPaint.setColor(Color.rgb(252, 240, 242));

        //making the squares in the right place
        for (int i = 0; i < this.allSquares.length; i++) {
            for (int j = 0; j < this.allSquares[i].length; j++) {
                allSquares[i][j] = new Square((i * 175)+100, (j * 190)+600, (i * 175) + 270, (j * 190) + 790);
            }
        }
    }

    //draws the board
    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawPaint(backgroundPaint);
        for (int i = 0; i < this.allSquares.length; i++) {
            for (int j = 0; j < this.allSquares[i].length; j++) {
                this.allSquares[i][j].DrawBorder(canvas, paint);
            }
        }
    }
    //a method that checks the user guess
    public void onSubmitGuess(String str,int numberofguesses,String CorrectWord) {
        if (isHebWordle){
            for (int j = 0; j < allSquares.length; j++) {
                allSquares[j][numberofguesses].isHeb = true;
                String x = String.valueOf(str.charAt(4-j));
                String y = CorrectWord;
                allSquares[j][numberofguesses].chartoadd = x;
                allSquares[j][numberofguesses].numberofgueeses =numberofguesses;
                allSquares[j][numberofguesses].charatplace = j;
                if (y.charAt(j) == str.charAt(j)) {
                    allSquares[4-j][numberofguesses].CorrectLandPos = true;
                    NumberofGreens++;
                } else if (isExist(y, str.charAt(j))) {
                    allSquares[4-j][numberofguesses].WrongPos = true;
                    NumberofGreens = 0;
                } else {
                    allSquares[4-j][numberofguesses].WrongL = true;
                    NumberofGreens = 0;
                }
            }
        }
        else{
            for (int j = 0; j < allSquares.length; j++) {
                String x = String.valueOf(str.charAt(j));
                String y = CorrectWord.toUpperCase();
                allSquares[j][numberofguesses].chartoadd = x.toUpperCase();
                allSquares[j][numberofguesses].numberofgueeses = numberofguesses;
                allSquares[j][numberofguesses].charatplace = j;
                if (y.charAt(j) == str.toUpperCase().charAt(j)) {
                    allSquares[j][numberofguesses].CorrectLandPos = true;
                    NumberofGreens++;
                } else if (isExist(y, str.toUpperCase().charAt(j))) {
                    allSquares[j][numberofguesses].WrongPos = true;
                    NumberofGreens = 0;
                } else {
                    allSquares[j][numberofguesses].WrongL = true;
                    NumberofGreens = 0;
                }
            }
        }
        invalidate();
    }

    //checks if char exists in a string
    public Boolean isExist(String CorrectWord,char c)
    {
        Boolean flag=false;
        for(int i=0;i<CorrectWord.length();i++)
        {
            if(CorrectWord.charAt(i)==c)
                flag=true;
        }
        return flag;
    }
    //checks if the number of correct chars is 5 (like the correct word length)
    public Boolean isCorrect()
    {
        if(NumberofGreens==5)
            return true;
        return false;
    }
}