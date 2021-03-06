package com.example.android.doyoureallyknowme;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * The Partner start to play here
 */

public class StartGameFragment extends Fragment {
    private QuizActivity quizActivity;
    public StartGameFragment(){ }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        quizActivity=((QuizActivity)getActivity());
        View fragmentView=inflater.inflate(R.layout.fragment_start_game, container, false);
        // Button Next listener
        final Button button = fragmentView.findViewById(R.id.btn_settingEnd_next);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                quizActivity.goToNextQuestion();
            }
        });
        return fragmentView;
    }

}
