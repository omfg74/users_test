package com.example.omfg.test_task_user_list.Objects;

/**
 * Created by omfg on 18.11.2017.
 */

public class ListData {
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }

    private String name;
    private String email;
    private String companyName;

    public ListData() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getName() {

        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCompanyName() {
        return companyName;
    }
}
