package DAO;

import java.util.List;

public interface DataAccessObject<T> {
    void insert(T genericObject);
    List<T> selectAll();
    void delete(T genericObject);
    T selectById(String iD);
    void update(T genericObject);
}
