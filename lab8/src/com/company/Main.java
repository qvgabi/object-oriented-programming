package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        JFrame frame = new JFrame("Choinka");
        frame.setContentPane(new DrawPanel()); //w tym miejscu rysuje
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);


    }
}
