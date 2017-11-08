package demo.com.roomdemo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;


/**
 * Created by Amoeba on 11/3/2017.
 */

public class MessagesDB extends SQLiteOpenHelper {
    private static final String DB_NAME = "SQLITE_DEMO";
    private static final int DB_VERSION = 1;
    private static final String TABLE_MESSAGE = "Messages";
    private static final String COL_TEXT = "message";
    private static final String COL_TIME = "time";
    public MessagesDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(String.format("CREATE TABLE %s (_id INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT);",
                TABLE_MESSAGE, COL_TEXT, COL_TIME));
        sqLiteDatabase.execSQL(String.format("INSERT INTO %s ('%s', '%s') VALUES ('%s', '%s');", TABLE_MESSAGE, COL_TEXT, COL_TIME,
                "Hello world", new Date().toString()));
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}