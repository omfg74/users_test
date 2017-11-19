package com.example.omfg.test_task_user_list.Logic;

import com.example.omfg.test_task_user_list.Objects.ListData;

import java.util.ArrayList;

/**
 * Created by omfg on 19.11.2017.
 */

public class GetRealId {
    int id;
    public int getID(ArrayList<ListData>list2,String name){
        for (int i = 0; i <list2.size() ; i++) {
            if(list2.get(i).getName().equals(name)){
                id=i+1;
                break;
            }
        }

        return id;
    }
}
