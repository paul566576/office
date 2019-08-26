package aimprosoft.departments.command.department;

import aimprosoft.departments.command.Command;
import aimprosoft.departments.dao.DepartmentDAO;
import aimprosoft.departments.dao.impl.DepartmentDAOImpl;
import aimprosoft.departments.exeption.DAOException;
import aimprosoft.departments.model.Department;
import aimprosoft.departments.web.ActionType;
import aimprosoft.departments.web.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ViewAllDepartmentCommand implements Command {

    private static final Logger LOG = Logger.getLogger(CreateDepartmentCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ActionType actionType) throws IOException, ServletException, DAOException, SQLException {
        LOG.debug("Start executing Command");

        showAllDepartments(req, resp);

        LOG.debug("Finished executing Command");

        return Path.FORWARD_DEPARTMENT_LIST_PAGE;
    }

    private void showAllDepartments(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, DAOException, SQLException {
        HttpSession session = req.getSession();

        DepartmentDAO idd = new DepartmentDAOImpl();

        List<Department> departments = idd.getAll();

        session.setAttribute("departments", departments);

    }
}
