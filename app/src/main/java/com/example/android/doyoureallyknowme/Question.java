package com.example.android.doyoureallyknowme;

public class Question {
    private Answer rightAnswer;
    private String question;
    private String subtitle;
    private String[] answers;
    private String type;

    public void setRightAnswer(Answer rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public void setAnswers(String[] answers) {
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

    public String[] getAnswers() {
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
