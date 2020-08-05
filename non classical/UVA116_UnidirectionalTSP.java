package UVA;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

//UVA116_UnidirectionalTSP
public class UVA116_UnidirectionalTSP {
    static int[][] a, dp, path;
    static int r, c;
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileInputStream("/home/itachi/codechef/Assignments _GFG _sessions/src/UVA/in.txt"));
//        Scanner sc = new Scanner(System.in);

        StringBuilder sb = new StringBuilder();
        while (sc.hasNext()) {
            r = sc.nextInt();
            c = sc.nextInt();
            a = new int[r][c];

            for (int i = 0; i < r; i++)
                for (int j = 0; j < c; j++)
                    a[i][j] = sc.nextInt();


            dp = new int[r][c];
            path = new int[r][c];

            /*for (int i = 0; i < r; i++) {
                dp[i][0] = a[i][0];
                path[i][0] = i;
            }

            // column wise traversal
            for (int j = 1; j < c; j++) {
                for (int i = 0; i < r; i++) {
                    // dp[i][j] = min(dp[i][j-1] , dp[(i-1 + r) % r][j-1] , dp [i+1 % r ][j-1])

                    int fromLeft = dp[i][j - 1];
                    int fromTopLeft = dp[(i - 1 + r) % r][j - 1];
                    int fromBotLeft = dp[(i + 1) % r][j - 1];

                    int min = Math.min(fromLeft, Math.min(fromBotLeft, fromTopLeft));
                    dp[i][j] = a[i][j] + min;

                    //TODO: update path
                    // if some values are equal and contest for min , then consider the one with the least rowNumber

                    int x = (i - 1 + r) % r,
                            y = i,
                            z = (i + 1) % r;

                    int set;

//                    if (fromTopLeft == min) {
//                        if (fromLeft == min)
//                            set = Math.min(x, y);
//                        if (fromBotLeft == min)
//                            set = Math.min(x, z);
//                        else
//                            set = x;
//                    } else if (fromLeft == min) {
//                        if (fromBotLeft == min)
//                            set = Math.min(y, z);
//                        else
//                            set = y;
//                    } else
//                        set = z;

                    if (fromTopLeft == min) {
                        int o2 = fromLeft == min ? y : INF;
                        int o3 = fromBotLeft == min ? z : INF;

                        set = Math.min(x, Math.min(o2, o3));
                    } else if (fromLeft == min) {
                        int o3 = fromBotLeft == min ? z : INF;
                        set = Math.min(y, o3);
                    } else
                        set = z;


                    path[i][j] = set;
                }
            }


            System.out.println("=========== ARRAY =========");
            for (int i = 0; i < r; i++)
                System.out.println(Arrays.toString(a[i]));


            System.out.println("=========== DP =========");
            for (int i = 0; i < r; i++)
                System.out.println(Arrays.toString(dp[i]));

            System.out.println("==========\n");
            System.out.println("=========== path =========");
            for (int i = 0; i < r; i++)
                System.out.println(Arrays.toString(path[i]));

            System.out.println("==========\n");

            // find where the minimum is located in the column
            // TODO:doubt -- what if the final column contains more than one min ?????
            // TODO:how to handle and what ans should be considered in that case ?
            // Traverse all paths from all the mins and check for lexicographical ordering String.compareTo


            int minCost = INF;
            for (int i = 0; i < r; i++)
                minCost = Math.min(minCost, dp[i][c - 1]);


            // mins contains all the rows with min cost

            List<Integer> mins = new ArrayList<>();
            for (int i = 0; i < r; i++)
                if (dp[i][c - 1] == minCost)
                    mins.add(i);

            // path finding

//            LinkedList<Integer> ls = new LinkedList<>();
            //Store all paths in a list of String and then lexicographically compare and return

            List<String> ans = new ArrayList<>();

            for (int row : mins) {
                ans.add(findPath(row));
            }

            Collections.sort(ans);
            sb.append(ans.get(0) + "\n" + minCost + "\n");
        }

        System.out.print(sb);
    }

    static String findPath(int row) {
        LinkedList<Integer> ls = new LinkedList<>();
        int cnt = c;
        int col = c - 1;
        while (cnt-- > 0) {
            ls.addFirst(row + 1);
            // col is always the previous
            row = path[row][col--];
        }
        System.out.println("LIST =====");
        System.out.println(ls);

        StringBuilder sb = new StringBuilder();

        for (int x : ls)
            sb.append(x + " ");

        return sb.toString();
    }*/

            for (int i = 0; i < r; i++)
                Arrays.fill(dp[i], INF);
            for (int i = 0; i < r; i++) {
                dp[i][c - 1] = a[i][c - 1];
//                path[i][c - 1] = i;
            }

            // column traversal from right to left

            for (int j = c - 1; j > 0; j--) {
                for (int i = 0; i < r; i++) {

//                    int fromTopLeft = dp[(i - 1 + r) % r][j - 1];
//                    int fromLeft = dp[i][j - 1];
//                    int fromBotLeft = dp[(i + 1) % r][j - 1];
//
//                    int min = Math.min(fromLeft, Math.min(fromBotLeft, fromTopLeft));
//                    dp[i][j] = a[i][j] + min;

                    // find min among the 3 choices and update it

                    int tl = (i - 1 + r) % r, bl = (i + 1) % r;
//                    dp[tl][j - 1] = Math.min(dp[tl][j - 1], dp[i][j] + a[tl][j - 1]);
//                    dp[i][j - 1] = Math.min(dp[i][j - 1], dp[i][j] + a[i][j - 1]);
//                    dp[bl][j - 1] = Math.min(dp[bl][j - 1], dp[i][j] + a[bl][j - 1]);

                    if (dp[tl][j - 1] > dp[i][j] + a[tl][j - 1]) {
                        dp[tl][j - 1] = dp[i][j] + a[tl][j - 1];
                        path[tl][j - 1] = i;
                    }

                    if (dp[i][j - 1] > dp[i][j] + a[i][j - 1]) {
                        dp[i][j - 1] = dp[i][j] + a[i][j - 1];
                        path[i][j - 1] = i;
                    }

                    if (dp[bl][j - 1] > dp[i][j] + a[bl][j - 1]) {
                        dp[bl][j - 1] = dp[i][j] + a[bl][j - 1];
                        path[bl][j - 1] = i;
                    }
                }

            }
//            System.out.println("===========DP===========");
//            for (int i = 0; i < r; i++)
//                System.out.println(Arrays.toString(dp[i]));
//
//            System.out.println("===========PATH===========");
//            for (int i = 0; i < r; i++)
//                System.out.println(Arrays.toString(path[i]));


            //find the minimum cost
            int minCost = INF, st = 0;
            for (int i = 0; i < r; i++) {
                if (minCost > dp[i][0]) {
                    st = i;
                    minCost = dp[i][0];
                }

            }

            StringBuilder s = new StringBuilder();
            s.append((st + 1));

            int row = st;
            int cnt = c - 1, col = 0;
            while (cnt-- > 0) {
                s.append(" ");
                row = path[row][col++];
                s.append((row + 1));
            }

            sb.append(s + "\n" + minCost + "\n");
        }

        System.out.print(sb);
    }
}
