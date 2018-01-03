package com.example.android.doyoureallyknowme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * End of the Questions Settings
 * This fragment tell to the user that the questions are all settes
 */
public class SettingEndFragment extends Fragment {
    private QuizActivity quizActivity;
    public SettingEndFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        quizActivity=((QuizActivity)getActivity());
        quizActivity.displayStep(false);
        // Inflate the layout for this fragment
        View fragmentView=inflater.inflate(R.layout.fragment_setting_end, container, false);
        final Button button = fragmentView.findViewById(R.id.btn_settingEnd_next);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                quizActivity.setPartnerMode();
                getActivity().recreate();
            }
        });
        return fragmentView;
    }

}
