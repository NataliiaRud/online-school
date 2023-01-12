package repository;

import models.Base;


public class GenericRepository<E extends Base> {
    E[] tests;
    public GenericRepository(E[] tests) {
        this.tests = tests;
    }

    public int size() {
        return this.tests.length;
    }
//    boolean isEmpty()
//    E get (int index)
//    void add(E element)
//    void add(int index, E element)
//    void remove (int index)
}
