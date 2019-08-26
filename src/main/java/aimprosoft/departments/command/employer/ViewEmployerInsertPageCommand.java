package aimprosoft.departments.command.employer;

import aimprosoft.departments.command.Command;
import aimprosoft.departments.command.department.CreateDepartmentCommand;
import aimprosoft.departments.web.ActionType;
import aimprosoft.departments.web.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewEmployerInsertPageCommand implements Command {

    private static final Logger LOG = Logger.getLogger(CreateDepartmentCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ActionType actionType) throws IOException, ServletException {
        LOG.debug("Start executing Command");

        req.getRequestDispatcher(Path.FORWARD_EMPLOYER_INSERT_PAGE).forward(req, resp);

        HttpSession session = req.getSession();

        session.setAttribute("", null);

        LOG.debug("Finished executing Command");

        return Path.FORWARD_EMPLOYER_LIST_PAGE;
    }
}
