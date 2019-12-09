package com.company;

import java.io.IOException;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
       /* int counter = 0;
        CSVReader reader = new CSVReader("admin-units.csv",",",true);
        while(reader.next() && counter<10){
            String name = reader.get(2);
            double area = reader.getDouble(5);
            //System.out.printf(Locale.US, "%s\n", name);
            System.out.printf(Locale.US, "%s area: %f\n", name, area);
            counter++;
        }*/

        AdminUnitList aul = new AdminUnitList();
        String filename = "admin-units.csv";

        aul.read(filename);
    }
}