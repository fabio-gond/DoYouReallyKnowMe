package com.example.android.doyoureallyknowme;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;


public class EditTextQuestionFragment extends Fragment {
    private Game game;
    private QuizActivity quizActivity;

    public EditTextQuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get the game object from the activity
        game=getArguments().getParcelable("game");
        quizActivity=((QuizActivity)getActivity());
        quizActivity.displayStep(true);
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
                String tryText=editText.getText().toString();
                if (tryText.equals("")){
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(quizActivity, R.string.all_toastInsertAnswer, duration);
                    toast.show();
                    return;
                }
                if (!game.getIsPlaying()) {
                    Answer rightAnswer = new Answer(tryText);
                    game.setRightAnswer(game.getCurrentQuestionNum(), rightAnswer); // Set the right answers to this quiz
                    quizActivity.goToNextQuestion();
                    return;
                }
                Answer tryAnswer=new Answer(tryText);
                game.checkAnswer(tryAnswer,game.getCurrentQuestionNum());
                String rightText = game.getEditTextRightAnswer(game.getCurrentQuestionNum());
                if(rightText.equals(tryText)){
                    editText.setTextColor(getResources().getColor(R.color.colorRightAnswer));
                }
                quizActivity.goToNextQuestion(2000);
            }
        });
        return fragmentView;
    }

}
