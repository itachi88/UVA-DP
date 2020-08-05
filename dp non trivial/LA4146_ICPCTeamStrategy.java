package UVA;

import java.io.FileInputStream;
import java.util.Scanner;

//LA4146_ICPCTeamStrategy
public class LA4146_ICPCTeamStrategy {
    static int n, all;
    static int[][] a;
    static int[][][] dp;
    static final int INF = (int) 1e9;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileInputStream("/home/itachi/codechef/Assignments _GFG _sessions/src/UVA/in.txt"));
//        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            n = sc.nextInt();
            a = new int[3][n];

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < n; j++)
                    a[i][j] = sc.nextInt();
            }

            dp = new int[4][281][1 << n];

            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[0].length; j++) {
                    for (int k = 0; k < dp[0][0].length; k++)
                        dp[i][j][k] = -1;
                }
            }
            all = (1 << n) - 1;
            sb.append(dfs(3, 280, 0) + "\n"); // curr person , rem time , mask of problems
        }

        System.out.print(sb);
    }

    static int dfs(int last, int rem, int mask) {
        if (rem < 0)
            return -INF;
        if (dp[last][rem][mask] != -1)
            return dp[last][rem][mask];


        if (mask == all)
            return 0;

        int ans = 0;

        for (int q = 0; q < n; q++) { // question
            if ((mask & 1 << q) == 0) { //curr question unsolved
                for (int i = 0; i < 3; i++) {
                    if (i != last) {
                        // mark the curr question as done
                        int cur = 1 << q | mask;
                        ans = Math.max(ans, 1 + dfs(i, rem - a[i][q], cur));
                    }
                }
            }
        }

        return dp[last][rem][mask] = ans;
    }
}
