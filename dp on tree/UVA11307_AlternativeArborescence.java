package UVA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//UVA11307_AlternativeArborescence
public class UVA11307_AlternativeArborescence {
    static List<Integer>[] g;
    static int colors = 6;
    static boolean[] vis;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
//        Scanner sc = new Scanner(new FileInputStream("/home/itachi/codechef/Assignments _GFG _sessions/src/UVA/in.txt"));
//        Scanner sc = new Scanner(System.in);

//        BufferedReader br = new BufferedReader(new FileReader("/home/itachi/codechef/Assignments _GFG _sessions/src/UVA/in.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0)
                break;

            g = new ArrayList[n];
            vis = new boolean[n];
            dp = new long[n][7];

            for (int i = 0; i < n; i++)
                for (int j = 0; j < 7; j++)
                    dp[i][j] = -1;

            for (int i = 0; i < n; i++)
                g[i] = new ArrayList<>();


            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String s = st.nextToken();

                int k = st.countTokens();
//            System.out.println("rem tokens =" + k);

                while (k-- > 0) {
                    int j = Integer.parseInt(st.nextToken());
                    g[i].add(j);
                    g[j].add(i);
                }

            }

            long ans = Long.MAX_VALUE;
            for (int i = 1; i <= colors; i++)
                ans = Math.min(ans, dfs(0, i));

//            int ans = dfs(0, 1);
            br.readLine(); // reading empty line
            sb.append(ans + "\n");
        }

        System.out.print(sb);
    }


    static long dfs(int curr, int color) {
//        System.out.println("node = " + curr + " color =" + color);

        if (dp[curr][color] != -1) {
//            System.out.println("in");
            return dp[curr][color];
        }


        vis[curr] = true;
        long ans = color;

        boolean flag = false;

        for (int adj : g[curr]) {
            long tmp = Long.MAX_VALUE;
            if (!vis[adj]) {
                flag = true;

                for (int i = 1; i <= 6; i++) {

                    if (i == color)
                        continue;

                    tmp = Math.min(tmp, dfs(adj, i));

                }

                ans += tmp;
            }

        }


        if (!flag) { // leaf
            vis[curr] = false;
            return color;
        }

        vis[curr] = false;
        return dp[curr][color] = ans;
    }
}

