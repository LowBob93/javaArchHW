package ru.geekbrains.Handlers;



import ru.geekbrains.Services.SocketService;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;

import java.io.StringReader;
import java.util.Map;
@Handler(order = 1)
public class PostMethodHandler extends MethodHandler{
    private final String www;

    public PostMethodHandler(String method, MethodHandler next, SocketService socketService, String www) {
        super(method, next, socketService);
        this.www = www;
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest request) {
        return HttpResponse.createResponseBuilder()
                .withStatusCode(404)
                .withHeaders(Map.of("Content-Type", "text/html; charset=utf-8"))
                .withBody(new StringReader("<h1>Метод POST</h1>\n"))
                .build();
    }
}
