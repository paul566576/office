package aimprosoft.departments.command.department;

import aimprosoft.departments.command.Command;
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

public class ViewAllEmployerFromCurrentDepartmentCommand implements Command {

    private static final Logger LOG = Logger.getLogger(CreateDepartmentCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) throws IOException, ServletException, DAOException, SQLException {
        LOG.debug("Start executing Command");

        HttpSession session = request.getSession();

        String id = request.getParameter("id");
        if (id != null && id != "") {
            session.setAttribute("id", id);
        }

        showAllEmployersFromCurrentDepartment(request, session);

        LOG.debug("Finished executing Command");

        return Path.FORWARD_EMPLOYER_LIST_PAGE;
    }


    private void showAllEmployersFromCurrentDepartment(HttpServletRequest req, HttpSession session) throws DAOException, SQLException {

        EmployerDAO ied = new EmployerDAOImpl();

        DepartmentDAO idd = new DepartmentDAOImpl();

        int departmentId = Integer.parseInt(session.getAttribute("id") +"");

        List<Employer> employers = ied.getAllEmployersFromDepartment(departmentId);

        String depName = ((Department)idd.getByID(departmentId)).getName();

        session.setAttribute("depId", departmentId);

        req.setAttribute("depName", depName);

        req.setAttribute("employers", employers);

    }

}
