package com.example.exam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Load extends AppCompatActivity {

    EditText et_id;
    EditText et_password;
    Button load;
    Button register;
    private String id;
    private String password;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        Bmob.initialize(this,"1eecd58732bf214e4b72f39a9ec07541");
        initView();
        initDB();
    }

    private void initView() {
        et_id = (EditText) findViewById(R.id.et1);
        et_password = (EditText) findViewById(R.id.et2);
        load = (Button) findViewById(R.id.bt1);
        register = (Button) findViewById(R.id.register);
    }

    private void initDB() {
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = et_id.getText().toString();
                password = et_password.getText().toString();

                BmobQuery<Users> query = new BmobQuery<Users>();
                query.addWhereEqualTo("user_id",id);
                query.findObjects(new FindListener<Users>() {
                    @Override
                    public void done(List<Users> list, BmobException e) {
                        if (e==null)
                        {
                            for (Users user : list)
                            {
                                if(user.getUser_password().equals(password))
                                {
                                    et_password.setText("");
                                    Intent intent = new Intent(Load.this, com.example.exam.Menu.class);
                                    intent.putExtra("name",user.getUser_name());
                                    intent.putExtra("id",user.getUser_id());
                                    startActivityForResult(intent, 123);
                                }
                                else
                                {
                                    Toast.makeText(getBaseContext(),"账号或密码有误",Toast.LENGTH_LONG).show();
                                    et_password.setText("");
                                }
                            }

                        }
                        else
                        {
                            Toast.makeText(getBaseContext(),"账号或密码有误",Toast.LENGTH_LONG).show();
                            et_password.setText("");
                        }
                    }
                });

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Load.this,Register.class);
                startActivity(intent);
            }
        });

    }


}
