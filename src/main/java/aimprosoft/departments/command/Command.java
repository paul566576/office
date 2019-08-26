package aimprosoft.departments.command;

import aimprosoft.departments.exeption.DAOException;
import aimprosoft.departments.web.ActionType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public interface Command {

    String execute(HttpServletRequest request,HttpServletResponse response, ActionType actionType) throws IOException, ServletException, DAOException, SQLException;
}
