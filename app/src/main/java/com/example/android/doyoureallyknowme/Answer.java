package com.example.android.doyoureallyknowme;

/**
 * Created by Fabio on 08/12/2017.
 */

public class Answer {
    private String stringAnswer;
    private Boolean booleanAnswer;
    private int intAnswer;
    public Answer(String stringAnswer){
        this.stringAnswer=stringAnswer;
    }
    public Answer(Boolean booleanAnswer){
        this.booleanAnswer=booleanAnswer;
    }
    public Answer(int intAnswer){
        this.intAnswer=intAnswer;
    }

    public Boolean getBooleanAnswer() {
        return booleanAnswer;
    }

    public int getIntAnswer() {
        return intAnswer;
    }

    public String getStringAnswer() {
        return stringAnswer;
    }
}
