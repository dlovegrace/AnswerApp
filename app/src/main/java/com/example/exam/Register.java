package com.example.exam;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by 魏于翔 on 2017/2/9.
 */

public class Register extends AppCompatActivity{

    EditText et_name;
    EditText et_id;
    EditText et_password;
    EditText et_password1;
    EditText et_mail;

    Button register;

    String name;
    String id0;
    String password;
    String password1;
    String mail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent intent = new Intent();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bmob.initialize(this,"1eecd58732bf214e4b72f39a9ec07541");

        et_name = (EditText) findViewById(R.id.et_name);
        et_id = (EditText) findViewById(R.id.et_id);
        et_password = (EditText) findViewById(R.id.et_password);
        et_password1 = (EditText) findViewById(R.id.et_password1);
        et_mail = (EditText) findViewById(R.id.et_email);

        register = (Button) findViewById(R.id.register);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = et_name.getText().toString();
                id0 = et_id.getText().toString();
                password = et_password.getText().toString();
                password1 = et_password1.getText().toString();
                mail = et_mail.getText().toString();

                if (name.equals(""))
                {
                    Toast.makeText(getBaseContext(),"用户名不能为空",Toast.LENGTH_LONG).show();
                }
                else if (id0.equals(""))
                {
                    Toast.makeText(getBaseContext(),"账号不能为空",Toast.LENGTH_LONG).show();
                }
                else if (password.equals(""))
                {
                    Toast.makeText(getBaseContext(),"密码不能为空",Toast.LENGTH_LONG).show();
                }
                else if (password1.equals(""))
                {
                    Toast.makeText(getBaseContext(),"请再次确认密码",Toast.LENGTH_LONG).show();
                }
                else if (mail.equals(""))
                {
                    Toast.makeText(getBaseContext(),"邮箱不能为空",Toast.LENGTH_LONG).show();
                }
                else if (password.equals(password1))
                {
                    BmobQuery<Users> query = new BmobQuery<Users>();
                    Log.d("123",id0);
                    Log.d("123",id0);
                    Log.d("123",id0);
                    Log.d("123",id0);
                    Log.d("123",id0);
                    Log.d("123",id0);
                    query.addWhereEqualTo("user_id",id0);
                    query.findObjects(new FindListener<Users>() {
                        @Override
                        public void done(List<Users> list, BmobException e) {
                            if (e==null)
                            {
                                int i = 0;
                                for (Users user : list)
                                {
                                    if (user.getUser_id().equals(id0))
                                    {
                                        Toast.makeText(getBaseContext(),"该账号已注册",Toast.LENGTH_LONG).show();
                                        i = 1;
                                    }
                                }
                                if(i==0)
                                {
                                    Users users = new Users();
                                    users.setUser_name(name);
                                    users.setUser_id(id0);
                                    users.setUser_password(password);
                                    users.setUser_mail(mail);

                                    users.save(new SaveListener<String>() {
                                        @Override
                                        public void done(String s, BmobException e) {
                                            if (e==null)
                                            {
                                                new AlertDialog.Builder(Register.this).setTitle("提示").setMessage("注册成功")
                                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                finish();
                                                            }
                                                        }).show();
                                            }
                                        }
                                    });
                                }


                            }
                            else
                            {
                                Users users = new Users();
                                users.setUser_name(name);
                                users.setUser_id(id0);
                                users.setUser_password(password);
                                users.setUser_mail(mail);

                                users.save(new SaveListener<String>() {
                                    @Override
                                    public void done(String s, BmobException e) {
                                                if (e==null)
                                                {
                                                    new AlertDialog.Builder(Register.this).setTitle("提示").setMessage("注册成功")
                                                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    finish();
                                                                }
                                                            }).show();
                                                }
                                    }
                                });

                            }
                        }
                    });


                }
            }
        });

    }
}
