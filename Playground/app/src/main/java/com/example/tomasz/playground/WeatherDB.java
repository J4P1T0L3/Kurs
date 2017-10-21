package com.example.tomasz.playground;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by uczen on 2017-10-21.
 */

public class WeatherDB extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    private static final String DB_NAME = "weather.db";
    private static final String KEY_WHEATHER = "wheather";
    private static final String KEY_CITY = "city";
    private static final String KEY_DATE = "date";
    private static final String KEY_TEMP = "temp";
    private static final String KEY_OPIS = "opis";
    private static final String KEY_ICONID = "icon";

    public WeatherDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String Create_Table = "CREATE TABLE " + KEY_WHEATHER + "(" + KEY_CITY + " TEXT," + KEY_DATE + " TEXT," + KEY_TEMP + " REAL," + KEY_OPIS + " TEXT," + KEY_ICONID + " TEXT";
        sqLiteDatabase.execSQL(Create_Table);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + KEY_WHEATHER);
        onCreate(sqLiteDatabase);
    }
    public void addRepo(WeatherModel pogo){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, pogo.getDate());
        values.put(KEY_TEMP, pogo.getTemp());
        values.put(KEY_CITY, pogo.getCity());
        values.put(KEY_ICONID, pogo.getIconId());

        db.insert(KEY_WHEATHER, null, values);
        db.close();
    }
    public List<WeatherModel> getAllRepos(){
        List<WeatherModel> repos = new LinkedList<>();

        String selectQuerty = "SELECT * FROM " + KEY_WHEATHER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuerty, null);

        db.close();
        return repos;
    }
    public void clearRepos(){
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL(("delete from " + KEY_WHEATHER));
        db.close();
    }
}
