package com.example.android.doyoureallyknowme;

public class Game implements Question1Fragment.OnSetRightAnswerListener {
    private Boolean inQuiz= false;
    private int score=0;
    private Question question1= new Question();
    private int currentQuestion=0;
    private OnSetRightAnswerListener mListener;

    public int getScore(){return score;}
    public void  setScore(int score){this.score=score;}
    public Boolean getInQuiz(){return inQuiz;}
    public void setInQuiz(Boolean inQuiz){this.inQuiz=inQuiz;}
    public int getCurrentQuestion() {return currentQuestion;   }
    public void setCurrentQuestion(int currentQuestion) { this.currentQuestion = currentQuestion;   }

    public void setAnswer(int questionId,Answer rightAnswer){
        question1.setRightAnswer(rightAnswer.getStringAnswer());
        mListener.goToNextQuestion();
    }

    public interface OnSetRightAnswerListener {
        public void goToNextQuestion();
    }
}
