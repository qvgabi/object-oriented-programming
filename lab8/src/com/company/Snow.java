package com.company;

import java.awt.*;

public class Snow implements XmasShape {

    int x;
    int y;
    double scale;
    Color fillColor;


    Snow(int x, int y, double scale, Color fillColor){
        this.x=x;
        this.y=y;
        this.scale=scale;
        this.fillColor=fillColor;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.translate(100, 100);
        g2d.scale(.2, .2);
        g2d.setColor(fillColor);
        for (int i = 0; i < 12; i++) {
            g2d.drawLine(0, 0, 100, 100);
            g2d.rotate(2 * Math.PI / 12);
        }

    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}

