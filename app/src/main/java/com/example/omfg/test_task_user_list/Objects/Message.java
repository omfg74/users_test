package com.example.omfg.test_task_user_list.Objects;

/**
 * Created by omfg on 18.11.2017.
 */

public class Message {
    private int id;
    private String title;
    private String body;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getId() {

        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
