package service;

import java.util.List;

public interface Manager<T> {
    int findIndexByPhone(String phone);

    void add(T t) throws Exception;

    void update(String phone) throws Exception;

    void deleteByPhone(String phone);

    void print() throws Exception;

    List<T> readFile() throws Exception;

    void writeFile() throws Exception;
    T getBookPhone(String phone) throws Exception;
}
