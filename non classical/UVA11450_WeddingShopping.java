package UVA;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

//UVA11450_WeddingShopping
public class UVA11450_WeddingShopping {
    static int[][] a, dp;
    static int m, c;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileInputStream("/home/itachi/codechef/Assignments _GFG _sessions/src/UVA/in.txt"));
//        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        dp = new int[21][300];
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            m = sc.nextInt();
            c = sc.nextInt();
            a = new int[c][];
            for (int i = 0; i < c; i++) {
                int k = sc.nextInt();
                a[i] = new int[k];
                int j = 0;
                while (k-- > 0) {
                    a[i][j++] = sc.nextInt();
                }
            }

            for (int i = 0; i < dp.length; i++)
                Arrays.fill(dp[i], -1);

            int ans = dfs(0, 0);
            sb.append(ans == -1 ? "no solution" : ans);
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static int dfs(int curr, int sum) {
        if (sum > m)
            return -1;

        if (curr == a.length)
            return sum;

        if (dp[curr][sum] != -1)
            return dp[curr][sum];

        int ans = -1;

        for (int j : a[curr])
            ans = Math.max(ans, dfs(curr + 1, sum + j));

        return dp[curr][sum] = ans;

    }
}
