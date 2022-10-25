package ru.geekbrains.Response;

import ru.geekbrains.domain.HttpResponse;

public interface ResponseSerializer {

    String serialize(HttpResponse httpResponse);
}