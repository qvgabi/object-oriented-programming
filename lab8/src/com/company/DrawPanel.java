package com.company;



import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {


    List<XmasShape> shapes = new ArrayList<>();

    DrawPanel(){
        setBackground(new Color(237, 39, 21));
//        Bubble b=new Bubble(200,400,0.5, Color.blue,Color.blue);
//        shapes.add(b);
        Branch br1=new Branch(500,100,0.5,new Color(45, 128, 54));
        shapes.add(br1);
        Branch br2=new Branch(500,140,0.7,new Color(45, 128, 54));
        shapes.add(br2);
        Branch br3=new Branch(500,200,0.9,new Color(45, 128, 54));
        shapes.add(br3);
        Branch br4=new Branch(500,270,1.1,new Color(45, 128, 54));
        shapes.add(br4);


    }



    public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for(XmasShape s:shapes){
                s.draw((Graphics2D)g);
            }

    }





}



