package com.example.android.doyoureallyknowme;

/**
 * Created by Fabio on 08/12/2017.
 */

public class Question {
    private Boolean rightAnswered=false;
    private String rightAnswer;
    private Boolean answered;

    public void setAnswered(Boolean answered) {
        this.answered = answered;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public void setRightAnswered(Boolean rightAnswered) {
        this.rightAnswered = rightAnswered;
    }

    public Boolean getAnswered() {
        return answered;
    }

    public Boolean getRightAnswered() {
        return rightAnswered;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }
}
