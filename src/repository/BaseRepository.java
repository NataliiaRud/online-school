package repository;


public interface BaseRepository<T> {
    public Integer getSize();
    public boolean isEmpty();
    public T getByIndex(Integer indexToGet);
     void add(T t);
     void add(Integer id, T t);
     T getById(Integer id);
     T[] getAll();
    void deleteById(Integer id);
}