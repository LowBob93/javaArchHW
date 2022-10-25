package ru.geekbrains.Services;

import ru.geekbrains.Response.ResponseSerializer;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.loggers.Logger;
import ru.geekbrains.loggers.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SocketService implements Closeable {

    private final Socket socket;

    private static final Logger logger = LoggerFactory.create();

    public SocketService(Socket socket) {
        this.socket = socket;
    }

    public List<String> readRequest() {

        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream(), StandardCharsets.UTF_8));

            while (!input.ready()) ;

            List<String> request = new ArrayList<>();
            while (input.ready()) {

                String line = input.readLine();
                logger.getInfo(line);
                request.add(line);
            }
            return request;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void writeResponse(HttpResponse response, ResponseSerializer serializer) {
        try {
            PrintWriter output = new PrintWriter(socket.getOutputStream());
            String responseString = serializer.serialize(response);
            output.print(responseString);
            output.flush();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws IOException {

        if (!socket.isClosed()) {
            socket.close();
        }
    }
}