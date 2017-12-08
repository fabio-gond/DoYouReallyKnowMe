package com.example.android.doyoureallyknowme;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;

public class Game implements Question1Fragment.OnSetRightAnswerListener, Parcelable {
    private Boolean isPlaying = false; // Detect if the user is playing or setting the answers
    private int score=0;
    private Question question1= new Question();
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
        question1.setRightAnswer(rightAnswer.getStringAnswer());
        currentQuestion=1;
        mGameListener.goToNextQuestion();
    }

    public interface OnSetRightAnswerListener {
        public void goToNextQuestion();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(isPlaying);
    }
    public Game(Parcel parcel) {
        this.isPlaying = (Boolean) parcel.readValue(null);
    }
    public Game(){    }
    public Game(Activity activity){
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
