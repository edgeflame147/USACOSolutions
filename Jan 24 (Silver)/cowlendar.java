import java.util.*;
import java.io.*;
public class cowlendar {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long[] a = new long[N];
        long ans = 0;
        Set<Long> s = new HashSet<>();
        long minimum = Long.MAX_VALUE;
        for(int i = 0; i<N; i++) {
            a[i] = sc.nextLong();
            s.add(a[i]);
            minimum = Math.min(minimum, a[i]);
        }
        minimum /= 4;
        if(s.size() <= 3) {
            System.out.println(minimum * (minimum + 1) / 2);
            sc.close();
            return;
        }

        for(int i = 1;i <= 1000000; i++) {
            if(i>minimum) break;
            Set<Long> tmps = new HashSet<>();
            for(long num: s){
                tmps.add(num % i);
                if(tmps.size() == 4){
                    break;
                }
            }
            if(tmps.size() < 4) ans += i;
        }
        
        if (minimum > 1000000) {
            Set<Long> factorSet = new HashSet<>();
            for (long num1 : s) {
                for (long num2 : s) {
                    if (num2 - num1 > 1000000) { 
                        for (int k = 1; k <= 4000; ++k) {
                            if ((num2 - num1) % k == 0) {
                                factorSet.add((num2 - num1) / k); 
                            }
                        }
                    }
                }
            }

            for (long factor : factorSet) {
                if (factor > minimum) continue;
                if (factor <= 1000000) continue;
                Set<Long> tmps = new HashSet<>();
                for (long num : s) {
                    tmps.add(num % factor);
                    if (tmps.size() == 4) {
                        break;
                    }
                }
                if (tmps.size() < 4) {
                    ans += factor;
                }
            }
        }

        System.out.println(ans);
        sc.close();
    }
}