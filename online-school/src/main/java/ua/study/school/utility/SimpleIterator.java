package ua.study.school.utility;


import java.util.Iterator;
import java.util.function.Consumer;

public class SimpleIterator<E> implements Iterator<E> {
    private int pointer;

    private Object[] elements;

    public SimpleIterator(Object[] elements) {
        this.pointer = -1;
        this.elements = elements;
    }

    private int getLastIndex() {
        int lastIndex = -1;
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null) {
                lastIndex = i;
            } else {
                break;
            }
        }

        return lastIndex;
    }

    @Override
    public void remove() {
        if (pointer > getLastIndex() || pointer < 0) {
            throw new IllegalStateException();
        }

        int lastIndex = getLastIndex();
        for (int i = pointer; i < lastIndex; i++) {
            elements[i] = elements[i + 1];
        }

        pointer--;
        elements[lastIndex] = null;
    }

    @Override
    public void forEachRemaining(Consumer action) {
        // not implemented
    }

    @Override
    public boolean hasNext() {
        if (pointer + 1 >= elements.length) {
            return false;
        }

        return elements[pointer + 1] != null;
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new IllegalStateException();
        }

        pointer++;

        return (E) elements[pointer];
    }
}

