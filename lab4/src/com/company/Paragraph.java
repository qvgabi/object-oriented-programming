package com.company;


import java.io.PrintStream;

public class Paragraph {


    String content;

    Paragraph(){
        this.setContent("");
    }

    Paragraph(String content){
        this.setContent(content);
    }

    Paragraph setContent(String newcontent){
        this.content=newcontent;
        return this;

    }

    void writeHTML(PrintStream out){
        out.printf("<p>%s</p>\n",content);
    }
}
