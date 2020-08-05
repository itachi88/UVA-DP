package UVA;

import java.io.FileInputStream;
import java.util.Scanner;

//UVA10681_TeobaldoTrip
public class UVA10681_TeobaldoTrip {
    static int c, l;
    static int[][] a, dp;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileInputStream("/home/itachi/codechef/Assignments _GFG _sessions/src/UVA/in.txt"));

        StringBuilder sb = new StringBuilder();
        while (true) {
            c = sc.nextInt();
            l = sc.nextInt();

            if (c == 0 && l == 0)
                break;

            a = new int[c + 1][c + 1];
            for (int i = 0; i < l; i++) {
                int to = sc.nextInt(), from = sc.nextInt();
                a[to][from] = 1;
                a[from][to] = 1;
            }

            int st = sc.nextInt(), ed = sc.nextInt(), days = sc.nextInt();

//            for (int i = 0; i < a.length; i++)
//                System.out.println(Arrays.toString(a[i]));
//
//            System.out.println("=============");

            //dp init
            dp = new int[c + 1][days + 1];
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[0].length; j++)
                    dp[i][j] = -1;
            }

            if (dfs(st, ed, days))
                sb.append("Yes, Teobaldo can travel.\n");
            else
                sb.append("No, Teobaldo can not travel.\n");


        }
        System.out.print(sb);
    }

    static boolean dfs(int curr, int ed, int days) {
        if (days == 0)
            return curr == ed;

        if (dp[curr][days] != -1) {
//            System.out.println("dp");
            return dp[curr][days] != 0;
        }

        for (int i = 1; i <= c; i++) {
            if (a[curr][i] != 0 && dfs(i, ed, days - 1)) {
                dp[curr][days] = 1;
                return true;
            }
        }

        dp[curr][days] = 0;
        return false;
    }
}
