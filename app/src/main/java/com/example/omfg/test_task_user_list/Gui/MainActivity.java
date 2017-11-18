package com.example.omfg.test_task_user_list.Gui;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.omfg.test_task_user_list.R;

public class MainActivity extends AppCompatActivity {
//    DBHelper dbHelper = new DBHelper(this);
MainActivityFragment mainActivityFragment = new MainActivityFragment();
FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        transaction = getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.mainFrame,mainActivityFragment);
        transaction.commit();

    }
}
