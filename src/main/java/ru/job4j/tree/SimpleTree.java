package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        if (findBy(child).isEmpty()) {
            Optional<Node<E>> par = findBy(parent);
            if (par.isPresent()) {
                par.get().children.add(new Node<>(child));
                rsl = true;
            }
        }
        return rsl;
    }

    public boolean isBinary() {
        Predicate<Node<E>> predicate = a -> a.children.size() > 2;
        return findByPredicate(predicate).isEmpty();
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> predicate = a -> a.value.equals(value);
        return findByPredicate(predicate);
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}

