package aimprosoft.departments.command.department;

import aimprosoft.departments.command.Command;
import aimprosoft.departments.web.ActionType;
import aimprosoft.departments.web.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewDepartmentInsertPageCommand implements Command {

    private static final Logger LOG = Logger.getLogger(CreateDepartmentCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) throws IOException, ServletException {

        LOG.debug("Start executing Command");

        request.getRequestDispatcher(Path.FORWARD_DEPARTMENT_INSERT_PAGE).forward(request, response);

        LOG.debug("Finished executing Command");

        return Path.FORWARD_EMPLOYER_INSERT_PAGE;
    }
}
