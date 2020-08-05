package UVA;

import java.io.FileInputStream;
import java.util.Scanner;

//LA4106_ACORN
public class LA4106_ACORN {
    static int[][] a;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileInputStream("/home/itachi/codechef/Assignments _GFG _sessions/src/UVA/in.txt"));
//      Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        while (test-- > 0) {
            int t = sc.nextInt(), h = sc.nextInt(), f = sc.nextInt();
            a = new int[h + 1][t + 1];
            dp = new int[h + 1];
            for (int i = 0; i < t; i++) {
                int num = sc.nextInt();
                while (num-- > 0) {
                    int ht = sc.nextInt() - 1;
                    a[ht][i]++;
                }
            }

//            for (int i = 0; i < a.length; i++)
//                System.out.println(Arrays.toString(a[i]));

            for (int ht = h - 1; ht >= 0; ht--) {
                for (int tree = 0; tree < t; tree++) {
                    a[ht][tree] += Math.max(a[ht + 1][tree], (ht + f <= h) ? dp[ht + f] : 0);
                    dp[ht] = Math.max(dp[ht], a[ht][tree]);
                }
            }

            sb.append(dp[0] + "\n");

        }

        System.out.print(sb);

    }
}
