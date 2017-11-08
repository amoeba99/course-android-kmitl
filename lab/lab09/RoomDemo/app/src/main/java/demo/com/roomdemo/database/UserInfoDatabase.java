package demo.com.roomdemo.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import demo.com.roomdemo.controller.UserInfoDAO;
import demo.com.roomdemo.model.UserInfo;

/**
 * Created by Amoeba on 11/3/2017.
 */

    @Database(entities = {UserInfo.class}, version = 1)
    public abstract class UserInfoDatabase extends RoomDatabase {
        public abstract UserInfoDAO userInfoRoomDAO();
    }

