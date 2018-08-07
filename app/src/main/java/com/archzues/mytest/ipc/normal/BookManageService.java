package com.archzues.mytest.ipc.normal;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.archzues.mytest.IBookManager;
import com.archzues.mytest.bean.Book;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BookManageService extends Service {

    private CopyOnWriteArrayList<Book> mBooks = new CopyOnWriteArrayList<>();

    private Binder mBinder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBooks;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBooks.add(new Book(38926, "小王子"));
            mBooks.add(new Book(23454, "世界史"));
        }
    };


    public BookManageService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
         return mBinder;
    }
}
