package Chelper_codes_gen;

//package com.company;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

//ECoins
public class ECoins {
    static long TIME_START, TIME_END;
    private static final int M = (int) 1e9 + 7;
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new FileInputStream("/home/itachi/codechef/Assignments _GFG _sessions/src/Chelper_codes_gen/in.txt"));
        PrintWriter pw = new PrintWriter(new BufferedOutputStream(System.out));
//        PrintWriter pw = new PrintWriter(new FileOutputStream("nondec.out"));

        TIME_START = System.currentTimeMillis();
        solve(sc, pw);
        TIME_END = System.currentTimeMillis();
        pw.close();
        System.err.println("Time used: " + (TIME_END - TIME_START) + ".");

    }

    static void solve(Scanner sc, PrintWriter pw) throws IOException {

        int t = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {

            int n = sc.nextInt();
            int emod = sc.nextInt();
            Ecoin[] ec = new Ecoin[n];

            for (int i = 0; i < n; i++) {
                int f = sc.nextInt();
                int s = sc.nextInt();
                ec[i] = new Ecoin(f, s);
            }


            //BU logic

            int[][] dp = new int[emod + 1][emod + 1];
            for (int i = 0; i <= emod; i++)
                for (int j = 0; j <= emod; j++)
                    dp[i][j] = INF;
            dp[0][0] = 0;

            for (int i = 0; i <= emod; i++) {
                for (int j = 0; j <= emod; j++) {
                    for (Ecoin e : ec) {
                        if (i - e.x < 0 || j - e.y < 0)
                            continue;

                        if (dp[i - e.x][j - e.y] == INF)
                            continue;

                        else
                            dp[i][j] = Math.min(1 + dp[i - e.x][j - e.y], dp[i][j]);
                    }
                }
            }

            /*//dp array print

            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[0].length; j++)
                    System.out.print(dp[i][j] + " ");
                System.out.println();
            }

            System.out.println();
            System.out.println("---------------End of DP array-----------------");
            System.out.println();*/

            int ans = INF;

            for (int i = 0; i <= emod; i++) {
                for (int j = 0; j <= emod; j++) {
                    if (i * i + j * j == emod * emod)
                        ans = Math.min(ans, dp[i][j]);
                }
            }

            if (ans == INF)
                sb.append("not possible\n");
            else
                sb.append(ans + "\n");

        }

        System.out.print(sb);
        System.exit(0);

    }


    static class Ecoin {
        int x;
        int y;

        public Ecoin(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Ecoin{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

}
