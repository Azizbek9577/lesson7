package lesson7;

import java.io.*;
import java.util.*;

class Employee {
    String name;
    int age;
    String position;

    Employee(String name, int age, String position) {
        this.name = name;
        this.age = age;
        this.position = position;
    }

    @Override
    public String toString() {
        return name + "," + age + "," + position;
    }
}

public class EmployeeData {

    public static void saveEmployeesToFile(List<Employee> employees, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (Employee employee : employees) {
            writer.write(employee.toString());
            writer.newLine();
        }
        writer.close();
    }

    public static void readEmployeesFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            System.out.println("Name: " + data[0] + ", Age: " + data[1] + ", Position: " + data[2]);
        }
        reader.close();
    }

    public static void main(String[] args) throws IOException {
        List<Employee> employees = Arrays.asList(
                new Employee("John Doe", 30, "Developer"),
                new Employee("Jane Smith", 28, "Designer"),
                new Employee("Mike Johnson", 35, "Manager")
        );

        String filename = "employees.txt";
        saveEmployeesToFile(employees, filename);
        System.out.println("Employee data from file:");
        readEmployeesFromFile(filename);
    }
}
