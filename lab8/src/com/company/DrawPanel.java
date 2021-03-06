package com.company;



import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawPanel extends JPanel {


    List<XmasShape> shapes = new ArrayList<>();

    DrawPanel() {
        setBackground(new Color(249, 5, 7, 181));
        //Bubble b=new Bubble(200,400,0.5, Color.blue,Color.blue);
        //shapes.add(b);
        Branch br4 = new Branch(500, 270, 1.1, new Color(52, 130, 80));
        shapes.add(br4);
        Branch br3 = new Branch(500, 200, 0.9, new Color(54, 142, 90, 255));
        shapes.add(br3);
        Branch br2 = new Branch(500, 140, 0.7, new Color(56, 152, 98, 255));
        shapes.add(br2);
        Branch br1 = new Branch(500, 100, 0.5, new Color(71, 170, 101, 255));
        shapes.add(br1);




        Star s = new Star(440,35,1, new Color(255, 229, 58));
        shapes.add(s);


        Pien p = new Pien(470, 490, 0.7, new Color(160, 82, 27));
        shapes.add(p);

        for (int i = 0; i < 70; i++) {
            Random r = new Random();
            Snow f = new Snow(r.nextInt(900), r.nextInt(600), 0.2, new Color(255, 255, 255));
            shapes.add(f);
        }


        Bubble b = new Bubble(350, 260, 0.5, new Color(255, 193, 51));
        shapes.add(b);
        Bubble b1 = new Bubble(600, 260, 0.5, new Color(255, 126, 52));
        shapes.add(b1);
        Bubble b2 = new Bubble(430, 330, 0.5, new Color(255, 126, 52));
        shapes.add(b2);
        Bubble b3 = new Bubble(520, 400, 0.5, new Color(255, 96, 179));
        shapes.add(b3);
        Bubble b4 = new Bubble(320, 460, 0.5, new Color(255, 188, 46));
        shapes.add(b4);
        Bubble b5 = new Bubble(500, 210, 0.5, new Color(255, 96, 179));
        shapes.add(b5);
        Bubble b6 = new Bubble(630, 460, 0.5, new Color(168, 102, 255));
        shapes.add(b6);

        Bubble zaspa1 = new Bubble(-300, 500, 10, new Color(231, 238, 255));
        shapes.add(zaspa1);

        Bubble zaspa2 = new Bubble(400, 500, 11, new Color(230, 255, 252));
        shapes.add(zaspa2);


        }





    public void paintComponent (Graphics g){
        super.paintComponent(g);
        for (XmasShape s : shapes) {
            s.draw((Graphics2D) g);
        }

    }





}



