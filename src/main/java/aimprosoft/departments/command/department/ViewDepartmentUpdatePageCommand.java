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

public class ViewDepartmentUpdatePageCommand implements Command {

    private static final Logger LOG = Logger.getLogger(CreateDepartmentCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ActionType actionType) throws IOException, ServletException, DAOException, SQLException {

        LOG.debug("Start executing Command");

        HttpSession session = req.getSession();

        DepartmentDAO idd = new DepartmentDAOImpl();

        String id = req.getParameter("id");

        Department dep = (Department) idd.getByID(Integer.parseInt(id));

        req.setAttribute("dep", dep);

        if (id != null && id != "") {
            session.setAttribute("id", id);
        }

        LOG.debug("Finished executing Command");

        return Path.FORWARD_DEPARTMENT_UPDATE_PAGE;
    }
}
