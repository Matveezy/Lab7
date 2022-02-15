package server.interfaces;

import client.user.User;

public interface CommandWithArguments extends Command {
    void getArguments(String arguments);
}
