package Chelper_codes_gen;
//package com.company;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

//WavioSequence
public class WavioSequence {
    static long TIME_START, TIME_END;
    private static final int M = (int) 1e9 + 7;


    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new FileInputStream("/home/itachi/codechef/Assignments _GFG _sessions/src/Chelper_codes_gen/in.txt"));
        PrintWriter pw = new PrintWriter(new BufferedOutputStream(System.out));
//        PrintWriter pw = new PrintWriter(new FileOutputStream("nondec.out"));

        TIME_START = System.currentTimeMillis();
        solve(sc, pw);
        TIME_END = System.currentTimeMillis();
        pw.close();
        System.err.println("Time used: " + (TIME_END - TIME_START) + ".");

    }

    static void solve(Scanner sc, PrintWriter pw) throws IOException {

//        String n;
        StringBuilder sb = new StringBuilder();
        try {
            for (; sc.hasNextInt(); ) {
                int size = sc.nextInt();
                int[] a = new int[size];
                for (int i = 0; i < size; i++) {
                    a[i] = sc.nextInt();
                }

                //input ends
                // perform lis and lds
                int[] lis = new int[size];
                int[] lds = new int[size];

                int[] subLIS = new int[size];
                Arrays.fill(subLIS, Integer.MAX_VALUE);
                //I think integer should do because we can get the LIS value from its current index
                //print(subLIS);
                subLIS[0] = a[0];
                lis[0] = 1; //TODO: fill up lds as well


                for (int i = 1; i < size; i++) {
                    int pos = ub(subLIS, a[i]);
                    subLIS[pos] = a[i];
                    lis[i] = pos + 1;
                }

//            System.out.println("---Printing LIS Array ---");
//            print(lis);

                //lds -> reverse array lis -- fill lds from backwards
                int[] subLDS = new int[size];
                Arrays.fill(subLDS, Integer.MAX_VALUE);
                lds[size - 1] = 1;
                subLDS[0] = a[size - 1];

                for (int i = size - 2; i >= 0; i--) {
                    int pos = ub(subLDS, a[i]);
                    subLDS[pos] = a[i];
                    lds[i] = pos + 1;
                }

//            System.out.println("---Printing LDS Array ---");
//            print(lds);

                // Finding longest Wavio Sequence

                long max = Long.MIN_VALUE;
                for (int i = 0; i < size; i++) {
                    long tmp = 2L * (Math.min(lis[i], lds[i])) - 1;
                    max = Math.max(max, tmp);
                }

                sb.append(max + "\n");
//                int ans = getAns(size, a);
//                System.out.println(ans);
            }

            System.out.print(sb);
        } catch (Exception e) {
            System.exit(0);
        }
    }

    /*static int getAns(int N, int[] arr) {
        int[] incr = LIS(N, arr);
        arr = reverse(arr, N);
        int[] decr = LIS(N, arr);
        decr = reverse(decr, N);

        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans = Math.max(2 * Math.min(incr[i], decr[i]) - 1, ans);
        }
        return ans;
    }

    static int[] LIS(int N, int[] arr) {
        int[] L = new int[N];
        int[] I = new int[N + 1];

        I[0] = Integer.MIN_VALUE;
        for (int i = 1; i < N + 1; i++) {
            L[i - 1] = 1;
            I[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i < N + 1; i++) {
            int low, mid, high;
            low = 0;
            high = i;
            while (low <= high) {
                mid = (low + high) / 2;
                if (I[mid] < arr[i - 1])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
            I[low] = arr[i - 1];
            L[i - 1] = low;
        }

        return L;
    }

    static int[] reverse(int[] arr, int N) {
        int[] ans = new int[N];
        for (int i = 0; i < N; i++)
            ans[N - i - 1] = arr[i];
        return ans;
    }*/

    static void print(int[] a) {
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");

        System.out.println();
    }

    static int ub(int[] a, int x) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            if (lo == hi)
                return lo;
            int mid = lo + (hi - lo) / 2;
            if (a[mid] >= x)
                hi = mid;
            else
                lo = mid + 1;
        }
        return -1;
    }

    /*static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner(FileReader s) throws FileNotFoundException {
            br = new BufferedReader(s);
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        public boolean ready() throws IOException {
            return br.ready();
        }
    }*/

}
