package com.example.proyecto.Entidades;

public class QuestionsList {

    private String question, option1,option2,option3,option4,asnwer,exeImg;
    private String userSelectedAnswer = "";

    public QuestionsList(String question, String option1, String option2, String option3, String option4, String asnwer, String exeImg, String userSelectedAnswer) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.asnwer = asnwer;
        this.exeImg = exeImg;
        this.userSelectedAnswer = userSelectedAnswer;
    }

    public String getExeImg() {
        return exeImg;
    }

    public void setExeImg(String exeImg) {
        this.exeImg = exeImg;
    }

    public QuestionsList() {
    }

    public String getQuestion() {
        return question;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String getAsnwer() {
        return asnwer;
    }

    public String getUserSelectedAnswer() {
        return userSelectedAnswer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public void setAsnwer(String asnwer) {
        this.asnwer = asnwer;
    }

    public void setUserSelectedAnswer(String userSelectedAnswer) {
        this.userSelectedAnswer = userSelectedAnswer;
    }
}
