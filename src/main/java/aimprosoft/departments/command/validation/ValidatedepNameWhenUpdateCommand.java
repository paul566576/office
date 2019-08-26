package aimprosoft.departments.command.validation;

import aimprosoft.departments.command.Command;
import aimprosoft.departments.command.department.CreateDepartmentCommand;
import aimprosoft.departments.dao.DepartmentDAO;
import aimprosoft.departments.dao.impl.DepartmentDAOImpl;
import aimprosoft.departments.exeption.DAOException;
import aimprosoft.departments.model.Department;
import aimprosoft.departments.web.ActionType;
import aimprosoft.departments.web.Constants;
import aimprosoft.departments.web.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class ValidatedepNameWhenUpdateCommand implements Command {

    private static final Logger LOG = Logger.getLogger(CreateDepartmentCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ActionType actionType) throws IOException, ServletException, DAOException, SQLException {
        LOG.debug("Start executing Command");

        DepartmentDAO dao = new DepartmentDAOImpl();

        Department dep = new Department();

        HttpSession session = req.getSession();

        dep.setName((String) session.getAttribute("department"));

        req.setAttribute("isNameExist", true);
        req.setAttribute("emailMessage", Constants.DEPARTMENT_EXIST_ERROR);
        req.setAttribute("newName", dep.getName());
        dep = (Department) dao.getDepartmentByName(req.getParameter("newName"));
        req.setAttribute("id", dep.getId());

        return Path.FORWARD_DEPARTMENT_UPDATE_PAGE;
    }
}
