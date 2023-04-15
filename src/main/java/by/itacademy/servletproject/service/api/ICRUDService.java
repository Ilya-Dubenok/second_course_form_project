package by.itacademy.servletproject.service.api;

import java.util.List;

public interface ICRUDService<T,S> {

    List<T> get();

    T get(int id);

    T save(S item);


}
