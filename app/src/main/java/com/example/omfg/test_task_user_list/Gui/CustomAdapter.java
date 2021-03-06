package com.example.omfg.test_task_user_list.Gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.omfg.test_task_user_list.Objects.ListData;
import com.example.omfg.test_task_user_list.Objects.User;
import com.example.omfg.test_task_user_list.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omfg on 18.11.2017.
 */

public class CustomAdapter extends BaseAdapter{
private Context context;
List<User> listDatas= new ArrayList<>();

    public CustomAdapter(Context context,List<User> listData) {
        this.context = context;
        this.listDatas = listData;
    }

    @Override
    public int getCount() {
        return listDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return listDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.listitem,parent,false);

//            ListData listData = (ListData)getItem(position);
User user = (User)getItem(position);
            TextView nameTextView = (TextView)view.findViewById(R.id.name_textView);
            TextView emailTextview = (TextView)view.findViewById(R.id.email_textView);
            TextView companyTextView = (TextView)view.findViewById(R.id.company_textView);
            nameTextView.setText(user.getName());
            emailTextview.setText(user.getEmail());
            companyTextView.setText(user.getCompany().getName());
        }
        return view;
    }
}
