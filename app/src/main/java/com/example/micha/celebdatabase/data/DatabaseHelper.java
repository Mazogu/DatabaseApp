package com.example.micha.celebdatabase.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.micha.celebdatabase.Celebrity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by micha on 1/15/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Celebrity";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DatabaseContract.CREATE_CELEBRITY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long saveCeleb(Celebrity celeb){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(DatabaseContract.Celebrity.NAME,celeb.getName());
        content.put(DatabaseContract.Celebrity.AGE, celeb.getAge());
        content.put(DatabaseContract.Celebrity.GENDER, celeb.getGender());
        content.put(DatabaseContract.Celebrity.INDUSTRY, celeb.getIndustry());
        content.put(DatabaseContract.Celebrity.FAVORITE,celeb.getFavorite());

        long rowNumber = database.insert(DatabaseContract.Celebrity.TABLE_NAME, null, content);
        database.close();
        return rowNumber;
    }

    public List<Celebrity> getCelebrityList(){
        ArrayList<Celebrity> celebList = new ArrayList<>();
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseContract.Celebrity.TABLE_NAME, null);

        if(cursor.moveToFirst()){
            do{
                Celebrity celebrity = new Celebrity(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
                celebList.add(celebrity);
            }while(cursor.moveToNext());
        }
        database.close();
        return celebList;
    }

}
