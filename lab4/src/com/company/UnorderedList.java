package com.company;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {
    List<ListItem> items =new ArrayList<>();

    UnorderedList addItem(String item){
        ListItem i= new ListItem(item);
        items.add(i);
        return this;

    }

    void writeHTML(PrintStream out){
        out.printf("<ul>");
        for(ListItem element : items){
            element.writeHTML(out);
        }
        out.printf("</ul>");
    }
}
