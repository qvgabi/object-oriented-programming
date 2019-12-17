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
        setBackground(new Color(235, 40, 20));
        //Bubble b=new Bubble(200,400,0.5, Color.blue,Color.blue);
        //shapes.add(b);
        Branch br1 = new Branch(500, 100, 0.5, new Color(52, 130, 80));
        shapes.add(br1);
        Branch br2 = new Branch(500, 140, 0.7, new Color(52, 130, 80));
        shapes.add(br2);
        Branch br3 = new Branch(500, 200, 0.9, new Color(52, 130, 80));
        shapes.add(br3);
        Branch br4 = new Branch(500, 270, 1.1, new Color(52, 130, 80));
        shapes.add(br4);

        Pien p = new Pien(470, 490, 0.7, new Color(160, 82, 27));
        shapes.add(p);

        for (int i = 0; i < 70; i++) {
            Random r = new Random();
            Snow f = new Snow(r.nextInt(900), r.nextInt(600), 0.2, new Color(255, 255, 255));
            shapes.add(f);
        }




        for (int k = 3; k < 6; k++) {
            Random generator = new Random();
            for (int i = 0; i < 3; i++) {
                int rand1 = generator.nextInt(20);
                int rand2 = generator.nextInt(5);
                Bubble b = new Bubble(5 * rand1 + 200, 10 * rand2 + k * 50, 0.5, new Color(255, 193, 51));
                shapes.add(b);
            }
            for (int i = 0; i < 3; i++) {
                int rand1 = generator.nextInt(20);
                int rand2 = generator.nextInt(5);
                Bubble b = new Bubble(5 * rand1 + 200, 10 * rand2 + k * 50, 0.5, new Color(204, 102, 255));
                shapes.add(b);
            }
            for (int i = 0; i < 3; i++) {
                int rand1 = generator.nextInt(20);
                int rand2 = generator.nextInt(5);
                Bubble b = new Bubble(5 * rand1 + 200, 10 * rand2 + k * 50, 0.5, new Color(230, 0, 0));
                shapes.add(b);
            }

        }


        public void paintComponent (Graphics g){
            super.paintComponent(g);
            for (XmasShape s : shapes) {
                s.draw((Graphics2D) g);
            }

        }

    }





}



