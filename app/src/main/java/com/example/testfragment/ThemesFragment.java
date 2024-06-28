package com.example.testfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ThemesFragment extends Fragment {
    ThemesListAdapter themesListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_themes, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.themes_recycler_view);

        System.out.println("ThemesListAdapter = " + DataPresenter.getInstance().getThemesList());
        themesListAdapter = new ThemesListAdapter(DataPresenter.getInstance().getThemesList());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(themesListAdapter);
        return view;
    }

    public void refreshThemesList(){
        //System.out.println("refreshThemesList list = " + DataPresenter.getInstance().getThemesList()[0]);
        themesListAdapter.refreshThemesList(DataPresenter.getInstance().getThemesList());
        themesListAdapter.notifyDataSetChanged();
    }

}