package aimprosoft.departments.command.employer;

import aimprosoft.departments.command.Command;
import aimprosoft.departments.command.department.CreateDepartmentCommand;
import aimprosoft.departments.dao.EmployerDAO;
import aimprosoft.departments.dao.impl.DepartmentDAOImpl;
import aimprosoft.departments.dao.impl.EmployerDAOImpl;
import aimprosoft.departments.exeption.DAOException;
import aimprosoft.departments.model.Department;
import aimprosoft.departments.model.Employer;
import aimprosoft.departments.web.ActionType;
import aimprosoft.departments.web.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class ViewEmployerUpdatePageCommand implements Command {

    private static final Logger LOG = Logger.getLogger(CreateDepartmentCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ActionType actionType) throws IOException, ServletException, DAOException, SQLException {
        LOG.debug("Start executing Command");

        EmployerDAO ied = new EmployerDAOImpl();

        DepartmentDAOImpl idd = new DepartmentDAOImpl();

        Employer empl = (Employer) ied.getByID(Integer.parseInt(req.getParameter("idEmployer")));

        Department dep = idd.getByID(empl.getDepId());

        Boolean isNameExist = true;

        req.setAttribute("isNameExist", isNameExist);

        req.setAttribute("empl", empl);

        req.setAttribute("depName", dep.getName());

        HttpSession session = req.getSession();
        session.setAttribute("currentDepartment", dep.getName());
        session.setAttribute("currentEmployer", empl);

        LOG.debug("Finished executing Command");

        return Path.FORWARD_EMPLOYER_UPDATE_PAGE;
    }
}
