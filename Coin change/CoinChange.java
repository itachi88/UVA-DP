package Chelper_codes_gen;

import FastIO.InputReader;

import java.io.PrintWriter;

public class CoinChange {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.readInt();

        while (t-- > 0) {
            int m = in.readInt(); // size of denomination array
            int[] d = new int[m];

            for (int i = 0; i < m; i++)
                d[i] = in.readInt();

            int n = in.readInt();

            int[][] dp = new int[n + 1][m + 1];
            for (int i = 0; i <= n; i++)
                for (int j = 0; j <= m; j++)
                    dp[i][j] = -1;

            coinChange(n, d, m, 0, dp);

//            System.out.println("printing dp array");
//            for (int i = 0; i <= n; i++) {
//                for (int j = 0; j <= m; j++)
//                    System.out.print(dp[i][j] + " ");
//                System.out.println();
//            }
            System.out.println(dp[n][m]);
        }
    }

    int coinChange(int n, int[] d, int m, int st, int[][] dp) {
        if (n == 0)
            return 1;

        if (n < 0 || m == 0)
            return 0;

        if (dp[n][m] >= 0)
            return dp[n][m];

        int op1 = coinChange(n - d[st], d, m, st, dp);
        int op2 = coinChange(n, d, m - 1, st + 1, dp);

        dp[n][m] = op1 + op2;

        return dp[n][m];
    }


}
