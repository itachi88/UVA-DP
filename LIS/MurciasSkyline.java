package Chelper_codes_gen;

import FastIO.InputReader;

import java.io.PrintWriter;

public class MurciasSkyline {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.readInt();
        int[] h, w;
        testNumber = 1;
        while (t-- > 0) {
            int n = in.readInt();
            h = new int[n];
            w = new int[n];

            for (int i = 0; i < n; i++)
                h[i] = in.readInt();
            for (int i = 0; i < n; i++)
                w[i] = in.readInt();

            long inc = 0, dec = 0;

            long[] lis = new long[n];
            long[] lds = new long[n];
            for (int i = 0; i < n; i++) {
                lis[i] = w[i];
                lds[i] = w[i];
            }

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (h[i] > h[j]) {
                        lis[i] = Math.max(lis[i], lis[j] + w[i]);
                    }
                }
            }

            for (int i = 0; i < n; i++)
                inc = Math.max(inc, lis[i]);

//            System.out.println("lis array ");
//            print(lis);

            //LDS

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (h[i] < h[j]) {
                        lds[i] = Math.max(lds[i], lds[j] + w[i]);
                    }
                }
            }

//            System.out.println("LDS ----");
//            print(lds);

            for (int i = 0; i < n; i++)
                dec = Math.max(dec, lds[i]);


            if (inc >= dec)
                System.out.println("Case " + testNumber++ + ". Increasing (" + inc + "). Decreasing (" + dec + ").");
            else
                System.out.println("Case " + testNumber++ + ". Decreasing (" + dec + "). Increasing (" + inc + ").");
        }
    }

    /*void print(long[] arr) {

        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");

        System.out.println();
    }*/
}
