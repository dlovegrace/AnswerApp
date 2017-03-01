package com.example.exam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by 魏于翔 on 2017/2/9.
 */

public class ReDate extends AppCompatActivity{

    TextView tv_name;
    TextView tv_id;
    TextView tv_password;
    TextView tv_password1;
    TextView tv_mail;

    EditText et_name;
    EditText et_id;
    EditText et_password;
    EditText et_password1;
    EditText et_mail;

    Button revise;

    String id;
    String objectId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        Bmob.initialize(this,"1eecd58732bf214e4b72f39a9ec07541");

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_id = (TextView) findViewById(R.id.tv_id);
        tv_password = (TextView) findViewById(R.id.tv_password);
        tv_password1 = (TextView) findViewById(R.id.tv_password1);
        tv_mail = (TextView) findViewById(R.id.tv_email);

        et_name = (EditText) findViewById(R.id.et_name);
        et_id = (EditText) findViewById(R.id.et_id);
        et_password = (EditText) findViewById(R.id.et_password);
        et_password1 = (EditText) findViewById(R.id.et_password1);
        et_mail = (EditText) findViewById(R.id.et_email);

        revise = (Button) findViewById(R.id.register);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv_password.setText("邮        箱 :");
        revise.setText("修         改");

        et_password.setInputType(InputType.TYPE_CLASS_TEXT);
        et_id.setEnabled(false);
        tv_password1.setVisibility(View.GONE);
        tv_mail.setVisibility(View.GONE);
        et_password1.setVisibility(View.GONE);
        et_mail.setVisibility(View.GONE);

        BmobQuery<Users> query = new BmobQuery<Users>();
        query.addWhereEqualTo("user_id",id);
        query.findObjects(new FindListener<Users>() {
            @Override
            public void done(List<Users> list, BmobException e) {
                if (e==null)
                {
                    et_name.setText(list.get(0).getUser_name());
                    et_id.setText(list.get(0).getUser_id());
                    et_password.setText(list.get(0).getUser_mail());
                    objectId = list.get(0).getObjectId();
                }
            }
        });

        revise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Users users = new Users();
                users.setUser_name(et_name.getText().toString());
                users.setUser_mail(et_password.getText().toString());
                users.update(objectId, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e==null)
                        {
                            Toast.makeText(getBaseContext(),"更新成功",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getBaseContext(),"更新失败",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });



    }
}
