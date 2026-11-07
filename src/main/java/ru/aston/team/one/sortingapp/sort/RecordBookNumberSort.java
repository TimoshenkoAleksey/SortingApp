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
        Objects.requireNonNull(list, "Список не может быть null");
        CustomList<Student> result = new CustomList<>(list);
        bookNumberSort(result);
        return result;
    }

    private void bookNumberSort(CustomList<Student> list) {
        for (int i = 1; i < list.size(); i++) {
            Student current = list.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(list.get(j), current) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, current);
        }
    }
}
