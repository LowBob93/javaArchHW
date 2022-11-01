package ru.geekbrains.Handlers;


import ru.geekbrains.Services.SocketService;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.loggers.Logger;
import ru.geekbrains.loggers.LoggerFactory;
import ru.geekbrains.request.RequestParser;

import java.io.*;
import java.util.List;

public class RequestHandler implements Runnable {
    private RequestParser requestParser;
    private static final Logger logger = LoggerFactory.create("RequestHandlerLog.txt");

    private SocketService socketService;
    private MethodHandler handler;

    public RequestHandler(RequestParser requestParser, SocketService socketService, MethodHandler handler) {
        this.requestParser = requestParser;
        this.socketService = socketService;
        this.handler = handler;
    }

    public RequestHandler(SocketService socketService) {
    }

    @Override
    public void run() {
        List<String> requests = socketService.readRequest();
        HttpRequest request = requestParser.parse(requests);
        handler.handle(request);
        try{socketService.close();}
        catch (IOException e){
            throw new IllegalStateException(e);
        }

        logger.getInfo("Client disconnected!");
    }
}
