package com.example.omfg.test_task_user_list.Gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.omfg.test_task_user_list.Objects.ListData;
import com.example.omfg.test_task_user_list.R;

import java.util.ArrayList;

/**
 * Created by omfg on 18.11.2017.
 */

public class CustomAdapter extends BaseAdapter{
private Context context;
ArrayList <ListData> listDatas= new ArrayList<>();

    public CustomAdapter(Context context, ArrayList<ListData> listData) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.listitem,parent,false);

            ListData listData = (ListData)getItem(position);
            TextView nameTextView = (TextView)convertView.findViewById(R.id.name_textView);
            TextView emailTextview = (TextView)convertView.findViewById(R.id.email_textView);
            TextView companyTextView = (TextView)convertView.findViewById(R.id.company_textView);
            nameTextView.setText(listData.getName());
            emailTextview.setText(listData.getEmail());
            companyTextView.setText(listData.getCompanyName());
        }
        return convertView;
    }
}
