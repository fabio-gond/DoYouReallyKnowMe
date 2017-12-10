package com.example.android.doyoureallyknowme;


public class Question {
    private Boolean rightAnswered=false;
    private Answer rightAnswer;
    private Boolean answered;
    private String question;
    private String subtitle;
    private Answer[] answers;
    private String type;

    public void setAnswered(Boolean answered) {
        this.answered = answered;
    }
    public void setRightAnswer(Answer rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
    public void setRightAnswered(Boolean rightAnswered) {
        this.rightAnswered = rightAnswered;
    }

    public void setAnswers(Answer[] answers) {
        this.answers = answers;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setType(String type) {
        this.type = type;
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

    public Answer[] getAnswers() {
        return answers;
    }

    public String getQuestion() {
        return question;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getType() {
        return type;
    }
}
