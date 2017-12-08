package com.example.android.doyoureallyknowme;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class QuizActivity extends AppCompatActivity implements Game.OnSetRightAnswerListener  {
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        game=new Game(this);
        Bundle bundle = new Bundle();
        bundle.putParcelable("game", game);
        Question1Fragment question1Fragment = new Question1Fragment();
        // Send game parcelable to fragment
        question1Fragment.setArguments(bundle);
        //Open fragment 1
        getSupportFragmentManager().beginTransaction().add(R.id.quiz_container, question1Fragment).commit();
    }

    public void goToNextQuestion(){
        Log.i("QuizActivity","Go to the next question! " +game.getCurrentQuestion());
    }
}
