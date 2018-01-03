package com.example.android.doyoureallyknowme;

import java.util.List;

public class Question {
    private Answer rightAnswer;
    private String question;
    private String subtitle;
    private List<String> answers;
    private String type;

    void setRightAnswer(Answer rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    void setQuestion(String question) {
        this.question = question;
    }

    void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    void setType(String type) {
        this.type = type;
    }


    Answer getRightAnswer() {
        return rightAnswer;
    }

    List<String> getAnswers() {
        return answers;
    }

    String getQuestion() {
        return question;
    }

    String getSubtitle() {
        return subtitle;
    }

    String getType() {
        return type;
    }
}
