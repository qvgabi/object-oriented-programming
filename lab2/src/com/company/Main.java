package com.company;

public class Main {

    public static void main(String[] args) {
        Matrix m = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
        Matrix col = m.sumCols();

        col.toString();
    }
}
