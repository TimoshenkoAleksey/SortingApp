package ru.aston.team.one.sortingapp.comparator;

import ru.aston.team.one.sortingapp.model.Student;
import java.util.Comparator;

public class AverageScoreComparator implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
        return Double.compare(s1.averageNumber(), s2.averageNumber());
    }
}