package server.commands;

import client.user.User;
import server.interfaces.Command;
import server.interfaces.CommandWithArguments;
import server.interfaces.CommandWithDragons;

import java.util.HashMap;
import java.util.Map;

public class Help implements Command {
    private StringBuffer sb;
    private HashMap<String, Command> commandsWithoutArguments;
    private HashMap<String, CommandWithArguments> commandWithArguments;
    private HashMap<String, CommandWithDragons> commandWithDragonsArgument;
    private User user;

    public Help(HashMap<String, Command> commandsWithoutArguments, HashMap<String, CommandWithArguments> commandWithArguments, HashMap<String, CommandWithDragons> commandWithDragonsArgument) {
        this.commandsWithoutArguments = commandsWithoutArguments;
        this.commandWithArguments = commandWithArguments;
        this.commandWithDragonsArgument = commandWithDragonsArgument;
        sb = new StringBuffer();
    }

    @Override
    public String execute() {
        commandWithDragonsArgument.entrySet().stream()
                .forEach(value -> sb.append(value.getKey() + " " + value.getValue().getDescription() + "\n"));
        commandWithArguments.entrySet().stream()
                .forEach(value -> sb.append(value.getKey() + " " + value.getValue().getDescription() + "\n"));
        commandsWithoutArguments.entrySet().stream()
                .forEach(value -> sb.append(value.getKey() + " " + value.getValue().getDescription() + "\n"));
        String response = sb.toString();
        sb.setLength(0);
        return response;
    }

    @Override
    public void setUserArgument(User user) {
        this.user=user;
    }

    @Override
    public String getDescription() {
        return "Вывести справку по доступным командам";
    }
}

