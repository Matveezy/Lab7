package server;

import client.Request;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ServerUtil {
    public ServerUtil() {
    }

    public void sendResponseToClient(Response response, SocketChannel client) throws IOException {
        client.write(ByteBuffer.wrap(serialize(response)));
    }

    private byte[] serialize(Response rsp) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (
                ObjectOutputStream oos = new ObjectOutputStream(baos);
        ) {
            oos.writeObject(rsp);
            return baos.toByteArray();
        }
    }


}
