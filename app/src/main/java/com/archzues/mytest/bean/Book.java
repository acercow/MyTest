package com.archzues.mytest.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by acercow on 18-8-3.
 */

public class Book implements Parcelable {
    public int bookId;
    public String bookName;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.bookId);
        dest.writeString(this.bookName);
    }

    public Book() {
    }
    public Book(int bookId, String bookName) {
        this.bookId = bookId;
        this.bookName = bookName;
    }


    protected Book(Parcel in) {
        this.bookId = in.readInt();
        this.bookName = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
