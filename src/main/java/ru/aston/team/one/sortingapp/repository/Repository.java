package ru.aston.team.one.sortingapp.repository;



import ru.aston.team.one.sortingapp.model.Student;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    private List<Student> collection;

    public Repository() {
        this.collection = new ArrayList<>();
    }


    public List<Student> getCollection() {
        return new ArrayList<>(this.collection);
    }


    public void setCollection(List<Student> collection) {
        this.collection = collection;
    }


    public void addAll(List<Student> students) {
        students.forEach(this.collection::add);
    }


    public void add(Student student) {
        this.collection.add(student);
    }


    public int size() {
        return this.collection.size();
    }
}