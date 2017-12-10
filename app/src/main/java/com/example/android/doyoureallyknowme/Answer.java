package com.example.android.doyoureallyknowme;


public class Answer {
    // an Answer can be a String, a Boolean, a number (like radio button id), an int array (like checkboxes ids)
    private String stringAnswer;
    private Boolean booleanAnswer;
    private int intAnswer;
    private int[] intArrayAnswer;

    // Constructors based on the answer type
    Answer(String stringAnswer){
        this.stringAnswer=stringAnswer;
    }
    Answer(Boolean booleanAnswer){
        this.booleanAnswer=booleanAnswer;
    }
    Answer(int intAnswer){
        this.intAnswer=intAnswer;
    }
    Answer(int[] intArrayAnswer){
        this.intArrayAnswer=intArrayAnswer;
    }

    /*
    I know the type of the answer, so I can choose the appropriate method
     */
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
