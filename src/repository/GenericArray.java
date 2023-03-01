package repository;

import utility.SimpleIterator;

import java.util.ArrayList;
import java.util.List;
public class GenericArray<E> {
    private int lastIndex = -1;

    private Object[] elements = new Object[0];

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
    public int size() {
        return getLastIndex() + 1;
    }

    public boolean isEmpty() {
        return getLastIndex() == -1;
    }

    public E get(int index) {
        if (index > getLastIndex() || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return (E) elements[index];
    }

    private void resizeIfNeeded() {
        if (getLastIndex() + 1 >= elements.length) {
            Object[] newElements = new Object[3 * elements.length / 2 + 1];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            this.elements = newElements;
        }
    }

    public void add(E element) {
        lastIndex++;
        resizeIfNeeded();
        elements[getLastIndex() + 1] = element;
    }

    public void add(int index, E element) {
        if (index > getLastIndex() + 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        lastIndex++;
        resizeIfNeeded();

        int lastIndex = getLastIndex();
        for (int i = lastIndex; i >= index + 1; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = element;
    }

    public void remove(int index) {
        if (index > getLastIndex() || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int lastIndex = getLastIndex();
        for (int i = index; i < lastIndex; i++) {
            elements[i] = elements[i + 1];
        }

        elements[lastIndex] = null;
    }

    public List<E> findAll() {
        List<E> list = new ArrayList<>();

        SimpleIterator<E> simpleIterator = new SimpleIterator<>(elements);
        while (simpleIterator.hasNext()) {
            list.add(simpleIterator.next());
        }

        return list;
    }
}
