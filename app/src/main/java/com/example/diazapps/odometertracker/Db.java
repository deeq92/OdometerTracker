package com.example.diazapps.odometertracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

    public class Db extends SQLiteOpenHelper {

        private final ArrayList<MyEntry> entryList = new ArrayList<>();

        public Db(Context context) {
            super(context, Constants.DATABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + Constants.TABLE_NAME + "  " +
                    " (" + Constants.START_ODO + " TEXT, "
                    + Constants.DATE + " TEXT, "
                    + Constants.END_ODO + " TEXT, "
                    + Constants.MILES_DRIVEN + " TEXT, "
                    + Constants.NOTE + " TEXT, "
                    + Constants.ENTRY_ID + " INTEGER PRIMARY KEY)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        }

    public void clearDatabase() {
        SQLiteDatabase db = this.getReadableDatabase();
        String clearDBQuery = "DELETE FROM "+ Constants.TABLE_NAME;
        db.execSQL(clearDBQuery);
    }


    public ArrayList<MyEntry> getEntries(){

        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Constants.TABLE_NAME, new String[]{Constants.START_ODO, Constants.DATE, Constants.END_ODO,
                Constants.MILES_DRIVEN, Constants.NOTE}, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                MyEntry entry = new MyEntry();
                entry.setStartOdo(Integer.valueOf(cursor.getString(cursor.getColumnIndex(Constants.START_ODO))));
                entry.setDate(cursor.getString(cursor.getColumnIndex(Constants.DATE)));
                entry.setEndOdo(Integer.valueOf(cursor.getString(cursor.getColumnIndex(Constants.END_ODO))));
                entry.setMilesDriven(Integer.valueOf(cursor.getString(cursor.getColumnIndex(Constants.MILES_DRIVEN))));
                entry.setNote(cursor.getString(cursor.getColumnIndex(Constants.NOTE)));

                entryList.add(entry);

            } while (cursor.moveToNext());
        }

        return entryList;

    }

    public void addEntry(MyEntry entry)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.START_ODO, entry.getStartOdo());
        values.put(Constants.DATE, entry.getDate());
        values.put(Constants.END_ODO, entry.getEndOdo());
        values.put(Constants.MILES_DRIVEN, entry.getMilesDriven());
        values.put(Constants.NOTE, entry.getNote());

        db.insert(Constants.TABLE_NAME, null, values);

        db.close();
    }


}
