package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {

    @Test
    public void testMatrix() {
        int rows=5;
        int cols=3;

        Matrix m = new Matrix(rows,cols);
        assertEquals(m.shape()[0], cols);
        assertEquals(m.shape()[1], rows);
    }

    @Test
    public void testMatrix2() {

        Matrix m = new Matrix(new double[][]{{1,2,3},{4,5}});
        int size = m.asArray().length;

        assertEquals(size,2);

    }


    @Test
    public void asArray() {
        Matrix m = new Matrix(new double[][]{{1,2,3},{4,5}});
        assertEquals(m.asArray()[1][1],5, 0);

    }

    @Test
    public void get() {
        Matrix m = new Matrix(new double[][]{{1,2,3},{4,5}});
        assertEquals(m.get(0,2),3, 0);
    }

    @Test
    public void set() {
        Matrix m = new Matrix(new double[][]{{1,2,3},{4,5}});

        m.set(1,2,6);
        assertEquals(m.get(1,2),6, 0);
    }

    @Test
    public void testToString() {
        String s= "[[1.0,2.3,4.56], [12.3,  45, 21.8]]";
        s= s.replaceAll("(\\[|\\]|\\s)+","");
        String[] t = s.split("(,)+");
        for(String x:t){
            System.out.println(String.format("\'%s\'",x ));
        }

        double[]d=new double[t.length];
        for(int i=0;i<t.length;i++) {
            d[i] = Double.parseDouble(t[i]);
        }

        double arr[][]=new double[1][];
        arr[0]=d;

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.println(arr[i][j]);
            }
        }
    }

    @Test
    public void reshape() {
        Matrix m = new Matrix(4,3);
        int row=3;
        int col=3;

        try{
            m.reshape(row,col);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }


    }

    @Test
    public void sumCols() {
        Matrix m = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
        Matrix col = m.sumCols();
        assertEquals(col.get(2,0), 24,0);

    }

    @Test
    public void add() {
        Matrix m1 = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
        Matrix m2 = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
        Matrix m3 = new Matrix(new double[][]{{2,4,6},{8,10,12},{14,16,18}});

        Matrix res = m1.add(m2);

        assertEquals(m3.data, res.data);

    }


    @Test
    public void sub() {


        Matrix m2 = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
        Matrix m3 = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
        Matrix m1 = new Matrix(new double[][]{{2,4,6},{8,10,12},{14,16,18}});

        Matrix res = m1.sub(m2);

        assertEquals(m3.data, res.data);
    }

    @Test
    public void dot() {
    }




}