package ru.geekbrains.patterns.bridge;

public class DarkTheme implements Theme {
    @Override
    public String getColor() {
        return "Dark Black";
    }
}
