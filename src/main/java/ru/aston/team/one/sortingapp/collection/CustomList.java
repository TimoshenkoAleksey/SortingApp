package ru.aston.team.one.sortingapp.collection;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;

public class CustomList<E> extends AbstractList<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements = new Object[DEFAULT_CAPACITY];
    private int size;

    public CustomList() {
    }

    public CustomList(Collection<? extends E> c) {
        Object[] array = c.toArray();
        if (array.length == 0) {
            return;
        }
        elements = new Object[Math.max(array.length, DEFAULT_CAPACITY)];
        System.arraycopy(array, 0, elements, 0, array.length);
        size = array.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    @Override
    public boolean add(E e) {
        if (size == elements.length) {
            grow();
        }
        elements[size++] = e;
        modCount++;
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        checkIndex(index);
        E oldValue = (E) elements[index];
        elements[index] = element;
        return oldValue;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + " находится за пределами размеров коллекции");
        }
    }

    private void grow() {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        elements = Arrays.copyOf(elements, newCapacity);
    }
}
