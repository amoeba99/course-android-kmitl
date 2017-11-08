package demo.com.roomdemo;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import demo.com.roomdemo.database.UserInfoDatabase;
import demo.com.roomdemo.model.UserInfo;

public class MainActivity extends AppCompatActivity {

    UserInfoDatabase userInfoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInfoDB = Room.databaseBuilder(getApplicationContext(),
                UserInfoDatabase.class, "DEMOINFO")
                .fallbackToDestructiveMigration()
                .build();


        final UserInfo user = new UserInfo();
        user.setName("Merry");
        user.setAge("14");
        new AsyncTask<Void, Void, UserInfo>() {
            @Override
            protected UserInfo doInBackground(Void... params) {
                userInfoDB.userInfoRoomDAO().insert(user);
                return user;
            }
        }.execute();
    }

    public void onTest(View view){
        TextView id = findViewById(R.id.textView2);
        TextView age = findViewById(R.id.textView3);
        TextView name = findViewById(R.id.textView);

        new AsyncTask<Void, Void, List<UserInfo>>() {
            @Override
            protected List<UserInfo> doInBackground(Void... params) {
                return userInfoDB.userInfoRoomDAO().getAll();
            }

        }.execute();


    }
}
