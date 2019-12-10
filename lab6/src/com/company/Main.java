package com.company;


import java.io.IOException;

public class Main {
    CSVReader reader = new CSVReader("src/lab4/CsvFiles/no-header.csv", ";", false);
    try {
        while (reader.next()) {
            int id = reader.getInt(0);
            String name = reader.get(1);
            double flat = reader.getDouble(5);
            System.out.printf("%d %s %f \n", id, name, flat);
        }
    }
        catch (Exception e) {
        System.out.println("Blebleble");
    }

}
