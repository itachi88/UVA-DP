package UVA;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

//LA4143_FreeParantheses
public class LA4143_FreeParantheses {
    static int[][][] dp;
    static Set<Integer> set;
    static int[] nums, sig;
    static int n;
    static long st, ed;

    public static void main(String[] args) throws Exception {
        st = System.currentTimeMillis();
//        Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new FileInputStream("/home/itachi/codechef/Assignments _GFG _sessions/src/UVA/in.txt"));

        while (sc.hasNext()) {
            String s = sc.nextLine();
            String[] str = s.split("[+-]");
//            System.out.println(Arrays.toString(str));

            n = str.length;
            nums = new int[n];
            int i = 0;
            for (String tmp : str) {
                nums[i] = Integer.parseInt(str[i++].trim());
            }

            sig = new int[n];
            int j = 1;
            sig[0] = 1;
            for (i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '+')
                    sig[j++] = 1;
                else if (s.charAt(i) == '-')
                    sig[j++] = -1;
            }

            dp = new int[n][n][7000]; // curr store , open ( , int runningSum
//            System.out.println(Arrays.toString(sig));

            for (i = 0; i < dp.length; i++) {
                for (j = 0; j < dp[0].length; j++) {
                    for (int k = 0; k < dp[0][0].length; k++)
                        dp[i][j][k] = -1;
                }
            }
            set = new HashSet<>();
            dfs(0, 0, 0);

            System.out.println(set.size());

        }
        ed = System.currentTimeMillis();
        System.err.println("Time taken = " + (ed - st) + " ms");
    }

    static void dfs(int curr, int op, int runSum) {
        if (curr == n) {
            set.add(runSum);
            return;
        }

        if (dp[curr][op][runSum + 3500] != -1) {
//            System.out.println("dp");
            return;
        }
        dp[curr][op][runSum + 3500] = 1; // this state is reached now
        int sum = runSum + nums[curr] * sig[curr] * (op % 2 == 0 ? 1 : -1);

        // opt1-  put ( if sign is negative

        if (sig[curr] == -1)
            dfs(curr + 1, op + 1, sum);

        // opt2 - put close bracket -> atmost one since even ( flips sign so nore than one ) can be reduced to 1 or 0

        if (op > 0)
            dfs(curr + 1, op - 1, sum);

        // opt3 - move the pointer and do nothing

        dfs(curr + 1, op, sum);
    }
}
