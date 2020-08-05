package UVA;

import java.io.FileInputStream;
import java.util.Scanner;

//UVA590_AlwaysOnTheRun
public class UVA590_AlwaysOnTheRun {
    static long[][][] a;
    static int n, k;
    private static final int INF = Integer.MAX_VALUE;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileInputStream("/home/itachi/codechef/Assignments _GFG _sessions/src/UVA/in.txt"));
        n = sc.nextInt();
        k = sc.nextInt();
        int t = 1;
        StringBuilder sb = new StringBuilder();
        while (n != 0 && k != 0) {
            a = new long[n][n][];

            //input
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j)
                        continue;

                    int days = sc.nextInt();
                    a[i][j] = new long[days];

                    for (int d = 0; d < days; d++)
                        a[i][j][d] = sc.nextInt();
                }
            }

//            for (int i = 0; i < n; i++)
//                for (int j = 0; j < n; j++) {
//                    System.out.println(i + " --> " + j);
//                    System.out.println(Arrays.toString(a[i][j]));
//                }

            //init dp
            dp = new long[n][k];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < k; j++)
                    dp[i][j] = INF;

            long ans = dfs(0, 0); // currcity , currDay
            sb.append("Scenario #" + t++ + "\n");

            if (ans >= INF)
                sb.append("No flight possible.\n");
            else
                sb.append("The best flight costs " + ans + ".\n");
            sb.append("\n");

            n = sc.nextInt();
            k = sc.nextInt();
        }

        System.out.print(sb);
    }

    static long dfs(int curr, int day) {


        // k flights exhausted
        if (day == k) {
            if (curr == n - 1)
                return 0;

            return INF; // impossible
        }


        if (dp[curr][day] != INF) {
//            System.out.println("dp hit " + curr + " --- " + day);
            return dp[curr][day];
        }

        long min = INF;
        for (int i = 0; i < n; i++) {
            if (curr == i)
                continue;
            int dlen = a[curr][i].length;
            if (a[curr][i][day % dlen] == 0)
                continue;

            min = Math.min(min, a[curr][i][day % dlen] + dfs(i, day + 1));
        }

        return dp[curr][day] = min;
    }
}
