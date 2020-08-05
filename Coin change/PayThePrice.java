package UVA;

import java.util.Scanner;

//PayThePrice
//10313
public class PayThePrice {
    static long start, end;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        start = System.currentTimeMillis();
//        Scanner sc = new Scanner(new FileInputStream("/home/itachi/codechef/Assignments _GFG _sessions/src/UVA/in.txt"));
        Scanner sc = new Scanner(System.in);

        initDP();
        StringBuilder sb = new StringBuilder();
        while (sc.hasNext()) {
            String query = sc.nextLine();
            String[] strs = query.trim().split("\\s+");

            if (strs.length == 1) {
                int n = Integer.parseInt(strs[0]);
                long sum = 0;
                for (int i = 0; i <= n; i++)
                    sum += dp[n][i];
                sb.append(sum + "\n");

            } else if (strs.length == 2) {
                int n = Integer.parseInt(strs[0]);
                int r = Integer.parseInt(strs[1]);
                long sum = 0;
                for (int i = 0; i <= Math.min(r, 300); i++)
                    sum += dp[n][i];

                sb.append(sum + "\n");
            } else {
                int n = Integer.parseInt(strs[0]);
                int l = Integer.parseInt(strs[1]);
                int r = Integer.parseInt(strs[2]);

                long sum = 0;

                for (int i = l; l <= 300 && i <= Math.min(r, 300); i++)
                    sum += dp[n][i];

                sb.append(sum + "\n");
            }


        }

        System.out.print(sb);
        end = System.currentTimeMillis();
        System.err.println("Time taken = " + (end - start) + " ms");
    }

    static void initDP() {
        int r = 300, c = 300, coins = 300;
//        int r = 10, c = 10, coins = 10;
        dp = new long[r + 1][c + 1];

        for (int coin = 1; coin <= coins; coin++) {
            dp[coin][1]++;
            for (int i = 1; i <= r; i++) { // amount
                for (int j = 1; j <= c; j++) { // no of coins
                    if (dp[i][j] != 0 && i + coin <= r && j + 1 <= c)
                        dp[i + coin][j + 1] += dp[i][j];

                }
            }
        }

        dp[0][0] = 1;
//        for (int i = 0; i <= r; i++)
//            dp[0][i] = 1;

//        pd();
    }


    static void pd() {

        System.out.println("---- Printing DP array ----");
        for (int i = 0; i < dp.length; i++) {
            System.out.print(i + "---->  ");
            for (int j = 0; j < dp[0].length; j++)
                System.out.print(dp[i][j] + " ");
            System.out.println();
        }
    }

}
