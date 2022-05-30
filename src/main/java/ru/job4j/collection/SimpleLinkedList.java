package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private int size = 0;
    private Node<E> first = null;
    private Node<E> last = null;
    private int modCount = 0;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    @Override
    public void add(E value) {
        Node<E> tmp = last;
        last = new Node<>(value, null);
        if (tmp == null) {
            first = last;
        } else {
            tmp.next = last;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rsl = first;
        for (int count = 0; count < index; count++) {
            rsl = rsl.next;
        }
        return rsl.item;
    }

    @Override
    public Iterator<E> iterator() {
        int expModCount = modCount;
        return new Iterator<E>() {
            Node<E> thisNode = null;
            @Override
            public boolean hasNext() {
                if (expModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return thisNode == null ? first != null : thisNode.next != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                thisNode = thisNode == null ? first : thisNode.next;
                return thisNode.item;
            }
        };
    }
}


