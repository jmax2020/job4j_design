package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size = 0;
    private int modCount = 0;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    private T[] grow(int newLength) {
    return Arrays.copyOf(container, newLength == 0 ? 1 : newLength);
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            container = grow(2 * size);
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = get(index);
        container[index] = newValue;
        modCount++;
        return oldValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public T remove(int index) {
        T element = get(index);
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        size--;
        container[container.length - 1] = null;
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
        return new Iterator<T>() {
            int index = 0;
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
