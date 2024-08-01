package uz.pdp.maven.repository;

import uz.pdp.maven.model.BaseModel;

import java.util.List;

public interface BaseRepository<T extends BaseModel> {

    T get(String id);

    boolean save(T t);

    boolean delete(String id);

    boolean update(T t);

    List<T> getAll();

}

