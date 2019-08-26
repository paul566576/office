package aimprosoft.departments.command.department;

import aimprosoft.departments.command.Command;
import aimprosoft.departments.command.validation.ValidateDepNameWhenCreateCommand;
import aimprosoft.departments.dao.DepartmentDAO;
import aimprosoft.departments.dao.impl.DepartmentDAOImpl;
import aimprosoft.departments.exeption.DAOException;
import aimprosoft.departments.model.Department;
import aimprosoft.departments.web.ActionType;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CreateDepartmentCommand implements Command {

    private static final Logger LOG = Logger.getLogger(CreateDepartmentCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType)
            throws IOException, ServletException, DAOException, SQLException {
        LOG.debug("Start executing Command");

        String result = createDepartment(request, response, actionType);

        LOG.debug("Finished executing Command");

        return result;
    }

    private String createDepartment(HttpServletRequest req, HttpServletResponse resp, ActionType actionType)
            throws ServletException, IOException, DAOException, SQLException {

        DepartmentDAO idd = new DepartmentDAOImpl();

        Department dep = new Department();

        dep.setName(req.getParameter("name"));

        if (idd.isValueExist(dep.getName())) {
            Command command = new ValidateDepNameWhenCreateCommand();
            return command.execute(req, resp, actionType);

        } else {
            idd.create(dep);
            ViewAllDepartmentCommand vadc = new ViewAllDepartmentCommand();
            return vadc.execute(req, resp, actionType);
        }
    }
}
