package ru.job4j.collection;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleStackTest {

    @Test
    public void whenPushThenPoll() {
       SimpleStack<Integer> stack = new SimpleStack<>();
       stack.push(1);
       assertThat(stack.pop(), is(1));
    }

    @Test
    public void whenPushPollThenPushPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.pop();
        stack.push(2);
        assertThat(stack.pop(), is(2));
    }

    @Test
    public void whenPushPushThenPollPoll() {
        SimpleStack<Integer> st = new SimpleStack<>();
        st.push(1);
        st.push(2);
        st.pop();
        assertThat(st.pop(), is(1));
    }
}