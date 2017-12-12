package com.example.android.doyoureallyknowme;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class EditTextQuestionFragment extends Fragment {
    private Game game;

    public EditTextQuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get the game object from the activity
        game=getArguments().getParcelable("game");
        // Inflate the layout for this fragment
        View fragmentView= inflater.inflate(R.layout.fragment_edit_text_question, container, false);
        // Receive the question and the subtitle
        TextView question = fragmentView.findViewById(R.id.tv_editquestion_question);
        question.setText(getArguments().getString("question"));
        if (getArguments().getString("subtitle")!=null){
            TextView subtitle =  fragmentView.findViewById(R.id.tv_editquestion_subtitle);
            subtitle.setText(getArguments().getString("subtitle"));
        }
        // Set the current question number to the bottom bar
        TextView bottomText =  getActivity().findViewById(R.id.textview_quiz_bottomText);
        bottomText.setText( getString(R.string.quiz_stepindicator,game.getCurrentQuestionNum(),game.getQuestionsQuantity()));
        // Set Click listener on the next button
        final Button button = fragmentView.findViewById(R.id.btn_editquestion_next);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText editText = getActivity().findViewById(R.id.et_editquestion_answer);
                Answer rightAnswer=new Answer(editText.getText().toString());
                game.setRightAnswer(game.getCurrentQuestionNum(),rightAnswer); // Set the right answers to this quiz
                ((QuizActivity)getActivity()).goToNextQuestion();
            }
        });
        return fragmentView;
    }

}
