package com.djenerson.slide_show;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GradeDBHelper extends SQLiteOpenHelper {

    public static final String GRADE_TABLE_NAME = "grades";
    public static final String PRIMARY_KEY_NAME = "id";
    public static final String FIELD1_NAME = "grade1";
    public static final String FIELD2_NAME = "grade2";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "KnowlaGrades.db";
    private static final String TABLE_SPECIFICATIONS =
            // form: "CREATE TABLE grades(id INTEGER PRIMARY KEY, grade1 INTEGER)"
            "CREATE TABLE "+ GRADE_TABLE_NAME +" ( "+ PRIMARY_KEY_NAME +" INTEGER PRIMARY KEY," + FIELD1_NAME +" INTEGER, "+FIELD2_NAME+" INTEGER )";


    public GradeDBHelper(Context context) {
        // A database exists, named DATABASE_NAME, with TABLE_SPECIFICATIONS
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_SPECIFICATIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
