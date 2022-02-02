package client.scriptutil;

import client.ClientUtil;
import client.CommandListener;
import client.Request;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Script {

    private String path;
    private innerScript innerScript;
    private CommandListener commandListener;
    private ClientUtil clientUtil;
    private Socket socket;

    public Script(CommandListener commandListener, ClientUtil clientUtil) {

        this.commandListener = commandListener;
        innerScript = new innerScript();
        this.clientUtil = clientUtil;
    }

    public void executeScript(String command, Socket socket, ByteBuffer buffer) {
        String[] com = command.trim().toLowerCase(Locale.ROOT).split("\\s+");
        String[] args = Arrays.copyOfRange(com, 1, com.length);
        String path = args[0];
        try {
            innerScript.putScript(path);
            File ioFile = new File(path);
            if (!ioFile.canWrite() || ioFile.isDirectory() || !ioFile.isFile()) throw new IOException();
            FileInputStream fileInputStream = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            Scanner scanner = new Scanner(inputStreamReader);
            while (scanner.hasNext()) {
                String str = scanner.nextLine();
                String[] scrCom = str.trim().toLowerCase(Locale.ROOT).split("\\s+");
                String[] scrArgs = Arrays.copyOfRange(scrCom, 1, scrCom.length);
                if (scrCom[0].equals("execute_script") && scrCom.length != 0 && innerScript.scriptPath.contains(scrArgs[0])) {
                    System.err.println("Попытка рекурсивного вызова скрипта внутри него же!");
                    return;
                } else if (scrCom[0].equals("execute_script") && scrCom.length != 0 && !innerScript.scriptPath.contains(scrArgs[0])) {
                    executeScript(str, socket , buffer);
                }
                Request request = commandListener.getRequestFromCommand(str);
                clientUtil.sendRequestToServer(request, socket);
                System.out.println(clientUtil.receive(socket).getBody());
                buffer.clear();
            }
        } catch (IOException e) {
            System.err.println("Нет доступа к файлу!" + e.getMessage());
        }

    }

    static class innerScript {
        private ArrayList<String> scriptPath = new ArrayList<>();

        public void putScript(String path) {
            scriptPath.add(path);
        }

        public void removeScript(String path) {
            scriptPath.remove(path);
        }

    }
}
