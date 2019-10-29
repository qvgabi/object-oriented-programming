package com.company;

import java.io.PrintStream;

public class ListItem {
    String content;
    ListItem(){}

    ListItem(String content){
        this.content=content;
    }
    void set_Item(String newitem){
        this.content=newitem;
    }

    void writeHTML(PrintStream out){
        out.printf("<li> %s </li>", content);
    }


}
