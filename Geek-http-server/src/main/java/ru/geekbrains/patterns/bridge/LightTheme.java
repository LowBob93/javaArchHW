package ru.geekbrains.patterns.bridge;

public class LightTheme implements Theme {
    @Override
    public String getColor() {
        return "Off white";
    }
}
