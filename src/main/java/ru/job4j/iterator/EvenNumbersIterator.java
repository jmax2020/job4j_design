package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] data;
    private int index = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    public boolean hasEvenNumber() {
        while (index < data.length) {
            if (data[index] % 2 == 0) {
                return true;
            }
            index++;
        }
        return false;
    }

    @Override
    public boolean hasNext() {
        return hasEvenNumber();
    }

    @Override
    public Integer next() throws NoSuchElementException {
        if (!hasEvenNumber()) {
            throw new NoSuchElementException();
        }
        return  data[index++];
    }
}
