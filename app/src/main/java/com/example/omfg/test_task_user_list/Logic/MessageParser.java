package com.example.omfg.test_task_user_list.Logic;

import android.os.AsyncTask;
import android.util.Log;

import com.example.omfg.test_task_user_list.Objects.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by omfg on 18.11.2017.
 */

public class MessageParser extends AsyncTask<Void,Void,String> {
    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    String result = "";
    MessageSelector messageSelector;
    int i;
    public MessageParser(MessageSelector messageSelector,int i) {
    this.messageSelector = messageSelector;
    this.i = i;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try{

            URL url =new URL("https://jsonplaceholder.typicode.com/posts?userId="+i);
            Log.d("URL"," "+url);
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
        return result;
    }



    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            ArrayList<Message>msgList = new ArrayList<>();
            JSONArray messages = new JSONArray(s);
            for (int i = 0; i <messages.length() ; i++) {
Message msg = new Message();

                JSONObject message = messages.getJSONObject(i);
                String userId = message.getString("userId");
                String id = message.getString("id");
                String title = message.getString("title");
                String body = message.getString("body");
msg.setId(Integer.parseInt(id));
msg.setTitle(title);
msg.setBody(body);
msgList.add(msg);
            }
            messageSelector.msg(msgList);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
