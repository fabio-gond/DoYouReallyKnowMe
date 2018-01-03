package com.example.android.doyoureallyknowme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * Result of the game
 */
public class ResultFragment extends Fragment {
    private QuizActivity quizActivity;

    public ResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        quizActivity=((QuizActivity)getActivity());
        quizActivity.displayStep(false);
        View fragmentView=inflater.inflate(R.layout.fragment_result, container, false);
        // Retry Button listener
        final Button btnRetry = fragmentView.findViewById(R.id.btn_result_retry);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().recreate();
            }
        });
        // Reset Button Listener
        final Button btnReset = fragmentView.findViewById(R.id.btn_result_reset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                getActivity().finish();
                startActivity(intent);
            }
        });
        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Display the score and the text result
        int score=getArguments().getInt(quizActivity.SCORE);
        int totalScore=getArguments().getInt(quizActivity.TOTAL_SCORE);
        TextView scoreTv = getActivity().findViewById(R.id.tv_result_score);
        scoreTv.setText(String.valueOf(score));
        TextView totalTv = getActivity().findViewById(R.id.tv_result_total);
        totalTv.setText("/" + String.valueOf(totalScore));
        TextView textTv = getActivity().findViewById(R.id.tv_result_text);
        // Set the score based result text
        if (score<=((60*totalScore)/100)){
            textTv.setText(R.string.result_negative);
        }else if(score<=((80*totalScore)/100)){
            textTv.setText(R.string.result_positive);
        }else if(score<= ((95*totalScore)/100)){
            textTv.setText(R.string.result_great);
        }else textTv.setText(R.string.result_perfect);
    }
}
