package client;

import server.Response;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

public class ClientUtil {
    public ClientUtil() {
    }


    public Response deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        return (Response) ois.readObject();
    }

    public Response receive(Socket socket) throws IOException {
        try {
            byte[] bytes = new byte[10000];
            socket.getInputStream().read(bytes);
            return deserialize(bytes);
        } catch (ClassNotFoundException e) {
            System.out.println("Class Cast Exception");
            return null;
        }
    }

    private byte[] serialize(Request req) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(baos)) {
            objectOutputStream.writeObject(req);
            return baos.toByteArray();
        }
    }

    public void sendRequestToServer(Request request, Socket socket) throws IOException {
        socket.getOutputStream().write(serialize(request));
        socket.getOutputStream().flush();
    }
}
