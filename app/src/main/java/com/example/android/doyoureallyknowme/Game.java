package com.example.android.doyoureallyknowme;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class Game implements Parcelable {
    private Boolean isPlaying = false; // Detect if the user is playing or setting the answers
    private int score=0;
    private Question[] questions;
    private int currentQuestionNum =0;
    private QuizActivity quizActivity;
    private int questionQuantity;

    public int getScore(){return score;}
    public void  setScore(int score){this.score=score;}
    public Boolean getIsPlaying(){return isPlaying;}
    public void setIsPlaying(Boolean isPlaying){this.isPlaying = isPlaying;}
    public int getCurrentQuestionNum() {return currentQuestionNum;   }
    public void setCurrentQuestionNum(int currentQuestionNum) { this.currentQuestionNum = currentQuestionNum;   }

    public Question getQuestion(int questionNum) {
        return questions[questionNum];
    }

    /*
            Set the right answer to the current question
         */
    public void setRightAnswer(int questionId, Answer rightAnswer){
        questions[questionId].setRightAnswer(rightAnswer);
    }

    public int getIntRightAnswer(int questionId){
        return  questions[questionId].getRightAnswer().getRadioButtonTag();
    }

    public int[] getRightCheckBoxesTags(int questionId){
        return questions[questionId].getRightAnswer().getCheckBoxesTags();
    }

    public String getEditTextRightAnswer(int questionId){
        return  questions[questionId].getRightAnswer().getEditText();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(isPlaying);
    }
    public Game(Parcel parcel) {
        questions=new Question[10];
        //this.isPlaying = (Boolean) parcel.readValue(null);
    }
    public Game(Activity activity){
        quizActivity=(QuizActivity)activity;
        createQuestions();
    }

    // Mandatory!
    public int describeContents() {
        return 0;
    }
    public final static Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Game createFromParcel(Parcel source) {
            return new Game(source);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    /**
     * Manually create all the questions
     */
    private void createQuestions(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(quizActivity);
        databaseAccess.open();
        List<Question> questionsList = databaseAccess.getQuestions();
        databaseAccess.close();

        questions=new Question[10];
        questions[0]=new Question();
        for ( questionQuantity=0;questionQuantity<questionsList.size();questionQuantity++){
            questions[questionQuantity+1]=questionsList.get(questionQuantity);
        }
    }

    /**
     * Get the quantity of questions in the game
     * @return quantity of questions
     */
    public int getQuestionsQuantity(){
        return questionQuantity;
    }

    /**
     * Start the quiz game for the partner
     */
    public void startGame(){
        isPlaying=true;
        currentQuestionNum=0;
        score=0;
    }

    /**
     * Compare the Partner answer with the right one
     */
    public void checkAnswer(Answer tryAnswer, int questionId){
        Answer rightAnswer= questions[questionId].getRightAnswer();
        switch (questions[questionId].getType()){
            case "edit":
                if (rightAnswer.getEditText().equals(tryAnswer.getEditText())){
                    this.score++;
                    makeToast("All right!!");
                }else{
                    makeToast("Wrong!! The right answer is " + rightAnswer.getEditText());
                }
                break;
            case "radio":
                if (rightAnswer.getRadioButtonTag()==tryAnswer.getRadioButtonTag()){
                    this.score++;
                    makeToast(quizActivity.getString(R.string.all_toastright));
                }else{
                    makeToast(quizActivity.getString(R.string.all_toastwrong));
                }
                break;
            case "check":
                if (Arrays.equals(rightAnswer.getCheckBoxesTags(),tryAnswer.getCheckBoxesTags())){
                    this.score++;
                    makeToast(quizActivity.getString(R.string.all_toastright));
                }else{
                    makeToast(quizActivity.getString(R.string.all_toastwrong));
                }
                break;
        }
    }

    private void makeToast(CharSequence text){
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(quizActivity, text, duration);
        toast.show();
    }
}
