package Chelper_codes_gen;

import FastIO.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//input issue - mult test

public class StrategicDefenseInitiative {
    private static final String MAX_HITS = "Max hits: ";

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.readInt();
        in.read(); // reading a blank line
        while (t-- > 0) {
            String s;
            List<Integer> ls = new ArrayList<>();
            while ((s = in.readString()) != "\n") {
                ls.add(Integer.parseInt(s));
            }

            lisLog(ls);
        }
    }

    void lisLog(List<Integer> ls) {
        /*
            Out sample
            Max hits: 4
            1
            2
            3
            5

         */
        int n = ls.size();
        Pair[] sub = new Pair[n];
        Arrays.fill(sub, new Pair(Integer.MAX_VALUE, 0));
        int[] parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i; // self parent

        sub[0] = new Pair(ls.get(0), 0);
        int ans = 1;

        for (int i = 1; i < n; i++) {
            int tmp = ls.get(i);
            int pos = upperBound(sub, tmp); // pos to be replaced
            sub[pos] = new Pair(tmp, i);
            if (pos != 0)
                parent[i] = sub[pos - 1].id; // need to handle boundary

            ans = Math.max(ans, pos);
        }

        //LIS print
        StringBuilder sb = new StringBuilder();
        sb.append(MAX_HITS + (ans + 1) + "\n");
        int ch = sub[ans].id;
        int cnt = ans + 1;
        List<Integer> res = new ArrayList<>();
        while (cnt > 0) {
            res.add(ls.get(ch));
            ch = parent[ch];
            cnt--;
        }

        Collections.reverse(res);

        for (int x : res)
            sb.append(x + "\n");

        System.out.println(sb);
    }


    int upperBound(Pair[] a, int key) {
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
        return lo;
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
            return "{" + this.id + "," + this.val + "}";
        }
    }
}
