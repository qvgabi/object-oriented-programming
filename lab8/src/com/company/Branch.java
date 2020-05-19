package com.company;

import java.awt.*;



public class Branch implements XmasShape {

    int x;
    int y;
    double scale;
    Color fillColor;


    Branch(int x, int y, double scale, Color fillColor){
        this.x=x;
        this.y=y;
        this.scale=scale;

        this.fillColor=fillColor;
    }

    @Override
    public void render(Graphics2D g2d) {
        int x[]={-200,0,200};
        int y[]={200,50,200};
        g2d.setColor(fillColor);
        g2d.fillPolygon(x,y,x.length);


    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}

