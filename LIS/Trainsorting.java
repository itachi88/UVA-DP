package Chelper_codes_gen;

import FastIO.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class Trainsorting {
    int[] a;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.readInt();
        while (t-- > 0) {
            int n = in.readInt();
            a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = in.readInt();

            //lis = lds ending at A
            int[] lis = new int[n], lds = new int[n];
            Arrays.fill(lis, 1);
//            lis[n - 1] = 1;

            for (int i = n - 1; i >= 0; i--) {
                for (int j = n - 1; j >= i + 1; j--) {
                    if (a[j] > a[i])
                        lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }

            /*System.out.println("printing lis array---");

            for (int i = 0; i < n; i++)
                System.out.print(lis[i] + " ");*/

            // lds starting at A = lis ending at A

            Arrays.fill(lds, 1);
//            lds[n - 1] = 1;

            for (int i = n - 1; i >= 0; i--) {
                for (int j = n - 1; j >= i + 1; j--) {
                    if (a[j] < a[i])
                        lds[i] = Math.max(lds[i], lds[j] + 1);
                }
            }

//            System.out.println("printing lds array---");
//
//            for (int i = 0; i < n; i++)
//                System.out.print(lds[i] + " ");

            int max = 0;
            for (int i = 0; i < n; i++) {
                max = Math.max(max, lis[i] + lds[i] - 1);
            }

            System.out.println(max);
        }
    }
}
