package com.example.testfragment;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

public class DataPresenter {
    private static DataPresenter instance;
    ChangeFragmentInterface changeFragmentInterface;
    ArrayList<Content> subjectsContentList;
    private String[] subjects_list;
    private Content currentSubject;
    private String[] themes_list;
    private Theme currentTheme;
    ArrayList<com.example.testfragment.Question> questions_list = new ArrayList<com.example.testfragment.Question>();
    private int currentQuestion = -1;
    private int score;

    public DataPresenter(Context context) {
        instance = this;
        changeFragmentInterface = (ChangeFragmentInterface) context;
    }

    public static DataPresenter getInstance() {
        return instance;
    }

    public void setContent(String str_data){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        subjectsContentList = gson.fromJson(str_data, new TypeToken<List<Content>>(){}.getType());
        System.out.println("GSON" + subjectsContentList.get(0).subject);

        subjects_list = new String[subjectsContentList.size()];
        System.out.println(subjectsContentList.size());
        for(int i = 0; i < subjectsContentList.size() - 1; i++){
            System.out.println("Subject" + subjectsContentList.get(i).subject);
            subjects_list[i] = subjectsContentList.get(i).subject;
        }
    }

    public String[] getSubjects_list() {
        return subjects_list;
    }

    //Текущий предмет
    public void setCurrentSubject(int indexSubject) {
        this.currentSubject = subjectsContentList.get(indexSubject);
        themes_list = new String[currentSubject.themes.size()];

        for(int i = 0; i < currentSubject.themes.size(); i++){
            themes_list[i] = currentSubject.themes.get(i).name;
        }
        changeFragmentInterface.changeSubject();
    }

    public String[] getThemesList() {
        return themes_list;
    }

    //Текущая тема предмета
    public void setCurrentTheme(int indexTheme) {
        this.currentTheme = currentSubject.themes.get(indexTheme);
        questions_list = currentTheme.questions;
        changeFragmentInterface.changeTheme();
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public String getTextCurrentQuestion() {
        return questions_list.get(currentQuestion).text;
    }

    public String getAnswerCurrentQuestion() {
        return String.valueOf(questions_list.get(currentQuestion).answer);
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
        changeFragmentInterface.changeQuestion();
    }


    public int getScore() {
        return score;
    }

    public void upScore() {
        this.score++;
    }

}

