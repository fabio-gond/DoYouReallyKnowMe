package com.example.android.doyoureallyknowme;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;

public class Game implements Parcelable {
    private Boolean isPlaying = false; // Detect if the user is playing or setting the answers
    private int score=0;
    private Question[] questions;
    private int currentQuestion=0;
    private OnSetRightAnswerListener mGameListener;

    public int getScore(){return score;}
    public void  setScore(int score){this.score=score;}
    public Boolean getIsPlaying(){return isPlaying;}
    public void setIsPlaying(Boolean isPlaying){this.isPlaying = isPlaying;}
    public int getCurrentQuestion() {return currentQuestion;   }
    public void setCurrentQuestion(int currentQuestion) { this.currentQuestion = currentQuestion;   }

    /*
        Set the right answer to the current question
     */
    public void setAnswer(int questionId,Answer rightAnswer){
        questions[0].setRightAnswer(rightAnswer);
        currentQuestion=1;
        mGameListener.goToNextQuestion();
    }

    public Answer getAnswer(int questionId){
        return  questions[0].getRightAnswer();
    }

    public interface OnSetRightAnswerListener {
        public void goToNextQuestion();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(isPlaying);
    }
    public Game(Parcel parcel) {
        questions=new Question[10];
        this.isPlaying = (Boolean) parcel.readValue(null);
    }
    public Game(){  questions=new Question[10];  }
    public Game(Activity activity){
        questions=new Question[10];
        // Connect the Game Listener to the parent activity
        try {
            mGameListener = (OnSetRightAnswerListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnSetRightAnswerListener");
        }
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

}
