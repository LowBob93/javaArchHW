package ru.geekbrains.Handlers;

import ru.geekbrains.loggers.LoggerFactory;
import ru.geekbrains.request.RequestParserImpl;
import ru.geekbrains.Response.ResponseSerializerImpl;
import ru.geekbrains.Services.SocketService;
import ru.geekbrains.domain.HttpResponse;

import ru.geekbrains.loggers.Logger;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestHandler implements Runnable {

    private static final String WWW = "C:/Users/siarh/IdeaProjects/arhitecture-gb-webserver/www/";

    private static final Logger logger = LoggerFactory.create("RequestHandlerLog.txt");

    private final SocketService socketService;

    public RequestHandler(SocketService socketService) {
        this.socketService = socketService;
    }

    @Override
    public void run() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "text/html; charset=utf-8");

        List<String> request = socketService.readRequest();

        Path path = Paths.get(WWW, new RequestParserImpl().parse(request).getPath());
        if (!Files.exists(path)) {
            HttpResponse response = HttpResponse.createResponseBuilder()
                    .withStatusCode(404)
                    .withHeaders(headers)
                    .withBody(new StringReader("<h1>Файл не найден!</h1>\n"))
                    .build();
            socketService.writeResponse(response, new ResponseSerializerImpl());
            return;
        }

        try {
            HttpResponse response = HttpResponse.createResponseBuilder()
                    .withStatusCode(200)
                    .withHeaders(headers)
                    .withBody(Files.newBufferedReader(path))
                    .build();

            socketService.writeResponse(response, new ResponseSerializerImpl());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logger.getInfo("Client disconnected!");
    }
}
