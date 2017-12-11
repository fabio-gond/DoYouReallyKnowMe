package com.example.android.doyoureallyknowme;

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

    /**
     * Create a new fragment and replace it to the current one
     */
    public void goToNextQuestion(){
        int currentQuestionNum= game.getCurrentQuestionNum();
        currentQuestionNum++;
        game.setCurrentQuestionNum(currentQuestionNum);
        Question question= game.getQuestion(currentQuestionNum);
        Bundle bundle = new Bundle();
        bundle.putParcelable("game", game);
        bundle.putString("question",question.getQuestion());
        if (question.getSubtitle()!= null) bundle.putString("subtitle",question.getSubtitle());
        switch (question.getType()){
            case "radio":{
                Answer[] answers = question.getAnswers();
                String[] answersTexts = new String[answers.length];
                int i=0;
                for(Answer answer:answers){
                    answersTexts[i]=answer.getStringAnswer();
                    i++;
                }
                bundle.putStringArray("answersTexts",answersTexts);

                // Create new fragment and transaction
                RadioQuestionFragment questionFragment = new RadioQuestionFragment();
                // Send game parcelable to fragment
                questionFragment.setArguments(bundle);
                android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment
                if (currentQuestionNum==1){
                    transaction.add(R.id.layout_quiz_quizcontainer, questionFragment);
                }else{
                    transaction.replace(R.id.layout_quiz_quizcontainer, questionFragment);
                }
                // Commit the transaction
                transaction.commit();}
                break;
            case "check":{
                Answer[] answers = question.getAnswers();
                String[] answersTexts = new String[answers.length];
                int i=0;
                for(Answer answer:answers){
                    answersTexts[i]=answer.getStringAnswer();
                    i++;
                }
                bundle.putStringArray("answersTexts",answersTexts);

                // Create new fragment and transaction
                CheckQuestionFragment questionFragment = new CheckQuestionFragment();
                // Send game parcelable to fragment
                questionFragment.setArguments(bundle);
                android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment
                if (currentQuestionNum==1){
                    transaction.add(R.id.layout_quiz_quizcontainer, questionFragment);
                }else{
                    transaction.replace(R.id.layout_quiz_quizcontainer, questionFragment);
                }
                // Commit the transaction
                transaction.commit();}
                break;
            case "edit":{
                // Create new fragment and transaction
                EditTextQuestionFragment questionFragment = new EditTextQuestionFragment();
                // Send game parcelable to fragment
                questionFragment.setArguments(bundle);
                android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment
                if (currentQuestionNum==1){
                    transaction.add(R.id.layout_quiz_quizcontainer, questionFragment);
                }else{
                    transaction.replace(R.id.layout_quiz_quizcontainer, questionFragment);
                }
                // Commit the transaction
                transaction.commit();}
            break;
        }
    }
}
