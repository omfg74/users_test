package com.example.omfg.test_task_user_list.Logic;

import com.example.omfg.test_task_user_list.Objects.ListData;
import com.example.omfg.test_task_user_list.Objects.User;
import com.example.omfg.test_task_user_list.Objects.UserList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by omfg on 22.11.2017.
 */

public interface ApiService {

@GET("/users")
Call<List<UserList>> getMyJSON();

}
