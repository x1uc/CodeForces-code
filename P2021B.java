/**
 * Name: B. Maximize Mex
 * Group: Codeforces - Codeforces Round 977 (Div. 2, based on COMPFEST 16 - Final Round)
 * URL: https://codeforces.com/problemset/problem/2021/B
 * Memory Limit: 256
 * Time Limit: 1000
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class P2021B {
    static FastScanner in = new FastScanner();
    static PrintWriter out = new PrintWriter(System.out);


    private static void solve() {
        int n, m;
        n = in.nextInt();
        m = in.nextInt();
        int[] arr = in.readArray(n);
        Arrays.sort(arr);
        Map<Integer, Integer> nowMap = new HashMap<>();
        int now = 0;
        int i;
        for (i = 0; i < arr.length; i++) {
            int cur = arr[i];
            int curMod = cur % m;
            int temp = now % m;
            // 如果当前的数值与now不相等，进行处理
            if (cur != now) {
                if (nowMap.getOrDefault(temp, 0) > 0) {
                    nowMap.put(temp, nowMap.get(temp) - 1);
                    now++;
                    i --;
                    continue;
                }
                if (now < arr[i]) {
                    break;
                } else {
                    nowMap.put(curMod, nowMap.getOrDefault(curMod,0) + 1);
                }
            } else {
                // 如果 arr[i] 已经等于 now，直接增加
                now++;
            }
        }
        // 处理剩余的可以增加的元素
            while (nowMap.getOrDefault(now % m, 0) > 0) {
                nowMap.put(now % m, nowMap.get(now % m) - 1);
                now++;
            }
        out.print(now + "\n");
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
