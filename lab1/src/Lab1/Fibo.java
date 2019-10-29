package Lab1;

import java.util.Scanner;
import java.util.Locale;

public class Fibo {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        int n  = scan.nextInt();
        if (n>=1 && n<=45){
            int[] tab = new int[n];
            tab[1]=1;
            tab[0]=1;
            for(int i=2; i<n; i++){
                tab[i]=tab[i-1]+tab[i-2];
            }
            for(int i=0;i<n; i++){
                System.out.printf("%d\n",tab[i]);
            }
        }
    }
}
