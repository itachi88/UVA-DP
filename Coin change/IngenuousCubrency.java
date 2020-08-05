package Chelper_codes_gen;

import FastIO.InputReader;

import java.io.PrintWriter;

public class IngenuousCubrency {
    int[] nums = new int[22];
    long[][] dp = new long[23][10000];

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        genCube();
        initDP();
        Integer amt;
        StringBuilder sb = new StringBuilder();
        try {
            while ((amt = in.readInt()) != null) {
                sb.append(dp[22][amt] + "\n");
            }
        } finally {
            System.out.print(sb);
            System.exit(0);
        }

    }

    void printDP() {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++)
                System.out.print(dp[i][j] + " ");
            System.out.println();
        }
    }

    void genCube() {
        for (int i = 1; i < 22; i++)
            nums[i] = i * i * i;
    }

    void initDP() {
        for (int i = 0; i <= 22; i++)
            dp[i][0] = 1;

        for (int i = 1; i <= 22; i++) {
            for (int j = 1; j < 10000; j++) {
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
