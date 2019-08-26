package aimprosoft.departments.dao;

import aimprosoft.departments.exeption.DAOException;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {

    void create(T object) throws SQLException, DAOException;

    void update(T object) throws SQLException, DAOException;

    void delete(int id) throws SQLException, DAOException;

    T getByID(int key) throws SQLException, DAOException;

    List<T> getAll() throws SQLException, DAOException;

    boolean isValueExist(String name) throws DAOException;

}
