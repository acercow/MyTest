package com.archzues.mytest.ipc.normal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.archzues.mytest.IBookManager;
import com.archzues.mytest.IOnNewBookArrivedListener;
import com.archzues.mytest.R;
import com.archzues.mytest.bean.Book;

public class BookMangerActivity extends AppCompatActivity {
    private IBookManager mBookManager;


    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IBookManager bookManager = IBookManager.Stub.asInterface(service);
            mBookManager = bookManager;
            try {
                bookManager.registerListener(listener);
                Log.i("ipc", "BookMangerActivity: " + bookManager.getBookList());
                bookManager.addBook(new Book(23345, "活着"));
                Log.i("ipc", "BookMangerActivity: " + bookManager.getBookList());

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manger);

        bindService(new Intent(this, BookManagerService.class), mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private IOnNewBookArrivedListener listener = new IOnNewBookArrivedListener.Stub() {
        @Override
        public void onNewBookArrived(Book book) throws RemoteException {
            Log.i("ipc", "Client onNewBookArrived: " + book.bookName);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBookManager != null && mBookManager.asBinder().isBinderAlive()) {
            try {
                mBookManager.unRegisterListener(listener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        unbindService(mServiceConnection);

    }
}
