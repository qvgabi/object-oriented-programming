package com.company;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {
    String title;
    List<Paragraph> paragraps = new ArrayList<>() ;

    void setTitle(String title){
        this.title=title;
    }
    Section addParagraph(String paragraphText){
        Paragraph paragraph=new Paragraph(paragraphText);
        paragraps.add(paragraph);
        return this;
    }
    Section addParagraph(Paragraph p){
        paragraps.add(p);
        return this;
    }
    void writeHTML(PrintStream out){
        out.printf("<div>" + "<h2> %s </h2>",title);
        for(Paragraph  p: paragraps){
            p.writeHTML(out);
        }
        out.printf("</div>");

    }
}
