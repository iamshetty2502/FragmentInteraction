package com.d2ktask.sujeet.d2ktask.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.d2ktask.sujeet.d2ktask.POJO.Semesters;
import com.d2ktask.sujeet.d2ktask.R;
import com.d2ktask.sujeet.d2ktask.adapters.SemesterAdapter;

import java.util.ArrayList;


public class SemesterFragment extends Fragment {

    private static final String TAG = SemesterFragment.class.getName() ;
    private RecyclerView semesterRecyclerView;
    private RecyclerView.Adapter semesterAdapter;
    private RecyclerView.LayoutManager semesterLayoutManager;

    private ArrayList<Semesters> semArraydata;


    private OnFragmentInteractionListener mListener;
    private semesterInterface myDataListener;



    public SemesterFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
     @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_semesters, container, false);

        semesterRecyclerView =  view .findViewById(R.id.semesters_recycler_view);
        semesterRecyclerView.setHasFixedSize(false);

        semesterLayoutManager = new LinearLayoutManager(getContext());
        semesterRecyclerView.setLayoutManager(semesterLayoutManager);
        semArraydata=new ArrayList<Semesters>();
        //String[] sem_data= getResources().getStringArray(R.array.Semester);

        semesterAdapter = new SemesterAdapter(semArraydata);
        semesterRecyclerView.setAdapter(semesterAdapter);
        semesterRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if (myDataListener != null && motionEvent.getAction()==MotionEvent.ACTION_DOWN ) {
                    if(recyclerView.getChildPosition(child)>=0)
                    {myDataListener.passDataToMain(recyclerView.getChildPosition(child));
                    }
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
        if (context instanceof OnFragmentInteractionListener && context instanceof  semesterInterface) {
            mListener = (OnFragmentInteractionListener) context;
            myDataListener = (semesterInterface) context;
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
    public interface semesterInterface {
        // TODO: Update argument type and name
        void passDataToMain(int postion);
    }

    public void setData(ArrayList<Semesters> semesterData){
        if(semArraydata.size()>0)
        {
            semArraydata.clear();
        }
        for (Semesters semdata:semesterData
             ) {

            semArraydata.add(semdata);
        }
        semesterAdapter.notifyDataSetChanged();
    }
    public void setCounter(){
        semesterAdapter.notifyDataSetChanged();
    }
}
