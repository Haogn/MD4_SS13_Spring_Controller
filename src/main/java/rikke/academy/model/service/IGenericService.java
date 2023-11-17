package rikke.academy.model.service;

import java.util.List;

public interface IGenericService<T, ID> {
    List<T> findAll();
    T findById(ID id);
    Boolean create(T t) ;
    Boolean delete(ID id) ;
    Boolean update(T t, ID id);
}
