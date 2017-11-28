package com.example.omfg.test_task_user_list.Logic;

import com.example.omfg.test_task_user_list.Objects.ListData;
import com.example.omfg.test_task_user_list.Objects.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by omfg on 19.11.2017.
 */

public class SortListAlbeticaly {


    public List<User>sort(List<User> lists){

        Collections.sort(lists, new Comparator<User>() {
            @Override
            public int compare(ListData listData, ListData t1) {
                return listData.getName().compareTo(t1.getName());
            }
        });


        return lists;
    }
}
