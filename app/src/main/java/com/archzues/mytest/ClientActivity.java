package com.archzues.mytest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientActivity extends AppCompatActivity {

    private static final int MSG_SOCKET_ESTABLISHED = 0x01;

    private Socket mClientSocket;
    private PrintWriter mPrintWriter;

    private final Handler mHander = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SOCKET_ESTABLISHED:
                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader(mClientSocket.getInputStream()));
                        while(!ClientActivity.this.isFinishing()) {
                            String str = in.readLine();
                            System.out.println("msg receive: " + str);

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;

            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);


        Socket socket = null;
        while (socket == null) {
            try {
                socket = new Socket("localhost", 8688);
                mClientSocket = socket;
                mPrintWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                mHander.sendEmptyMessage(MSG_SOCKET_ESTABLISHED);
                System.out.println("Connect server success");
            } catch (IOException e) {
                SystemClock.sleep(1000);
                System.out.println("Connect tcp server failed, retry...");
                e.printStackTrace();
            }

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHander != null) {
            mHander.removeCallbacksAndMessages(null);
        }
    }
}
