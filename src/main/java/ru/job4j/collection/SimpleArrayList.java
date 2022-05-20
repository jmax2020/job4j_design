package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size = 0;
    private int modCount = 0;
    private int index = 0;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            container = Arrays.copyOf(container, 2 * size);
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, container.length);
        T oldValue = container[index];
        container[index] = newValue;
        modCount++;
        return oldValue;
    }

    @Override
    public T get(int index) {
        return container[index];
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, container.length);
        T element = container[index];
        System.arraycopy(container, index + 1, container, index, size-- - index - 1);
        modCount++;
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        index = 0;
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }
}
