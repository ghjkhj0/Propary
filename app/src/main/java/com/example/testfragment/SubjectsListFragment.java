package com.example.testfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


public class SubjectsListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subjects_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.subjects_recycler_view);

        String[] data = getResources().getStringArray(R.array.subjects_list);
        SubjectsListAdapter subjectsListAdapter = new SubjectsListAdapter(DataPresenter.getInstance().getSubjects_list());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(subjectsListAdapter);
        return view;
    }

}