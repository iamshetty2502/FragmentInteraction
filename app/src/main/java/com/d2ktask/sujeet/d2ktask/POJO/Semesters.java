package com.d2ktask.sujeet.d2ktask.POJO;

import javax.security.auth.Subject;

public class Semesters {

    String semesterName;
    int count;
    Subjects[] subjects;

    public Subjects[] getSubjects() {
        return subjects;
    }

    public void setSubjects(Subjects[] subjects) {
        this.subjects = subjects;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

}
