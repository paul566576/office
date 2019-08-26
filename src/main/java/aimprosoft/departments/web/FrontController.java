package aimprosoft.departments.web;

import aimprosoft.departments.command.Command;
import aimprosoft.departments.command.CommandManager;
import aimprosoft.departments.dao.impl.DepartmentDAOImpl;
import aimprosoft.departments.exeption.DAOException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class FrontController extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(DepartmentDAOImpl.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOG.error(e);
        }
        try {
            process(req, resp, ActionType.GET);
        } catch (DAOException | SQLException e) {
            LOG.error(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOG.error(e);
        }

        try {
            process(req, resp, ActionType.POST);
        } catch (DAOException | SQLException e) {
            LOG.error(e);
        }
    }

    private void process(HttpServletRequest req, HttpServletResponse resp, ActionType actionType) throws ServletException, IOException, DAOException, SQLException {

        String commandName = req.getParameter("command");

        LOG.trace("Request parameter: 'command' = " + commandName);

        Command command = CommandManager.get(commandName);

        String path = command.execute(req, resp, actionType);

        LOG.trace("Forward to address = " + path);
        LOG.debug("Controller proccessing finished");
        req.getRequestDispatcher(path).forward(req, resp);
    }
}

