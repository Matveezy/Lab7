package server;

import jdk.swing.interop.SwingInterOpUtils;
import server.database.DataBaseManager;

import java.io.IOException;

public class ServerApplication {

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.run();
    }

}
