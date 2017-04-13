package com.example.exam.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import com.example.exam.R;
import com.example.exam.db.History;
import com.example.exam.db.UslHistory;


/**
 * Created by 魏于翔 on 2017/2/7.
 */

public class HistoryView extends AppCompatActivity{
    private String id0;
    String bcd;
    private FrameLayout chartFragments;
    private Fragment lineChartFragment;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historyview);
        Intent intent = getIntent();
        id0 = intent.getStringExtra("id");
/*


        chartFragments = (FrameLayout) findViewById(R.id.chart_fragments);

        FragmentManager manager=getSupportFragmentManager();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        lineChartFragment = manager.findFragmentByTag("Tag2");

        Bundle bundle = new Bundle();
        bundle.putString("id", id0);

        if (lineChartFragment == null){
            lineChartFragment = new LineChartFragment();
            lineChartFragment.setArguments(bundle);
            fragmentTransaction.add(R.id.chart_fragments,lineChartFragment,"Tag2");
          }
        //else {
        //    fragmentTransaction.show(lineChartFragment);
      //  }
        fragmentTransaction.commit();
 */
        Bmob.initialize(this,"1eecd58732bf214e4b72f39a9ec07541");
        BmobQuery<History> query = new BmobQuery<History>();
        query.addWhereEqualTo("user_id",id0);
        query.findObjects(new FindListener<History>() {
            @Override
            public void done(final List<History> list, BmobException e) {

                BmobQuery<UslHistory> query1 = new BmobQuery<UslHistory>();
                query1.addWhereEqualTo("user_id",id0);
                query1.findObjects(new FindListener<UslHistory>() {
                    @Override
                    public void done(List<UslHistory> list1, BmobException e) {
                        String date[] =new String[list.size()+list1.size()];
                        for (int i=0;i<list.size();i++)
                        {

                            date[i] = list.get(i).getTime() + "         " + list.get(i).getScore() + "分";
                        }
                        for (int i = list.size();i<list.size()+list1.size();i++)
                        {
                            date[i] = list1.get(i-list.size()).getTime() + "         " + list.get(i-list.size()).getScore() + "分";
                        }

                        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                HistoryView.this, android.R.layout.simple_list_item_1 ,date);
                        ListView listView = (ListView) findViewById(R.id.list_view);
                        listView.setAdapter(adapter);


                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String abc = adapter.getItem(position);
                                if (abc.substring(14,15).equals(""))
                                {
                                    bcd = abc.substring(0,14);
                                }
                                else if (abc.substring(15,16).equals(""))
                                {
                                    bcd = abc.substring(0,15);
                                }
                                else if (abc.substring(16,17).equals(""))
                                {
                                    bcd = abc.substring(0,16);
                                }
                                else if (abc.substring(17,18).equals(""))
                                {
                                    bcd = abc.substring(0,17);
                                }
                                else if (abc.substring(18,19).equals(""))
                                {
                                    bcd = abc.substring(0,18);
                                }
                                else if (abc.substring(19,20).equals(""))
                                {
                                    bcd = abc.substring(0,19);
                                }
                                else if (abc.substring(20,21).equals(""))
                                {
                                    bcd = abc.substring(0,20);
                                }
                                Intent intent = new Intent(HistoryView.this,WrongView.class);
                                intent.putExtra("id",id0);
                                intent.putExtra("time",bcd);
                                startActivity(intent);
                            }
                        });
                    }
                });




            }
        });



    }


}