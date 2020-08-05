package UVA;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

//UVA10364_Square
public class UVA10364_Square {
    static int sum;
    static int m;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileInputStream("/home/itachi/codechef/Assignments _GFG _sessions/src/UVA/in.txt"));
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            m = sc.nextInt();
            sum = 0;
            int[] len = new int[m];
            for (int i = 0; i < m; i++) {
                len[i] = sc.nextInt();
                sum += len[i];
            }

            if (sum % 4 != 0) {
                sb.append("no\n");
                continue;
            }

            dp = new int[1 << 20]; // int dp since cant handle invalid case in bool dp
            Arrays.fill(dp, -1);

            sb.append(ans(0, 0, len) == 1 ? "yes\n" : "no\n");
        }

        System.out.print(sb);
    }

    static int ans(int mask, int slen, int[] len) {

        if (dp[mask] != -1)
            return dp[mask];

        if (slen > sum / 4)
            return 0;

        if (slen == sum / 4) {
            //if all sticks used , then found
            if (mask == (1 << m) - 1)
                return 1;

            slen = 0; // build a new segment
        }
        for (int i = 0; i < m; i++)  // iterating sticks
            if ((mask & 1 << i) == 0 && ans(mask | 1 << i, slen + len[i], len) == 1) // stick not used
                return 1;


        return dp[mask] = 0;
    }
}
