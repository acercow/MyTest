package com.archzues.mytest.binderpool;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.archzues.mytest.R;
import com.archzues.mytest.pool.ISecurityCenter;

public class BinderPoolTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_pool_test);


        new Thread(new Runnable() {
            @Override
            public void run() {
                BinderPool binderPool = BinderPool.getInstance(BinderPoolTestActivity.this);

                Log.i("BinderPoolTestActivity", "Ac TheadC: " + Thread.currentThread());

                Log.i("BinderPoolTestActivity", "binder");


                IBinder binder = binderPool.queryBinder(BinderPool.BINDER_SECURITY_CENTER);
                ISecurityCenter securityCenter = SecurityCenterImpl.asInterface(binder);

                try {
                    String encrypted = securityCenter.encrypt("zhaosen");
                    Log.i("BinderPoolTestActivity", encrypted);
                    Log.i("BinderPoolTestActivity", securityCenter.decrypt(encrypted));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }).start();




    }
}
