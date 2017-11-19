package com.example.omfg.test_task_user_list.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.net.IDN;

/**
 * Created by omfg on 17.11.2017.
 */

public class DBHelper extends SQLiteOpenHelper {
   
    public static final String DB_NAME = "USERS";
    public static final int DB_VERSION = 1;
    public static final String TABLE_USERS = "TABLE_USERS";
    public static final String TABLE_ADRESS = "TABLE_ADRESS";
    public static final String TABLE_COMPANY = "TABLE_COMPANY";

    public static final String COLUMN_ID="USER_ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_USERNAME = "USERNAME";
    public static final String COLUMN_EMAIL = "EMAIL";
    public static final String COLUMN_PHONE = "PHONE";
    public static final String COLUMN_WEBSITE = "WEB_SITE";
    //TABLE ADRESS
    //ID
    public static final String COLUMN_STREET = "STREET";
    public static final String COLUMN_SUITE = "SUITE";
    public static final String COLUMN_CITY = "CITY";
    public static final String COLUMN_ZIPCODE = "ZIPCODE";
    public static final String COLUMN_LAT = "LAT";
    public static final String COLUMN_LNG = "LNG";

    //TABLE COMPANY
    //ID
    public static final String COLUMN_COMPANY_NAME = "COMPANY_NAME";
    public static final String COLUMN_CATCH_PHRASE = "CATCH_PHRASE";
    public static final String COLUMN_BS = "BS";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "
                        +TABLE_USERS+"(_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                        +COLUMN_ID+ " LONG, "
                        +COLUMN_NAME+ " TEXT, "
                        +COLUMN_USERNAME+ " TEXT, "
                        +COLUMN_EMAIL+ " TEXT, "
                        +COLUMN_PHONE+ " TEXT, "
                        +COLUMN_WEBSITE + " TEXT,"

        +COLUMN_STREET+" TEXT, "
        +COLUMN_SUITE+" TEXT, "
        +COLUMN_CITY+" TEXT, "
        +COLUMN_ZIPCODE+" TEXT, "
        +COLUMN_LAT+" TEXT, "
        +COLUMN_LNG + " TEXT, "
        +COLUMN_COMPANY_NAME + " TEXT, "
        +COLUMN_CATCH_PHRASE + " TEXT, "
        +COLUMN_BS + " TEXT "+

        ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
