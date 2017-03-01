package com.example.exam;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import io.github.kexanie.library.MathView;

/**
 * Created by 魏于翔 on 2017/2/8.
 */

public class WrongView extends AppCompatActivity{

  private String id;
    private String time;

    private int count;
    private int count1;
    private int count2;
    private int corrent;
    private RadioGroup mRadioGroup;
    RadioButton[] mRadioButton = new RadioButton[4];
    private Button btn_up;
    private Button btn_down;
    private MathView title;
    private MathView answer;
    private MathView answerA;
    private MathView answerB;
    private MathView answerC;
    private MathView answerD;

    private int question_id[] = new int[20];
    private int selectedAnswer[] = new int[20];


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mid_exam);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        time = intent.getStringExtra("time");
        Bmob.initialize(this,"1eecd58732bf214e4b72f39a9ec07541");

        initView();
        initDB();
    }



    private void initView() {
        title = (MathView) findViewById(R.id.title);
        answer= (MathView) findViewById(R.id.answer);

        answerA = (MathView) findViewById(R.id.answerA);
        answerB = (MathView) findViewById(R.id.answerB);
        answerC = (MathView) findViewById(R.id.answerC);
        answerD = (MathView) findViewById(R.id.answerD);

        mRadioButton[0] = (RadioButton) findViewById(R.id.mRadioA);
        mRadioButton[1] = (RadioButton) findViewById(R.id.mRadioB);
        mRadioButton[2] = (RadioButton) findViewById(R.id.mRadioC);
        mRadioButton[3] = (RadioButton) findViewById(R.id.mRadioD);
        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);

        btn_down = (Button) findViewById(R.id.btn_down);
        btn_up = (Button) findViewById(R.id.btn_up);
    }

    private void initDB() {

        BmobQuery<Question> query3 = new BmobQuery<Question>();
        query3.findObjects(new FindListener<Question>() {
            @Override
            public void done(final List<Question> list3, BmobException e) {

                BmobQuery<History> query = new BmobQuery<History>();
                query.addWhereEqualTo("user_id",id);
                query.addWhereEqualTo("time",time);
                query.findObjects(new FindListener<History>() {
                    @Override
                    public void done(final List<History> list, BmobException e) {

                        BmobQuery<History1> query1 = new BmobQuery<History1>();
                        query1.addWhereEqualTo("user_id",id);
                        query1.addWhereEqualTo("time",time);
                        query1.findObjects(new FindListener<History1>() {
                            @Override
                            public void done(final List<History1> list1, BmobException e) {

                                BmobQuery<History2> query2 = new BmobQuery<History2>();
                                query2.addWhereEqualTo("user_id",id);
                                query2.addWhereEqualTo("time",time);
                                query2.findObjects(new FindListener<History2>() {
                                    @Override
                                    public void done(List<History2> list2, BmobException e) {
                                        final List<Question> list4 =  newList(list3,list,list1,list2);

                                        corrent = 0;
                                        title.setText(list4.get(corrent).getQuestion());
                                        answerA.setText(list4.get(corrent).getAnswerA());
                                        answerB.setText(list4.get(corrent).getAnswerB());
                                        answerC.setText(list4.get(corrent).getAnswerC());
                                        answerD.setText(list4.get(corrent).getAnswerD());
                                        if (list4.get(corrent).getSelectedAnswer() != -1)
                                        {
                                            mRadioButton[list4.get(corrent).getSelectedAnswer()].setChecked(true);
                                        }

                                        btn_up.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                if (corrent>0)
                                                {
                                                    corrent--;
                                                    title.setText(list4.get(corrent).getQuestion());
                                                    answerA.setText(list4.get(corrent).getAnswerA());
                                                    answerB.setText(list4.get(corrent).getAnswerB());
                                                    answerC.setText(list4.get(corrent).getAnswerC());
                                                    answerD.setText(list4.get(corrent).getAnswerD());
                                                    for(int i=0;i<4;i++)
                                                    {
                                                        mRadioButton[i].setChecked(false);
                                                    }
                                                    if (list4.get(corrent).getSelectedAnswer() != -1)
                                                    {
                                                        mRadioButton[list4.get(corrent).getSelectedAnswer()].setChecked(true);
                                                    }
                                                }
                                            }
                                        });



                                        btn_down.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                if (corrent < list4.size() - 1)
                                                {
                                                    corrent++;
                                                    title.setText(list4.get(corrent).getQuestion());
                                                    answerA.setText(list4.get(corrent).getAnswerA());
                                                    answerB.setText(list4.get(corrent).getAnswerB());
                                                    answerC.setText(list4.get(corrent).getAnswerC());
                                                    answerD.setText(list4.get(corrent).getAnswerD());
                                                    for (int i = 0; i < 4; i++)
                                                    {
                                                        mRadioButton[i].setChecked(false);
                                                    }

                                                    if (list4.get(corrent).getSelectedAnswer() != -1)
                                                    {
                                                        mRadioButton[list4.get(corrent).getSelectedAnswer()].setChecked(true);
                                                    }

                                                }
                                                else
                                                {
                                                    new AlertDialog.Builder(WrongView.this).setTitle("提示").setMessage("已经到达最后一道题，是否退出？")
                                                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    finish();
                                                                }
                                                            }).setNegativeButton("取消",null).show();
                                                }
                                            }
                                        });





                                    }
                                });

                            }
                        });
                    }
                });

            }
        });












    }

    private List<Question> newList(List<Question> list,List<History> list1,List<History1> list2,List<History2> list3){
        List<Question> list4 = new ArrayList<>();
        for(int i = 0 ;i<list.size();i++)
        {
            if(list.get(i).getID()==list1.get(count).getQuestion_id1())
            {
                list.get(i).setSelectedAnswer(list1.get(count).getSelectedAnswer1());
                list4.add(list.get(i));
            }
            else if(list.get(i).getID()==list1.get(count).getQuestion_id2())
            {
                list.get(i).setSelectedAnswer(list1.get(count).getSelectedAnswer2());
                list4.add(list.get(i));
            }
            else if(list.get(i).getID()==list1.get(count).getQuestion_id3())
            {
                list.get(i).setSelectedAnswer(list1.get(count).getSelectedAnswer3());
                list4.add(list.get(i));
            }
            else if(list.get(i).getID()==list1.get(count).getQuestion_id4())
            {
                list.get(i).setSelectedAnswer(list1.get(count).getSelectedAnswer4());
                list4.add(list.get(i));
            }
            else if(list.get(i).getID()==list1.get(count).getQuestion_id5())
            {
                list.get(i).setSelectedAnswer(list1.get(count).getSelectedAnswer5());
                list4.add(list.get(i));
            }
            else if(list.get(i).getID()==list1.get(count).getQuestion_id6())
            {
                list.get(i).setSelectedAnswer(list1.get(count).getSelectedAnswer6());
                list4.add(list.get(i));
            }
            else if(list.get(i).getID()==list2.get(count1).getQuestion_id7())
            {
                list.get(i).setSelectedAnswer(list2.get(count1).getSelectedAnswer7());
                list4.add(list.get(i));
            }
            else if(list.get(i).getID()==list2.get(count1).getQuestion_id8())
            {
                list.get(i).setSelectedAnswer(list2.get(count1).getSelectedAnswer8());
                list4.add(list.get(i));
            }
            else if(list.get(i).getID()==list2.get(count1).getQuestion_id9())
            {
                list.get(i).setSelectedAnswer(list2.get(count1).getSelectedAnswer9());
                list4.add(list.get(i));
            }
            else if(list.get(i).getID()==list2.get(count1).getQuestion_id10())
            {
                list.get(i).setSelectedAnswer(list2.get(count1).getSelectedAnswer10());
                list4.add(list.get(i));
            }
            else if(list.get(i).getID()==list2.get(count1).getQuestion_id11())
            {
                list.get(i).setSelectedAnswer(list2.get(count1).getSelectedAnswer11());
                list4.add(list.get(i));
            }
            else if(list.get(i).getID()==list2.get(count1).getQuestion_id12())
            {
                list.get(i).setSelectedAnswer(list2.get(count1).getSelectedAnswer12());
                list4.add(list.get(i));
            }
            else if(list.get(i).getID()==list3.get(count2).getQuestion_id13())
            {
                list.get(i).setSelectedAnswer(list3.get(count2).getSelectedAnswer13());
                list4.add(list.get(i));
            }
            else if(list.get(i).getID()==list3.get(count2).getQuestion_id14())
            {
                list.get(i).setSelectedAnswer(list3.get(count2).getSelectedAnswer14());
                list4.add(list.get(i));
            }
            else if(list.get(i).getID()==list3.get(count2).getQuestion_id15())
            {
                list.get(i).setSelectedAnswer(list3.get(count2).getSelectedAnswer15());
                list4.add(list.get(i));
            }
            else if(list.get(i).getID()==list3.get(count2).getQuestion_id16())
            {
                list.get(i).setSelectedAnswer(list3.get(count2).getSelectedAnswer16());
                list4.add(list.get(i));
            }
            else if(list.get(i).getID()==list3.get(count2).getQuestion_id17())
            {
                list.get(i).setSelectedAnswer(list3.get(count2).getSelectedAnswer17());
                list4.add(list.get(i));
            }
            else if(list.get(i).getID()==list3.get(count2).getQuestion_id18())
            {
                list.get(i).setSelectedAnswer(list3.get(count2).getSelectedAnswer18());
                list4.add(list.get(i));
            }
            else if(list.get(i).getID()==list3.get(count2).getQuestion_id19())
            {
                list.get(i).setSelectedAnswer(list3.get(count2).getSelectedAnswer19());
                list4.add(list.get(i));
            }
            else if(list.get(i).getID()==list3.get(count2).getQuestion_id20())
            {
                list.get(i).setSelectedAnswer(list3.get(count2).getSelectedAnswer20());
                list4.add(list.get(i));
            }

        }
        return list4;
    }

}
