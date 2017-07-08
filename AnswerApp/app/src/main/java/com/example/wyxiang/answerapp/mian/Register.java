package com.example.wyxiang.answerapp.mian;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wyxiang.answerapp.R;
import com.example.wyxiang.answerapp.db.Users;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by wyxiang on 08.07.17.
 */

public class Register extends AppCompatActivity{


    private EditText et_name;
    private EditText et_id;
    private EditText et_password;
    private EditText et_password_1;
    private EditText et_mail;

    private Button register;

    private String name;
    private String id0;
    private String password;
    private String password1;
    private String mail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = (Toolbar) findViewById(R.id.reg_toolbar);
        setSupportActionBar(toolbar);

        Bmob.initialize(this,"03ed672534583aab5914232995118da3");

        et_name = (EditText) findViewById(R.id.reg_et_name);
        et_id = (EditText) findViewById(R.id.reg_et_id);
        et_password = (EditText) findViewById(R.id.reg_et_password);
        et_password_1 = (EditText) findViewById(R.id.reg_et_password1);
        et_mail = (EditText) findViewById(R.id.reg_et_email);

        register = (Button) findViewById(R.id.reg_register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = et_name.getText().toString();
                id0 = et_id.getText().toString();
                password = et_password.getText().toString();
                password1 = et_password_1.getText().toString();
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

                    final NiftyDialogBuilder dialog = NiftyDialogBuilder.getInstance(Register.this);
                    Users user = new Users();
                    user.setUsername(id0);
                    user.setPassword(password);
                    user.setNickname(name);
                    user.setEmail(mail);
                    user.signUp(new SaveListener<Users>() {

                        @Override
                        public void done(Users users, BmobException e) {
                            if (e==null){
                                dialog.withTitle("提示")
                                        .withDialogColor("#2894FF")
                                        .withMessage("注册成功")
                                        .withButton1Text("确定")
                                        .setButton1Click(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dialog.hide();
                                                finish();
                                            }
                                        }).show();
                            }else {
                                Log.e("TAG", String.valueOf(e));
                            }
                        }
                    });
                }
            }
        });

    }

}
