package server.interfaces;

import client.user.User;
import lib.collection.Dragon;

public interface CommandWithDragons extends Command{
    public void setDragonAgrument(Dragon dragon);
}
