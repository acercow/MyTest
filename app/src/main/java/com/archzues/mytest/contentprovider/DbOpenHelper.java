package com.archzues.mytest.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by acercow on 18-9-4.
 */

public class DbOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "book_provider.db";
    public static final String TABLE_BOOK = "book";
    public static final String TABLE_USER = "user";

    private static final int DB_VERSION = 1;

    private static final String CREATE_BOOK_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_BOOK
            + " (_id INTEGER PRIMARY KEY,"
            + "name TEXT)";

    private static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_USER
            + "(_id INTEGER PRIMARY KEY,"
            + "name TEXT,"
            + "sex INTEGER)";


    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK_TABLE);
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
