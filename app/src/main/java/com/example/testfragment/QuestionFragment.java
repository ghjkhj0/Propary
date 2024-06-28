package com.example.testfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class QuestionFragment extends Fragment {
    TextView textQuest, textScore;
    EditText textAnswer;
    Button btnNext, btnPrev, btnCheck;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_question, container, false);
        textQuest = view.findViewById(R.id.text_quest);
        textAnswer = view.findViewById(R.id.et_answer);
        textScore = view.findViewById(R.id.tv_score);

        RefreshQuestion();

        btnPrev = view.findViewById(R.id.btnPrev);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataPresenter.getInstance().setCurrentQuestion(DataPresenter.getInstance().getCurrentQuestion() - 1);
            }
        });

        btnNext = view.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataPresenter.getInstance().setCurrentQuestion(DataPresenter.getInstance().getCurrentQuestion() + 1);
            }
        });

        btnCheck = view.findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ans = textAnswer.getText().toString();
                if(ans.equals(DataPresenter.getInstance().getAnswerCurrentQuestion())){
                    DataPresenter.getInstance().upScore();
                    textScore.setText(String.valueOf(DataPresenter.getInstance().getScore()));
                    btnCheck.setEnabled(false);
                    textAnswer.setText("");
                }
            }
        });

        String txt = ((MainActivity)getActivity()).settings.getString(DataPresenter.getInstance().theme,"");
        if(!txt.isEmpty()){
            String[] list = txt.split("_");
            int sc = Integer.parseInt(list[0]);
            int num = Integer.parseInt(list[1]);

            DataPresenter.getInstance().setCurrentQuestion(num);
            textScore.setText(sc);
        }


        return view;
    }

    private void EnabledButtons(int currentQuest){
        btnNext.setEnabled(true);
        btnPrev.setEnabled(true);
        if (currentQuest == 0) {
            btnPrev.setEnabled(false);
        }
        if (currentQuest == 9) {
            btnNext.setEnabled(false);
        }
    }

    public void RefreshQuestion(){
        if(btnCheck != null)
            btnCheck.setEnabled(true);
        if(DataPresenter.getInstance().getCurrentQuestion() > -1){
            textQuest.setText(DataPresenter.getInstance().getTextCurrentQuestion());
            EnabledButtons(DataPresenter.getInstance().getCurrentQuestion());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("onStop");

        SharedPreferences.Editor prefEditor = ((MainActivity)getActivity()).settings.edit();
        prefEditor.putString(DataPresenter.getInstance().theme, textScore.getText().toString() + "_" + DataPresenter.getInstance().getCurrentQuestion());
        prefEditor.apply();
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("onPause");

    }
}