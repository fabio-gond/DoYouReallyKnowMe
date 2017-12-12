package com.example.android.doyoureallyknowme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RadioQuestionFragment extends Fragment  {
    private Game game;
    private String[] answersTexts; // answers of the current quiz - used on radio button creation
    private View fragmentView;
    private QuizActivity quizActivity;

    public RadioQuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get the game object from the activity
        game=getArguments().getParcelable("game");
        answersTexts =getArguments().getStringArray("answersTexts");
        quizActivity=((QuizActivity)getActivity());
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_radio_question, container, false);

        populateRadioGroup(); // create radio buttons with answers
        // Receive the question and the subtitle and set the corresponding TextViews answersTexts
        TextView question =  fragmentView.findViewById(R.id.tv_radioquestion_question);
        question.setText(getArguments().getString("question"));
        if (getArguments().getString("subtitle")!=null){
            TextView subtitle = fragmentView.findViewById(R.id.tv_radioquestion_subtitle);
            subtitle.setText(getArguments().getString("subtitle"));
        }
        // Set the current question number to the bottom bar
        TextView bottomText = getActivity().findViewById(R.id.textview_quiz_bottomText);
        bottomText.setText( getString(R.string.quiz_stepindicator,game.getCurrentQuestionNum(),game.getQuestionsQuantity()));

        return fragmentView;
    }

    // Called on a click on a RadioButton
    private View.OnClickListener mRadioListener = new View.OnClickListener() {
        public void onClick(View v) {
            // What's the clicked radio button?
            RadioButton radioButton= (RadioButton) v;
            int checkedTag = (int)radioButton.getTag();
            if (!game.getIsPlaying()){
                Answer rightAnswer=new Answer((int)radioButton.getTag());
                game.setRightAnswer(game.getCurrentQuestionNum(),rightAnswer); // Set the right answer to this quiz
                quizActivity.goToNextQuestion(300);
                return;
            }
            Answer tryAnswer=new Answer(checkedTag);
            game.checkAnswer(tryAnswer,game.getCurrentQuestionNum());
            int rightTag = game.getIntRightAnswer(game.getCurrentQuestionNum());

            // Disable all Radio Buttons and Color the right answer
            RadioGroup radioGroup=fragmentView.findViewById(R.id.rg_radioquestion_answers);
            for(int i = 0; i < radioGroup.getChildCount(); i++){
                radioButton = (RadioButton) radioGroup.getChildAt(i);
                radioButton.setClickable(false);
                int currentTag = (int)radioButton.getTag();
                if (currentTag==rightTag){
                    radioButton.setTextColor(getResources().getColor(R.color.colorRightAnswer));
                }
            }
            quizActivity.goToNextQuestion(2000);
        }
    };


    /**
    * Create the RadioButtons from the answers
     */
    private void populateRadioGroup(){
        int i=0;
        RadioGroup radioGroup=fragmentView.findViewById(R.id.rg_radioquestion_answers);
        for (String text:answersTexts){
            RadioButton radioButton=new RadioButton(getActivity(),null,R.attr.radioButtonStyle);
            radioButton.setOnClickListener(mRadioListener);
            radioButton.setId(i);
            radioButton.setTag(i);
            radioButton.setText(text);
            radioGroup.addView(radioButton);
            i++;
        }
    }

}
