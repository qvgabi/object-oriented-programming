package com.company;

import java.awt.*;

public class Pien implements XmasShape {

    int x;
    int y;
    double scale;
    Color fillColor;

    Pien(int x, int y, double scale, Color fillColor){
        this.x=x;
        this.y=y;
        this.scale=scale;

        this.fillColor=fillColor;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(fillColor);
        g2d.fillRect(0,0,100,100);

    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}

