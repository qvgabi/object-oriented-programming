package com.company;


import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Predicate;

public class zad2 {
    static double[] array;
    static BlockingQueue<Double> results = new ArrayBlockingQueue<Double>(100);

    static void initArr(int size) {
        array = new double[size];
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = r.nextInt(100);
        }
    }

    static class zad2Calc extends Thread {

        private final int start;
        private final int end;
        double max = 0;
        Predicate<Integer> p;

        zad2Calc(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void run() {
            double sum = 0;
            for (int i = 0; i < end; i++) {
                if (p.equals(array[i])) sum += 1;
            }

            System.out.printf("%f\n", sum);
        }
    }


        static void parallelzad2(int cnt) throws InterruptedException {
            zad2Calc threads[] = new zad2Calc[ cnt];
            int thread_len = array.length / cnt;
            for (int i = 0; i < cnt; i++) {
                threads[i] = new zad2Calc(i * thread_len, (i + 1) * thread_len);
            }
            double t1 = System.nanoTime() / 1e6;
            for (zad2Calc mc : threads) {
                mc.run();
            }
            double t2 = System.nanoTime() / 1e6;
            for (zad2Calc mc : threads) {
                results.put(mc.max);
            }

            double m = 0;
            for (zad2Calc mc : threads) {
                double mt = results.take();
                if (mt > m) {
                    m = mt;
                }
            }

            double t3 = System.nanoTime() / 1e6;
            System.out.printf("t2-t1=%f, t3-t1=%f\n", t2 - t1, t3 - t1);
        }

        public static void main(String[] args) {
            initArr(10000000);
            try {
                parallelzad2(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }





