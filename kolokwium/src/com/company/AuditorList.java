package com.company;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AuditorList {
    List<Auditor> auditor = new ArrayList<>();

    public AuditorList(List<Auditor> au) {
        this.auditor = auditor;
    }

    public AuditorList(Stream<Auditor> auditorStream) {
        auditor = auditorStream.collect(Collectors.toList());
    }

    public AuditorList() {

    }

    public void read(String filename) throws IOException {
        CSVReader reader = new CSVReader(filename, ";", true);

        while (reader.next()) {
            Auditor au = new Auditor();

            if (!reader.isMissing(0)) {
                au.lp = reader.get(0);
            }
            if (!reader.isMissing(1)) {
                au.data_wpisania = reader.get(1);
            }
            if (!reader.isMissing(2)) {
                au.name = reader.get(2);
            }
            if (!reader.isMissing(3)) {
                au.adres = reader.get(3);
            }
            if (!reader.isMissing(4)) {
                au.telefon = reader.get(4);
            }
            if (!reader.isMissing(5)) {
                au.email = reader.get(5);
            }
            if (!reader.isMissing(6)) {
                au.zakres = reader.get(6);
            }
            if (!reader.isMissing(7)) {
                au.numer = reader.get(7);
            }
            if (!reader.isMissing(8)) {
                au.data_wpisu = reader.get(8);
            }
            if (!reader.isMissing(9)) {
                au.data_wykreslenia = reader.get(9);
            }
            auditor.add(au);

        }
    }

    public void list(PrintStream out) {
        for (Auditor a : auditor) {
            out.println(a.toString());
        }
    }

    AuditorList filter(Predicate<Auditor> pred) {
        return new AuditorList(auditor.stream().filter(pred));
    }


    AuditorList sortInplaceByName(){
        class AuditorListComparator implements Comparator<Auditor> {
                         @Override
                         public int compare(Auditor au1, Auditor au2) {
                             return au1.name.compareTo(au2.name);
                         }
                     }
                auditor.sort(new AuditorListComparator());
        return this;


    }

}





































