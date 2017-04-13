package com.example.exam.db;

import cn.bmob.v3.BmobObject;

/**
 * Created by 魏于翔 on 2017/2/7.
 */

public class History extends BmobObject{
    private String user_id;

    private String time;

    private int question_id1;
    private int question_id2;
    private int question_id3;
    private int question_id4;
    private int question_id5;
    private int question_id6;



    private int selectedAnswer1;
    private int selectedAnswer2;
    private int selectedAnswer3;
    private int selectedAnswer4;
    private int selectedAnswer5;
    private int selectedAnswer6;



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

    public int getQuestion_id1() {
        return question_id1;
    }

    public void setQuestion_id1(int question_id1) {
        this.question_id1 = question_id1;
    }

    public int getQuestion_id2() {
        return question_id2;
    }

    public void setQuestion_id2(int question_id2) {
        this.question_id2 = question_id2;
    }

    public int getQuestion_id3() {
        return question_id3;
    }

    public void setQuestion_id3(int question_id3) {
        this.question_id3 = question_id3;
    }

    public int getQuestion_id4() {
        return question_id4;
    }

    public void setQuestion_id4(int question_id4) {
        this.question_id4 = question_id4;
    }

    public int getQuestion_id5() {
        return question_id5;
    }

    public void setQuestion_id5(int question_id5) {
        this.question_id5 = question_id5;
    }

    public int getQuestion_id6() {
        return question_id6;
    }

    public void setQuestion_id6(int question_id6) {
        this.question_id6 = question_id6;
    }





    public int getSelectedAnswer1() {
        return selectedAnswer1;
    }

    public void setSelectedAnswer1(int selectedAnswer1) {
        this.selectedAnswer1 = selectedAnswer1;
    }

    public int getSelectedAnswer2() {
        return selectedAnswer2;
    }

    public void setSelectedAnswer2(int selectedAnswer2) {
        this.selectedAnswer2 = selectedAnswer2;
    }

    public int getSelectedAnswer3() {
        return selectedAnswer3;
    }

    public void setSelectedAnswer3(int selectedAnswer3) {
        this.selectedAnswer3 = selectedAnswer3;
    }

    public int getSelectedAnswer4() {
        return selectedAnswer4;
    }

    public void setSelectedAnswer4(int selectedAnswer4) {
        this.selectedAnswer4 = selectedAnswer4;
    }

    public int getSelectedAnswer5() {
        return selectedAnswer5;
    }

    public void setSelectedAnswer5(int selectedAnswer5) {
        this.selectedAnswer5 = selectedAnswer5;
    }

    public int getSelectedAnswer6() {
        return selectedAnswer6;
    }

    public void setSelectedAnswer6(int selectedAnswer6) {
        this.selectedAnswer6 = selectedAnswer6;
    }





    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
