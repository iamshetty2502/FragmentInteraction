package com.d2ktask.sujeet.d2ktask;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.d2ktask.sujeet.d2ktask.POJO.Semesters;
import com.d2ktask.sujeet.d2ktask.POJO.Subjects;
import com.d2ktask.sujeet.d2ktask.fragments.SemesterFragment;
import com.d2ktask.sujeet.d2ktask.fragments.SubjectFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SemesterFragment.OnFragmentInteractionListener, SubjectFragment.OnFragmentInteractionListener, SemesterFragment.semesterInterface, SubjectFragment.SetCountListener {


    private   ArrayList<Semesters> myData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.semesters);
        if (myData == null || myData.isEmpty()) {
            myData = initArray();
        }

        ((SemesterFragment) fragment).setData(myData);
        //setData
    }

    //InterfaceImplementation
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void passDataToMain(int postion) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.subjects);
        ((SubjectFragment) fragment).setData(myData.get(postion).getSubjects());
    }

    @Override
    public void setCount() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.semesters);
        ((SemesterFragment) fragment).setCounter();
    }


    //methods

    public ArrayList<Semesters> initArray() {
        int semcounter;
        int subcounter;
        String[] semData = getResources().getStringArray(R.array.Semester);
        ArrayList<Semesters> semesterArrayList = new ArrayList<Semesters>();
        Semesters temp_sem;
        String[] subData;
        Subjects[] subjectList;

        for (semcounter = 0; semcounter < semData.length; semcounter++) {
            temp_sem = new Semesters();
            subData = getResources().getStringArray(getSubjectList(semcounter));

            subjectList = new Subjects[subData.length];

            for (subcounter = 0; subcounter < subData.length; subcounter++) {
                subjectList[subcounter] = new Subjects();
                subjectList[subcounter].setState(false);
                subjectList[subcounter].setSubjectName(subData[subcounter]);
            }

            temp_sem.setCount(0);
            temp_sem.setSemesterName(semData[semcounter]);
            temp_sem.setSubjects(subjectList);

            semesterArrayList.add(temp_sem);

        }
        return semesterArrayList;
    }

    public int getSubjectList(int i) {
        switch (i) {
            case 0:
                return R.array.Semester_1;
            case 1:
                return R.array.Semester_2;
            case 2:
                return R.array.Semester_3;
            case 3:
                return R.array.Semester_4;
            case 4:
                return R.array.Semester_5;
            case 5:
                return R.array.Semester_6;
            default:
                return R.array.Semester_1;
        }
    }
}



