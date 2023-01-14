package repository;


public interface BaseRepository<T> {
    public abstract void add(T t);
    public abstract void add(int id, T t);
    public abstract T getById(int id);
    public abstract T[] getAll();
    public abstract void deleteById(int id);
}