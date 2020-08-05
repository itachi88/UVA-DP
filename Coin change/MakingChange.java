package UVA;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

//MakingChange - 166
public class MakingChange {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(new FileInputStream("/home/itachi/codechef/Assignments _GFG _sessions/src/UVA/in.txt"));
//        Scanner sc = new Scanner(System.in);

        while (true) {
            String s = sc.nextLine();
            if (s.equals("0 0 0 0 0 0"))
                break;

            //5 10 20 50 100 200
            int[] nums = {5, 10, 20, 50, 100, 200};
            int[] freq = new int[6];
            String[] strs = s.trim().split("\\s+");
            freq[0] = Integer.parseInt(strs[0]);
            freq[1] = Integer.parseInt(strs[1]);
            freq[2] = Integer.parseInt(strs[2]);
            freq[3] = Integer.parseInt(strs[3]);
            freq[4] = Integer.parseInt(strs[4]);
            freq[5] = Integer.parseInt(strs[5]);

            int amt = (int) Math.round(Double.parseDouble(strs[strs.length - 1]) * 100);
            mc(amt, nums, freq);
            System.out.println();
        }

    }


    static void mc(int amt, int[] a, int[] freq) {

        int size = 600;


        //need 2d dp for customer
        int[][] dp = new int[7][size + 1]; // optimise as all 5 multiples
        for (int i = 0; i < 7; i++) {
            for (int j = 1; j < dp[0].length; j++)
                dp[i][j] = INF;
        }

        dp[0][0] = 0;

        for (int i = 1; i <= 6; i++) { // introducing a new coin
            if (a[i - 1] == 0)
                continue;
            for (int j = 0; j <= size; j += 5) { // amount in multiples of 5

                //copy the values from prev to current
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j]);

                for (int k = 1; k <= freq[i - 1]; k++) { // frequency
                    if (j + k * a[i - 1] <= size) {
                        dp[i][j + k * a[i - 1]] = Math.min(dp[i][j + k * a[i - 1]],
                                dp[i - 1][j] == INF ? INF : k + dp[i - 1][j]);
                    }
                }
            }
        }


//        System.out.println("Customer DP================");
//
//        for (int i = 0; i < dp.length; i++) {
//            System.out.print(i == 0 ? 0 : a[i - 1]);
//            System.out.print("---> ");
//            for (int j = 0; j < dp[0].length; j++) {
//                System.out.print((dp[i][j] == INF ? "-" : dp[i][j]) + " ");
//            }
//            System.out.println();
//        }



        /*int[] dp = new int[size + 1];

        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 0; i < a.length; i++) { // introducing a new coin
            if (a[i] == 0)
                continue;
            for (int j = 0; j <= size; j += 5) {
                for (int k = 1; k <= freq[i]; k++) { // apply a check for freq
                    if (j + k * a[i] <= size)
                        dp[j + k * a[i]] = Math.min(dp[j + k * a[i]], dp[j] == INF ? INF : (k + dp[j]));
                }
            }
        }

        System.out.println("Customer DP================");
        dp(dp);*/


        //shopkeeper dp array(unlimited coins)

        int[] sdp = new int[size + 1];
        Arrays.fill(sdp, INF);
        sdp[0] = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j <= size; j++) {
                if (j + a[i] <= size)
                    sdp[j + a[i]] = Math.min(sdp[j + a[i]], sdp[j] != INF ? (1 + sdp[j]) : INF);
            }
        }

//        System.out.println("Shopkeeper DP ==========");
//        dp(sdp);

        int ans = INF;

//        for (int i = amt; i <= size; i++) {
//            if (dp[i] != INF) {
//                int change = i - amt;
//                if (change == 0)
//                    ans = dp[amt];
//                else {
////                    System.out.println("cust min = " + dp[i] + " sk min = " + sdp[change]);
//                    ans = Math.min(ans, dp[i] + sdp[change]);
////                    System.out.println("ans set = " + ans);
//                }
//            }
//        }

        for (int i = amt; i <= size; i++) {
            int tmp = INF;
            for (int j = 1; j <= 6; j++) {
                tmp = Math.min(tmp, dp[j][i]);
            }

            if (tmp != INF) {
                if (i - amt == 0)
                    ans = Math.min(ans, tmp);
                else
                    ans = Math.min(ans, tmp + sdp[i - amt]);
            }
        }

//        System.out.println("-----ANS----");
        System.out.format("%3d", ans);
    }


    static void dp(int[] a) {
        for (int i = 0; i < a.length; i++)
            System.out.println("i = " + i + " ----> " + a[i]);

    }
}
