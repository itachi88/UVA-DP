package Chelper_codes_gen;

import FastIO.InputReader;

import java.io.PrintWriter;

public class HistoryGrading111 {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.readInt(); // event

        //given rank of events .. we need to the find the order and apply LCS

        int[] cs = new int[n]; // correct sequence
        for (int i = 0; i < n; i++)
            cs[i] = in.readInt();

        cs = order(cs);
        Integer x;
        try {
            while ((x = in.readInt()) != null) {
                int[] ss = new int[n]; // student sequence
                ss[0] = x;
                for (int i = 1; i < n; i++)
                    ss[i] = in.readInt();

                ss = order(ss);
                System.out.println(lcs(ss, cs));
            }

        } catch (Exception e) {
            System.exit(0);
        }
    }

    int lcs(int[] s1, int[] s2) {
        int n = s1.length;
        int[][] dp = new int[n][n];

        // 0 could be a valid answer because if we substring ,
        // then we could lose common characters and those 2 substrings may have
        // 0 common subsequence

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                dp[i][j] = -1;
        return lcsUtil(0, 0, s1, s2, n, dp);


    }

    int lcsUtil(int ptr1, int ptr2, int[] s1, int[] s2, int n, int[][] dp) {
        if (ptr1 > n - 1 || ptr2 > n - 1)
            return 0;

        if (dp[ptr1][ptr2] == -1) {
            if (s1[ptr1] == s2[ptr2])
                dp[ptr1][ptr2] = 1 + lcsUtil(ptr1 + 1, ptr2 + 1, s1, s2, n, dp);
            else {
                int opt1 = lcsUtil(ptr1 + 1, ptr2, s1, s2, n, dp);
                int opt2 = lcsUtil(ptr1, ptr2 + 1, s1, s2, n, dp);

                dp[ptr1][ptr2] = Math.max(opt1, opt2);
            }
        }

        return dp[ptr1][ptr2];
    }

    int[] order(int[] a) {
        int n = a.length;
        int[] temp = new int[n];

        for (int i = 0; i < n; i++) {
            temp[a[i] - 1] = i + 1;
        }

        return temp;
    }

}
