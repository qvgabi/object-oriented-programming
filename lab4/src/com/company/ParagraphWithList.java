package com.company;

public class ParagraphWithList extends Paragraph{


    UnorderedList list = new UnorderedList();


    ParagraphWithList addItemToList(String item){
        list.addItem(item);
        return this;
    }

    ParagraphWithList setContent(String content){
        list.addItem(new ListItem(content));
        return this;
    }
}
