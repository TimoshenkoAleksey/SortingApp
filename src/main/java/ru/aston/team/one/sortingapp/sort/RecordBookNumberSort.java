package ru.aston.team.one.sortingapp.sort;

import ru.aston.team.one.sortingapp.collection.CustomList;
import ru.aston.team.one.sortingapp.comparator.RecordBookNumberComparator;
import ru.aston.team.one.sortingapp.model.Student;

import java.util.Comparator;
import java.util.Objects;

public class RecordBookNumberSort implements Sort {
    private final Comparator<Student> comparator = new RecordBookNumberComparator();

    @Override
    public CustomList<Student> sort(CustomList<Student> list) {
        Objects.requireNonNull(list, "List must not be null");
        CustomList<Student> result = new CustomList<>(list);
        BookNumberSort(result);
        return result;
    }

    private void BookNumberSort(CustomList<Student> list) {
        for (int current = 1; current < list.size(); current++) {
            Student thisStudent = list.get(current);
            for (int comparing = current - 1; comparing >= 0 && comparator.compare(list.get(comparing), thisStudent) > 0; comparing--) {
                list.set(comparing + 1, list.get(comparing));
                list.set(comparing, thisStudent);
            }
        }
    }
}
