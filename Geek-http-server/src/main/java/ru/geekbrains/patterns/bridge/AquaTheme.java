package ru.geekbrains.patterns.bridge;

public class AquaTheme implements Theme {
    @Override
    public String getColor() {
        return "Light blue";
    }
}
