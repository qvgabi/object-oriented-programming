package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class AdminUnit {
    List<AdminUnit> children = new ArrayList<>();
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    BoundingBox bbox = new BoundingBox();


    public String toString() {
        StringBuilder sb = new StringBuilder(String.format(
                "Nazwa: %s, typ jednostki: %d, populacja: %f, powierzchnia: %f, gestosc zaludnienia: %f",
                this.name, this.adminLevel, this.population, this.area, this.density));
        return sb.toString();
    }

    //uzupelnienie brakujacych wartosci dla danego elementu
    protected void fixMissingValues(){
        if(this.density == 0 && this.parent != null){
            if(this.parent.density == 0) {
                this.parent.fixMissingValues();
            }
            this.density = this.parent.density;
        }
        if(this.population == 0){
            this.population = this.area*this.density;
        }
    }
}
