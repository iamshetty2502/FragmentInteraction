package com.iamshetty.fragmentinteraction.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.iamshetty.fragmentinteraction.POJO.Subjects;
import com.iamshetty.fragmentinteraction.R;

import java.util.ArrayList;

public class SubjectAdapter  extends RecyclerView.Adapter<SubjectAdapter.MyViewHolder> {
    private ArrayList<Subjects>  myDataset;
    MyViewHolder dataHolder;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public View semesterview;
        public CheckBox subject;
        public MyViewHolder(View v) {
            super(v);
            semesterview = v;
            subject=v.findViewById(R.id.subject_checkbox);
        }
    }

    public  SubjectAdapter(ArrayList<Subjects> myDataset) {
        this.myDataset = myDataset;
    }

    @NonNull
    @Override
    public SubjectAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subject_view, parent, false);
        CheckBox subjectName= v.findViewById(R.id.subject_checkbox);
        SubjectAdapter.MyViewHolder vh = new SubjectAdapter.MyViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.subject.setText(myDataset.get(position).getSubjectName());
        if (myDataset.get(position).getState()) {
            myViewHolder.subject.setChecked(true);
        } else {
            myViewHolder.subject.setChecked(false);
        }

        dataHolder=myViewHolder;
    }

    @Override
    public int getItemCount() {
        return myDataset.size();
    }

    public Boolean getCheckValue() {
       return  dataHolder.subject.isChecked();
    }


}

