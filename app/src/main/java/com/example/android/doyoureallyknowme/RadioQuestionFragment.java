package com.example.android.doyoureallyknowme;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RadioQuestionFragment extends Fragment  {
    int rix=0;
    private Game game;
    private String[] texts;

    public RadioQuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get the game object from the activity
        game=(Game)getArguments().getParcelable("game");
        texts=getArguments().getStringArray("texts");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_radio_question, container, false);
    }

    public void onActivityCreated (Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        populateRadioGroup(texts);
        TextView question = (TextView) getActivity().findViewById(R.id.question);
        question.setText(getArguments().getString("question"));
        if (getArguments().getString("subtitle")!=null){
            TextView subtitle = (TextView) getActivity().findViewById(R.id.subtitle);
            subtitle.setText(getArguments().getString("subtitle"));
        }
        TextView bottomText = (TextView) getActivity().findViewById(R.id.bottomText);
        bottomText.setText(game.getCurrentQuestionNum() + " di 10");
    }

    // Called on a click on a radiobutton
    private View.OnClickListener mRadioListener = new View.OnClickListener() {
        public void onClick(View v) {
            Answer rightAnswer=new Answer(0);
            // What's the clicked radio button?
            RadioButton radioButton= (RadioButton) v;
            rightAnswer=new Answer(radioButton.getText().toString());
            game.setRightAnswer(game.getCurrentQuestionNum(),rightAnswer); // Set the right answer to this quiz
        }
    };


    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void populateRadioGroup(String[] texts){
        int i=0;
        for (String text:texts){
            RadioGroup radioGroup=(RadioGroup) getActivity().findViewById(R.id.radiogroup);
            RadioButton radioButton=new RadioButton(getActivity(),null,R.attr.radioButtonStyle);
            radioButton.setOnClickListener(mRadioListener);
            radioButton.setId(i);
            radioButton.setText(text);
            radioGroup.addView(radioButton);
            i++;
        }
    }

}
