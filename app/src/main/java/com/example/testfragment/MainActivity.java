package com.example.testfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ChangeFragmentInterface{
    private SubjectsListFragment subjectsListFragment;
    private ThemesFragment themesFragment;
    private QuestionFragment questionFragment;
    public SharedPreferences settings;
    DataPresenter dataPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataPresenter = new DataPresenter(this);

        settings = getSharedPreferences("PreferencesName",Context.MODE_PRIVATE);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        subjectsListFragment = new SubjectsListFragment();
        transaction.add(R.id.fragment_lay, subjectsListFragment);

        themesFragment = new ThemesFragment();
        transaction.add(R.id.fragment_lay, themesFragment);
        transaction.hide(themesFragment);

        questionFragment = new QuestionFragment();
        transaction.add(R.id.fragment_lay, questionFragment);
        transaction.hide(questionFragment);

        transaction.commit();
        //if (getSupportFragmentManager().getBackStackEntryCount() > 0)
        //    getSupportFragmentManager().popBackStack();

        readFileToJSon();
    }

    private void showFragment(Fragment fragment){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(subjectsListFragment);
        transaction.hide(themesFragment);
        transaction.hide(questionFragment);
        transaction.show(fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(themesFragment.isVisible())
            showFragment(subjectsListFragment);
        if(questionFragment.isVisible())
            showFragment(themesFragment);
    }

    @Override
    public void changeSubject() {
        themesFragment.refreshThemesList();
        showFragment(themesFragment);
    }

    @Override
    public void changeTheme() {
        questionFragment.RefreshQuestion();
        showFragment(questionFragment);
    }

    @Override
    public void changeQuestion() {
        questionFragment.RefreshQuestion();
        //questionFragment.EnabledButtons(DataPresenter.getInstance().getCurrentQuestion());
        showFragment(questionFragment);
    }

    private void readFileToJSon(){
        AssetManager am = getAssets();
        InputStream is = null;
        byte[] buffer = null;
        try {
            is = am.open("Content.txt");
            int size = is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String str_data = new String(buffer);
        dataPresenter.setContent(str_data);
    }
}

