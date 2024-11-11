import java.util.*;
import java.io.*;

public class lead {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        input.nextLine();
        String str = input.nextLine();
        int[] ends = new int[N];
        boolean[] isLead = new boolean[N];
        int hLeads = 0, gLeads = 0;
        int fG = -1, fH = -1, lG = -1, lH = -1;
        
        for(int i=0; i<N; i++) ends[i] = input.nextInt();
        for(int i=0; i<N; i++) {
            if(str.charAt(i)=='G') { fG = i; break; } 
        }
        for(int i=0; i<N; i++) {
            if(str.charAt(i)=='H') { fH = i; break; } 
        }
        for(int i=N-1; i>=0; i--) {
            if(str.charAt(i)=='G') { lG = i; break; } 
        }
        for(int i=N-1; i>=0; i--) {
            if(str.charAt(i)=='H') { lH = i; break; }
        }

        if(lG <= ends[fG]-1) {
            gLeads++;
            for(int i=fG-1; i>=0; i--) {
                if(ends[i] > fG) hLeads++; 
            }
        }
        if(lH <= ends[fH]-1) {
            hLeads++;
            for(int i=fH-1; i>=0; i--) {
                if(ends[i] > fH) gLeads++; 
            }
        }
        
        System.out.println(hLeads*gLeads);
        input.close();
    }
    
    public static boolean conAll(String str, int letter, int num) {
        int count = 0;
        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) == letter) count++; 
        }
        return count == num;
    }
    
    public static boolean capture(int start, int end, boolean[] isLead, String str) {
        for(int i=start; i<end; i++) {
            if(isLead[start] && str.charAt(i)!=str.charAt(start-1)) return true;
        }
        return false;
    }
}