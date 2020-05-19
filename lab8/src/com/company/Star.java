package com.company;

import java.awt.*;



public class Star implements XmasShape {

    int x;
    int y;
    double scale;
    Color fillColor;


    Star(int x, int y, double scale, Color fillColor){
        this.x=x;
        this.y=y;
        this.scale=scale;

        this.fillColor=fillColor;
    }

    @Override
    public void render(Graphics2D g2d) {
        int x[] = {60, 80, 120, 93, 100, 60, 23, 30, 0, 40};
        int y[] = {0, 37, 43, 73, 113, 97, 113, 73, 43, 37};

        g2d.setColor(fillColor);
        //g2d.fillPolygon(pentx,penty,pentx.length);
        g2d.fillPolygon(x,y,x.length);


    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
