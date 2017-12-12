package com.example.android.doyoureallyknowme;


public class Answer {
    // an Answer can be an EditText , the radio button tag, an int array of checkboxes tags
    private String editText =" ";
    private int radioButtonTag =0;
    private int[] checkBoxesTags =new int[10];

    // Constructors based on the answer type
    Answer(String editText){
        this.editText = editText;
    }
    Answer(int radioButtonTag){
        this.radioButtonTag = radioButtonTag;
    }
    Answer(int[] checkBoxesTags){this.checkBoxesTags = checkBoxesTags;   }

    /**
    I know the type of the answer, so I can choose the appropriate method
     */
    public int getRadioButtonTag() {
        return radioButtonTag;
    }
    public String getEditText() {
        return editText;
    }
    public int[] getCheckBoxesTags() {
        return checkBoxesTags;
    }
}
