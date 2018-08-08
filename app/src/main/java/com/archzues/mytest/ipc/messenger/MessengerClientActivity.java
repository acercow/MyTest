package com.archzues.mytest.ipc.messenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.archzues.mytest.R;

public class MessengerClientActivity extends AppCompatActivity {
    public static final int MSG_FROM_CLIENT = 1;

    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Messenger messenger = new Messenger(service);
            Bundle bundle = new Bundle();
            bundle.putString("msg", "zhaosen");
            Message msg = Message.obtain(null, MSG_FROM_CLIENT);
            msg.setData(bundle);
            msg.replyTo = mGetReplyMessenger;
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private final Messenger mGetReplyMessenger = new Messenger(new GetReplyHandler());

    private static class GetReplyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MessengerService.MSG_FROM_SERVER:
                    Bundle bundle = msg.getData();
                    Log.i("ipc", "Reply: " + bundle.getString("reply"));
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger_client);
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

}
