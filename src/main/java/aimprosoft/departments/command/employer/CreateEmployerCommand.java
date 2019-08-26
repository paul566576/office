package aimprosoft.departments.command.employer;

import aimprosoft.departments.command.Command;
import aimprosoft.departments.command.CommandManager;
import aimprosoft.departments.command.department.ViewAllEmployerFromCurrentDepartmentCommand;
import aimprosoft.departments.dao.EmployerDAO;
import aimprosoft.departments.dao.impl.EmployerDAOImpl;
import aimprosoft.departments.exeption.DAOException;
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

public class CreateEmployerCommand implements Command {

    private static final Logger LOG = Logger.getLogger(CommandManager.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) throws IOException, ServletException, DAOException, SQLException {

        LOG.debug("Start executing Command");

        HttpSession session = request.getSession();

        String result = createEmployer(request, response, session, actionType);

        LOG.debug("Finished executing Command");

        return result;

    }

    private String createEmployer(HttpServletRequest req, HttpServletResponse resp, HttpSession session, ActionType actionType)
            throws ServletException, IOException, DAOException, SQLException {

        EmployerDAO ied = new EmployerDAOImpl();

        Employer employer = new Employer();
        employer.setName(req.getParameter("name"));
        employer.setDepId(Integer.parseInt(session.getAttribute("depId") + ""));
        employer.setEmail(req.getParameter("email"));
        employer.setEmployedAt(ied.dateFormatter(req.getParameter("date")));

        if (ied.isValueExist(employer.getEmail())) {

            boolean isEmaisExist = false;

            req.setAttribute("isEmaisExist", isEmaisExist);
            req.setAttribute("emailMessage", Constants.EMAIL_ERROR);

            req.setAttribute("newName", employer.getName());
            req.setAttribute("newEmail", employer.getEmail());
            req.setAttribute("newDate", employer.getEmployedAt());

            return Path.FORWARD_EMPLOYER_INSERT_PAGE;
        } else {
            ied.create(employer);

            session.setAttribute("dep_id", employer.getDepId());

            ViewAllEmployerFromCurrentDepartmentCommand viewEmpl = new ViewAllEmployerFromCurrentDepartmentCommand();

            return viewEmpl.execute(req, resp, actionType);
        }
    }
}
