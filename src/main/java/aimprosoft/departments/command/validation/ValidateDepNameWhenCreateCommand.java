package aimprosoft.departments.command.validation;

import aimprosoft.departments.command.Command;
import aimprosoft.departments.command.department.CreateDepartmentCommand;
import aimprosoft.departments.exeption.DAOException;
import aimprosoft.departments.model.Department;
import aimprosoft.departments.web.ActionType;
import aimprosoft.departments.web.Constants;
import aimprosoft.departments.web.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ValidateDepNameWhenCreateCommand implements Command {
    private static final Logger LOG = Logger.getLogger(CreateDepartmentCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ActionType actionType) throws IOException, ServletException, DAOException, SQLException {
        LOG.debug("Start executing Command");

        Department dep = new Department();

        dep.setName(req.getParameter("name"));

        req.setAttribute("isNameExist", false);
        req.setAttribute("emailMessage", Constants.DEPARTMENT_EXIST_ERROR);
        req.setAttribute("newName", dep.getName());

        LOG.debug("Finished executing Command");

        return Path.FORWARD_DEPARTMENT_INSERT_PAGE;
    }
}
