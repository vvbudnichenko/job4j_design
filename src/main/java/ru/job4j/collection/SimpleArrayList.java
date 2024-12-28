package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        container = (T[]) new Object[capacity];
    }

    private T[] grow() {
        int newCapacity = container.length == 0 ? 1 : container.length * 2;
        return Arrays.copyOf(container, newCapacity);
    }

    @Override
    public void add(T value) {
        modCount++;
        if (size == container.length) {
            container = grow();
        }
        container[size] = value;
        size += 1;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T oldValue = container[index];
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removedElement = container[index];
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        size--;
        container[size] = null;
        modCount++;
        return removedElement;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int cursor;
            int lastRet = -1;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor != size;
            }

            @Override
            public T next() {
                int i = cursor;
                if (i >= size) {
                    throw new NoSuchElementException();
                }
                Object[] elementData = container;
                if (i >= elementData.length || modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                cursor = i + 1;
                lastRet = i;
                return (T) elementData[lastRet];
            }
        };
    }
}