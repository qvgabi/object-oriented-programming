package com.company;


import java.io.IOException;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader("no-header.csv", ";", false);
        try {
            while (reader.next()) {
                int id = reader.getInt(0);
                String name = reader.get(1);
                double flat = reader.getDouble(5);
                System.out.printf(Locale.US, "%d %s %f \n", id, name, flat);
            }
        }
        catch (Exception e) {
            System.out.println("Error");
        }
    }



}
