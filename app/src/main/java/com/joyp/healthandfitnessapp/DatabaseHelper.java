package com.joyp.healthandfitnessapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database name and version
    private static final String DATABASE_NAME = "health.db";
    private static final int DATABASE_VERSION = 1;

    // Table name and columns
    private static final String TABLE_PROFILE = "user_profile";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_WEIGHT = "weight";
    private static final String COLUMN_HEIGHT = "height";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create the profile table
        String createTable = "CREATE TABLE " + TABLE_PROFILE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_AGE + " INTEGER, " +
                COLUMN_WEIGHT + " REAL, " +
                COLUMN_HEIGHT + " REAL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
        // Create table again
        onCreate(db);
    }

    // Insert user profile data
    public boolean insertProfile(String name, int age, double weight, double height) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_AGE, age);
        contentValues.put(COLUMN_WEIGHT, weight);
        contentValues.put(COLUMN_HEIGHT, height);

        long result = db.insert(TABLE_PROFILE, null, contentValues);
        return result != -1; // return true if inserted successfully
    }

    // Update user profile data
    public boolean updateProfile(int id, String name, int age, double weight, double height) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_AGE, age);
        contentValues.put(COLUMN_WEIGHT, weight);
        contentValues.put(COLUMN_HEIGHT, height);

        int result = db.update(TABLE_PROFILE, contentValues, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0; // return true if updated successfully
    }

    // Get user profile data
    public Cursor getProfile() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_PROFILE + " LIMIT 1", null);
    }
}
