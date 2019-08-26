package aimprosoft.departments.command;

import java.util.HashMap;
import java.util.Map;

import aimprosoft.departments.command.department.*;
import aimprosoft.departments.command.employer.*;
import org.apache.log4j.Logger;


public final class CommandManager {

    private static final Logger LOG = Logger.getLogger(CommandManager.class);

    private static Map<String, Command> commands = new HashMap<String, Command>();

    private CommandManager() {}

    static {
        commands.put("create department", new CreateDepartmentCommand());
        commands.put("insert department", new ViewDepartmentInsertPageCommand());
        commands.put("delete", new DeleteDepartmentCommand());
        commands.put("update department", new UpdateDepartmentCommand());
        commands.put("departments list", new ViewAllDepartmentCommand());
        commands.put("employers list", new ViewAllEmployerFromCurrentDepartmentCommand());
        commands.put("update", new ViewDepartmentUpdatePageCommand());

        commands.put("Create employer", new CreateEmployerCommand());
        commands.put("Delete", new DeleteEmployerCommand());
        commands.put("Update", new ViewEmployerUpdatePageCommand());
        commands.put("Insert employer", new ViewEmployerInsertPageCommand());
        commands.put("Update employer", new UpdateEmployerCommand());

        LOG.trace("Total number of commands equals to " + commands.size());
    }

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found with name = " + commandName);
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }


}
