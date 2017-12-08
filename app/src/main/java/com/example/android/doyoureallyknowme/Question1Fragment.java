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
    Boolean inQuiz=false;
    private OnSetRightAnswerListener mListener;


    public Question1Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnSetRightAnswerListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnSetRightAnswerListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inQuiz=getArguments().getBoolean("inQuiz");

        return inflater.inflate(R.layout.fragment_question1, container, false);
    }

    public void onActivityCreated (Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        RadioButton radioButton=(RadioButton) getActivity().findViewById(R.id.radio1);
        radioButton.setOnClickListener(mRadioListener);
    }

    // Create an anonymous implementation of OnClickListener
    private View.OnClickListener mRadioListener = new View.OnClickListener() {
        public void onClick(View v) {
            Answer rightAnswer=new Answer(0);
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
            mListener.setAnswer(1,rightAnswer);
        }
    };


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnSetRightAnswerListener {
        public void setAnswer(int questionId,Answer rightAnswer);
    }
}
