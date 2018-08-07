package com.archzues.mytest.ipc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.archzues.mytest.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Log.i("ipc", "ThirdActivity id : " + UserManager.sUserId);

        User user = new User(1112, "zhaosen", true);

//        serialize(user);
//        User result = deSerialize();
//        Log.i("ipc", "ThirdActivity deSerialize: " + result.getUserName());


    }

    private void serialize(User user) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ace-cache.txt"));
            out.writeObject(user);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private User deSerialize() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("ace-cache.txt"));
            User user = (User) in.readObject();
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
