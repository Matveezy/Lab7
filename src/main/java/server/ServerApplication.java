package server;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;

public class ServerApplication {

    public static void main(String[] args) throws IOException {
        String inputFile = System.getenv("PATH_FILE");
        Server server = new Server(inputFile);
        server.run();
    }

}
