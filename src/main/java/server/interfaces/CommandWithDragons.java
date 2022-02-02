package server.interfaces;

import lib.collection.Dragon;

public interface CommandWithDragons extends Command{
    public void setDragonAgrument(Dragon dragon);
}
