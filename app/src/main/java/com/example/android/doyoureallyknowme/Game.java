package com.example.android.doyoureallyknowme;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import java.util.Arrays;

public class Game implements Parcelable {
    private Boolean isPlaying = false; // Detect if the user is playing or setting the answers
    private int score=0;
    private Question[] questions;
    private int currentQuestionNum =0;
    private QuizActivity quizActivity;

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
        return  questions[questionId].getRightAnswer().getIntAnswer();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(isPlaying);
    }
    public Game(Parcel parcel) {
        questions=new Question[10];
        //this.isPlaying = (Boolean) parcel.readValue(null);
    }
    public Game(){  questions=new Question[10];  }
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

    private void createQuestions(){
        questions=new Question[10];
        questions[0]=new Question();
        Answer[] answers={new Answer("Neri"),new Answer("Gialli"),new Answer("Rossi")};
        questions[1]=new Question();
        questions[1].setAnswers(answers);
        questions[1].setType("radio");
        questions[1].setQuestion("Di che colore sono i miei occhi?");

        questions[2]=new Question();
        questions[2].setType("edit");
        questions[2].setQuestion("Qual'è il mio soprannome?");


        questions[3]=new Question();
        questions[3].setType("check");
        questions[3].setQuestion("Di che colore sono i miei capelli?");
        questions[3].setSubtitle("Una sola risposta possibile");
        Answer[] answers1={new Answer("Gialli"),new Answer("Rossi")};
        questions[3].setAnswers(answers1);
    }

    /**
     * Get the quantity of questions in the game
     * @return quantity of questions
     */
    public int getQuestionsQuantity(){
        return 3;
    }

    /**
     * Start the quiz game for the partner
     */
    public void startGame(){
        isPlaying=true;
        currentQuestionNum=0;
    }

    public void checkAnswer(Answer tryAnswer, int questionId){
        Answer rightAnswer= questions[questionId].getRightAnswer();
        String a=rightAnswer.getStringAnswer();
        String b=tryAnswer.getStringAnswer();
        Boolean isCorrect=false; // Does the partner answer correspond to the right answer?
        if (a.equals(b)
            && rightAnswer.getIntAnswer()==tryAnswer.getIntAnswer()
            && Arrays.equals(rightAnswer.getIntArrayAnswer(),tryAnswer.getIntArrayAnswer())){
                    isCorrect= true;
        }
        if (isCorrect){
            this.score++;
            makeToast(quizActivity.getString(R.string.toast_game_right));
        }else{
            makeToast(quizActivity.getString(R.string.toast_game_wrong));
        }
    }

    private void makeToast(CharSequence text){
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(quizActivity, text, duration);
        toast.show();
    }
}
