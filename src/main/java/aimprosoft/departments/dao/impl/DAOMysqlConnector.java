package aimprosoft.departments.dao.impl;


import aimprosoft.departments.exeption.DAOException;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DAOMysqlConnector {

    private static final Logger LOG = Logger.getLogger(DepartmentDAOImpl.class);

    public static Connection getConnection() throws DAOException {

        Connection conn = null;
        try (FileInputStream fis = new FileInputStream(DAOMysqlConnector.class.getClassLoader().getResource("config.properties").getPath())){
            Properties property = new Properties();
            property.load(fis);

            String url = property.getProperty("url");
            String login = property.getProperty("login");
            String password = property.getProperty("pass");
            String driver = property.getProperty("driver");

            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, login, password);
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            LOG.error(e);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
