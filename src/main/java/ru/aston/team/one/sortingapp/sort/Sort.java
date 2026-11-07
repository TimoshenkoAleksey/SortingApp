package ru.aston.team.one.sortingapp.sort;

import ru.aston.team.one.sortingapp.collection.CustomList;
import ru.aston.team.one.sortingapp.model.Student;

public interface Sort {
    CustomList<Student> sort(CustomList<Student> list);
}
