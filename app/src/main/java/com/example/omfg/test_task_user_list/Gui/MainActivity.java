package com.example.omfg.test_task_user_list.Gui;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.omfg.test_task_user_list.R;

import java.sql.BatchUpdateException;

public class MainActivity extends AppCompatActivity {
//    DBHelper dbHelper = new DBHelper(this);
MainActivityFragment mainActivityFragment = new MainActivityFragment();
FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean phone;
        int screenSize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        if(screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE || screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            phone =false;
            transaction = getSupportFragmentManager().beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putBoolean("phone",phone);
mainActivityFragment.setArguments(bundle);
            transaction.add(R.id.mainFragmentList,mainActivityFragment);
            transaction.commit();

           MessageFragment messageFragment = new MessageFragment();
            Bundle bundle1 = new Bundle();
            int i = 1;
            bundle1.putInt("i",i);
            bundle1.putString("name","name");
            bundle1.putBoolean("phone",phone);


            FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
            tr.add(R.id.message_fragment,messageFragment);
           messageFragment.setArguments(bundle1);
            tr.commit();

        }else {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
phone =true;
Bundle bundle = new Bundle();
bundle.putBoolean("phone",phone);
            mainActivityFragment.setArguments(bundle);
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.mainFrame, mainActivityFragment);
            transaction.commit();

        }


    }
}
