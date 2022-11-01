package ru.geekbrains;

import ru.geekbrains.Handlers.RequestHandler;
import ru.geekbrains.Services.SocketService;

import ru.geekbrains.loggers.Logger;
import ru.geekbrains.loggers.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class HTTPServer {

    private static final Logger logger = LoggerFactory.create("ServerLog.txt");

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            logger.getInfo("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                logger.getInfo("New client connected!");

                new Thread(new RequestHandler(new SocketService(socket))).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
