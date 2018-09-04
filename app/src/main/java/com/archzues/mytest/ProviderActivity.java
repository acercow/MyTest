package com.archzues.mytest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class ProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);

//        Uri uri = Uri.parse("content://tk.acercow.book.provider");

        Uri bookUri = Uri.parse("content://tk.acercow.book.provider/book");

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "Harry Potter");
        contentValues.put("_id", "93");
        getContentResolver().insert(bookUri, contentValues);

        Cursor cursor = getContentResolver().query(bookUri, null, null, null, null);

        while (cursor.moveToNext()) {
            Log.i("ProviderActivity",  "Book [name: " + cursor.getString(cursor.getColumnIndex("name"))
                    + ", id: " + cursor.getInt(cursor.getColumnIndex("_id")) + "]");
        }
    }
}
