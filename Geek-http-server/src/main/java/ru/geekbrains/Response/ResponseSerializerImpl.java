package ru.geekbrains.Response;

import ru.geekbrains.domain.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class ResponseSerializerImpl implements ResponseSerializer {
    @Override
    public String serialize(HttpResponse httpResponse) {
        StringBuilder builder = new StringBuilder();
        builder.append("HTTP/1.1 ");
        switch (httpResponse.getStatusCode()) {
            case 200:
                builder.append(httpResponse.getStatusCode()).append("STATUS_CODE: 200 OK\n");
                break;
            case 400: builder.append(httpResponse.getStatusCode()).append("STATUS_CODE: 400 BAD_REQUEST\n");
                break;
            case 404:
                builder.append(httpResponse.getStatusCode()).append("STATUS_CODE: 404 NOT_FOUND\n");
                break;
            case 500:
                builder.append(httpResponse.getStatusCode()).append("STATUS_CODE: 500 INTERNAL_SERVER_ERROR\n");
                break;
        }
        for (Map.Entry<String, String> header : httpResponse.getHeaders().entrySet()) {
            builder.append(header.getKey()).append(": ").append(header.getValue()).append("\n\n");
        }
        BufferedReader reader = new BufferedReader(httpResponse.getBody());
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return builder.toString();
    }
}