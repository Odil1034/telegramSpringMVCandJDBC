package uz.pdp.maven.service;

import uz.pdp.maven.model.BaseModel;

import java.util.List;

public interface BaseService<T extends BaseModel> {

    T get(String id);
    boolean save(T t);
    boolean delete(String id);
    boolean update(T t);
    List<T> getAll();

}
