package com.example.omfg.test_task_user_list.Gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.omfg.test_task_user_list.Objects.Message;
import com.example.omfg.test_task_user_list.R;

import java.util.ArrayList;

/**
 * Created by omfg on 19.11.2017.
 */

public class MessageAdapter extends BaseAdapter {
    Context context;
    ArrayList<Message>messages;
    public MessageAdapter(Context context, ArrayList<Message>messages) {
    this.context = context;
    this.messages = messages;

    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int i) {
        return messages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.massage_item, viewGroup, false);
            Message message = (Message)getItem(i);
            TextView titleTextView = view.findViewById(R.id.tittle_textView);
            TextView bodyTextView = view.findViewById(R.id.body_textView);
            titleTextView.setText(message.getTitle());
            bodyTextView.setText(message.getBody());

        }
        return view;
    }
}
