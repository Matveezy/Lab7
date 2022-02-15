package client;

import client.user.User;
import lib.collection.Dragon;

import java.io.Serial;
import java.io.Serializable;

public class Request implements Serializable {

    @Serial
    private static final long serialVersionUID = 7923661429084687689L;

    private User user;
    private String command;
    private String argument;
    private Dragon dragon;


    public Request() {
    }

    public Dragon getDragon() {
        return dragon;
    }

    public void setDragon(Dragon dragon) {
        this.dragon = dragon;
    }

    public Request(String command, String argument, Dragon dragon) {
        this.command = command;
        this.argument = argument;
        this.dragon = dragon;
    }

    public Request(User user, String command, String argument, Dragon dragon) {
        this.user = user;
        this.command = command;
        this.argument = argument;
        this.dragon = dragon;
    }

    public Request(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    @Override
    public String toString() {
        return "Request{" +
                "command='" + command + '\'' +
                ", argument='" + argument + '\'' +
                ", dragon=" + dragon +
                '}';
    }
}
