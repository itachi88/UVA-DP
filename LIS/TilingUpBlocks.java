package Chelper_codes_gen;

import FastIO.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class TilingUpBlocks {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n;
        StringBuilder sb = new StringBuilder();
        while ((n = in.readInt()) != 0) {
            Pair[] a = new Pair[n];
            for (int i = 0; i < n; i++)
                a[i] = new Pair(in.readInt(), in.readInt());

            Arrays.sort(a);

            int[] dp = new int[n];
            Arrays.fill(dp, 1);

//            System.out.println("After sort");
//            for (int i = 0; i < n; i++)
//                System.out.println(a[i]);


            //LIS

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (a[i].left >= a[j].left && a[i].mid >= a[j].mid)
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            // ans

            int max = 0;

            for (int i = 0; i < n; i++)
                max = Math.max(max, dp[i]);

            sb.append(max + "\n");

        }
        sb.append("*");
        System.out.println(sb);
    }

    private static class Pair implements Comparable<Pair> {
        int left;
        int mid;

        public Pair(int left, int mid) {
            this.left = left;
            this.mid = mid;
        }


        @Override
        public int compareTo(Pair o) {
            if (left < o.left)
                return -1;
            if (left > o.left)
                return 1;
            else {
                if (mid < o.mid)
                    return -1;
                if (mid > o.mid)
                    return 1;
                return 0;
            }
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "left=" + left +
                    ", mid=" + mid +
                    '}';
        }
    }
}
