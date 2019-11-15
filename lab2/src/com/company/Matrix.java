package com.company;

public class Matrix {
    double[] data;
    int rows;
    int cols;

    //...
    Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows * cols];
    }

    Matrix(double[][] d) {
        this.rows = d.length;

        this.cols=d[0].length;
        for(int i=0;i<d.length;i++){
            if (cols<d[i].length){
                cols=d[i].length;
            }
        }

        this.data = new double[rows * cols];

        int k = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j < d[i].length) {
                    data[k] = d[i][j];
                } else
                    data[k] = 0;
                k++;
            }
        }
    }

    double[][] asArray() {
        double[][] tab = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tab[i][j] = data[i*cols+j];

            }
        }
        return tab;
    }

    double get (int r, int c){
        return data [r*cols+c];
    }

    void set (int r, int c,double value){
        data[r*cols+c]=value;
    }

    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for(int i=0;i<rows;i++){
            buf.append("[");
            for(int j=0;j<cols;j++){
                buf.append(data[i*cols+j]);
                buf.append(",");
            }
        }

        buf.append("]");

        return buf.toString();
    }

    void reshape(int newRows,int newCols){
        if(rows*cols != newRows*newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",rows,cols,newRows,newCols));
        else{
            rows=newRows;
            cols=newCols;
        }


    }

    int [] shape(){
        int [] size = new int[2];

        size[0]=cols;
        size[1]=rows;
        return size;
    }


    Matrix add (Matrix m){
        Matrix result = new Matrix(this.rows, this.cols);
        if( cols==m.cols && rows==m.rows){
            for(int i=0;i<rows;i++){
                for (int j=0;  j<cols;j++){
                    result.data[i*cols+j]=this.data[i*cols+j]+m.data[i*cols+j];
                }
            }

        }
        else
            throw new RuntimeException(String.format("ZLE WYMIARY"));

        return result;

    }

    Matrix sub(Matrix m){
        Matrix result = new Matrix(this.rows, this.cols);
        if( cols==m.cols && rows==m.rows){
            for(int i=0;i<rows;i++){
                for (int j=0;  j<cols;j++){
                    result.data[i*cols+j]=this.data[i*cols+j]-m.data[i*cols+j];
                }
            }

        }
        else
            throw new RuntimeException(String.format("ZLE WYMIARY"));

        return result;

    }

    Matrix mul(Matrix m){
        Matrix result = new Matrix(this.rows, this.cols);
        if (rows == m.rows && cols == m.cols) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    result.data[i*cols+j] = this.data[i*cols+j] * m.data[i*cols+j];
                }
            }
        }
        else{
            throw new RuntimeException("ZLE WYMIARY");
        }
        return result;
    }

    Matrix div(Matrix m){
        Matrix result = new Matrix(this.rows, this.cols);
        if (rows == m.rows && cols == m.cols) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if(m.data[i*cols+j]!=0) {
                        result.data[i*cols+j] = this.data[i*cols+j] / m.data[i*cols+j];
                    }
                    else{
                        throw new RuntimeException("NIE WOLNO DZIELIC PRZEZ 0");
                    }
                }
            }
        }
        else{
            throw new RuntimeException("DIVIDE --> A co to za wymiary takie brzydkie?");
        }
        return result;
    }

    Matrix dot(Matrix m) {
        if (m.cols != this.rows) {
            throw new RuntimeException(String.format("ZLE WYMIARY"));
        } else {
            Matrix result = new Matrix(this.rows, m.cols);
            for (int i = 0; i < this.cols; i++) {
                for (int j = 0; j < m.cols; j++) {
                    result.set(i, j, 0);
                    for (int k = 0; k < this.cols; k++) {
                        result.set(i, j, result.get(i, j) + this.get(i, k) * m.get(k, j));
                    }
                }
            }
            return result;
        }
    }

    double frobenius(){
        double frob = 0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                frob += Math.pow(data[i*cols+j], 2);
            }
        }
        return Math.pow(frob, 1/2);
    }


    public static Matrix random(int rows, int cols){
        Matrix m = new Matrix(rows,cols);
        Random r = new Random();
        m.set(0,0, r.nextDouble());
        //... wypełnij wartościami losowymi
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                m.data[i*cols+j] = r.nextInt(50);
            }
        }
        return m;
    }

    public static Matrix eye(int n){
        Matrix m = new Matrix(n,n);
        //... wypełnij jedynkami na przekątnej
        for(int i=0;i<m.rows;i++){
            for(int j=0;j<m.cols;j++){
                if(i == j){
                    m.data[i*m.cols+j] = 1;
                }
                else{
                    m.data[i*m.cols+j] = 0;
                }
            }
        }
        return m;
    }

    Matrix sumCols(){
        Matrix result = new Matrix(new double[this.rows][1]);
        for(int i=0; i<this.rows;i++){
            for(int j=0;j<this.cols;j++){
                result.data[i]+=this.get(i,j);
            }
        }
        return result;
    }

}