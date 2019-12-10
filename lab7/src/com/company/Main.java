package com.company;

import java.io.IOException;
import java.util.Comparator;
import java.util.Locale;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        AdminUnitList aul = new AdminUnitList();

        try{
            aul.read("admin-unit.csv");
            aul.fixAll();
            aul.list(System.out, 2,3); //list
            aul.selectByName("Kraków", false).list(System.out); //select
            AdminUnit au = aul.units.get(4454);
            AdminUnitList aulist = aul.getNeighbors(au, 14); //neighbours
            aulist.list(System.out);
            //czas wyszukiwania
            double time1 = System.nanoTime()/1e6;
            double time2 = System.nanoTime()/1e6;
            System.out.printf(Locale.US,"t2-t1=%f\n",time2-time1);

            aul.filter(a -> a.name.startsWith("G")).sortInplaceByName().list(System.out);
            aul.filter(a -> a.adminLevel == 5).list(System.out);
            aul.filter(a -> a.population < 1).filter(a -> a.name.startsWith("M")).list(System.out);
            aul.filter(a -> a.name.startsWith("W") || a.name.endsWith("a")).list(System.out);
            aul.filter(a -> a.name.startsWith("Żu") && a.name.endsWith("e"), 1, 3).list(System.out);

            AdminUnitQuery query = new AdminUnitQuery()
                    .selectFrom(aul)
                    .where(a->a.area>1500)
                    .or(a->a.name.startsWith("Wa"))
                    .sort(Comparator.comparingDouble(a -> a.area))
                    .limit(10);
            query.execute().list(System.out);



        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}