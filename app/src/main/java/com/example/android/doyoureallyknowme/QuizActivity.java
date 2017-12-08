package com.example.android.doyoureallyknowme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class QuizActivity extends AppCompatActivity implements Game.OnSetRightAnswerListener  {
    public Boolean inQuiz=false;
    private Game game=new Game();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Bundle bundle = new Bundle();
        bundle.putBoolean("inQuiz",inQuiz);

        Question1Fragment question1Fragment = new Question1Fragment();
        question1Fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.quiz_container, question1Fragment).commit();
    }

    public void goToNextQuestion(){
        Log.i("QuizActivity","Go to the next question!");
    }
}
