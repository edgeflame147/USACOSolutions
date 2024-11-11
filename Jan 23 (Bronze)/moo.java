import java.util.*;
import java.io.*;

public class moo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int Q = input.nextInt();
        input.nextLine();
        
        for(int i=0; i<Q; i++) {
            String str = input.nextLine();
            int min = str.length();
            if(str.length()<3) { System.out.println(-1); continue; }
            for(int j=0; j<str.length()-2; j++) {
                int change = 0;
                if(str.charAt(j+1) == 'O') {
                    if(str.charAt(j) != 'M') change++;
                    if(str.charAt(j+2) != 'O') change++;
                    
                    int a = str.length()-3+change;
                    if(a<min) min = a;
                }
            }
            if(min == str.length()) min = -1;
            System.out.println(min);
        }
        
        input.close();
    }
}