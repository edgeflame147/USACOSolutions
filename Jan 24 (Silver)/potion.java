import java.util.*;
import java.io.*;
public class potion {
    static int n = 1000010, ans = 0;
    static int[] a = new int[n];
    static int[] ok = new int[n];
    static int[] leaf_num = new int[n];

    static ArrayList<Integer>[] g  = new ArrayList[n];

    public static void dfs1(int u , int f){
        boolean flag = true;
        for(int v : g[u]){
            if(v == f) continue;
            flag = false;
            dfs1(v, u);
            leaf_num[u] += leaf_num[v];
        }
        if(flag) leaf_num[u] = 1;
    }

    public static void dfs2(int u , int f){
        leaf_num[u] = 0;
        boolean flag = true;
        for(int v : g[u]){
            if(v == f) continue;
            dfs2(v, u);
            flag = false;
            leaf_num[u] += leaf_num[v];
        }
        if(flag) leaf_num[u] = 1; 
        int tmp = Math.min(leaf_num[u], ok[u]); 
        leaf_num[u] -= tmp; 
        ans += tmp;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i = 1;i <= n; i++) {
            g[i] = new ArrayList<>();
            a[i] = sc.nextInt();
        }
        for(int i = 1;i < n;i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            g[u].add(v);
            g[v].add(u);
        }
        dfs1(1, -1);
        for(int i = 1;i <= leaf_num[1];++i) {
            ok[a[i]]++;
        }

        dfs2(1, -1);
        System.out.println(ans);
        sc.close();
    }
}