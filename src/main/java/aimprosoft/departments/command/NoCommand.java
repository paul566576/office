package aimprosoft.departments.command;

import aimprosoft.departments.web.ActionType;
import aimprosoft.departments.web.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class NoCommand  implements Command{

    private static final Logger LOG = Logger.getLogger(NoCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) throws IOException, ServletException {

        String errorMessage = "No such command";
        request.setAttribute("errorMessage", errorMessage);
        LOG.error("Set the request attribute: 'errorMessage' = " + errorMessage);

        LOG.debug("Command execution finished");
        return Path.ERROR_PAGE;
    }
}
