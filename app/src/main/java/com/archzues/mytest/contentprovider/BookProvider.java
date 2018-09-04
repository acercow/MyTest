package com.archzues.mytest.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by acercow on 18-9-4.
 */

public class BookProvider extends ContentProvider {

    private static final String TAG = BookProvider.class.getSimpleName();

    private static final String AUTHORITY = "tk.acercow.book.provider";

    private static final int BOOK_URI_CODE = 0;
    private static final int USER_URI_CODE = 1;

    private static final Uri BOOK_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/book");
    private static final Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/user");

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, "book", BOOK_URI_CODE);
        sUriMatcher.addURI(AUTHORITY, "user", USER_URI_CODE);
    }

    private SQLiteDatabase mDb;

    @Override
    public boolean onCreate() {
        Log.i(TAG, "onCreate, thread: " + Thread.currentThread().getName());
        mDb = new DbOpenHelper(getContext()).getWritableDatabase();

        mDb.execSQL("DELETE FROM " + DbOpenHelper.TABLE_BOOK);
        mDb.execSQL("DELETE FROM " + DbOpenHelper.TABLE_USER);

        mDb.execSQL("INSERT INTO " + DbOpenHelper.TABLE_BOOK + " VALUES(3, 'Android');");
        mDb.execSQL("INSERT INTO " + DbOpenHelper.TABLE_BOOK + " VALUES(4, 'iOS');");
        mDb.execSQL("INSERT INTO " + DbOpenHelper.TABLE_BOOK + " VALUES(7, 'Html5');");

        mDb.execSQL("INSERT INTO " + DbOpenHelper.TABLE_USER + " VALUES(1, 'joke', 1);");
        mDb.execSQL("INSERT INTO " + DbOpenHelper.TABLE_USER + " VALUES(2, 'tim', 0);");

        return true;
    }

    private String getTableName(Uri uri) {
        String tableName = null;
        switch (sUriMatcher.match(uri)) {
            case BOOK_URI_CODE:
                tableName = DbOpenHelper.TABLE_BOOK;
                break;

            case USER_URI_CODE:
                tableName = DbOpenHelper.TABLE_USER;
                break;
        }
        return tableName;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.i(TAG, "query, thread: " + Thread.currentThread().getName());
        String table = getTableName(uri);
        return mDb.query(table, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        String table = getTableName(uri);
        mDb.insert(table, null, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        String table = getTableName(uri);
        int row = mDb.delete(table, selection, selectionArgs);
        if (row > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return row;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        String table = getTableName(uri);
        int row = mDb.update(table, values, selection, selectionArgs);
        if (row > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return row;
    }
}
