package ru.geekbrains.patterns.composite;

public class CompositeMain {
    public static void main(String[] args) {

        Employee dave = new Developer("Dave Jones", 2000.0);
        Employee jhon = new Designer("Jhon Dorian", 500.0);
        Organization organization = new Organization();
        organization.addEmployee(dave);
        organization.addEmployee(jhon);
        System.out.println("Net salaries: " + organization.getNetSalaries());

    }
}
