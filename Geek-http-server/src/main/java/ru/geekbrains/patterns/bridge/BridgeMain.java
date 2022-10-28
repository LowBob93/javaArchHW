package ru.geekbrains.patterns.bridge;

public class BridgeMain {
        public static void main(String[] args) {
            Theme darkTheme = new DarkTheme();
            Theme lightTheme = new LightTheme();
            Theme aquaTheme = new AquaTheme();

            WebPage about = new About(darkTheme);
            WebPage career = new Careers(aquaTheme);
            WebPage projects = new Projects(lightTheme);
            System.out.println(about.getContent());
            System.out.println(career.getContent());
            System.out.println(projects.getContent());
        }
}