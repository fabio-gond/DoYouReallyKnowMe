package com.example.android.doyoureallyknowme;

import android.content.Intent;
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
    final String ISPLAYING = "isplaying";

    public SettingEndFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView=inflater.inflate(R.layout.fragment_setting_end, container, false);
        final Button button = fragmentView.findViewById(R.id.btn_settingend_next);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*Intent intent = new Intent(getContext(), QuizActivity.class);
                intent.putExtra(ISPLAYING, true);
                getActivity().finish();
                startActivity(intent); */
                ((QuizActivity)getActivity()).setIsPlaying();
                getActivity().recreate();
            }
        });
        return fragmentView;
    }

}
