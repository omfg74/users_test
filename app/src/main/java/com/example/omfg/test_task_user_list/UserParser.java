package com.example.omfg.test_task_user_list;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.Buffer;

/**
 * Created by omfg on 17.11.2017.
 */

public class UserParser extends AsyncTask{

    HttpURLConnection urlConnection = null;
BufferedReader reader = null;
String result = "";



    @Override
    protected Object doInBackground(Object[] objects) {
        try{
            URL url =new URL("https://jsonplaceholder.typicode.com/users");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while((line = reader.readLine())!=null){
                buffer.append(line);
            }
            result = buffer.toString();
            Log.d("JSON",result);

        } catch (Exception e) {

        }
        return null;
    }
}
