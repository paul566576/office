package aimprosoft.departments.command.validation;

import aimprosoft.departments.command.Command;
import aimprosoft.departments.command.department.CreateDepartmentCommand;
import aimprosoft.departments.dao.EmployerDAO;
import aimprosoft.departments.dao.impl.EmployerDAOImpl;
import aimprosoft.departments.exeption.DAOException;
import aimprosoft.departments.model.Department;
import aimprosoft.departments.model.Employer;
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

public class ValidateEmailWhenUpdateEmployerCommand implements Command {

    private static final Logger LOG = Logger.getLogger(CreateDepartmentCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ActionType actionType) throws IOException, ServletException, DAOException, SQLException {
        LOG.debug("Start executing Command");

        HttpSession session = req.getSession();

        Department dep1 = new Department();
        EmployerDAO dao = new EmployerDAOImpl();

        Employer employer = (Employer) session.getAttribute("employer");

        dep1.setName(req.getParameter("DepName"));

        req.setAttribute("isNameExist", false);
        req.setAttribute("emailMessage", Constants.EMAIL_ERROR);

        req.setAttribute("newName", employer.getName());
        req.setAttribute("newDepName", dep1.getName());
        req.setAttribute("newEmail", employer.getEmail());
        req.setAttribute("newDate", employer.getEmployedAt());

        LOG.debug("End executing Command");

        return Path.FORWARD_EMPLOYER_UPDATE_PAGE;
    }
}
