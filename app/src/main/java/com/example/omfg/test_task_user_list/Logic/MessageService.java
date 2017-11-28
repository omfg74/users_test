package com.example.omfg.test_task_user_list.Logic;

import com.example.omfg.test_task_user_list.Objects.MessageJSON;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by omfg on 28.11.2017.
 */

public interface MessageService {
    @GET("posts?userId={i}")
    Call<List<MessageJSON>>messageJson(@Path("i") int i);
}
