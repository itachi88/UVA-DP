package UVA;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

//UVA10337_FlightPlanner
public class UVA10337_FlightPlanner {
    private static final int INF = (int) 1e7;
    static int x;
    static int[][] a, dp;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileInputStream("/home/itachi/codechef/Assignments _GFG _sessions/src/UVA/in.txt"));
//        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            sc.nextLine(); // read blank line
            x = sc.nextInt() / 100;
            a = new int[10][x]; // wind input

            //arranged from 0 to 9 (reverse of input array)
            for (int i = 9; i >= 0; i--) {
                for (int j = 0; j < x; j++)
                    a[i][j] = sc.nextInt();
            }

            dp = new int[10][x];

            for (int i = 0; i < 10; i++)
                Arrays.fill(dp[i], -1);

            sb.append(dfs(0, 0) + "\n\n");
        }

        System.out.print(sb);
    }

    static int dfs(int col, int row) {
        if (row > 9 || row < 0 || (col == x && row != 0))
            return INF;

        if (col == x)
            return 0;
        if (dp[row][col] != -1)
            return dp[row][col];

        int top = 60 - a[row][col] + dfs(col + 1, row + 1); // since row from 0 to 9
        int same = 30 - a[row][col] + dfs(col + 1, row);
        int bot = 20 - a[row][col] + dfs(col + 1, row - 1);

        return dp[row][col] = Math.min(top, Math.min(same, bot));
    }
}
