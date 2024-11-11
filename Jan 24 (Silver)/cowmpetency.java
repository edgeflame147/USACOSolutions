import java.util.*;
import java.io.*;
public class cowmpetency {
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) throws IOException {
        int t = sc.nextInt();
        for(int i = 1;i <= t;i++){
            solve();
        }
        sc.close();
    }
    
    public static void solve() {
        int n = sc.nextInt();
        int q = sc.nextInt();
        int c = sc.nextInt();
        int[] arr = new int[n];
        int[] update = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
            update[i] = arr[i];
        }
        int r=0, hb=0, h=0;
        Questions[] que = new Questions[q];
        for(int i=0; i<q;i++) {
            int x = sc.nextInt()-1;
            int y = sc.nextInt()-1;
            que[i] = new Questions(x, y);
        }
        Arrays.sort(que);

        Set<Integer> rs = new TreeSet<>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return -Integer.compare(o1, o2);
            }
        });
        Set<Integer> hbs = new TreeSet<>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return -Integer.compare(o1, o2);
            }
        });
        Set<Integer> hs = new TreeSet<>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return -Integer.compare(o1, o2);
            }
        });

        if(arr[0] == 0) arr[0] = 1;
        for(Questions q1: que) {
            int st = q1.first;
            int en = q1.second;

            while(r <= st) rs.add(arr[r++]);
            while(hb < en) hbs.add(arr[hb++]);
            while(h <= en) hs.add(arr[h++]);

            Iterator<Integer> it1 = rs.iterator();
            Iterator<Integer> it2 = hbs.iterator();
            Iterator<Integer> it3 = hs.iterator();

            int left = it1.next(); 
            int right = it2.next(); 
            int current = it3.next();

            if(left == current) {
                if(arr[en] != 0) {
                    System.out.println(-1);
                    return;
                }
                arr[en] = current + 1; 
                hs.add(arr[en]);
            }

            if(left < right) {
                for(int i=st; i>=0; i--) {
                    if(update[i] == 0) {
                        arr[i] = right;
                        hs.add(right);
                        rs.add(right);
                        hbs.add(right);
                        break;
                    }
                }
            }
        }
        int mx = 0;
        for(int i=0; i<n; i++) {
            if(arr[i] == 0){
                arr[i] = 1;
            }
            mx = Math.max(mx, arr[i]);
        }
        if(mx > c) { 
            System.out.println(-1);
            return;
        }
        rs.clear();
        hbs.clear();
        hs.clear();

        r = 0;
        hb = 0;
        h = 0;
        
        for(Questions q1: que) {
            int st = q1.first;
            int en = q1.second;

            while(r <= st) rs.add(arr[r++]);
            while(hb < en) hbs.add(arr[hb++]);
            while(h <= en) hs.add(arr[h++]);

            Iterator<Integer> it1 = rs.iterator();
            Iterator<Integer> it2 = hbs.iterator();
            Iterator<Integer> it3 = hs.iterator();

            int left = it1.next();
            int right = it2.next();
            int current = it3.next();

            if(left == current || left < right) {
                System.out.println(-1);
                return;
            }
        }
        
        for(int i=0; i<n-1; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println(arr[n-1]);
    }
    public static class Questions implements Comparable {
        private int first;
        private int second;

        public Questions (int first, int second) {
            this.first = first;
            this.second = second;
        }

        public int compareTo(Object o) {
            Questions m = (Questions) o;
            if(this.first!= m.first) return Integer.compare(this.first,((Questions) o).first);
            else return Integer.compare(this.second,((Questions) o).second);
        }
    }
}