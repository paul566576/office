package aimprosoft.departments.dao.impl;

import aimprosoft.departments.dao.EmployerDAO;
import aimprosoft.departments.exeption.DAOException;
import aimprosoft.departments.exeption.Messages;
import aimprosoft.departments.model.Employer;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EmployerDAOImpl implements EmployerDAO<Employer> {

    private static final Logger LOG = Logger.getLogger(EmployerDAOImpl.class);

    private static final String SQL_DELETE_EMPLOYER = "DELETE FROM employers WHERE id=?";
    private static final String SQL_UPDATE_EMPLOYER = "UPDATE employers SET name=?, dep_id=?, email=?, employed_at=? WHERE id = ?";
    private static final String SQL_INSERT_EMPLOYER = "INSERT INTO employers (name, dep_id, email, employed_at) VALUES (?, ?, ?, ?)";
    private static final String SQL_SHOW_ALL_EMPLOYERS_FROM_DEPARTMENT = "SELECT * FROM employers WHERE dep_id = ?";
    private static final String SQL_GET_EMPLOYER_BY_ID = "SELECT * FROM employers WHERE id = ?";
    private static final String SQL_GET_EMPLOYER_BY_EMAIL = "SELECT * FROM employers WHERE email = ?";
    private static final String SQL_SHOW_ALL_EMPLOYERS = "SELECT * FROM employers";

    @Override
    public List<Employer> getAll() throws DAOException {
        List<Employer> employerList = new ArrayList<>();

        try (Connection conn = DAOMysqlConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_SHOW_ALL_EMPLOYERS)) {

            while (rs.next()) {
                Employer employer = new Employer();
                employer.setId(rs.getInt("id"));
                employer.setName(rs.getString("name"));
                employer.setDepId(rs.getInt("dep_id"));
                employer.setEmail(rs.getString("email"));
                employer.setEmployedAt(rs.getDate("employed_at"));
                employerList.add(employer);
            }
        } catch (SQLException | DAOException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_EMPLOYERS_LIST, e);
            throw new DAOException(Messages.ERR_CANNOT_OBTAIN_EMPLOYERS_LIST, e);
        }
        return employerList;
    }

    public void update(Employer empl) throws DAOException {
        try (Connection conn = DAOMysqlConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_EMPLOYER)) {
            int k = 1;
            pstmt.setString(k++, empl.getName());
            pstmt.setInt(k++, empl.getDepId());
            pstmt.setString(k++, empl.getEmail());
            pstmt.setDate(k++, (Date) empl.getEmployedAt());
            pstmt.setInt(k, empl.getId());
            pstmt.executeUpdate();
        } catch (SQLException | DAOException e) {
            LOG.error(Messages.ERR_CANNOT_UPDATE_EMPLOYER, e);
            throw new DAOException(Messages.ERR_CANNOT_UPDATE_EMPLOYER, e);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        try (Connection conn = DAOMysqlConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_DELETE_EMPLOYER);) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException | DAOException e) {
            LOG.error(Messages.ERR_CANNOT_DELETE_EMPLOYER, e);
            throw new DAOException(Messages.ERR_CANNOT_DELETE_EMPLOYER, e);
        }
    }

    @Override
    public void create(Employer empl) throws DAOException {
        try (Connection conn = DAOMysqlConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT_EMPLOYER)) {
            int k = 1;
            pstmt.setString(k++, empl.getName());
            pstmt.setInt(k++, empl.getDepId());
            pstmt.setString(k++, empl.getEmail());
            pstmt.setDate(k++, (Date) empl.getEmployedAt());
            pstmt.executeUpdate();
        } catch (SQLException | DAOException e) {
            LOG.error(Messages.ERR_CANNOT_CREATE_EMPLOYER, e);
            throw new DAOException(Messages.ERR_CANNOT_CREATE_EMPLOYER, e);
        }
    }

    @Override
    public List<Employer> getAllEmployersFromDepartment(final int dep_id) throws DAOException {
        List<Employer> employerList = new ArrayList<>();
        try (Connection conn = DAOMysqlConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_SHOW_ALL_EMPLOYERS_FROM_DEPARTMENT)) {
            stmt.setInt(1, dep_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Employer empl = new Employer();
                empl.setId(rs.getInt("id"));
                empl.setName(rs.getString("name"));
                empl.setDepId(rs.getInt("dep_id"));
                empl.setEmail(rs.getString("email"));
                empl.setEmployedAt(rs.getDate("employed_at"));
                employerList.add(empl);
            }
        } catch (SQLException | DAOException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_EMPLOYER_BY_DEPARTMENT_ID, e);
            throw new DAOException(Messages.ERR_CANNOT_OBTAIN_EMPLOYER_BY_DEPARTMENT_ID, e);
        }
        return employerList;
    }

    @Override
    public Employer getByID(int id) throws DAOException {
        Employer empl = new Employer();
        try (Connection conn = DAOMysqlConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_GET_EMPLOYER_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                empl.setId(rs.getInt(1));
                empl.setName(rs.getString(2));
                empl.setDepId(rs.getInt(3));
                empl.setEmail(rs.getString(4));
                empl.setEmployedAt(rs.getDate(5));
            }
        } catch (SQLException | DAOException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_EMPLOYER_BY_ID, e);
            throw new DAOException(Messages.ERR_CANNOT_OBTAIN_EMPLOYER_BY_ID, e);
        }
        return empl;
    }

    @Override
    public boolean isValueExist(String email) throws DAOException {
        if (email != null && email != "") {
            List<Employer> employersList = getAll();
            for (Employer d : employersList) {
                if (d.getEmail().equals(email)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Date dateFormatter(String date) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date newDate = null;
        try {
            Date d = Date.valueOf(date);
            String dateFormat = formatter.format(d);
            newDate = Date.valueOf(dateFormat);
        } catch (Exception e) {
            LOG.error(Messages.ERR_CANNOT_FORMAT_DATE, e);
        }
        return newDate;
    }

    @Override
    public Employer getEmployerByEmail(String email) {
        Employer employer = new Employer();
        try (Connection conn = DAOMysqlConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_GET_EMPLOYER_BY_EMAIL)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int k = 1;
                employer.setId(rs.getInt(k++));
                employer.setName(rs.getString(k++));
                employer.setDepId(rs.getInt(k++));
                employer.setEmail(rs.getString(k++));
                employer.setEmployedAt(rs.getDate(k));
            }
        } catch (SQLException | DAOException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_Employer_BY_EMAIL, e);
        }
        return employer;
    }

    @Override
    public boolean isEmployerExist(Employer employer) throws DAOException {
        List<Employer> employersList = getAll();
        for (Employer e : employersList) {
            if (e.equals(employer)) return true;
        }
        return false;
    }

    public static void main(String[] args) throws SQLException, DAOException {
        EmployerDAO dao = new EmployerDAOImpl();
        Employer employer = (Employer) dao.getEmployerByEmail("22@gmail.com");
        System.out.println(employer);

        System.out.println(dao.isEmployerExist(employer));
    }
}


