package com.example.omfg.test_task_user_list.Logic;

import com.example.omfg.test_task_user_list.Objects.ListData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by omfg on 19.11.2017.
 */

public class SortListAlbeticaly {


    public ArrayList<ListData>sort(ArrayList<ListData>lists){

        Collections.sort(lists, new Comparator<ListData>() {
            @Override
            public int compare(ListData listData, ListData t1) {
                return listData.getName().compareTo(t1.getName());
            }
        });


        return lists;
    }
}
