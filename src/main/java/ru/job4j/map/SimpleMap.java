package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;

    private int count = 0;
    private int vodCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    public int getCount() {
        return count;
    }

    @Override
    public boolean put(K key, V value) {
        int index = indexFor(hash(key.hashCode()));
        boolean rsl = false;
        if (table[index] == null) {
            if (count >= LOAD_FACTOR * capacity) {
                expand();
            }
            table[index] = new MapEntry<>(key, value);
            count++;
            vodCount++;
            rsl = true;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] tmpTable =  new MapEntry[capacity];
        for (MapEntry<K, V> el : table) {
            if (el != null) {
                int index = indexFor(hash(el.key.hashCode()));
                tmpTable[index] = el;
            }
            table = tmpTable;
        }
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(key.hashCode()));
        Objects.checkIndex(index, capacity);
        return table[index] == null ? null : table[index] .value;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = indexFor(hash(key.hashCode()));
        Objects.checkIndex(index, capacity);
        if (table[index] != null) {
            table[index] = null;
            rsl = true;
            count--;
            vodCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        int expModCount = vodCount;
        return new Iterator<>() {
            int mark = 0;
            int i = 0;
            @Override
            public boolean hasNext() {
                if (expModCount != vodCount) {
                    throw new ConcurrentModificationException();
                }
                return mark < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                for (int index = i; index < table.length; index++) {
                    if (table[index] != null) {
                        i = index;
                        mark++;
                    }
                }
                return table[mark++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
