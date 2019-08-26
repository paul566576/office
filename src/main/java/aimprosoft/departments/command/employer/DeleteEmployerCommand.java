package aimprosoft.departments.command.employer;

import aimprosoft.departments.command.Command;
import aimprosoft.departments.command.department.CreateDepartmentCommand;
import aimprosoft.departments.command.department.ViewAllEmployerFromCurrentDepartmentCommand;
import aimprosoft.departments.dao.EmployerDAO;
import aimprosoft.departments.dao.impl.EmployerDAOImpl;
import aimprosoft.departments.exeption.DAOException;
import aimprosoft.departments.web.ActionType;
import aimprosoft.departments.web.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteEmployerCommand  implements Command {

    private static final Logger LOG = Logger.getLogger(CreateDepartmentCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) throws IOException, ServletException, DAOException, SQLException {
        LOG.debug("Start executing Command");

        deleteEmployer(request, response, actionType);

        LOG.debug("Finished executing Command");

        return Path.FORWARD_EMPLOYER_LIST_PAGE;
    }

    private void deleteEmployer(HttpServletRequest req, HttpServletResponse resp, ActionType actionType) throws ServletException, IOException, DAOException, SQLException {

        EmployerDAO ied = new EmployerDAOImpl();

        ied.delete(Integer.parseInt(req.getParameter("idEmployer")));

        ViewAllEmployerFromCurrentDepartmentCommand veiwEempl = new ViewAllEmployerFromCurrentDepartmentCommand();

        veiwEempl.execute(req, resp, actionType);

    }
}
