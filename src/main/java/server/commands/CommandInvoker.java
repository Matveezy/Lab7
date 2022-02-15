package server.commands;

import client.Request;
import lib.collectionworker.CollectionManager;
import lib.userio.UserIO;
import lib.utils.DragonFieldsReader;
import server.interfaces.Command;
import server.interfaces.CommandWithArguments;
import server.interfaces.CommandWithDragons;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

public class CommandInvoker {
    private HashMap<String, Command> commandWithoutArguments;
    private HashMap<String, CommandWithArguments> commandWithArguments;
    private HashMap<String, CommandWithDragons> commandWithDragonArguments;
    private CollectionManager collectionManager;
    private String inputFile;

    public CommandInvoker(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
//        this.inputFile = inputFile;
        commandWithArguments = new HashMap<>();
        commandWithoutArguments = new HashMap<>();
        commandWithDragonArguments = new HashMap<>();
        this.putCommands();

    }

    private void putCommands() {
        commandWithoutArguments.put("help", new Help(commandWithoutArguments, commandWithArguments, commandWithDragonArguments));
        commandWithoutArguments.put("show", new Show(collectionManager));
        commandWithoutArguments.put("clear", new Clear(collectionManager));
        commandWithoutArguments.put("head", new Head(collectionManager));
        commandWithoutArguments.put("max_by_creation_date", new MaxByCreationDate(collectionManager));
        commandWithoutArguments.put("print_field_descending_type", new PrintFieldDescendingType(collectionManager));
        commandWithoutArguments.put("print_unique_age", new PrintUniqueAge(collectionManager));
        commandWithoutArguments.put("exit" , new Exit(collectionManager , System.getenv("PATH_FILE")));
        commandWithoutArguments.put("info" , new Info(collectionManager));
        commandWithDragonArguments.put("add", new Add(collectionManager));
        commandWithDragonArguments.put("add_if_min", new AddIfMin(collectionManager));
        commandWithDragonArguments.put("remove_lower", new RemoveLower(collectionManager));
        commandWithDragonArguments.put("update_by_id" , new UpdateById(collectionManager));

        commandWithArguments.put("remove_by_id", new RemoveById(collectionManager));
    }

    public String execute(Request request) {

        String response = new String();
        if (request.getCommand().equals("update_by_id")){
            UpdateById updateById = new UpdateById(collectionManager);
            updateById.setDragonAgrument(request.getDragon());
            updateById.getArguments(request.getArgument());
            updateById.setUserArgument(request.getUser());
            response = updateById.execute();
            return response;
        }
        if (commandWithArguments.containsKey(request.getCommand())) {
            CommandWithArguments command;
            command = commandWithArguments.get(request.getCommand());
            command.setUserArgument(request.getUser());
            command.getArguments(request.getArgument());
            response = command.execute();
        } else if (commandWithoutArguments.containsKey(request.getCommand())) {
            Command command;
            command = commandWithoutArguments.get(request.getCommand());
            command.setUserArgument(request.getUser());
            response = command.execute();
        } else if (commandWithDragonArguments.containsKey(request.getCommand())) {
            CommandWithDragons command;
            command = commandWithDragonArguments.get(request.getCommand());
            command.setDragonAgrument(request.getDragon());
            command.setUserArgument(request.getUser());
            response = command.execute();
        }
        return response;
    }

}
