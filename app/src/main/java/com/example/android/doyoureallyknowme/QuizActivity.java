package com.example.android.doyoureallyknowme;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class QuizActivity extends AppCompatActivity implements Game.OnSetRightAnswerListener  {
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        game=new Game(this);
        goToNextQuestion();
    }

    public void goToNextQuestion(){
        int currentQuestionNum= game.getCurrentQuestionNum();
        currentQuestionNum++;
        game.setCurrentQuestionNum(currentQuestionNum);
        Question question= game.getQuestion(currentQuestionNum);
        Bundle bundle = new Bundle();
        bundle.putParcelable("game", game);
        switch (question.getType()){
            case "radio":
                Answer[] answers = question.getAnswers();
                String[] texts = new String[answers.length];
                int i=0;
                for(Answer answer:answers){
                    texts[i]=answer.getStringAnswer();
                    i++;
                }
                bundle.putString("question",question.getQuestion());
                if (question.getSubtitle()!= null) bundle.putString("subtitle",question.getSubtitle());
                bundle.putStringArray("texts",texts);

                // Create new fragment and transaction
                RadioQuestionFragment questionFragment = new RadioQuestionFragment();
                // Send game parcelable to fragment
                questionFragment.setArguments(bundle);
                android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment
                if (currentQuestionNum==1){
                    transaction.add(R.id.quiz_container, questionFragment);
                }else{
                    transaction.replace(R.id.quiz_container, questionFragment);
                }
                // Commit the transaction
                transaction.commit();
                break;
        }
    }
}
