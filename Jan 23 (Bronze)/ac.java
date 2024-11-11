import java.util.*;
import java.io.*;

public class ac {
    static int[] air = new int[101];
    static int[] s = new int[21];
    static int[] t = new int[21];
    static int[] c = new int[21];
    static int[] a = new int[11];
    static int[] b = new int[11];
    static int[] p = new int[11];
    static int[] m = new int[11];
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int M = input.nextInt();
        int z = Integer.MAX_VALUE;
        for (int i=0; i<N; i++) {
            s[i] = input.nextInt();
            t[i] = input.nextInt();
            c[i] = input.nextInt();
        }
        for (int i=0; i<M; i++) {
            a[i] = input.nextInt();
            b[i] = input.nextInt();
            p[i] = input.nextInt();
            m[i] = input.nextInt();
        }
        for (int i = 0; i<Math.pow(2, M); i++) { 
            int num = i;
            int[] bin = new int[M];
            int run = M-1;
            while(num>1) {
                bin[run--] = num%2;
                num /= 2;
            }
            if(i!=0) bin[run] = 1;
        
            int cost = 0;
            reset();
            for (int j=0; j<M; j++) {
                if (bin[j] == 1) { 
                    cost += m[j];
                    for (int k = a[j]; k <= b[j]; k++)
                        air[k] += p[j];
                }
            }
            if (meetCow(N)) z = Math.min(z, cost); 
        }
        System.out.println(z);
    }
    
    public static void reset() {
        for (int i = 1; i <= 100; i++) air[i] = 0;
    }
    
    public static boolean meetCow(int N) {
        for (int i=0; i<N; i++){
            for (int j=s[i]; j <= t[i]; j++) {
               if (air[j] < c[i]) return false; 
            }
        }
        return true;
    }
}