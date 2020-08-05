package UVA;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//UVA1218_PerfectService
public class UVA1218_PerfectService {
    static List<Integer>[] g;
    static int n;
    static boolean[] vis;
    static final int INF = (int) 1e5;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileInputStream("/home/itachi/codechef/Assignments _GFG _sessions/src/UVA/in.txt"));
//        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        while (true) {
            n = sc.nextInt(); // nodes
            if (n == -1)
                break;
            if (n == 0)
                continue;

            g = new ArrayList[n + 1];
            vis = new boolean[n + 1];
            dp = new int[n + 1][3];

            for (int i = 0; i <= n; i++) {
                Arrays.fill(dp[i], -1);
            }
            for (int i = 1; i <= n; i++)
                g[i] = new ArrayList<>();

            for (int i = 0; i < n - 1; i++) {
                int to = sc.nextInt(), from = sc.nextInt();
                g[to].add(from);
                g[from].add(to);
            }

//            System.out.println(Arrays.toString(g));

            // 3 statuses :
            // not a server - 0
            // is a server - 1
            // not a server but serviced - 2

            int ans = Math.min(countServers(1, 0), countServers(1, 1));

//            System.out.println("============DP array++++++++");
//            for (int i = 1; i <= n; i++)
//                System.out.println(Arrays.toString(dp[i]));
            sb.append(ans + "\n");
        }

        /*
          1---> 2
                |
                |
                3 ----> 4
         */
        System.out.print(sb);
    }

    static int countServers(int curr, int status) {

        if (dp[curr][status] != -1) {

//            System.out.println("in");
            return dp[curr][status];
        }

        boolean leaf = true;
        int ans = INF;
        vis[curr] = true;

        // is leaf ?
        for (int adj : g[curr]) {
            if (!vis[adj]) {
                leaf = false;
            }
        }

        if (leaf) {
//            System.out.println("status = " + status + " leaf " + "curr = " + curr);
            vis[curr] = false;
            if (status == 0)
                return dp[curr][status] = INF;

            if (status == 1)
                return dp[curr][status] = 1;

            return dp[curr][status] = 0;
        }


        if (status == 0) {

            // assign one of the children as 1 and rest as 0
            // min ([c11 + c20 + c30] , [c10 + c21 + c30 + ...])

            int min = INF;

            for (int server : g[curr]) {
                if (!vis[server]) {
                    int ret = 0;
                    ret += countServers(server, 1);

                    for (int nonServer : g[curr]) {
                        if (!vis[nonServer] && nonServer != server) {
                            ret += countServers(nonServer, 0);
                        }
                    }

                    min = Math.min(min, ret);
                }
//                System.out.println("ret = " + ret + "min = " + min);
            }

            vis[curr] = false;
//            System.out.println("status 0 curr = " + curr + " ans = " + min);

            return dp[curr][status] = min;
        }


        if (status == 1) {

            int ret = 1;
            for (int adj : g[curr]) {
                if (!vis[adj]) {

                    //infinty check overflow
                    ret += Math.min(countServers(adj, 1), countServers(adj, 2));
                }
            }
            vis[curr] = false;
//            System.out.println("status 1 curr = " + curr + " ans = " + ret);
            return dp[curr][status] = ret;
        }

        if (status == 2) {
            int ret = 0;
            for (int adj : g[curr]) {
                if (!vis[adj]) {
                    //infinty check overflow
                    ret += countServers(adj, 0);
                }
            }

            vis[curr] = false;
//            System.out.println("status 2 curr = " + curr + " ans = " + ret);
            return dp[curr][status] = ret;

        }


        vis[curr] = false;
        return dp[curr][status] = ans;
    }
}
