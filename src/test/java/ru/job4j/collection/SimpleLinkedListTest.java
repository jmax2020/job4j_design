package ru.job4j.collection;

import java.util.Iterator;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class SimpleLinkedListTest {
    @Test
    public void whenAddAndGet() {
        LinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        assertThat(list.get(0)).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(2);
    }

    @Test
    public void whenGetFromOutOfBoundThenExceptionThrown() {
    LinkedList<Integer> list = new SimpleLinkedList<>();
    list.add(1);
    list.add(2);
        assertThatThrownBy(() -> list.get(2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void whenAddIterHasNextTrue() {
        LinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext()).isEqualTo(true);
    }

    @Test
    public void whenAddIterNextOne() {
        LinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertThat(it.next()).isEqualTo(1);
    }

    @Test
    public void whenEmptyIterHashNextFalse() {
        LinkedList<Integer> list = new SimpleLinkedList<>();
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext()).isEqualTo(false);
    }

    @Test
    public void whenAddIterMultiHasNextTrue() {
        LinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext()).isEqualTo(true);
        assertThat(it.hasNext()).isEqualTo(true);
    }

    @Test
    public void whenAddIterNextOneNextTwo() {
        LinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> it = list.iterator();
        assertThat(it.next()).isEqualTo(1);
        assertThat(it.next()).isEqualTo(2);
    }

    @Test
    public void whenGetIteratorTwiceThenEveryFromBegin() {
        LinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> first = list.iterator();
        assertThat(first.hasNext()).isEqualTo(true);
        assertThat(first.next()).isEqualTo(1);
        assertThat(first.hasNext()).isEqualTo(true);
        assertThat(first.next()).isEqualTo(2);
        assertThat(first.hasNext()).isEqualTo(false);
        Iterator<Integer> second = list.iterator();
        assertThat(second.hasNext()).isEqualTo(true);
        assertThat(second.next()).isEqualTo(1);
        assertThat(second.hasNext()).isEqualTo(true);
        assertThat(second.next()).isEqualTo(2);
        assertThat(second.hasNext()).isEqualTo(false);
    }
}