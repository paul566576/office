package aimprosoft.departments.dao;

import aimprosoft.departments.exeption.DAOException;

import java.sql.Date;
import java.util.List;

public interface EmployerDAO<T> extends GenericDAO<T> {

    List<T> getAllEmployersFromDepartment(final int dep_id) throws DAOException;

    Date dateFormatter(String date) throws DAOException;;

    T getEmployerByEmail(String email) throws DAOException;;

    boolean isEmployerExist(T employer) throws DAOException;
}
