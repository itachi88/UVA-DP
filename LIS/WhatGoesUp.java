package Chelper_codes_gen;

import FastIO.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WhatGoesUp {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        Integer x;
        List<Integer> ls = new ArrayList<>();

        try {
            while ((x = in.readInt()) != null) {
                ls.add(x);
            }
        } catch (Exception e) {
//            lisTLE(ls);
            lisBinarySearch(ls);
            System.exit(0);
        }
    }

    public void lisBinarySearch(List<Integer> ls) {
        int n = ls.size();
        Pair[] sub = new Pair[n];

        Arrays.fill(sub, new Pair(Integer.MAX_VALUE, 0));
        int[] parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i; // self parent

        sub[0] = new Pair(ls.get(0), 0);
        int ans = 0; // ans denotes position .. ans +1 is length

        for (int i = 1; i < n; i++) {
            int now = ls.get(i);
            int replPos = ubPair(sub, now);
            sub[replPos] = new Pair(now, i);
            if (replPos != 0)
                parent[i] = sub[replPos - 1].id;

            //update ans
            ans = Math.max(ans, replPos);

        }
        /*System.out.println("Pair array");
        for (int i = 0; i < n; i++)
            System.out.print(sub[i] + " ");

        System.out.println();
        System.out.println("Parent array");
        for (int i = 0; i < n; i++)
            System.out.print(parent[i] + " ");
        System.out.println();*/
//        System.out.println("ans =" + ans);

        //print LIS
        StringBuilder sb = new StringBuilder();
        sb.append(ans + 1);
        sb.append("\n-\n");

        int cnt = ans + 1;
        List<Integer> res = new ArrayList<>();
        int ch = sub[ans].id;
        while (cnt > 0) {
            res.add(ls.get(ch));
            ch = parent[ch];
            cnt--;
        }

        Collections.reverse(res);

        for (int x : res)
            sb.append(x + "\n");

        System.out.print(sb);

    }



    /*int ub(int[] a, int key) {
        // returns pos of element > key in array 'a'
        int n = a.length;
        int lo = 0, hi = n - 1;

        while (lo <= hi) {
            if (lo == hi)
                return lo;
            int mid = (lo + hi) / 2;

            if (a[mid] > key)
                hi = mid;

            else
                lo = mid + 1;

        }

        return lo;
    }*/

    int ubPair(Pair[] a, int key) {
        int n = a.length;
        int lo = 0, hi = n - 1;

        while (lo <= hi) {
            if (lo == hi)
                return lo;
            int mid = (lo + hi) / 2;
            if (a[mid].val >= key)
                hi = mid;
            else
                lo = mid + 1;
        }

        return -1;
    }

    public void lisTLE(List<Integer> ls) {
        StringBuilder sb = new StringBuilder();
        int n = ls.size();
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int[] parent = new int[n];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (ls.get(i) > ls.get(j)) {
                    //update dp and parent
                    if (dp[i] <= dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        parent[i] = j;
                    }

                }
            }
        }

        int ans = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            if (ans <= dp[i]) {
                ans = dp[i];
                start = i;
            }
        }

        sb.append(ans + "\n-\n");
        int cnt = ans;
        int[] lis = new int[ans];
        while (cnt > 0) {
            lis[cnt - 1] = ls.get(start);
            start = parent[start];
            cnt--;
        }

        for (int i = 0; i < lis.length; i++)
            sb.append(lis[i] + "\n");
        System.out.print(sb);
    }

    private static class Pair implements Comparable<Pair> {
        int val;
        int id;

        public Pair(int val, int id) {
            this.val = val;
            this.id = id;
        }

        @Override
        public int compareTo(Pair o) {
            return this.val - o.val;
        }

        @Override
        public String toString() {
            return "(" + this.val + " " + this.id + ")";
        }
    }

}
