package repository;


import models.Lecture;

public class GenericArray<E> {
    private int lastIndex = -1;

    private Object[] elements = new Object[0];

    public int size() {
        return lastIndex + 1;
    }

    public boolean isEmpty() {
        return lastIndex == -1;
    }

    public E get(int index) {
        if (index > lastIndex || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return (E) elements[index];
    }

    private void resizeIfNeeded() {
        if (lastIndex >= elements.length) {
            Object[] newElements = new Object[3 * elements.length / 2 + 1];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            this.elements = newElements;
        }
    }

    public void add(E element) {
        lastIndex++;
        resizeIfNeeded();
        elements[lastIndex] = element;
    }

    public void add(int index, E element) {
        if (index > lastIndex + 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        lastIndex++;
        resizeIfNeeded();

        for (int i = lastIndex; i >= index + 1; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = element;
    }

    public void remove(int index) {
        if (index > lastIndex || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        for (int i = index; i < lastIndex; i++) {
            elements[i] = elements[i + 1];
        }

        lastIndex--;
    }
}
