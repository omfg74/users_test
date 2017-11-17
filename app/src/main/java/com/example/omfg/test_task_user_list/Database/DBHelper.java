package com.example.omfg.test_task_user_list.Database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by omfg on 17.11.2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    @Override
    public static final String DB_NAME = "USERS";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "TABLE_USERS";
    public static final String COLUMN_ID="ID";
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
