package aimprosoft.departments.command.employer;

import aimprosoft.departments.command.Command;
import aimprosoft.departments.command.department.CreateDepartmentCommand;
import aimprosoft.departments.command.validation.ValidateDepartmentNameFromEmployerCommand;
import aimprosoft.departments.command.validation.ValidateEmailWhenUpdateEmployerCommand;
import aimprosoft.departments.dao.DepartmentDAO;
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
import java.util.List;

public class UpdateEmployerCommand implements Command {

    private static final Logger LOG = Logger.getLogger(CreateDepartmentCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ActionType actionType) throws IOException, ServletException, DAOException, SQLException {
        LOG.debug("Start executing Command");

        HttpSession session = req.getSession();

        session.setAttribute("currentEmployerId", req.getParameter("id"));

        String id = req.getParameter("id");
        if (id != null && id != "") {
            session.setAttribute("id", id);
        }

        String result = updateEmployer(req, resp, session, actionType);

        LOG.debug("Finished executing Command");

        return result;
    }

    private String updateEmployer(HttpServletRequest req, HttpServletResponse resp, HttpSession session, ActionType actionType) throws ServletException, IOException, DAOException, SQLException {

        EmployerDAO ied = new EmployerDAOImpl();
        Employer currentEmployer = (Employer) ied.getByID(Integer.parseInt((String) session.getAttribute("currentEmployerId")));

        Employer employer = new Employer();
        DepartmentDAO idd = new DepartmentDAOImpl();

        Department dep = (Department) idd.getDepartmentByName(req.getParameter("DepName"));
        Department curDep = (Department) idd.getDepartmentByName(session.getAttribute("currentDepartment") + "");

        employer.setId(Integer.parseInt(req.getParameter("id")));
        employer.setName(req.getParameter("name"));
        employer.setDepId(dep.getId());
        employer.setEmail(req.getParameter("email"));
        employer.setEmployedAt(ied.dateFormatter(req.getParameter("date")));

        session.setAttribute("employerEmail", employer.getEmail());
        session.setAttribute("employer", employer);

        if (!idd.isValueExist(dep.getName())) {
            Command command = new ValidateDepartmentNameFromEmployerCommand();
            return command.execute(req, resp, actionType);
        }
        if (ied.isValueExist(employer.getEmail())) {
            Employer empl = (Employer) ied.getEmployerByEmail(employer.getEmail());
            req.setAttribute("curEmpl", empl);

            if (ied.isEmployerExist(empl) && empl.equals(currentEmployer)) {
                ied.update(employer);
                session.setAttribute("id", curDep.getId());
                showListEmployers(req, session);
                return Path.FORWARD_EMPLOYER_LIST_PAGE;
            }

            Command command = new ValidateEmailWhenUpdateEmployerCommand();
            return command.execute(req, resp, actionType);

        } else {
            ied.update(employer);
            session.setAttribute("id", curDep.getId());
            showListEmployers(req, session);
            return Path.FORWARD_EMPLOYER_LIST_PAGE;
        }
    }

    private void showListEmployers(HttpServletRequest req, HttpSession session) throws DAOException, SQLException {

        EmployerDAO ied = new EmployerDAOImpl();

        DepartmentDAO idd = new DepartmentDAOImpl();

        int departmentId = Integer.parseInt(session.getAttribute("id") + "");

        List<Employer> employers = ied.getAllEmployersFromDepartment(departmentId);

        String depName = ((Department) idd.getByID(departmentId)).getName();

        session.setAttribute("depId", departmentId);

        req.setAttribute("depName", depName);

        req.setAttribute("employers", employers);
    }
}
