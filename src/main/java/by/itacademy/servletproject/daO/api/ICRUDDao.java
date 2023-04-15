package by.itacademy.servletproject.daO.api;

import java.util.List;

public interface ICRUDDao<T>{

    List<T> get();

    T get(int id);

    T save(T item);

}
