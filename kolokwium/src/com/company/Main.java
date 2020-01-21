package com.company;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {
        AuditorList audit = new AuditorList();
        String filename = "lista-bieglych.csv";
        audit.read(filename);
        //audit.list(System.out);


        //zad a

        audit.filter(s->s.name.isEmpty()).list(System.out);

         //zad b
        audit.filter(a -> a.zakres.contains("pojazdy")).sortInplaceByName().list(System.out);

        //zad c
        Map<String, Integer> miasta = new TreeMap<>();
        for (Auditor u : audit.auditor){
            if (!miasta.containsKey(u.adres)){
                miasta.put(u.adres, 1);
            }
            else {
                miasta.replace(u.adres, miasta.get(u.adres)+1);
            }
        }
        System.out.println("Miasta: ");
        System.out.println(miasta.toString());

        //zad d
        audit.filter(a->a.telefon.contains("kom")).list(System.out);


    }
}
