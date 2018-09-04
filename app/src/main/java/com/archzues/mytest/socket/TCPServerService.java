package com.archzues.mytest.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TCPServerService extends Service {

    private boolean mIsServerDestroyed = false;

    private String[] mChatMessage = new String[]{
            "你好",
            "天气好啊",
            "我觉得可以出去玩",
            "我也想出去啊"
    };

    public TCPServerService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class TcpServer implements Runnable {

        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(8688);
            } catch (IOException e) {
                System.err.println("Establish tcp server failed: 8688");
                e.printStackTrace();
                return;
            }

            while (!mIsServerDestroyed) {
                try {
                    final Socket client = serverSocket.accept();
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    private void responseClient(Socket client) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())));
        out.println("欢迎来到聊天室");
        while (!mIsServerDestroyed) {
            String str = in.readLine();
            System.out.println("[Client]: " + str);
            if (str == null) {
               break;
            }
            int i = new Random().nextInt(mChatMessage.length);
            String msg = mChatMessage[i];
            out.println(msg);
            System.out.println("[Send]: " + msg);
        }
        System.out.println("Client quit");

        out.close();
        in.close();

    }
}
