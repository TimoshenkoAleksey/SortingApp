package ru.aston.team.one.sortingapp.comparator;

import ru.aston.team.one.sortingapp.model.Student;

import java.util.Comparator;

public class RecordBookNumberComparator implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
        return Integer.compare(s1.recordBookNumber(), s2.recordBookNumber());
    }
}
