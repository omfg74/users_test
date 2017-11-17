package com.example.omfg.test_task_user_list.Logic;

import android.os.AsyncTask;
import android.util.Log;

import com.example.omfg.test_task_user_list.Objects.ListData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by omfg on 17.11.2017.
 */

public class UserParser extends AsyncTask<Void, Void, String>{

    HttpURLConnection urlConnection = null;
BufferedReader reader = null;
String result = "";
ArrayList<ListData>lists;
OnDownLoadComplated onDownLoadComplated;
    public UserParser(OnDownLoadComplated onDownLoadComplated) {
this.onDownLoadComplated  = onDownLoadComplated;
    }

    @Override
    protected String doInBackground(Void... voids) {
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
        return result;
    }



    @Override
    protected void onPostExecute(String jResult) {
        super.onPostExecute(jResult);
lists = new ArrayList<>();

        JSONObject jsonObject = null;

        try {
//            jsonObject = new JSONObject();
            JSONArray users = new JSONArray(jResult);

            for (int i = 0; i < users.length(); i++) {
                ListData listData = new ListData();
                JSONObject user = users.getJSONObject(i);
                String id = user.getString("id");
                Log.d("id", "" + id);
                String name = user.getString("name");
                String username = user.getString("username");
                String email = user.getString("email");
                String phone = user.getString("phone");
                String webSite = user.getString("website");
//Log.d("parsed"," "+id+" "+" "+ name+" "+email);

                JSONObject addresses = users.getJSONObject(i);

                JSONObject address = addresses.getJSONObject("address");
                String street = address.getString("street");
                String suite = address.getString("suite");
                String city = address.getString("city");
                String zipcode = address.getString("zipcode");

                JSONObject geo = address.getJSONObject("geo");
                String lat = geo.getString("lat");
                String lng = geo.getString("lng");
//                        Log.d("parsed"," "+street);


                JSONObject company = user.getJSONObject("company");
                String catchPrase = company.getString("catchPhrase");
                String companyName = company.getString("name");
                String bs = company.getString("bs");
//                    Log.d("parsed"," "+companyName);
                listData.setName(name);
                listData.setEmail(email);
                listData.setCompanyName(companyName);

                lists.add(listData);
            }
            onDownLoadComplated.downloaded(lists);

        }catch (Exception e){e.printStackTrace();}


    }
    public ArrayList<ListData> listBack(){

        return lists;
    };
}
