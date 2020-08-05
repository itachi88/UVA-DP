package Chelper_codes_gen;

import FastIO.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestingCatcher231 {
    private static final String TXT = "  maximum possible interceptions: ";

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int x;
        StringBuilder sb = new StringBuilder();
        testNumber = 1;
        while ((x = in.readInt()) != -1) {
            List<Integer> ls = new ArrayList<>();
            ls.add(x);
            while (((x = in.readInt()) != -1)) {
                ls.add(x);
            }

            // operate on list
            // longest non decreasing subsequence
            int n = ls.size();
            int[] dp = new int[n];
            Arrays.fill(dp, 1);

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (ls.get(i) <= ls.get(j))
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            int ans = 0;

            for (int i = 0; i < dp.length; i++)
                ans = Math.max(ans, dp[i]);
            //clear the list
            ls.clear();
            sb.append("Test #" + testNumber++ + ":\n");
            sb.append(TXT + ans + "\n\n");
        }

        System.out.println(sb.toString().trim());

    }
}
