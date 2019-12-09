package com.company;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    Map<Long, AdminUnit> idToUnit = new HashMap<>();
    Map<AdminUnit, Long> unitToParentId = new HashMap<>();

    public AdminUnitList() {
    }

    public AdminUnitList(Stream<AdminUnit> adminUnitStream) {
        units = adminUnitStream.collect(Collectors.toList());
    }

    /**
     * Czyta rekordy pliku i dodaje do listy
     * @param filename nazwa pliku
     */
    public void read(String filename) throws IOException {
        CSVReader reader = new CSVReader(filename, ",", true);
        while (reader.next()) {
            AdminUnit au = new AdminUnit();

            if (!reader.isMissing("name")) {
                au.name = reader.get("name");
            }
            if (!reader.isMissing("admin_level")) {
                au.adminLevel = reader.getInt("admin_level");
            }
            if (!reader.isMissing("population")) {
                au.population = reader.getDouble("population");
            }
            if (!reader.isMissing("area")) {
                au.area = reader.getDouble("area");
            }
            if (!reader.isMissing("density")) {
                au.density = reader.getDouble("density");
            }
            if (!reader.isMissing("id")) {
                idToUnit.put(reader.getLong("id"), au);
            }
            if (!reader.isMissing("parent")) {
                unitToParentId.put(au, reader.getLong("parent"));
            }

            if (!reader.isMissing("x1") && !reader.isMissing("y1")) {
                au.bbox.addPoint(reader.getDouble("x1"), reader.getDouble("y1"));
            }
            if (!reader.isMissing("x2") && !reader.isMissing("y2")) {
                au.bbox.addPoint(reader.getDouble("x2"), reader.getDouble("y2"));
            }
            if (!reader.isMissing("x3") && !reader.isMissing("y3")) {
                au.bbox.addPoint(reader.getDouble("x3"), reader.getDouble("y3"));
            }
            if (!reader.isMissing("x4") && !reader.isMissing("y4")) {
                au.bbox.addPoint(reader.getDouble("x4"), reader.getDouble("y4"));
            } else { //if(reader.isMissing("parent")) {
                unitToParentId.put(au, null);
            }
            units.add(au);
        }
        for (AdminUnit a : units) {
            if (unitToParentId.containsKey(a)) {
                Long parentId = unitToParentId.get(a);
                if (idToUnit.containsKey(parentId)) {
                    a.parent = idToUnit.get(parentId);
                    a.parent.children.add(a);
                }
            }
        }
    }


    /**
     * Wypisuje zawartość korzystając z AdminUnit.toString()
     * @param out
     */
    void list(PrintStream out){
        for (AdminUnit a : units) {
            out.println(a.toString());
        }
    }
    /**
     * Wypisuje co najwyżej limit elementów począwszy od elementu o indeksie offset
     * @param out - strumień wyjsciowy
     * @param offset - od którego elementu rozpocząć wypisywanie
     * @param limit - ile (maksymalnie) elementów wypisać
     */
    void list(PrintStream out,int offset, int limit ){
        for (int i = offset; i < (offset + limit); i++) {
            out.println(units.get(i).toString());
        }
    }

    /*public AdminUnit get(int index) {
        return units.get(index);
    }
*/
    /**
     * Zwraca nową listę zawierającą te obiekty AdminUnit, których nazwa pasuje do wzorca
     * @param pattern - wzorzec dla nazwy
     * @param regex - jeśli regex=true, użyj finkcji String matches(); jeśli false użyj funkcji contains()
     * @return podzbiór elementów, których nazwy spełniają kryterium wyboru
     */
    AdminUnitList selectByName(String pattern, boolean regex){
        AdminUnitList ret = new AdminUnitList();
        // przeiteruj po zawartości units
        // jeżeli nazwa jednostki pasuje do wzorca dodaj do ret

        for (AdminUnit a : units) {
            if (regex) {
                if (a.name.matches(pattern)) ret.units.add(a);
            } else {
                if (a.name.contains(pattern)) ret.units.add(a);
            }
        }
        return ret;

    }

    //uzupelnienie brakujacych wartosci dla wszystkich jednostek
    public void fixAll() {
        for (AdminUnit u : units) {
            u.fixMissingValues();
        }
    }

    /**
     * Zwraca listę jednostek sąsiadujących z jendostką unit na tym samym poziomie hierarchii admin_level.
     * Czyli sąsiadami wojweództw są województwa, powiatów - powiaty, gmin - gminy, miejscowości - inne miejscowości
     * @param unit - jednostka, której sąsiedzi mają być wyznaczeni
     * @param maxdistance - parametr stosowany wyłącznie dla miejscowości, maksymalny promień odległości od środka unit,
     *                    w którym mają sie znaleźć punkty środkowe BoundingBox sąsiadów
     * @return lista wypełniona sąsiadami
     */
    AdminUnitList getNeighbors(AdminUnit unit, double maxdistance) {
        AdminUnitList neighborsList = new AdminUnitList();
        for (AdminUnit au : units) {
            if (au != unit) {
                if (unit.adminLevel == au.adminLevel) {
                    if (unit.adminLevel == 8) {
                        if (unit.bbox.distanceTo(au.bbox) < maxdistance) {
                            neighborsList.units.add(au);
                        }
                    } else {
                        if (unit.bbox.intersects(au.bbox)) {
                            neighborsList.units.add(au);
                        }
                    }
                }
            }
        }
        return neighborsList;
    }

    /**
     * Sortuje daną listę jednostek (in place = w miejscu)
     * @return this
     */
    AdminUnitList sortInplaceByName() {
        class AdminUnitListComparator implements Comparator<AdminUnit> {
            @Override
            public int compare(AdminUnit au1, AdminUnit au2) {
                return au1.name.compareTo(au2.name);
            }
        }
        units.sort(new AdminUnitListComparator());
        return this;
    }

    /**
     * Sortuje daną listę jednostek (in place = w miejscu)
     * @return this
     */
    AdminUnitList sortInplaceByArea(){
        units.sort(new Comparator<AdminUnit>() {
            @Override
            public int compare(AdminUnit au1, AdminUnit au2) {
                return Double.compare(au1.area, au2.area);
            }
        });
        return this;

    }

    /**
     * Sortuje daną listę jednostek (in place = w miejscu)
     * @return this
     */
    AdminUnitList sortInplaceByPopulation(){
        units.sort(Comparator.comparingDouble(au -> au.population));
        return this;
    }

    AdminUnitList sortInplace(Comparator<AdminUnit> cmp) {
        units.sort(cmp);
        return this;
    }

    AdminUnitList sort(Comparator<AdminUnit> cmp) {
        AdminUnitList aul = new AdminUnitList();
        Collections.copy(aul.units, units);
        aul.sortInplace(cmp);
        return aul;
    }

    /**
     *
     * @param pred referencja do interfejsu Predicate
     * @return nową listę, na której pozostawiono tylko te jednostki,
     * dla których metoda test() zwraca true
     */

    AdminUnitList filter(Predicate<AdminUnit> pred) {
        return new AdminUnitList(units.stream().filter(pred));
    }

    /**
     * Zwraca co najwyżej limit elementów spełniających pred
     * @param pred - predykat
     * @param limit - maksymalna liczba elementów
     * @return nową listę
     */

    AdminUnitList filter(Predicate<AdminUnit> pred, int limit) {
        return new AdminUnitList(units.stream().filter(pred).limit(limit));


    }

    /**
     * Zwraca co najwyżej limit elementów spełniających pred począwszy od offset
     * Offest jest obliczany po przefiltrowaniu
     * @param pred - predykat
     * @param - od którego elementu
     * @param limit - maksymalna liczba elementów
     * @return nową listę
     */

    AdminUnitList filter(Predicate<AdminUnit> pred, int offset, int limit) {
        return new AdminUnitList(units.stream().filter(pred).skip(offset).limit(limit));
    }






}
