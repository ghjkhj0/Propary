package com.example.testfragment;

import android.icu.util.LocaleData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ThemesListAdapter extends RecyclerView.Adapter<ThemesListAdapter.ThemesListHolder> {
    private String[] localDataSet;
    public ThemesListAdapter(String[] data){
        localDataSet = data;
    }


    @NonNull
    @Override
    public ThemesListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.themes_list, parent, false);
        return new ThemesListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThemesListAdapter.ThemesListHolder holder, int position) {
        holder.getSubjectText().setText(localDataSet[position]);
    }

    @Override
    public int getItemCount() {
        if(localDataSet != null && localDataSet.length > 0)
            return localDataSet.length;
        else
            return 0;
    }

    public void refreshThemesList(String[] data){
        localDataSet = data;
    }

    public class ThemesListHolder extends RecyclerView.ViewHolder {
        private final TextView themesText;
        public TextView getSubjectText() {
            return themesText;
        }

        public ThemesListHolder(@NonNull View itemView) {
            super(itemView);
            themesText = itemView.findViewById(R.id.textContent);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("onClick = " + getAdapterPosition());
                    DataPresenter.getInstance().setCurrentTheme(getAdapterPosition());
                    DataPresenter.getInstance().setCurrentQuestion(0);
                }
            });
        }
    }
}
