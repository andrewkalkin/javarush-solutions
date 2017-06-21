package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        for (Student st: students) if (st.getAverageGrade() == averageGrade) return st;
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:

        double max = 0;

        Student maxStudent = students.get(0);

        for (Student st: students) if (st.getAverageGrade() > max) {
            max = st.getAverageGrade();
            maxStudent = st;
        }

        return maxStudent;
    }

    public Student getStudentWithMinAverageGrade() {
        //TODO:

        double min = Double.MAX_VALUE;

        Student minStudent = students.get(0);

        for (Student st: students) if (st.getAverageGrade() < min) {
            min = st.getAverageGrade();
            minStudent = st;
        }

        return minStudent;

    }

    public void expel(Student student){
        students.remove(student);
    }
}