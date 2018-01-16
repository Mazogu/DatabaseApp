package com.example.micha.celebdatabase.data;

import android.provider.BaseColumns;

/**
 * Created by micha on 1/15/2018.
 */

public class DatabaseContract {

    public static final String  CREATE_CELEBRITY_TABLE = "CREATE TABLE "+ Celebrity.TABLE_NAME + "(" + Celebrity.NAME +
            " TEXT PRIMARY KEY," + Celebrity.AGE +" TEXT," + Celebrity.GENDER+ " TEXT," + Celebrity.INDUSTRY + " TEXT,"
            + Celebrity.FAVORITE + " BIT)";


    public static class Celebrity implements BaseColumns{
        public static final String TABLE_NAME = "Celebrity";
        public static final String NAME = "Name";
        public static final String AGE = "Age";
        public static final String GENDER = "Gender";
        public static final String INDUSTRY = "Industry";
        public static final String FAVORITE = "Favorite";
    }
}
