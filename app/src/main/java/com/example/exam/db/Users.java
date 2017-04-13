package com.example.exam.db;

import cn.bmob.v3.BmobObject;

/**
 * Created by 魏于翔 on 2017/2/3.
 */

public class Users extends BmobObject{

    private String user_name;
    private String user_id;
    private String user_password;
    private String user_mail;

    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_mail() {
        return user_mail;
    }

    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
    }
}
