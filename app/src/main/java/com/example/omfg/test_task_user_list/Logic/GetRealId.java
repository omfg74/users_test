package com.example.omfg.test_task_user_list.Logic;

import com.example.omfg.test_task_user_list.Objects.ListData;
import com.example.omfg.test_task_user_list.Objects.User;
import com.example.omfg.test_task_user_list.Objects.UserList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omfg on 19.11.2017.
 */

public class GetRealId {
    int id;
    public int getID(ArrayList<List<UserList>> list2, String name){
        for (int i = 0; i <list2.size() ; i++) {
            if(list2.get(i).get(i).getUser().get(i).getName().equals(name)){
                id=i+1;
                break;
            }
        }

        return id;
    }
}
