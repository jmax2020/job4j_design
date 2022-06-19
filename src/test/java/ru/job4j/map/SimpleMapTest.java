package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenAdd() {
        SimpleMap<Integer, String> mapa = new SimpleMap<>();
        assertTrue(mapa.put(1, "one"));
        mapa.put(2, "two");
        assertThat(mapa.getCount(), is(2));
        assertThat(mapa.get(2), is("two"));
        assertNull(mapa.get(5));
        mapa.put(3, "three");
        mapa.put(4, "four");
        mapa.put(5, "fife");
        mapa.put(6, "sex");
        mapa.put(7, "seven");
        assertThat(mapa.getCapacity(), is(16));
        assertThat(mapa.get(6), is("sex"));
    }

    @Test
    public void whenRemove() {
        SimpleMap<Integer, String> mapa = new SimpleMap<>();
        mapa.put(1, "one");
        mapa.put(2, "two");
        assertTrue(mapa.remove(2));
        assertNull(mapa.get(2));
    }

    @Test
    public void whenHasNext() {
        SimpleMap<Integer, String> mapa = new SimpleMap<>();
        mapa.put(1, "one");
        Iterator<Integer> it = mapa.iterator();
        assertTrue(it.hasNext());
        it.next();
        assertFalse(it.hasNext());
    }

    @Test
    public void whenHasNot() {
        SimpleMap<Integer, String> mapa = new SimpleMap<>();
        mapa.put(1, "one");
        mapa.remove(1);
        Iterator<Integer> it = mapa.iterator();
        assertFalse(it.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenConcurrentModificationException() {
        SimpleMap<Integer, String> mapa = new SimpleMap<>();
        mapa.put(1, "one");
        Iterator<Integer> it = mapa.iterator();
        mapa.put(2, "two");
        it.next();
    }

}