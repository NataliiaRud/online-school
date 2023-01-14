package repository;

import models.Base;


public class GenericRepository<E extends Base> {
    E[] elements;

    public GenericRepository(E[] elements) {
        this.elements = elements;
    }
}


