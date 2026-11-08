package ru.aston.team.one.sortingapp.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.team.one.sortingapp.model.Student;

import static org.junit.jupiter.api.Assertions.*;

class CustomListTest {

    private CustomList<Student> customList;

    @BeforeEach
    void setUp() {
        customList = new CustomList<>();
    }

    @Test
    void testAddIncreasesSize() {
        assertEquals(0, customList.size());

        customList.add(new Student(1, 4.5, 1001));
        assertEquals(1, customList.size());

        customList.add(new Student(1, 4.0, 1002));
        assertEquals(2, customList.size());
    }

    @Test
    void testGetReturnsCorrectElement() {
        Student student1 = new Student(1, 4.5, 1001);
        Student student2 = new Student(2, 4.0, 1002);
        Student student3 = new Student(3, 3.5, 1003);

        customList.add(student1);
        customList.add(student2);
        customList.add(student3);

        assertEquals(student1, customList.get(0));
        assertEquals(student2, customList.get(1));
        assertEquals(student3, customList.get(2));
    }

    @Test
    void testSetReplacesElementAndReturnsOld() {
        Student oldStudent = new Student(1, 4.5, 1001);
        Student newStudent = new Student(2, 5.0, 1002);

        customList.add(oldStudent);
        customList.add(new Student(1, 4.0, 1003));

        Student returnedOld = customList.set(0, newStudent);

        assertEquals(oldStudent, returnedOld);
        assertEquals(newStudent, customList.get(0));
        assertEquals(2, customList.size());
    }

    @Test
    void testGetThrowsIndexOutOfBoundsException() {
        customList.add(new Student(1, 4.5, 1001));
        customList.add(new Student(1, 4.0, 1002));

        assertThrows(IndexOutOfBoundsException.class, () -> customList.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> customList.get(2));
        assertThrows(IndexOutOfBoundsException.class, () -> customList.get(100));
    }

    @Test
    void testListExpandsBeyondDefaultCapacity() {
        for (int i = 1; i <= 15; i++) {
            customList.add(new Student(1, 4.5, 1000 + i));
        }

        assertEquals(15, customList.size());
        assertEquals(1001, customList.get(0).recordBookNumber());
        assertEquals(1015, customList.get(14).recordBookNumber());

        for (int i = 16; i <= 25; i++) {
            customList.add(new Student(2, 4.0, 1000 + i));
        }

        assertEquals(25, customList.size());
        assertEquals(1025, customList.get(24).recordBookNumber());
    }

    @Test
    void testNewListIsEmpty() {
        CustomList<Student> newList = new CustomList<>();

        assertTrue(newList.isEmpty());
        assertEquals(0, newList.size());
    }
}
