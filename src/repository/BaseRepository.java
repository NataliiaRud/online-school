package repository;


public interface BaseRepository<T> {
     void add(T t);
     void add(Integer id, T t);
     T getById(Integer id);
     T[] getAll();
    void deleteById(Integer id);
}