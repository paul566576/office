package aimprosoft.departments.command.department;

import aimprosoft.departments.command.Command;

import aimprosoft.departments.command.validation.ValidatedepNameWhenUpdateCommand;
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

public class UpdateDepartmentCommand implements Command {

    private static final Logger LOG = Logger.getLogger(CreateDepartmentCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ActionType actionType) throws IOException, ServletException, DAOException, SQLException {

        LOG.debug("Start executing Command");

        HttpSession session = req.getSession();

        String result = updateDepartment(req, resp, session, actionType);

        LOG.debug("Finished executing Command");

        return result;
    }

    private String updateDepartment(HttpServletRequest req, HttpServletResponse resp, HttpSession session, ActionType actionType) throws ServletException, IOException, DAOException, SQLException {

        DepartmentDAO idd = new DepartmentDAOImpl();

        Department dep = (Department) idd.getByID(Integer.parseInt((String) session.getAttribute("id")));

        dep.setId(Integer.parseInt(req.getParameter("id")));

        dep.setName(req.getParameter("newName"));

        session.setAttribute("department", dep.getName());

        if (idd.isValueExist(dep.getName())) {
            Command command = new ValidatedepNameWhenUpdateCommand();
            return command.execute(req, resp, actionType);
        } else {
            idd.update(dep);

            ViewAllDepartmentCommand vadc = new ViewAllDepartmentCommand();

            return vadc.execute(req, resp, actionType);
        }
    }
}
