package Chelper_codes_gen;

import FastIO.InputReader;

import java.io.PrintWriter;

public class Dollars {
    int[] d = {5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000};
    long[][] dp = new long[12][30001];

    public void solve(int testNumber, InputReader in, PrintWriter out) {
//        for (int i = 0; i < dp.length; i++) {
//            for (int j = 0; j < 11; j++)
//                dp[i][j] = -1;
//        }
        for (int i = 0; i <= 11; i++)
            dp[i][0] = 1;

        for (int i = 1; i <= 11; i++) {
            for (int j = 1; j <= 30000; j++) {
                long take = 0;
                long leave = 0;

                if (j - d[i - 1] < 0)
                    take = 0;
                else
                    take = dp[i][j - d[i - 1]];
                leave = dp[i - 1][j];

                dp[i][j] = take + leave;
            }
        }

        double amt;
        while ((amt = Double.parseDouble(in.readString())) != 0) {
            int rem = (int) Math.round(amt * 100);
//            System.out.println("rem = " + rem);

//            long ans = ways(rem, 0);
//            System.out.format("%6.2f %16d\n", amt, ans);

            //iterative pull DP - curr x amt array

            long ans = dp[11][rem];
            System.out.format("%6.2f %16d\n", amt, ans);

        }

    }


    long ways(int amt, int curr) {
//        System.out.println(amt);
//        System.out.println(curr);
        if (curr >= d.length || amt < 0)
            return 0;
        if (amt == 0)
            return 1;

        if (dp[amt][curr] != -1) {
//            System.out.println("dp used");
            return dp[amt][curr];
        }

        long take = ways(amt - d[curr], curr);
        long leave = ways(amt, curr + 1);

        dp[amt][curr] = take + leave;
        return dp[amt][curr];
    }
}
