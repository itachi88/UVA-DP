package UVA;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

//UVA10911_FormingQuizTeams
public class UVA10911_FormingQuizTeams {
    static double[][] d;
    static int[] x, y;
    static int n, ones;
    static double[] dp;
    static long st, ed;

    public static void main(String[] args) throws Exception {
        st = System.currentTimeMillis();
        Scanner sc = new Scanner(new FileInputStream("/home/itachi/codechef/Assignments _GFG _sessions/src/UVA/in.txt"));
//        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
//        StringBuilder sb = new StringBuilder();
        int c = 1;
        while (n != 0) {
            x = new int[2 * n];
            y = new int[2 * n];
            d = new double[2 * n][2 * n];
            for (int i = 0; i < 2 * n; i++) {
                sc.next();
                x[i] = sc.nextInt();
                y[i] = sc.nextInt();
            }

            for (int i = 0; i < 2 * n; i++) {
                for (int j = 0; j < 2 * n; j++) {
                    if (i == j) {
                        d[i][j] = Integer.MAX_VALUE;
                        continue;
                    }
                    d[i][j] = Math.sqrt((double) (x[j] - x[i]) * (x[j] - x[i]) + (y[j] - y[i]) * (y[j] - y[i]));
                }
            }

//            for (int i = 0; i < 2 * n; i++) {
//                System.out.println(Arrays.toString(d[i]));
//            }
            dp = new double[1 << 16];
            Arrays.fill(dp, -1);
            ones = (1 << (2 * n)) - 1;
            System.out.format("Case " + c++ + ": %.2f\n", dfs(0));
            n = sc.nextInt();
        }
        ed = System.currentTimeMillis();
        System.err.println("Time taken = " + (ed - st) + " ms");
    }

    static double dfs(int mask) {
        // what to do when all ones
        // ideally I should calculate minimum ans at leaves
        if (dp[mask] != -1)
            return dp[mask];

        double min = Integer.MAX_VALUE;
        if (mask == ones)
            return dp[mask] = 0; // nothing left to find min of

        for (int i = 0; i < 2 * n; i++) {
            if ((mask & (1 << i)) == 0) {
                for (int j = 0; j < 2 * n; j++) {
                    if ((mask & (1 << j)) == 0) {
                        min = Math.min(min, d[i][j] + dfs(mask | (1 << i) | (1 << j))); // form the i j pair
                    }
                }
            }

        }
        return dp[mask] = min;
    }

}
