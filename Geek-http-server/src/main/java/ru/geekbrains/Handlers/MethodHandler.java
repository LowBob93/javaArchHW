package ru.geekbrains.Handlers;

import ru.geekbrains.Response.ResponseSerializerImpl;
import ru.geekbrains.Services.SocketService;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;

import java.io.StringReader;
import java.util.Map;
import java.util.Objects;

public abstract class MethodHandler {
    private final String method;
    private final MethodHandler next;
    protected final SocketService socketService;

    public MethodHandler(String method, MethodHandler next, SocketService socketService) {
        this.method = method;
        this.next = next;
        this.socketService = socketService;
    }
    public void handle(HttpRequest request){
        HttpResponse response;
        if(method.equalsIgnoreCase(request.getMethod())){
           response = handleInternal(request);
        } else if (Objects.nonNull(next)) {
            next.handle(request);
            return;
        } else {
            response = HttpResponse.createResponseBuilder()
                    .withStatusCode(405)
                    .withHeaders(Map.of("Content-Type", "text/html; charset=utf-8"))
                    .withBody(new StringReader("<h1>Method not allowed</h1>\n"))
                    .build();
        }
        socketService.writeResponse(response, new ResponseSerializerImpl());
    }
    protected abstract HttpResponse handleInternal(HttpRequest request);
}
