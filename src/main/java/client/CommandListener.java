package client;

import client.utils.DragonUpdater;
import lib.collection.*;
import lib.utils.CollectionInfo;
import lib.utils.DragonFieldsReader;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;

public class CommandListener {
    private DragonFieldsReader dragonFieldsReader;
    private CollectionInfo collectionInfo;
    private DragonUpdater dragonUpdater;

    public CommandListener() {
        this.dragonFieldsReader = new DragonFieldsReader();
        collectionInfo = new CollectionInfo();
        this.dragonUpdater = new DragonUpdater();
    }

    public Request getRequestFromCommand(String command) {
        String[] com = command.trim().toLowerCase(Locale.ROOT).split("\\s+");
        String[] args = Arrays.copyOfRange(com, 1, com.length);

        Request request = new Request();
        switch (com[0]) {
            case "show":
                request.setCommand("show");
                break;
            case "help":
                request.setCommand("help");
                break;
            case "info":
                request.setCommand("info");
                break;
            case "clear":
                request.setCommand("clear");
                break;
            case "head":
                request.setCommand("head");
                break;
            case "max_by_creation_date":
                request.setCommand("max_by_creation_date");
                break;
            case "print_field_descending_type":
                request.setCommand("print_field_descending_type");
                break;
            case "print_unique_age":
                request.setCommand("print_unique_age");
                break;
            case "exit":
                request.setCommand("exit");
                break;
            case "add":
                request.setCommand("add");
                request.setDragon(new Dragon(
                        dragonFieldsReader.readName(),
                        dragonFieldsReader.readCoordinates(),
                        LocalDate.now(),
                        dragonFieldsReader.readAge(),
                        dragonFieldsReader.readColor(),
                        dragonFieldsReader.readDragonType(),
                        dragonFieldsReader.readDragonCharacter(),
                        dragonFieldsReader.readDragonCave()
                ));
                break;
            case "add_if_min":
                request.setCommand("add_if_min");
                request.setDragon(new Dragon(
                        dragonFieldsReader.readName(),
                        dragonFieldsReader.readCoordinates(),
                        LocalDate.now(),
                        dragonFieldsReader.readAge(),
                        dragonFieldsReader.readColor(),
                        dragonFieldsReader.readDragonType(),
                        dragonFieldsReader.readDragonCharacter(),
                        dragonFieldsReader.readDragonCave()
                ));
                break;
            case "remove_by_id":
                request.setCommand("remove_by_id");
                if (args.length == 0) {
                    request.setArgument("no_args");
                    break;
                }
                request.setArgument(args[0]);
                break;
            case "remove_lower":
                request.setCommand("remove_lower");
                request.setDragon(new Dragon(
                        dragonFieldsReader.readName(),
                        dragonFieldsReader.readCoordinates(),
                        LocalDate.now(),
                        dragonFieldsReader.readAge(),
                        dragonFieldsReader.readColor(),
                        dragonFieldsReader.readDragonType(),
                        dragonFieldsReader.readDragonCharacter(),
                        dragonFieldsReader.readDragonCave()
                ));
                break;

            case "update_by_id":
                request.setCommand("update_by_id");
                if (args.length == 0) {
                    request.setArgument("no_args");
                    break;
                }
                request.setArgument(args[0]);
                request.setDragon(dragonUpdater.updateById());
                break;
            default:
                request.setCommand("no_command");
                System.err.println("Такой команды нет!");
                break;

        }
        return request;
    }


}
