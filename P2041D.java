/**
 * Name: C. Uninteresting Number
 * Group: Codeforces - Codeforces Round 991 (Div. 3)
 * URL: https://codeforces.com/problemset/problem/2050/C
 * Memory Limit: 256
 * Time Limit: 2000
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class P2041D {
    static FastScanner in = new FastScanner();
    static PrintWriter out = new PrintWriter(System.out);
    static int n;
    static int m;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};

    private static boolean check(int x, int y) {
        if (x >= 1 && x <= n && y >= 1 && y <= m)
            return true;
        return false;
    }
    static class Point {
        int x;
        int y;
        int direct;
        int num;
        int step;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static void solve() {
        n = in.nextInt();
        m = in.nextInt();
        int startX = 0 ;
        int startY = 0 ;
        int endX = 0 ;
        int endY = 0 ;
        int[][][][] vis = new int[n + 1][m + 1][4][4];
        char[][] map = new char[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String s = in.next();
            for (int j = 1; j <= m; j++) {
                map[i][j] = s.charAt(j - 1);
                if(map[i][j] == 'S'){
                    startX = i;
                    startY = j;
                }
                if(map[i][j] == 'T'){
                    endX = i;
                    endY = j;
                }
            }
        }
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(startX, startY));
        vis[startX][startY][0][0] = 1;
        vis[startX][startY][1][0] = 1;
        vis[startX][startY][2][0] = 1;
        vis[startX][startY][3][0] = 1;
        int flag = -1;
        bfs:
        while (!q.isEmpty()) {
            Point now = q.poll();
            int nx = now.x;
            int ny = now.y;
            for (int i = 0; i < 4; i++) {
                int px = nx + dx[i];
                int py = ny + dy[i];
                if (check(px, py) && map[px][py] != '#') {
                    if (now.direct != i && vis[px][py][i][1] != 1) {
                        if(px == endX && py == endY){
                            out.print(now.step + 1 + "\n");
                            flag = 1;
                            break bfs;
                        }
                        Point p = new Point(px, py);
                        p.direct = i;
                        p.num = 1;
                        p.step = now.step + 1;
                        vis[px][py][i][1] = 1;
                        q.add(p);
                    } else if (now.direct == i && now.num < 3 && vis[px][py][i][now.num + 1] != 1) {
                        if (px == endX && py == endY) {
                            out.print(now.step + 1 + "\n");
                            flag = 1;
                            break bfs;
                        }
                        Point p = new Point(px, py);
                        p.direct = i;
                        p.num = now.num + 1;
                        vis[px][py][i][p.num] = 1;
                        p.step = now.step + 1;
                        q.add(p);
                    }
                }
            }
        }
        if(flag == -1){
            out.println(-1);
        }
    }

    public static void main(String[] args) {
        int T = 1;
        while (T-- > 0) {
            solve();
        }
        out.close();
    }

    static final Random random = new Random();
    static final int mod = 1_000_000_007;

    static void ruffleSort(int[] a) {
        int n = a.length;//shuffle, then sort 
        for (int i = 0; i < n; i++) {
            int oi = random.nextInt(n), temp = a[oi];
            a[oi] = a[i];
            a[i] = temp;
        }
        Arrays.sort(a);
    }

    static long add(long a, long b) {
        return (a + b) % mod;
    }

    static long sub(long a, long b) {
        return ((a - b) % mod + mod) % mod;
    }

    static long mul(long a, long b) {
        return (a * b) % mod;
    }

    static long exp(long base, long exp) {
        if (exp == 0) return 1;
        long half = exp(base, exp / 2);
        if (exp % 2 == 0) return mul(half, half);
        return mul(half, mul(half, base));
    }

    static long[] factorials = new long[2_000_001];
    static long[] invFactorials = new long[2_000_001];

    static void precompFacts() {
        factorials[0] = invFactorials[0] = 1;
        for (int i = 1; i < factorials.length; i++) factorials[i] = mul(factorials[i - 1], i);
        invFactorials[factorials.length - 1] = exp(factorials[factorials.length - 1], mod - 2);
        for (int i = invFactorials.length - 2; i >= 0; i--)
            invFactorials[i] = mul(invFactorials[i + 1], i + 1);
    }

    static long nCk(int n, int k) {
        return mul(factorials[n], mul(invFactorials[k], invFactorials[n - k]));
    }

    static void sort(int[] a) {
        ArrayList<Integer> l = new ArrayList<>();
        for (int i : a) l.add(i);
        Collections.sort(l);
        for (int i = 0; i < a.length; i++) a[i] = l.get(i);
    }


    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens()) try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }


}
