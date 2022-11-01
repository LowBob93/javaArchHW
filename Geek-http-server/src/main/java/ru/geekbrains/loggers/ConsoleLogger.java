package ru.geekbrains.loggers;

public class ConsoleLogger implements Logger{
    @Override
    public void getInfo(String msg) {
        System.out.println(msg);
    }
}
