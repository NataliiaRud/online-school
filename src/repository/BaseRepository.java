package repository;


import java.util.List;

public interface BaseRepository<T> {
    Integer getSize();

    boolean isEmpty();

    T getByIndex(Integer indexToGet);

    void add(T t);

    void add(Integer id, T t);

    T getById(Integer id);

    List<T> getAll();

    void deleteById(Integer id);
}