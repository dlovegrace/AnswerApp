package com.example.exam.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.exam.R;
import com.example.exam.db.Users;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by 魏于翔 on 2017/2/9.
 */

public class RePassword extends AppCompatActivity{

    String id;
    String objectId;
    String password;
    String password1;
    String password2;

    EditText et_password;
    EditText et_password1;
    EditText et_password2;

    Button sure;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repassword);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        Bmob.initialize(this,"1eecd58732bf214e4b72f39a9ec07541");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        et_password = (EditText) findViewById(R.id.et_password);
        et_password1 = (EditText) findViewById(R.id.et_password1);
        et_password2 = (EditText) findViewById(R.id.et_password2);

        sure = (Button) findViewById(R.id.sure);

        BmobQuery<Users> query = new BmobQuery<Users>();
        query.addWhereEqualTo("user_id",id);
        query.findObjects(new FindListener<Users>() {
            @Override
            public void done(List<Users> list, BmobException e) {
                if (e==null)
                {
                    objectId = list.get(0).getObjectId();
                    password = list.get(0).getUser_password();
                }
                else
                {
                    Toast.makeText(getBaseContext(),"请重试",Toast.LENGTH_LONG).show();
                }
            }
        });

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                password1 = et_password1.getText().toString();
                password2 = et_password2.getText().toString();

                if (password.equals(et_password.getText().toString()))
                {
                    if (password1.equals(password2))
                    {
                        Users users = new Users();
                        users.setUser_password(password2);
                        users.update(objectId, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e==null)
                                {
                                    Toast.makeText(getBaseContext(),"修改成功",Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    Toast.makeText(getBaseContext(),"修改失败",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }

                }
                else
                {
                    Toast.makeText(getBaseContext(),"密码错误",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
