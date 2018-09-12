package com.archzues.mytest.binderpool;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.archzues.mytest.pool.IBinderPool;

import java.util.concurrent.CountDownLatch;

/**
 * Created by acercow on 18-9-11.
 */

public class BinderPool {
    private static final String TAG = BinderPool.class.getSimpleName();

    public static final int BINDER_NONE = -1;
    public static final int BINDER_SECURITY_CENTER = 1;
    public static final int BINDER_COMPUTE = 2;

    private Context mContext;
    private IBinderPool mBinderPool;
    private static volatile BinderPool sInstance;
    private CountDownLatch mCountdownLatch;

    public BinderPool(Context context) {
        this.mContext = context.getApplicationContext();
        connectBinderPoolService();
    }

    public static BinderPool getInstance(Context context) {
        if (sInstance == null) {
            synchronized (BinderPool.class) {
                if (sInstance == null) {
                    sInstance = new BinderPool(context);
                }
            }
        }
        return sInstance;
    }

    private synchronized void connectBinderPoolService() {
        mCountdownLatch = new CountDownLatch(1);
        Intent intent = new Intent(mContext, BinderPoolService.class);
        mContext.bindService(intent, mBinderPoolConnection, Context.BIND_AUTO_CREATE);
        try {
            mCountdownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private ServiceConnection mBinderPoolConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinderPool = IBinderPool.Stub.asInterface(service);

            try {
                mBinderPool.asBinder().linkToDeath(mBinderPoolDeathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            mCountdownLatch.countDown();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public IBinder queryBinder(int bindCode) {
        IBinder binder = null;
        if (mBinderPool == null) {
            return null;
        }
        try {
            binder = mBinderPool.queryBinder(bindCode);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return binder;
    }

    private IBinder.DeathRecipient mBinderPoolDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            mBinderPool.asBinder().unlinkToDeath(mBinderPoolDeathRecipient, 0);
            mBinderPool = null;
            connectBinderPoolService();
        }
    };

    public static class BinderPoolImpl extends IBinderPool.Stub {

        @Override
        public IBinder queryBinder(int binderCode) throws RemoteException {
            IBinder binder = null;
            switch (binderCode) {
                case BINDER_SECURITY_CENTER:
                    binder = new SecurityCenterImpl();
                    break;

                case BINDER_COMPUTE:
                    binder = new BinderPoolImpl();
                    break;
            }
            return binder;
        }
    }

}
