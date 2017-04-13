package com.example.exam.db;

import cn.bmob.v3.BmobObject;

/**
 * Created by 魏于翔 on 2017/2/16.
 */

public class UslHistoryAll extends BmobObject {
    private String user_id;

    private String time;

    private int question_id;


    private int selectedAnswer;


    private int score;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(int selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
}
