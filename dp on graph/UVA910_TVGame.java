package UVA;

import java.io.FileInputStream;
import java.util.Scanner;

//UVA910_TVGame
public class UVA910_TVGame {
    static int[][] a, dp;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileInputStream("/home/itachi/codechef/Assignments _GFG _sessions/src/UVA/in.txt"));

        StringBuilder sb = new StringBuilder();
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            a = new int[26][3];

            for (int i = 0; i < n; i++) {
                int ch = sc.next().charAt(0) - 'A';
                a[ch][0] = sc.next().charAt(0) - 'A';
                a[ch][1] = sc.next().charAt(0) - 'A';
                a[ch][2] = sc.next().charAt(0) == 'x' ? -1 : 0;
            }

//        for (int i = 0; i < 26; i++)
//            System.out.println(Arrays.toString(a[i]));
            int moves = sc.nextInt();

            dp = new int[26][moves + 1];
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[0].length; j++)
                    dp[i][j] = -1;
            }


            sb.append(dfs(0, moves) + "\n");
        }

        System.out.print(sb);
    }

    static int dfs(int curr, int moves) {
        if (moves == 0)
            return a[curr][2] == -1 ? 1 : 0; // special node is only one way if we exhaust our moves

        if (dp[curr][moves] != -1)
            return dp[curr][moves];


        int c0 = dfs(a[curr][0], moves - 1);
        int c1 = dfs(a[curr][1], moves - 1);

        return dp[curr][moves] = c0 + c1;
    }
}
