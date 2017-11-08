package demo.com.roomdemo.controller;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import demo.com.roomdemo.model.UserInfo;

/**
 * Created by Amoeba on 11/3/2017.
 */

@Dao
public interface UserInfoDAO {
    @Query("SELECT * FROM UserInfo")
    List<UserInfo> getAll();
    @Insert
    void insert(UserInfo userInfo);
}