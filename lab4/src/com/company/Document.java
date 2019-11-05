package com.company;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();


    Document setTitle(String title){
        this.title = title;
        return this;
    }

    Document setPhoto(String photoUrl){
        this.photo=new Photo(photoUrl);
    }

    Section addSection(String sectionTitle){
        Section section =  new Section(sectionTitle);
        sections.add(section);
        return section;
    }
    Document addSection(Section s){
        return this;
    }


    void writeHTML(PrintStream out){
        // zapisz niezbędne znaczniki HTML
        // dodaj tytuł i obrazek
        // dla każdej sekcji wywołaj section.writeHTML(out)

        out.printf("<!DOCTYPE html>\n" +
                "<html lang=\"pl\">\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<title>CV</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>%s</h1>\n", title);
        photo.writeHTML(out);
        for ( Section element : sections){
            element.writeHTML(out);
        }
        out.printf("</body>\n" +
                "</html>\n");
    }
    }
}
