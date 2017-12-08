package com.example.android.doyoureallyknowme;


public class Answer {
    // an Answer can be a String, a Boolean, a number (like radio button id), an int array (like checkboxes ids)
    private String stringAnswer;
    private Boolean booleanAnswer;
    private int intAnswer;
    private int[] intArrayAnswer;

    // Constructors based on the answer type
    public Answer(String stringAnswer){
        this.stringAnswer=stringAnswer;
    }
    public Answer(Boolean booleanAnswer){
        this.booleanAnswer=booleanAnswer;
    }
    public Answer(int intAnswer){
        this.intAnswer=intAnswer;
    }
    public Answer(int[] intArrayAnswer){
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
    public int[] getIntArrayAnswer() {
        return intArrayAnswer;
    }
}
