package com.archzues.mytest.ipc.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import static com.archzues.mytest.ipc.messenger.MessengerClientActivity.MSG_FROM_CLIENT;

/**
 * Created by acercow on 18-8-7.
 */

public class MessengerService extends Service {
    public static final int MSG_FROM_SERVER = 2;

    private static class MessengerHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_FROM_CLIENT:
                    Log.i("ipc", "Server response: " + msg.getData().getString("msg"));

                    Messenger messenger = msg.replyTo;
                    Message replyMsg = Message.obtain(null, MSG_FROM_SERVER);
                    Bundle bundle = new Bundle();
                    bundle.putString("reply", "阿拉收到啦");
                    replyMsg.setData(bundle);
                    try {
                        messenger.send(replyMsg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;

                default:
                    super.handleMessage(msg);
            }
        }
    }

    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
