package com.example.omfg.test_task_user_list.Gui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.omfg.test_task_user_list.Objects.ListData;
import com.example.omfg.test_task_user_list.Logic.OnDownLoadComplated;
import com.example.omfg.test_task_user_list.R;
import com.example.omfg.test_task_user_list.Logic.UserParser;

import java.util.ArrayList;

/**
 * Created by omfg on 16.11.2017.
 */

public class MainActivityFragment extends Fragment implements OnDownLoadComplated {
    ArrayList<ListData> lists=  new ArrayList<>();
    ListView listView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserParser userParser = new UserParser(this);
        userParser.execute();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mainfragment,null);



    }
    @Override
    public void downloaded(ArrayList<ListData> lists) {
        this.lists = lists;
        CustomAdapter customAdapter = new CustomAdapter(getContext(), lists);
        listView.setAdapter(customAdapter);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Log.d("LISTS_SIZE","list "+lists.size());



        listView = (ListView) getActivity().findViewById(R.id.mainListView);

    }


}
