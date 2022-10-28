package ru.geekbrains.patterns.facade;

public class FacadeMain {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade(new Computer());
        computer.turnOn();
        computer.turnOff();
    }
}
