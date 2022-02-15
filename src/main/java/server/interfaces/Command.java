package server.interfaces;

import client.user.User;

public interface Command {
    String execute();
    public void setUserArgument(User user);
    String getDescription();
}
