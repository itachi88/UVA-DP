package Chelper_codes_gen;

import FastIO.InputReader;

import java.io.PrintWriter;

public class LetMeCountTheWays {
    long[][] dp;
    int[] nums = {1, 5, 10, 25, 50};

    private static final String s1 = "There are ";
    private static final String s11 = "There is only ";
    private static final String s2 = " ways to produce ";
    private static final String s22 = " way to produce ";
    private static final String s3 = " cents change.";

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        Integer amt;
        StringBuilder sb = new StringBuilder();
        init();
        try {
            while ((amt = in.readInt()) != null) {
                long ans = dp[5][amt];
                if (ans == 1)
                    sb.append(s11 + ans + s22 + amt + s3 + "\n");
                else
                    sb.append(s1 + ans + s2 + amt + s3 + "\n");
            }
        } catch (Exception e) {
            System.out.print(sb);
            System.exit(0);
        }
    }

    void init() {
        dp = new long[6][30001];

        for (int i = 0; i < 6; i++)
            dp[i][0] = 1;

        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 30000; j++) {
                long take;
                long leave;

                if (j - nums[i - 1] < 0)
                    take = 0;
                else
                    take = dp[i][j - nums[i - 1]];
                leave = dp[i - 1][j];

                dp[i][j] = take + leave;
            }
        }

    }
}
