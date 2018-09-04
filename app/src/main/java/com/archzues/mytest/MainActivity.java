package com.archzues.mytest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.acercow.myapplication.IOnNewFellowListener;
import com.example.acercow.myapplication.IUserManager;

public class MainActivity extends AppCompatActivity {

    ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(getApplicationContext(), "Bind Success", Toast.LENGTH_LONG).show();
            IUserManager userManager = IUserManager.Stub.asInterface(service);
            try {
                userManager.registerListener(new IOnNewFellowListener.Stub() {
                    @Override
                    public void onNewFellowArrived(com.example.acercow.myapplication.bean.User user) throws RemoteException {
                        Log.i("jansen", "My Test: " + user.getName());
                    }
                });
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
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, ProviderActivity.class));
        finish();

//        Intent intent = new Intent("aaaa");
//        intent.setPackage("com.example.acercow.myapplication");
//        intent.setClassName("com.example.acercow.myapplication", "com.example.acercow.myapplication.GenFellowService");
//        intent.setComponent(new ComponentName("com.example.acercow.myapplication", "com.example.acercow.myapplication.GenFellowService"));
//        bindService(intent, mConn, Context.BIND_AUTO_CREATE);


//        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                Toast.makeText(MainActivity.this, "ni da ye yeyeyeyeyeyeyeye", Toast.LENGTH_SHORT).show();
//            }
//        }, new IntentFilter("zhaosen.vip"));
//        sendBroadcast(new Intent("zhaosen.vip"));
////        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("zhaosen.vip"));
//        try {
//            user.getUserName();
//            int i = 8 / 0;
//        } catch (Exception e) {
////            e.printStackTrace();
//        }
//        startActivity(new Intent(this, BookMangerActivity.class));



//        finish();


//        findViewById(R.id.btn_scroll).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                findViewById(R.id.container).scrollBy(-20, -20);
//            }
//        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unbindService(mConn);
    }
}
