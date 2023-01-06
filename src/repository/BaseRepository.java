package repository;
import models.Base;
import models.Lecture;

public abstract class BaseRepository<T> {
    public abstract void add(T t);
    public abstract T getById(int id);
    public abstract T[] getAll();
    public abstract void deleteById(int id);
}