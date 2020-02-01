package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BouncingBallsPanel extends JPanel {
    AnimationThread animationEngine = new AnimationThread();
    boolean stop= true;

    static class Ball{
        int x;
        int y;
        double vx;
        double vy;
        Color color;


        public Ball(int x, int y, double vx, double vy, Color color) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.color = color;
        }


        public Ball() {
        }

        public void collision(Ball other) {
            if (this==other)
                return;
            double dx=this.x-other.x;
            double dy=this.y-other.y;

            double dist = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));
            double nx=dx / dist;
            double ny=dy / dist;
            double dvx=vx - other.vx;
            double dvy=vy - other.vy;
            double stren = dvx * nx + dvy * ny;
            double[] impulse = {nx * stren, ny * stren};
            vx-= impulse[0];
            other.vx+= impulse[0];
            vy-= impulse[1];
            other.vy+= impulse[1];
        }
    }

    List<Ball> balls = new ArrayList<>();

    class AnimationThread extends Thread{
        public void start(){
            Dimension size = BouncingBallsPanel.this.getSize();
            int height=size.height;
            int width=size.width;

            Random random=new Random();

            for (int i = 0; i < 10; i++) {
                balls.add(new Ball(random.nextInt(width), random.nextInt(height),
                        random.nextInt(5),random.nextInt(5),Color.PINK));
            }

        }
        public void run(){
            System.out.printf("sdgsdfgd");
            //przesuń kulki
            //wykonaj odbicia od ściany
            //wywołaj repaint
            //uśpij
            for(;;){
                if(stop) break;
                try {
                    Dimension size = BouncingBallsPanel.this.getSize();
                    int height = size.height;
                    int width = size.width;


                    for (Ball ball : balls) {
                        ball.x += ball.vx;
                        ball.y += ball.vy;

                        if (ball.x < 0) {
                            ball.x = -ball.x;
                            ball.vx = -ball.vx;
                        } else if (ball.x > width) {
                            ball.x = 2 * width - ball.x;
                            ball.vx = -ball.vx;
                        }

                        if (ball.y < 0) {
                            ball.y = -ball.y;
                            ball.vy = -ball.vy;
                        } else if (ball.y > height) {
                            ball.y = 2 * height - ball.y;
                            ball.vy = -ball.vy;
                        }
                        for (Ball other : balls) {
                            ball.collision(other);
                        }
                    }
                    repaint();
                    sleep(20);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }


    BouncingBallsPanel(){
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Ball b : balls)
            g2d.setColor(Color.PINK);
    }

    void onStart(){
        stop=false;
        if (!animationEngine.isAlive()) {
            System.out.println("Start or resume animation thread");
            animationEngine = new AnimationThread();
            animationEngine.start();
        }
        System.out.println("Start or resume animation thread");
    }

    void onStop(){
        System.out.println("Suspend animation thread");
        stop=true;
    }

    void onPlus(){
        System.out.println("Add a ball");
        Ball b = new Ball();
        balls.add(b);
    }

    void onMinus(){
        System.out.println("Remove a ball");
        balls.remove(0);
    }
}