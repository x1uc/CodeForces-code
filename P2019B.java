/**
 * Name: B. All Pairs Segments
 * Group: Codeforces - Codeforces Round 975 (Div. 2)
 * URL: https://codeforces.com/problemset/problem/2019/B
 * Memory Limit: 256
 * Time Limit: 1500
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class P2019B {
    static FastScanner in = new FastScanner();
    static PrintWriter out = new PrintWriter(System.out);

    private static void solve() {
        long n = in.nextLong();
        long q = in.nextLong();
        int[] arr = in.readArray((int) n);

        // 使用 TreeMap 保证顺序性并减少可能的重复逻辑
        Map<Long, Long> map = new HashMap<>();

        // 初始化 map 的默认值
        for (long i = 0; i < n; i++) {
            long key = i * (n - i - 1) + n - 1;
            map.merge(key, 1L, Long::sum); // 合并简化逻辑
        }

        // 更新 map 的值
        for (int i = 0; i < n - 1; i++) {
            long key = (i + 1) * (n - 1 - i);
            int value = arr[i + 1] - arr[i] - 1;
            map.merge(key, (long) value, Long::sum); // 合并处理逻辑
        }

        // 处理查询
        StringBuilder result = new StringBuilder();
        while (q-- > 0) {
            long now = in.nextLong();
            result.append(map.getOrDefault(now, 0L)).append(" ");
        }

        out.println(result.toString().trim()); // 使用 StringBuilder 提升输出效率
    }


    public static void main(String[] args) {
        int T = in.nextInt();
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
