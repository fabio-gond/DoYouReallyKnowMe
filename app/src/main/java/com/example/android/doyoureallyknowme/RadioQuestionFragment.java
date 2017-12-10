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

    public RadioQuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get the game object from the activity
        game=getArguments().getParcelable("game");
        answersTexts =getArguments().getStringArray("answersTexts");
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
        bottomText.setText( getString(R.string.quiz_stepindicator,game.getCurrentQuestionNum(),10));

        return fragmentView;
    }

    // Called on a click on a RadioButton
    private View.OnClickListener mRadioListener = new View.OnClickListener() {
        public void onClick(View v) {
            // What's the clicked radio button?
            RadioButton radioButton= (RadioButton) v;
            Answer rightAnswer=new Answer((int)radioButton.getTag());
            game.setRightAnswer(game.getCurrentQuestionNum(),rightAnswer); // Set the right answer to this quiz
        }
    };


    /**
    * Create the RadioButtons from the answers
     */
    private void populateRadioGroup(){
        int i=0;
        for (String text:answersTexts){
            RadioGroup radioGroup=fragmentView.findViewById(R.id.rg_radioquestion_answers);
            RadioButton radioButton=new RadioButton(getActivity(),null,R.attr.radioButtonStyle);
            radioButton.setOnClickListener(mRadioListener);
            radioButton.setTag(i);
            radioButton.setText(text);
            radioGroup.addView(radioButton);
            i++;
        }
    }

}
