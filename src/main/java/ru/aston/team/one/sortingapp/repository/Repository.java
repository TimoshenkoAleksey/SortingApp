package ru.aston.team.one.sortingapp.repository;

import ru.aston.team.one.sortingapp.model.Student;

import ru.aston.team.one.sortingapp.collection.CustomList;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    private CustomList<Student> collection;

    public Repository() {
        this.collection = new CustomList<>();
    }


    public CustomList<Student> getCollection() {
        return new CustomList<>(this.collection);
    }

    public void setCollection(CustomList<Student> collection) {
        this.collection = collection;
    }

    public void addAll(List<Student> students) {
        if (students == null) {
            throw new IllegalArgumentException("Список студентов не может быть null");
        }
        students.stream().forEach(this.collection::add);
    }

    public int size() {
        return this.collection.size();
    }
}