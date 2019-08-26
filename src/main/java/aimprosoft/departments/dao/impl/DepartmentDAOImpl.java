package aimprosoft.departments.dao.impl;

import aimprosoft.departments.dao.DepartmentDAO;
import aimprosoft.departments.exeption.DAOException;
import aimprosoft.departments.exeption.Messages;
import aimprosoft.departments.model.Department;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO<Department> {

    private static final Logger LOG = Logger.getLogger(DepartmentDAOImpl.class);

    private static final String SQL_SHOW_ALL_DEPARTMENTS = "SELECT * FROM departments";
    private static final String SQL_DELETE_DEPARTMENT = "DELETE FROM departments WHERE id = ?";
    private static final String SQL_UPDATE_DEPARTMENT = "UPDATE departments SET name = ? WHERE id = ?";
    private static final String SQL_INSERT_DEPARTMENT = "INSERT INTO departments (name) VALUES (?)";
    private static final String SQL_GET_DEPARTMENT_BY_ID = "SELECT * FROM departments WHERE id = ?";
    private static final String SQL_GET_DEPARTMENT_BY_NAME = "SELECT * FROM departments WHERE name = ?";

    @Override
    public List<Department> getAll() throws DAOException {
        List<Department> departmentList = new ArrayList<>();

        try (Connection conn = DAOMysqlConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_SHOW_ALL_DEPARTMENTS)) {
            while (rs.next()) {
                Department dep = new Department();
                dep.setId(rs.getInt("id"));
                dep.setName(rs.getString("name"));
                departmentList.add(dep);
            }
        } catch (SQLException | DAOException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_DEPARTMENT_LIST, e);
            throw new DAOException(Messages.ERR_CANNOT_OBTAIN_DEPARTMENT_LIST, e);
        }
        return departmentList;
    }

    @Override
    public void update(Department dep) throws DAOException {
        try (Connection conn = DAOMysqlConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_DEPARTMENT)) {
            pstmt.setString(1, dep.getName());
            pstmt.setInt(2, dep.getId());
            pstmt.executeUpdate();
        } catch (SQLException | DAOException e) {
            LOG.error(Messages.ERR_CANNOT_UPDATE_DEPARTMENT, e);
            throw new DAOException(Messages.ERR_CANNOT_UPDATE_DEPARTMENT, e);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        try (Connection conn = DAOMysqlConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_DEPARTMENT)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException | DAOException e) {
            LOG.error(Messages.ERR_CANNOT_DELETE_DEPARTMENT, e);
            throw new DAOException(Messages.ERR_CANNOT_DELETE_DEPARTMENT, e);

        }
    }

    @Override
    public void create(Department dep) throws DAOException {
        try (Connection conn = DAOMysqlConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT_DEPARTMENT)) {

            pstmt.setString(1, dep.getName());
            pstmt.executeUpdate();
        } catch (SQLException | DAOException e) {
            LOG.error(Messages.ERR_CANNOT_CREATE_DEPARTMENT, e);
            throw new DAOException(Messages.ERR_CANNOT_CREATE_DEPARTMENT, e);
        }
    }

    @Override
    public Department getByID(int id) throws DAOException {
        Department dep = new Department();
        try (Connection conn = DAOMysqlConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_GET_DEPARTMENT_BY_ID)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                dep.setId(rs.getInt(1));
                dep.setName(rs.getString(2));
            }
        } catch (SQLException | DAOException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_DEPARTMENT_BY_ID, e);
            throw new DAOException(Messages.ERR_CANNOT_OBTAIN_DEPARTMENT_BY_ID, e);
        }
        return dep;
    }

    @Override
    public boolean isValueExist(String name) throws DAOException {
        if (name != null && name != "") {
            List<Department> departmentsList = getAll();
            Department dep = getDepartmentByName(name);
            for (Department d : departmentsList) {
                if (d.getName().equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Department getDepartmentByName(final String name) throws DAOException {
        Department dep = new Department();
        try (Connection conn = DAOMysqlConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_GET_DEPARTMENT_BY_NAME)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                dep.setId(rs.getInt(1));
                dep.setName(rs.getString(2));
            }
        } catch (SQLException | DAOException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_DEPARTMENT_BY_NAME, e);
            throw new DAOException(Messages.ERR_CANNOT_OBTAIN_DEPARTMENT_BY_NAME, e);
        }
        return dep;
    }

    public static void main(String[] args) throws DAOException {
        DepartmentDAO dao = new DepartmentDAOImpl();

        dao.isValueExist("dep_1");
    }
}
