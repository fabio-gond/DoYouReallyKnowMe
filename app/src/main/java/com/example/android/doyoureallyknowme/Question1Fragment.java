package com.example.android.doyoureallyknowme;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

public class Question1Fragment extends Fragment  {
    int rix=0;
    private Game game;

    public Question1Fragment() {
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question1, container, false);
    }

    public void onActivityCreated (Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        // Find the fragment RadioButtons
        RadioButton radioButton=(RadioButton) getActivity().findViewById(R.id.radio1);
        radioButton.setOnClickListener(mRadioListener);
    }

    // Called on a click on a radiobutton
    private View.OnClickListener mRadioListener = new View.OnClickListener() {
        public void onClick(View v) {
            Answer rightAnswer=new Answer(0);
            // What's the clicked radio button?
            switch (v.getId() ) {
                case R.id.radio1:
                    rightAnswer=new Answer(1);
                    break;
                case R.id.radio2:
                    rightAnswer=new Answer(2);
                    break;
                case R.id.radio3:
                    rightAnswer=new Answer(3);
                    break;
                case R.id.radio4:
                    rightAnswer=new Answer(4);
                    break;
                default:
                    break;
            }
            game.setAnswer(1,rightAnswer); // Set the right answer to this quiz
        }
    };


    @Override
    public void onDetach() {
        super.onDetach();
    }

}
