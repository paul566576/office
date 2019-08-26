package aimprosoft.departments.dao;

import aimprosoft.departments.exeption.DAOException;

public interface DepartmentDAO<T> extends GenericDAO<T> {

    T getDepartmentByName(String id) throws DAOException;
}
