package com.example.android.doyoureallyknowme;

public class Question {
    private Answer rightAnswer;
    private String question;
    private String subtitle;
    private Answer[] answers;
    private String type;

    public void setRightAnswer(Answer rightAnswer) {
        this.rightAnswer = rightAnswer;
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
