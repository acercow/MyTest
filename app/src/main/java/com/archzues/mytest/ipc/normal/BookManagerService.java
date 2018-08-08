package com.archzues.mytest.ipc.normal;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import com.archzues.mytest.IBookManager;
import com.archzues.mytest.IOnNewBookArrivedListener;
import com.archzues.mytest.bean.Book;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class BookManagerService extends Service {

    private CopyOnWriteArrayList<Book> mBooks = new CopyOnWriteArrayList<>();
    private RemoteCallbackList<IOnNewBookArrivedListener> mListeners = new RemoteCallbackList<>();
    private AtomicBoolean mIsDestroyed = new AtomicBoolean(false);

    @Override
    public void onCreate() {
        super.onCreate();
        mBooks.add(new Book(38926, "小王子"));
        mBooks.add(new Book(23465, "世界史"));
        new WorkerThread().start();
    }

    private Binder mBinder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBooks;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            if (book != null) {
                mBooks.add(book);
            }
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
//            if (mListeners.contains(listener)) {
//                Log.i("ipc", "This listener already exist");
//            } else {
//                mListeners.add(listener);
//            }
//            Log.i("ipc", "Current listener size: " + mListeners.size());
            mListeners.register(listener);
        }

        @Override
        public void unRegisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
//            if (mListeners.contains(listener)) {
//                mListeners.remove(listener);
//            } else {
//                Log.i("ipc", "Can't unregister this listener");
//            }
//            Log.i("ipc", "Current listener size: " + mListeners.size());
            mListeners.unregister(listener);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
         return mBinder;
    }

    private class WorkerThread extends Thread {
        @Override
        public void run() {
            while (!mIsDestroyed.get()) {
                try {
                    Thread.sleep(5000);
                    Book book = new Book(mBooks.size() + 1, "Book #" + mBooks.size() + 1);
                    addNewBook(book);
                } catch (InterruptedException | RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void addNewBook(Book book) throws RemoteException {
        mBooks.add(book);
        int N = mListeners.beginBroadcast();
        for (int i = 0; i < N; i++) {
            mListeners.getBroadcastItem(i).onNewBookArrived(book);
        }
//        for (IOnNewBookArrivedListener listener : mListeners) {
//            listener.onNewBookArrived(book);
//        }
        mListeners.finishBroadcast();
    }
}
