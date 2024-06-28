package com.example.testfragment;

import android.icu.util.LocaleData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SubjectsListAdapter extends RecyclerView.Adapter<SubjectsListAdapter.SubjectsListHolder> {
    private String[] localDataSet;
    public SubjectsListAdapter(String[] data){
        localDataSet = data;
    }

    @NonNull
    @Override
    public SubjectsListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subjects_list, parent, false);
        return new SubjectsListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectsListAdapter.SubjectsListHolder holder, int position) {
        holder.getSubjectText().setText(localDataSet[position]);
    }

    @Override
    public int getItemCount() {
        return localDataSet.length;
    }

    public class SubjectsListHolder extends RecyclerView.ViewHolder {
        private final TextView subjectText;
        public TextView getSubjectText() {
            return subjectText;
        }

        public SubjectsListHolder(@NonNull View itemView) {
            super(itemView);
            subjectText = itemView.findViewById(R.id.textContent);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("onClick Subject= " + getAdapterPosition());
                    DataPresenter.getInstance().setCurrentSubject(getAdapterPosition());
                }
            });
        }
    }
}
