package client;

import client.scriptutil.Script;
import client.user.User;
import client.user.UserAuthReg;
import client.user.UserFieldsReader;
import server.Response;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Client {
    private ClientUtil clientUtil;
    private CommandListener commandListener;
    private Scanner scanner;
    Script script;
    private UserAuthReg userAuthReg;
    private User user;

    public Client() {
        this.clientUtil = new ClientUtil();
        this.commandListener = new CommandListener();
        this.scanner = new Scanner(System.in);
        this.user = new User();
        this.script = new Script(this.commandListener, this.clientUtil, this.user);
        this.userAuthReg = new UserAuthReg(this.user, this.scanner);
    }

    public boolean checkIfScriptCommand(String command) {
        String[] com = command.trim().toLowerCase(Locale.ROOT).split("\\s+");
        String[] args = Arrays.copyOfRange(com, 1, com.length);
        if (com[0].equals("execute_script") && args.length != 0) return true;
        return false;
    }

    public void run() throws IOException {
        System.out.println("Введите номер порта, по которому вы хотите подключиться : ");
        int port = Integer.parseInt(scanner.nextLine().trim());
        InetAddress InetAddress = Inet4Address.getByName("localhost");

        try (Socket socket = new Socket(InetAddress, port);
             Scanner scanner = new Scanner(System.in);
        ) {
            ByteBuffer buffer = ByteBuffer.allocate(65000);
            Response response = new Response();
            while (!response.isAuthorized()) {
                userAuthReg.userAuthReg();
                Request authReq = new Request(user);
                clientUtil.sendRequestToServer(authReq, socket);
                response = clientUtil.receive(socket);
                System.out.println(response.getBody());
                System.out.println("-------------------------------------------------");
                if (userAuthReg.userExist(response)) {
                    continue;
                }
            }
            user.setRegistration(false);
            user.setAuthorization(false);
            System.out.println("-------------------------------------------------");
            System.out.println("Приложение запущено!");
            System.out.println("-------------------------------------------------");
            System.out.println("Пользователь: " + user.getUsername());
            System.out.println("-------------------------------------------------");
            System.out.println("Введите exit для заверешния работы приложения");
            while (true) {
                System.out.println("Введите команду :");
                String req = scanner.nextLine();

                while (true) {
                    if (checkIfScriptCommand(req)) {
                        script.executeScript(req, socket, buffer);
                    } else {
                        Request request1 = commandListener.getRequestFromCommand(req, user);
                        request1.setUser(user);
                        if (request1.getCommand().equals("no_command")) {
                            req = scanner.nextLine();
                            continue;
                        }
                        clientUtil.sendRequestToServer(request1, socket);
                        System.out.println(clientUtil.receive(socket).getBody());
                        buffer.clear();
                        if (request1.getCommand().equals("exit")) {
                            System.out.println("Приложение завершает работу!");
                            System.exit(0);
                        }
                    }
                    req = scanner.nextLine();
                }
            }
        } catch (SocketException e) {
            System.err.println("Нет подключения по данному порту!");
        } catch (IllegalArgumentException e2) {
            System.err.println("Нет соединения с сервером!");
        } catch (StreamCorruptedException e3) {
            System.err.println("Нет соединения с сервером!");
        }
    }

}
