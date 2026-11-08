package ru.aston.team.one.sortingapp.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.team.one.sortingapp.collection.CustomList;
import ru.aston.team.one.sortingapp.model.Student;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RepositoryTest {

    private Repository repository;

    @BeforeEach
    void setUp() {
        repository = new Repository();
    }

    @Test
    void testAddAllAndSize() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, 4.5, 1001));
        students.add(new Student(1, 4.0, 1002));
        students.add(new Student(1, 3.5, 1003));

        repository.addAll(students);

        assertEquals(3, repository.size());
    }

    @Test
    void testDefensiveCopying() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(2, 4.5, 2001));
        students.add(new Student(2, 4.0, 2002));

        repository.addAll(students);

        CustomList<Student> returnedCollection =
                repository.getCollection();

        returnedCollection.add(new Student(2, 3.5, 2003));

        assertEquals(2, repository.size());
    }

    @Test
    void testSetCollectionNull() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.setCollection(null));
    }

    @Test
    void testAddAllNull() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.addAll(null));
    }
}