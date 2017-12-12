package com.example.android.doyoureallyknowme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CheckQuestionFragment extends Fragment {
    private Game game;
    private String[] answersTexts; // answers of the current quiz - used on checkboxes creation
    private View fragmentView;

    public CheckQuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get the game object from the activity
        game=getArguments().getParcelable("game");
        answersTexts =getArguments().getStringArray("answersTexts");
        // Inflate the layout for this fragment
        fragmentView= inflater.inflate(R.layout.fragment_check_question, container, false);
        createCheckBoxes(); // create check boxes with answers
        // Receive the question and the subtitle and set the corresponding TextViews answersTexts
        TextView question = fragmentView.findViewById(R.id.tv_checkquestion_question);
        question.setText(getArguments().getString("question"));
        if (getArguments().getString("subtitle")!=null){
            TextView subtitle =  fragmentView.findViewById(R.id.tv_checkquestion_subtitle);
            subtitle.setText(getArguments().getString("subtitle"));
        }
        // Set the current question number to the bottom bar
        TextView bottomText =  getActivity().findViewById(R.id.textview_quiz_bottomText);
        bottomText.setText( getString(R.string.quiz_stepindicator,game.getCurrentQuestionNum(),game.getQuestionsQuantity()));
        // Set Click listener on the next button
        final Button button = fragmentView.findViewById(R.id.btn_checkquestion_next);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int checked=0; // quantity of checked answers checkboxes
                for (int i=0;i<answersTexts.length;i++){
                    CheckBox checkBox=fragmentView.findViewById(i);
                    if(checkBox.isChecked())checked++;
                }
                int[] answersTags= new int[checked]; // The tags of the checked answers
                checked=0;
                for (int i=0;i<answersTexts.length;i++){
                    CheckBox checkBox=fragmentView.findViewById(i);
                    if(checkBox.isChecked()){
                        answersTags[checked]= (int)checkBox.getTag();
                        checked++;
                    }
                }
                Answer rightAnswer=new Answer(answersTags);
                game.setRightAnswer(game.getCurrentQuestionNum(),rightAnswer); // Set the right answers to this quiz
                ((QuizActivity)getActivity()).goToNextQuestion();
            }
        });
        return fragmentView;
    }

    /**
     * Create CheckBoxes from the answers
     */
    private void createCheckBoxes(){
        int i=0;
        for (String text:answersTexts){
            LinearLayout linearLayout=fragmentView.findViewById(R.id.layout_checkquestion_answers) ;
            CheckBox checkBox= new CheckBox(getActivity(),null,R.attr.checkboxStyle);
            checkBox.setId(i);
            checkBox.setTag(i);
            checkBox.setText(text);
            linearLayout.addView(checkBox);
            i++;
        }
    }
}
