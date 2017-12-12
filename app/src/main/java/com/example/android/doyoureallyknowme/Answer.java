package com.example.android.doyoureallyknowme;


public class Answer {
    // an Answer can be a String, a number (like radio button id), an int array (like checkboxes ids)
    private String stringAnswer=" ";
    private int intAnswer=0;
    private int[] intArrayAnswer=new int[10];

    // Constructors based on the answer type
    Answer(String stringAnswer){
        this.stringAnswer=stringAnswer;
    }
    Answer(int intAnswer){
        this.intAnswer=intAnswer;
    }
    Answer(int[] intArrayAnswer){this.intArrayAnswer=intArrayAnswer;   }

    /**
    I know the type of the answer, so I can choose the appropriate method
     */
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
