package com.example.wordle;

public class Stats {

    //wordle stats
    public static int wordle_number_of_wins=0;
    public static double wordle_avg_tries=0;
    public static int wordle_tries=0;

    //hebrew wordle stats
    public static int hebwordle_number_of_wins=0;
    public static int hebwordle_tries=0;
    public static double hebwordle_avg_tries=0;

    //population global stats
    public static int popglobal_high=0;
    public static int popglobal_number_of_plays=0;

    // area global stats
    public static int areaglobal_high=0;
    public static int areaglobal_number_of_plays=0;

    // flagle stats
    public static int flagle_high=0;
    public static int flagle_Low=0;
    public static int flagle_number_of_plays=0;

    //us states stats
    public static int usstates_high=0;
    public static int usstates_number_of_plays=0;

    //countries stats
    public static int countries_high=0;
    public static int countries_number_of_plays=0;


    //NBA stats
    public static int NBA_high=0;
    public static int NBA_number_of_plays=0;
    public static double NBA_avg=0;
    public static int NBA_totals=0;

    //reset stats
    public static void Reset()
    {
        hebwordle_tries=0;
        wordle_avg_tries=0;
        wordle_tries=0;
        NBA_avg=0;
        wordle_number_of_wins=0;
        hebwordle_avg_tries=0;
        hebwordle_number_of_wins=0;
        popglobal_high=0;
        areaglobal_high=0;
        flagle_high=0;
        flagle_Low=0;
        usstates_high=0;
        countries_high=0;
        areaglobal_number_of_plays=0;
        popglobal_number_of_plays=0;
        NBA_high=0;
        usstates_number_of_plays=0;
        countries_number_of_plays=0;
        NBA_number_of_plays=0;
        flagle_number_of_plays=0;
    }

}
