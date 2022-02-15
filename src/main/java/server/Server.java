package server;

import client.Request;
import lib.collectionworker.CollectionManager;
import lib.utils.CollectionInfo;
import server.commands.CommandInvoker;
import server.database.DataBaseManager;
import server.serveruser.UserConnector;
import server.util.PasswordDecoder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private RequestHandler requestHandler;
    private Scanner scanner;
    private ServerUtil serverUtil;
    private ResponseHandler responseHandler;
    private CollectionManager collectionManager;
    private CommandInvoker commandInvoker;
    private CollectionInfo collectionInfo;
    private Response response;
    private UserConnector userConnector;
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

    public Server() {
        this.requestHandler = new RequestHandler();
        scanner = new Scanner(System.in);
        this.serverUtil = new ServerUtil();
        this.responseHandler = new ResponseHandler();
        this.collectionInfo = new CollectionInfo();
        this.response = new Response();
        this.userConnector = new UserConnector();
    }


    public void run() throws IOException {
        this.collectionManager = new CollectionManager();
        this.commandInvoker = new CommandInvoker(this.collectionManager);
        System.out.println("Введите номер порта, по которому вы хотите подключиться : ");
        int port = Integer.parseInt(scanner.nextLine().trim());
        DataBaseManager.load(this.collectionManager);
        if (collectionManager.getSize() == 0) {
            System.out.println("Добавьте объекты с помощью команды add, после чего объект добавится в базу данных!!");
        } else System.out.println("Объкты из базы загружены!");
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();

        serverSocketChannel.bind(new InetSocketAddress("localhost", port));

        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Сервер запущен!");

        ByteBuffer request = ByteBuffer.allocate(65000);
        fixedThreadPool.submit(() -> {
            while (true) {
                try {
                    selector.selectNow();
                    Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeySet.iterator();

                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (!key.isValid()) {
                            System.out.println("Key isn't valid!");
                            continue;
                        }
                        if (key.isAcceptable()) {
                            System.out.println("Запрос на подключение клиента!");
                            SocketChannel client = serverSocketChannel.accept();
                            client.configureBlocking(false);
                            client.register(selector, SelectionKey.OP_READ);
                            System.out.println("Клиент подключен!");
                        } else if
                        (key.isReadable()) {
                            System.out.println("Попытка чтения из канала!");
                            SocketChannel client = (SocketChannel) key.channel();
                            Request requestFromClient = requestHandler.receive(client);
//                        System.out.println(requestFromClient);
                            if (requestFromClient.getUser().isRegistration() || requestFromClient.getUser().isAuthorization()) {
                                requestFromClient.getUser().setPassword(PasswordDecoder.decodeToSha1(requestFromClient.getUser().getPassword()));
                                String responseString = userConnector.connectUser(requestFromClient.getUser(), response);
                                response.setBody(responseString);
                                responseHandler.setResponse(response);
                                request.clear();
                                key.interestOps(SelectionKey.OP_WRITE);
                            } else {
                                String responseString = commandInvoker.execute(requestFromClient);
                                response.setBody(responseString);
                                responseHandler.setResponse(response);

                                request.clear();
                                key.interestOps(SelectionKey.OP_WRITE);
                            }
                        } else if (key.isWritable()) {
                            key.interestOps(SelectionKey.OP_READ);
                            SocketChannel client = (SocketChannel) key.channel();
                            serverUtil.sendResponseToClient(response, client);
                            if (response.getBody().equals("Вы отключились от сервера!")) {
                                System.out.println("Клиент " + client.toString() + " отключился!");
                                key.interestOps(SelectionKey.OP_CONNECT);
                            }
                        }
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

    }
}
