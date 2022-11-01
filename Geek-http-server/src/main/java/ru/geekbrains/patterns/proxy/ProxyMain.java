package ru.geekbrains.patterns.proxy;

public class ProxyMain {
    public static void main(String[] args) {
        SecuredDoor door = new SecuredDoor(new LabDoor());
        door.open("invalid");
        door.open("$ecr@t");
        door.close();
    }
}

