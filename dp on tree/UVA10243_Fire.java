package UVA;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//UVA10243_Fire
public class UVA10243_Fire {
    static List<Integer>[] a;
    static long st, ed;
    static boolean[] vis;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        st = System.currentTimeMillis();
        Scanner sc = new Scanner(new FileInputStream("/home/itachi/codechef/Assignments _GFG _sessions/src/UVA/in.txt"));
//        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            a = new ArrayList[n + 1];
            vis = new boolean[n + 1];

            for (int i = 1; i <= n; i++)
                a[i] = new ArrayList<>();

            for (int i = 1; i <= n; i++) {
                int k = sc.nextInt();
                while (k-- > 0) {
                    a[i].add(sc.nextInt());
                }
            }

//            System.out.println(Arrays.toString(a));
            if (n == 1)
                sb.append(1 + "\n");
            else {
                dp = new int[n + 1][2];
                for (int i = 1; i < dp.length; i++) {
                    for (int j = 0; j < dp[0].length; j++)
                        dp[i][j] = -1;
                }
                sb.append(Math.min(dfs(1, 0), dfs(1, 1)) + "\n");  // dfs ( node , colored/uncolored , parent)
            }

            n = sc.nextInt();
        }

        System.out.print(sb);

        ed = System.currentTimeMillis();
        System.err.println("Time taken = " + (ed - st) + " ms");
    }

    static int dfs(int curr, int colored) {

        if (dp[curr][colored] != -1)
            return dp[curr][colored];


        int res = 0;
        vis[curr] = true;
        if (colored == 1) { //if colored , then 2 choices either color the adj or not
            for (int adj : a[curr]) {
                if (vis[adj])
                    continue;
                res += Math.min(dfs(adj, 0), dfs(adj, 1));

            }
            res++; // since colored count the curr vertex as well

        } else { //if not colored , then only one choice that is color the adj
            for (int adj : a[curr]) {
                if (vis[adj])
                    continue;

                res += dfs(adj, 1);

            }
        }

        vis[curr] = false;
        return dp[curr][colored] = res;
    }
}
