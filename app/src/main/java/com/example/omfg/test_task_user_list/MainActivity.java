package com.example.omfg.test_task_user_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
MainActivityFragment mainActivityFragment = new MainActivityFragment();
FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
transaction = getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.add(R.id.mainFrame,mainActivityFragment);
        transaction.commit();
        UserParser userParser = new UserParser();
        userParser.execute();
    }
}
