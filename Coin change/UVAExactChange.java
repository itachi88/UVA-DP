package Chelper_codes_gen;

import java.util.Scanner;

//UVAExactChange
//TLE
public class UVAExactChange {
    static final int INF = Integer.MAX_VALUE;
    static long start, end;


    public static void main(String[] args) throws Exception {
//        Scanner sc = new Scanner(new FileInputStream("/home/itachi/codechef/Assignments _GFG _sessions/src/Chelper_codes_gen/in.txt"));
        start = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int amt = sc.nextInt();
            int n = sc.nextInt();
            int[] nums = new int[n];

            int sum = 0;
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
//                sum += nums[i];
            }

            // push DP

            sum = 20000;
            int[][] dp = new int[n + 1][sum + 1];

            for (int i = 0; i <= n; i++)
                for (int j = 1; j <= sum; j++)
                    dp[i][j] = INF;

            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= sum; j++) {

//                    if (dp[i - 1][j] != INF) {
//                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
////                    if (dp[i - 1][j] != INF) {
//                        if (j + nums[i - 1] <= sum)
//                            dp[i][j + nums[i - 1]] = Math.min(dp[i][j + nums[i - 1]], dp[i][j] + 1);
//                    }
                    if (dp[i - 1][j] != INF) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
                        if (j + nums[i - 1] <= sum)
                            dp[i][j + nums[i - 1]] = Math.min(dp[i][j + nums[i - 1]], dp[i - 1][j] + 1);
                    }
                }
            }

            // print DP array

//            for (int i = 0; i < dp.length; i++) {
//                for (int j = 0; j < dp[0].length; j++)
//                    System.out.print((dp[i][j] == INF ? "oo" : dp[i][j]) + " ");
//                System.out.println();
//            }

            for (int i = amt; i <= sum; i++) {
                if (dp[n][i] != INF) {
                    sb.append(i + " " + dp[n][i] + "\n");
                    break;
                }
            }
        }

        System.out.print(sb);

        end = System.currentTimeMillis();

        System.err.println("Time taken = " + (end - start) + " ms");
    }
}


