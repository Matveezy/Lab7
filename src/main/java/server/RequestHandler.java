package server;

import client.Request;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class RequestHandler {

    public Request deserialize(ByteBuffer buffer) {
        byte[] array = new byte[buffer.remaining()];
        buffer.get(array);
        ByteArrayInputStream bais = new ByteArrayInputStream(array);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(bais)) {
            Object obj = objectInputStream.readObject();
            return (Request) obj;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error " + e.getMessage());
        }
        return null;
    }

    public Request receive(SocketChannel channel) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ByteBuffer requestBuff = ByteBuffer.allocate(1024 * 32);
            channel.read(requestBuff);
            do {
                requestBuff.flip();
                while (requestBuff.hasRemaining()) {
                    baos.write(requestBuff.get());
                }
                requestBuff.clear();
            } while (channel.read(requestBuff) > 0);
            ByteBuffer des = ByteBuffer.wrap(baos.toByteArray());
            return deserialize(des);
        } catch (ClassCastException e) {
            System.err.println("Class not found ex!");
            return null;
        }
    }
}
