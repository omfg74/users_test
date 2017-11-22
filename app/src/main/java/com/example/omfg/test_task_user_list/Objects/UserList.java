package com.example.omfg.test_task_user_list.Objects;

import java.util.ArrayList;

/**
 * Created by omfg on 23.11.2017.
 */

public class UserList {

    public void setUser(ArrayList<User> user) {
        this.user = user;
    }

    public ArrayList<User> getUser() {

        return user;
    }

    private ArrayList<User> user = new ArrayList<>();

}
