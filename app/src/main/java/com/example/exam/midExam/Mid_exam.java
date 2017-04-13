package com.example.exam.midExam;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.exam.db.History;
import com.example.exam.db.History1;
import com.example.exam.db.History2;
import com.example.exam.R;
import com.example.exam.db.Question;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import io.github.kexanie.library.MathView;

/**
 * Created by 魏于翔 on 2017/2/3.
 */
public class Mid_exam extends AppCompatActivity{


    private int count;
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
    private boolean wrongmode;

    private int question_id[] = new int[20];
    private int selectedAnswer[] = new int[20];

    private int min=20;
    private int second=00;
    private TextView time;
    Timer timer = new Timer();

    public String id;

    private String created;

    ContentValues values = new ContentValues();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mid_exam);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
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
        time = (TextView) findViewById(R.id.timer);
        timer.schedule(task, 1000, 1000);

    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                        if(second==00)
                        {
                            min--;
                            second=60;
                        }

                    second--;
                    if(second<10)
                    {
                        time.setText("倒计时: "+min+":"+"0"+second);
                    }
                    else
                    {
                        time.setText("倒计时: "+min+":"+second);
                    }

                    if(min< 0) {
                        timer.cancel();
                        time.setVisibility(View.GONE);


                    }
                }
            });
        }
    };

    private void initDB() {

        BmobQuery<History> query1 = new BmobQuery<History>();
        query1.addWhereEqualTo("user_id",id);
        query1.findObjects(new FindListener<History>() {
            @Override
            public void done(List<History> list1, BmobException e) {
                if (list1.size()==0)
                {
                    BmobQuery<Question> query = new BmobQuery<Question>();
                    query.findObjects(new FindListener<Question>() {
                        @Override
                        public void done(final List<Question> list, BmobException e) {
                            if (e==null)
                            {
                                corrent = 0;
                                title.setText(list.get(0).getQuestion());
                                answerA.setText(list.get(0).getAnswerA());
                                answerB.setText(list.get(0).getAnswerB());
                                answerC.setText(list.get(0).getAnswerC());
                                answerD.setText(list.get(0).getAnswerD());

                                    btn_up.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (corrent>0)
                                            {
                                                corrent--;
                                                title.setText(list.get(corrent).getQuestion());
                                                answerA.setText(list.get(corrent).getAnswerA());
                                                answerB.setText(list.get(corrent).getAnswerB());
                                                answerC.setText(list.get(corrent).getAnswerC());
                                                answerD.setText(list.get(corrent).getAnswerD());
                                                for(int i=0;i<4;i++)
                                                {
                                                    mRadioButton[i].setChecked(false);
                                                }

                                                if (list.get(corrent).getSelectedAnswer()!= -1)
                                                {
                                                    mRadioButton[list.get(corrent).getSelectedAnswer()].setChecked(true);
                                                }
                                            }
                                        }
                                    });


                                    btn_down.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            if (corrent<list.size()-1)
                                            {
                                                corrent++;
                                                Question q = list.get(corrent);
                                                title.setText(list.get(corrent).getQuestion());
                                                answerA.setText(list.get(corrent).getAnswerA());
                                                answerB.setText(list.get(corrent).getAnswerB());
                                                answerC.setText(list.get(corrent).getAnswerC());
                                                answerD.setText(list.get(corrent).getAnswerD());
                                                for(int i=0;i<4;i++)
                                                {
                                                    mRadioButton[i].setChecked(false);
                                                }

                                                if (list.get(corrent).getSelectedAnswer()!= -1)
                                                {
                                                    mRadioButton[list.get(corrent).getSelectedAnswer()].setChecked(true);
                                                }


                                            }

                                            else if(corrent == list.size() - 1 && wrongmode == true)
                                            {
                                                new AlertDialog.Builder(Mid_exam.this).setTitle("提示").setMessage("已经到达最后一道题，是否退出？")
                                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                finish();
                                                            }
                                                        }).setNegativeButton("取消",null).show();
                                            }

                                            else if(corrent == list.size() - 1 && wrongmode == false)
                                            {
                                                //提示已是最后一题
                                                new AlertDialog.Builder(Mid_exam.this).setTitle("提示").setMessage("已经到达最后一道题")
                                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {

                                                                final List<Integer> wrongList = checkAnswer(list);


                                                                getTime();
                                                                History history = new History();
                                                                History1 history1 = new History1();
                                                                History2 history2 = new History2();
                                                                history.setUser_id(id);
                                                                history.setTime(created);
                                                                history1.setUser_id(id);
                                                                history1.setTime(created);
                                                                history2.setUser_id(id);
                                                                history2.setTime(created);
                                                                history.setQuestion_id1(question_id[0]);
                                                                history.setQuestion_id2(question_id[1]);
                                                                history.setQuestion_id3(question_id[2]);
                                                                history.setQuestion_id4(question_id[3]);
                                                                history.setQuestion_id5(question_id[4]);
                                                                history.setQuestion_id6(question_id[5]);
                                                                history1.setQuestion_id7(question_id[6]);
                                                                history1.setQuestion_id8(question_id[7]);
                                                                history1.setQuestion_id9(question_id[8]);
                                                                history1.setQuestion_id10(question_id[9]);
                                                                history1.setQuestion_id11(question_id[10]);
                                                                history1.setQuestion_id12(question_id[11]);
                                                                history2.setQuestion_id13(question_id[12]);
                                                                history2.setQuestion_id14(question_id[13]);
                                                                history2.setQuestion_id15(question_id[14]);
                                                                history2.setQuestion_id16(question_id[15]);
                                                                history2.setQuestion_id17(question_id[16]);
                                                                history2.setQuestion_id18(question_id[17]);
                                                                history2.setQuestion_id19(question_id[18]);
                                                                history2.setQuestion_id20(question_id[19]);
                                                                history.setSelectedAnswer1(selectedAnswer[0]);
                                                                history.setSelectedAnswer2(selectedAnswer[1]);
                                                                history.setSelectedAnswer3(selectedAnswer[2]);
                                                                history.setSelectedAnswer4(selectedAnswer[3]);
                                                                history.setSelectedAnswer5(selectedAnswer[4]);
                                                                history.setSelectedAnswer6(selectedAnswer[5]);
                                                                history1.setSelectedAnswer7(selectedAnswer[6]);
                                                                history1.setSelectedAnswer8(selectedAnswer[7]);
                                                                history1.setSelectedAnswer9(selectedAnswer[8]);
                                                                history1.setSelectedAnswer10(selectedAnswer[9]);
                                                                history1.setSelectedAnswer11(selectedAnswer[10]);
                                                                history1.setSelectedAnswer12(selectedAnswer[11]);
                                                                history2.setSelectedAnswer13(selectedAnswer[12]);
                                                                history2.setSelectedAnswer14(selectedAnswer[13]);
                                                                history2.setSelectedAnswer15(selectedAnswer[14]);
                                                                history2.setSelectedAnswer16(selectedAnswer[15]);
                                                                history2.setSelectedAnswer17(selectedAnswer[16]);
                                                                history2.setSelectedAnswer18(selectedAnswer[17]);
                                                                history2.setSelectedAnswer19(selectedAnswer[18]);
                                                                history2.setSelectedAnswer20(selectedAnswer[19]);
                                                                history.setScore(list.size() - wrongList.size());

                                                                history.save(new SaveListener<String>() {
                                                                    @Override
                                                                    public void done(String s, BmobException e) {

                                                                    }
                                                                });
                                                                history1.save(new SaveListener<String>() {
                                                                    @Override
                                                                    public void done(String s, BmobException e) {

                                                                    }
                                                                });

                                                                history2.save(new SaveListener<String>() {
                                                                    @Override
                                                                    public void done(String s, BmobException e) {

                                                                    }
                                                                });

                                                                if(wrongList.size() == 0)
                                                                {
                                                                    new AlertDialog.Builder(Mid_exam.this).setTitle("提示").setMessage("你好厉害，答对了所有题！")
                                                                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                                                @Override
                                                                                public void onClick(DialogInterface dialog, int which) {
                                                                                    finish();
                                                                                }
                                                                            }).setNegativeButton("取消",null).show();
                                                                }

                                                                new AlertDialog.Builder(Mid_exam.this).setTitle("恭喜，答题完成！")
                                                                        .setMessage("答对了" + (list.size() - wrongList.size()) + "道题" + "\n"
                                                                                + "答错了" + wrongList.size() + "道题" + "\n" + "是否查看错题？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialog, int which) {

                                                                        if(wrongList.size()>0)
                                                                        {
                                                                            wrongmode = true;
                                                                            List<Question> newList = new ArrayList<Question>();
                                                                            for (int i = 0; i < wrongList.size(); i++) {
                                                                                newList.add(list.get(wrongList.get(i)));
                                                                            }
                                                                            list.clear();
                                                                            for (int i = 0; i < newList.size(); i++)
                                                                            {
                                                                                list.add(newList.get(i));
                                                                            }
                                                                            corrent = 0;
                                                                            title.setText(list.get(corrent).getQuestion());
                                                                            answerA.setText(list.get(corrent).getAnswerA());
                                                                            answerB.setText(list.get(corrent).getAnswerB());
                                                                            answerC.setText(list.get(corrent).getAnswerC());
                                                                            answerD.setText(list.get(corrent).getAnswerD());
                                                                        }


                                                                    }
                                                                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialog, int which) {
                                                                        finish();
                                                                    }
                                                                }).show();
                                                            }

                                                            private List<Integer> checkAnswer(List<Question> list) {
                                                                List<Integer>wrongList= new ArrayList<>();
                                                                for(int i = 0 ; i<list.size();i++){
                                                                    //添加数据
                                                                    selectedAnswer[i] = list.get(i).getSelectedAnswer();
                                                                    question_id[i] = list.get(i).getID();
                                                                    //判断对错
                                                                    if (list.get(i).getAnswer() != list.get(i).getSelectedAnswer())
                                                                    {
                                                                        wrongList.add(i);
                                                                    }
                                                                }
                                                                return wrongList;
                                                            }
                                                        }).setNegativeButton("取消",null).show();


                                            }
                                        }
                                    });


                                    //检查选中
                                    mRadioButton[0].setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            for(int i=0;i<4;i++)
                                            {
                                                mRadioButton[i].setChecked(false);
                                            }
                                            mRadioButton[0].setChecked(true);
                                            list.get(corrent).setSelectedAnswer(0);
                                        }
                                    });
                                    mRadioButton[1].setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            for(int i=0;i<4;i++)
                                            {
                                                mRadioButton[i].setChecked(false);
                                            }
                                            mRadioButton[1].setChecked(true);
                                            list.get(corrent).setSelectedAnswer(1);
                                        }
                                    });
                                    mRadioButton[2].setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            for(int i=0;i<4;i++)
                                            {
                                                mRadioButton[i].setChecked(false);
                                            }
                                            mRadioButton[2].setChecked(true);
                                            list.get(corrent).setSelectedAnswer(2);
                                        }
                                    });
                                    mRadioButton[3].setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            for(int i=0;i<4;i++)
                                            {
                                                mRadioButton[i].setChecked(false);
                                            }
                                            mRadioButton[3].setChecked(true);
                                            list.get(corrent).setSelectedAnswer(3);
                                        }
                                    });


                                }

                            }
                        });

                }
                else
                {
                    new AlertDialog.Builder(Mid_exam.this).setTitle("提示").setMessage("你已经完成过期中测试！")
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








    public void getTime(){
        Calendar calendar = Calendar.getInstance();
        created= calendar.get(Calendar.YEAR) + "年"
                + (calendar.get(Calendar.MONTH)+1) + "月"//从0计算
                + calendar.get(Calendar.DAY_OF_MONTH) + "日"
                + calendar.get(Calendar.HOUR_OF_DAY) + "时"
                + calendar.get(Calendar.MINUTE) + "分"+calendar.get(Calendar.SECOND)+"s";
    }


}
