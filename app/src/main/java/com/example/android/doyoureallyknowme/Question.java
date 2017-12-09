package com.example.android.doyoureallyknowme;


public class Question {
    private Boolean rightAnswered=false;
    private Answer rightAnswer;
    private Boolean answered;

    public void setAnswered(Boolean answered) {
        this.answered = answered;
    }
    public void setRightAnswer(Answer rightAnswer) {
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
    public Answer getRightAnswer() {
        return rightAnswer;
    }
}
