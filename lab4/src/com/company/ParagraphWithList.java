package com.company;

import java.io.PrintStream;

public class ParagraphWithList extends Paragraph{

    UnorderedList list;

    ParagraphWithList() {
        UnorderedList list = new UnorderedList();
    }


    ParagraphWithList addItemToList(String newitem){
        ListItem item = new ListItem(newitem);
        list.addItem(item);
        return this;
    }

    ParagraphWithList setContent(String content){
        list.addItem(new ListItem(content));
        return this;
    }


    void writeHTML(PrintStream out) {
        out.printf("<p>%s\n",content);
        list.writeHTML(out);
        out.printf("</p>\n");
    }
}
