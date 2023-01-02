package com.isetr.recylcerviewitemclick;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "reservation_db";
    public static final String TABLE_RESERVATION = "reservation";
    public static final String COL_ID = "_id";
    public static final String COL_USERNAME = "username";
    public static final String COL_STARTDAY = "startday";
    public static final String COL_ENDDAY = "endday";

    public static final String CREATE_RESERVATION_TABLE = "CREATE TABLE " +
            TABLE_RESERVATION + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_USERNAME + " TEXT NOT NULL, " + COL_STARTDAY + " TEXT, "+ COL_ENDDAY +") ";
    private SQLiteDatabase db;


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table reservations(username TEXT primary key, startday Date , endday Date)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF exists " + TABLE_RESERVATION);
        onCreate(db);
    }

    public SQLiteDatabase open() {
        db = this.getWritableDatabase();
        return db;
    }

    public void addReservation(Reservation reservation) {
        open();
        ContentValues v = new ContentValues();
        v.put(COL_USERNAME, reservation.getUsername());
        v.put(COL_STARTDAY, reservation.getStartday());
        v.put(COL_ENDDAY, reservation.getEndday());

        db.insert(TABLE_RESERVATION, null, v);
    }

    public void updateReservation(Reservation reservation) {
        open();
        ContentValues v = new ContentValues();
        v.put(COL_USERNAME, reservation.getUsername());
        v.put(COL_STARTDAY, reservation.getStartday());
        v.put(COL_ENDDAY, reservation.getEndday());

        db.update(TABLE_RESERVATION, v, COL_ID + "=?", new String[]{String.valueOf(reservation.getId())});
    }

    public void removeReservation(int id) {
        open();
        db.delete(TABLE_RESERVATION, COL_ID + "=?", new String[]{String.valueOf(id)});
    }

    public ArrayList<Reservation> getAllReservation() {
        ArrayList<Reservation> list = new ArrayList<Reservation>();
        db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_RESERVATION, null, null, null, null, null, null, null);
        c.moveToFirst();
        do {
            Reservation reservation = new Reservation(c.getInt(0), c.getString(1), c.getString(2), c.getString(3));
            list.add(reservation);
        } while (c.moveToNext());
        return list;

    }

    public int getReservationCount() {
        db=this.getReadableDatabase();
        Cursor c = db.query(TABLE_RESERVATION, null, null, null, null, null, null, null);
        return c.getCount();
    }
}
