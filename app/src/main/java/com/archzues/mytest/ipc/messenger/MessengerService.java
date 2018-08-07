package com.archzues.mytest.ipc.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;

import static com.archzues.mytest.ipc.messenger.MessengerClientActivity.MSG_FROM_CLIENT;

/**
 * Created by acercow on 18-8-7.
 */

public class MessengerService extends Service {

    private static class MessengerHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case MSG_FROM_CLIENT:
                    Log.i("ipc", "Server response: " + msg.getData().getString("msg"));
            }
            super.handleMessage(msg);
        }
    }


    private final Messenger mMessenger = new Messenger(new MessengerHandler());


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
