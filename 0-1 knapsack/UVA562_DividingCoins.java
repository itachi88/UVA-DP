package UVA;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

//UVA562_DividingCoins
public class UVA562_DividingCoins {
    static int n, limit;
    static int[] a;
    static int[][] dp;
    static long st, ed;
//    static BigInteger cnt;

    public static void main(String[] args) throws Exception {
        st = System.currentTimeMillis();
        Scanner sc = new Scanner(new FileInputStream("/home/itachi/codechef/Assignments _GFG _sessions/src/UVA/in.txt"));
//        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            n = sc.nextInt();
            a = new int[n];
            int sum = 0;
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
                sum += a[i];
            }

            limit = sum / 2;

            dp = new int[n + 1][limit + 1];
            for (int i = 0; i <= n; i++)
                Arrays.fill(dp[i], -1);

//            cnt = BigInteger.ZERO;
            int ans = ks(0, 0);

            sb.append(Math.abs(sum - 2 * ans) + "\n");

//            System.out.println(ans);
//            System.err.println("Times hit = " + cnt + " for test # = " + (t + 1));
        }

        System.out.print(sb);
        ed = System.currentTimeMillis();
        System.err.println("Time = " + (ed - st) + " ms");
    }

    static int ks(int curr, int sum) {

//        cnt = cnt.add(BigInteger.ONE);
        if (dp[curr][sum] != -1) {
//            System.out.println("in");
            return dp[curr][sum];
        }


        if (curr == n)
            return dp[curr][sum] = sum;

//        if (sum > limit)
//            return 0;

        int take = 0, leave = 0;

        if (sum + a[curr] <= limit)
            take = ks(curr + 1, sum + a[curr]);

        if (sum <= limit)
            leave = ks(curr + 1, sum);

        return dp[curr][sum] = Math.max(take, leave);
    }
}
