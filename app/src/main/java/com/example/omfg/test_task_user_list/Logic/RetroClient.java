package com.example.omfg.test_task_user_list.Logic;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by omfg on 22.11.2017.
 */

public class RetroClient {
private static final String ROOT_URL = "https://jsonplaceholder.typicode.com/";


private static Retrofit getRetrofitInstance(){
    return new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();

}
public static ApiService getApiService(){
    return getRetrofitInstance().create(ApiService.class);
}


}
