package ru.geekbrains.loggers;

public class LoggerFactory {
    public static Logger create(){
        return new ConsoleLogger();
    }
    public static Logger create(String filename){
        return new FileLogger(filename);
    }
}