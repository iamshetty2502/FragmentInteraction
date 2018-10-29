package com.d2ktask.sujeet.d2ktask.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.d2ktask.sujeet.d2ktask.POJO.Subjects;
import com.d2ktask.sujeet.d2ktask.R;
import com.d2ktask.sujeet.d2ktask.adapters.SubjectAdapter;

import java.util.ArrayList;


public class SubjectFragment extends Fragment {


    private RecyclerView subjectRecyclerView;
    private SubjectAdapter subjectAdapter;
    private RecyclerView.LayoutManager subjectLayoutManager;
    private String[] sub_data;
    private ArrayList<Subjects> subArraydata;
    private OnFragmentInteractionListener mListener;

    private SetCountListener setCountListener;
    private String TAG = SubjectFragment.class.getSimpleName();
    private TextView tvInitialText;

    public SubjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subjects, container, false);

        subjectRecyclerView = view.findViewById(R.id.subjects_recycler_view);
        subjectRecyclerView.setHasFixedSize(false);
        tvInitialText = view.findViewById(R.id.initial_text_tv);

        subjectLayoutManager = new LinearLayoutManager(getContext());
        subjectRecyclerView.setLayoutManager(subjectLayoutManager);
        subArraydata = new ArrayList<Subjects>();

        subjectAdapter = new SubjectAdapter(subArraydata);
        subjectRecyclerView.setAdapter(subjectAdapter);
        subjectRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && recyclerView.getChildPosition(child) >= 0) {
                    if (subArraydata.get(recyclerView.getChildPosition(child)).getState()) {
                        subArraydata.get(recyclerView.getChildPosition(child)).setState(false);
                    } else {
                        subArraydata.get(recyclerView.getChildPosition(child)).setState(true);
                    }
                    setCountListener.setCount();
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener && context instanceof SetCountListener) {
            mListener = (OnFragmentInteractionListener) context;
            setCountListener = (SetCountListener) context;

        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public interface SetCountListener {
        // TODO: Update argument type and name
        void setCount();
    }

    public void setData(Subjects[] temp_data) {
        if (tvInitialText.getVisibility() == View.VISIBLE) {
            tvInitialText.setVisibility(View.GONE);
        }
        if (subjectAdapter != null) {
            if (subArraydata.size() > 0) {
                subArraydata.clear();
            }
            for (Subjects data : temp_data
                    ) {
                subArraydata.add(data);
            }
            subjectAdapter.notifyDataSetChanged();
        }
    }

}
