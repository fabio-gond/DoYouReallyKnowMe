package com.example.android.doyoureallyknowme;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity extends AppCompatActivity  {
    public final String GAME = "game";
    public final String IN_PARTNER_MODE = "inPartnerMode";
    public final String SCORE = "score";
    public final String TOTAL_SCORE = "totalScore";
    private Game game;
    private Boolean inPartnerMode = false; // Are we in the question setting or playing mode?
    private Timer timer= new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        inPartnerMode = intent.getBooleanExtra("isplaying",false);

        if (savedInstanceState == null) {
            game=new Game(this);
            goToNextQuestion();
        }else{
            game=savedInstanceState.getParcelable(GAME);
            inPartnerMode =savedInstanceState.getBoolean(IN_PARTNER_MODE);
        }

        if (inPartnerMode && !game.getIsPlaying()){ // If the partner quiz mode is started but not the game
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
            // Are we in setting or playing mode?
            if(!game.getIsPlaying()){ // We are at the end of the setting mode
                // Go to the settingEnd fragment
                SettingEndFragment settingEndFragment = new SettingEndFragment();
                android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager()
                        .beginTransaction();
                transaction.replace(R.id.layout_quiz_quizcontainer, settingEndFragment);
                transaction.commit();
                return;
            }
            // The user clicked the retry button
            game.setIsPlaying(false);
            Bundle bundle = new Bundle();
            bundle.putInt(SCORE,game.getScore());
            bundle.putInt(TOTAL_SCORE,game.getQuestionsQuantity());
            ResultFragment resultFragment=new ResultFragment();
            resultFragment.setArguments(bundle);
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment
            transaction.replace(R.id.layout_quiz_quizcontainer, resultFragment);
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
        FragmentTransaction transaction;
        if (question.getSubtitle()!= null) bundle.putString("subtitle",question.getSubtitle());
        switch (question.getType()){
            case "radio":
            default:{
                bundle.putStringArray("answersTexts",question.getAnswers());
                // Create new fragment and transaction
                RadioQuestionFragment questionFragment = new RadioQuestionFragment();
                // Send game parcelable to fragment
                questionFragment.setArguments(bundle);
                transaction = getSupportFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment
                if (currentQuestionNum==1 && !inPartnerMode){
                    transaction.add(R.id.layout_quiz_quizcontainer, questionFragment);
                }else{
                    transaction.replace(R.id.layout_quiz_quizcontainer, questionFragment);
                }
            }
            break;
            case "check":{
                bundle.putStringArray("answersTexts",question.getAnswers());

                // Create new fragment and transaction
                CheckQuestionFragment questionFragment = new CheckQuestionFragment();
                // Send game parcelable to fragment
                questionFragment.setArguments(bundle);
                transaction = getSupportFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment
                if (currentQuestionNum==1 && !inPartnerMode){
                    transaction.add(R.id.layout_quiz_quizcontainer, questionFragment);
                }else{
                    transaction.replace(R.id.layout_quiz_quizcontainer, questionFragment);
                }
            }
            break;
            case "edit":{
                EditTextQuestionFragment questionFragment = new EditTextQuestionFragment();
                // Send game parcelable to fragment
                questionFragment.setArguments(bundle);
                transaction = getSupportFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment
                if (currentQuestionNum==1 && !inPartnerMode){
                    transaction.add(R.id.layout_quiz_quizcontainer, questionFragment);
                }else{
                    transaction.replace(R.id.layout_quiz_quizcontainer, questionFragment);
                }
            }
            break;
        }
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }
    /**
     * Go to the next question after the @param delay
     */
    public void goToNextQuestion (int delay){
        timer.cancel(); //this will cancel the current task. if there is no active task, nothing happens
        timer = new Timer();
        TimerTask action = new TimerTask() {
            public void run() {
                goToNextQuestion();
            }
        };
        timer.schedule(action, delay); //this starts the task*/
    }

    /**
     * Save the instance of the Game and inPartnerMode (i we are in the Partner mode)
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(GAME, game);
        outState.putBoolean(IN_PARTNER_MODE, inPartnerMode);
        super.onSaveInstanceState(outState);
    }

    public void setPartnerMode(){
        inPartnerMode =true;
    }

}
