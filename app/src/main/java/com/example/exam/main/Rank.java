package com.example.exam.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.exam.R;
import com.example.exam.db.History;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 魏于翔 on 2017/2/16.
 */

public class Rank extends AppCompatActivity {
    private String id0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        Intent intent = getIntent();
        id0 = intent.getStringExtra("id");

        Bmob.initialize(this, "1eecd58732bf214e4b72f39a9ec07541");
        BmobQuery<History> query = new BmobQuery<History>();
        query.order("-score");
        query.findObjects(new FindListener<History>() {
            @Override
            public void done(List<History> list, BmobException e) {
                if (e == null) {
                    String date[] = new String[list.size()];
                    for (int i = 0; i < list.size(); i++) {

                        date[i] = list.get(i).getUser_id() + "         " + list.get(i).getScore() + "分";

                    }

                    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            Rank.this, android.R.layout.simple_list_item_1, date);
                    ListView listView = (ListView) findViewById(R.id.list_view);
                    listView.setAdapter(adapter);


                }

            }
        });

    }
}
