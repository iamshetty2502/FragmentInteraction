package com.iamshetty.fragmentinteraction.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iamshetty.fragmentinteraction.POJO.Semesters;
import com.iamshetty.fragmentinteraction.POJO.Subjects;
import com.iamshetty.fragmentinteraction.R;

import java.util.ArrayList;

public class SemesterAdapter extends RecyclerView.Adapter<SemesterAdapter.MyViewHolder> {
    private ArrayList<Semesters> myDataset;
    private int subjectCount;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public View semesterview;
        public TextView sem_name;
        public TextView sub_count;

        public MyViewHolder(View v) {
            super(v);
            semesterview = v;
            sem_name = v.findViewById(R.id.semester_name);
            sub_count = v.findViewById(R.id.subject_count);
        }
    }

    public SemesterAdapter(ArrayList<Semesters> myDataset) {
        this.myDataset = myDataset;
    }

    @Override
    public SemesterAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.semester_view, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.sem_name.setText(myDataset.get(position).getSemesterName());
        Subjects[] temp_sub = myDataset.get(position).getSubjects();
        subjectCount = 0;
        for (int i = 0; i < temp_sub.length; i++) {
            if (temp_sub[i].getState()) {
                subjectCount++;
            }
        }
        myDataset.get(position).setCount(subjectCount);
        subjectCount = myDataset.get(position).getCount();
        if (subjectCount > 0) {
            holder.sub_count.setText(subjectCount + "");
        } else {
            holder.sub_count.setText(" ");
        }

    }

    @Override
    public int getItemCount() {
        return myDataset.size();
    }
}