package com.example.inbar.todolistmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import com.parse.ParseObject;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Inbar on 27/03/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "school_db";
    private static final String TABLE_NAME = "todo_db";
    private static final String KEY_ID = "_id";
    private static final String TITLE = "title";
    private static final String DUE = "due";

    public DBHelper(Context context){
        super(context, DATABASE_NAME  , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table todo_db(" + "_id integer primary key autoincrement, " +
                "title text, " + "due long);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer){
        //drop old tables if exist
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        //create new todo_db table
        this.onCreate(db);
    }

    public int addItem(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE, item.getTitle());
        long val = item.getDate().getTime();
        values.put(DUE, val);
        long itemId = db.insert(TABLE_NAME, null, values);
        int intItemId = (int)itemId;
        db.close();
        return intItemId;
    }

    public void deleteItem(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void setList(ArrayList<Item> list){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Item item = null;
        if(cursor.moveToFirst()){
            do{
                item = new Item(cursor.getString(1), new Date(cursor.getLong(2)));
                item.setId(Integer.parseInt(cursor.getString(0)));
                list.add(item);
            }while(cursor.moveToNext());
        }
    }

}
