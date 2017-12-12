package com.example.android.doyoureallyknowme;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class QuizActivity extends AppCompatActivity  {
    public final String GAME = "game";
    private Game game;
    private Boolean isPlaying = false; // Are we in the question setting or playing mode?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        isPlaying = intent.getBooleanExtra("isplaying",false);

        if (savedInstanceState == null) {
            game=new Game(this);
            goToNextQuestion();
        }else{
            game=savedInstanceState.getParcelable(GAME);
            int i= game.getCurrentQuestionNum();
        }

        if (isPlaying){
            game.startGame();
            // Create new fragment and transaction
            StartGameFragment startGameFragment = new StartGameFragment();
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment
            transaction.replace(R.id.layout_quiz_quizcontainer, startGameFragment);
            // Commit the transaction
            transaction.commit();
        }
    }

    /**
     * Insert a new fragment with the next question in the layout container .
     */
    public void goToNextQuestion(){
        int currentQuestionNum= game.getCurrentQuestionNum();
        if (currentQuestionNum>=game.getQuestionsQuantity()){
            // Create new fragment and transaction
            SettingEndFragment settingEndFragment = new SettingEndFragment();
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment
            transaction.replace(R.id.layout_quiz_quizcontainer, settingEndFragment);
            // Commit the transaction
            transaction.commit();
            return;
        }
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
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment
                if (currentQuestionNum==1 && !isPlaying){
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
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment
                if (currentQuestionNum==1 && !isPlaying){
                    transaction.add(R.id.layout_quiz_quizcontainer, questionFragment);
                }else{
                    transaction.replace(R.id.layout_quiz_quizcontainer, questionFragment);
                }
                // Commit the transaction
                transaction.commit();}
            break;
            case "edit":{
                EditTextQuestionFragment questionFragment = new EditTextQuestionFragment();
                // Send game parcelable to fragment
                questionFragment.setArguments(bundle);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment
                if (currentQuestionNum==1 && !isPlaying){
                    transaction.add(R.id.layout_quiz_quizcontainer, questionFragment);
                }else{
                    transaction.replace(R.id.layout_quiz_quizcontainer, questionFragment);
                }
                transaction.commit();
            }
            break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(GAME, game);
        super.onSaveInstanceState(outState);
    }


}
