package UVA;

import java.io.FileInputStream;
import java.util.Scanner;

//UVA10827_MaxSumTorus
public class UVA10827_MaxSumTorus {
    static long st, ed;
    static int[][] a, ps;

    public static void main(String[] args) throws Exception {
        st = System.currentTimeMillis();
        Scanner sc = new Scanner(new FileInputStream("/home/itachi/codechef/Assignments _GFG _sessions/src/UVA/in.txt"));
//        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = sc.nextInt();
            int size = 2 * n - 1;
            a = new int[size][size];
            ps = new int[size][size];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    a[i][j] = sc.nextInt();
                    if (n + i < size)
                        a[n + i][j] = a[i][j];
                    if (n + j < size)
                        a[i][n + j] = a[i][j];
                }
            }

            for (int i = n; i < size; i++) {
                for (int j = n; j < size; j++)
                    a[i][j] = a[i][j - n];
            }

//            System.out.println("original array");
//            for (int i = 0; i < size; i++)
//                System.out.println(Arrays.toString(a[i]));
//            System.out.println();

            for (int i = 0; i < size; i++)
                ps[0][i] = a[0][i];

            for (int i = 1; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    ps[i][j] = ps[i - 1][j] + a[i][j];
                }
            }


//            System.out.println("prefix sum array");
//            for (int i = 0; i < size; i++)
//                System.out.println(Arrays.toString(ps[i]));
//            System.out.println();
            int ans = Integer.MIN_VALUE;
            for (int rs = 0; rs < size; rs++) { // row head
                for (int re = rs; re < rs + n; re++) { // row end
                    if (re < size) {
                        int[] tmp = new int[size];
                        for (int i = 0; i < size; i++) {
                            if (rs - 1 >= 0)
                                tmp[i] = ps[re][i] - ps[rs - 1][i];
                            else
                                tmp[i] = ps[re][i];
                        }

//                        System.out.println("TMP ARRAY---------");
//                        System.out.println(Arrays.toString(tmp));
//                        System.out.println("------------------");
//                        System.out.println();
                        for (int i = 0; i < size; i++) { // apply Kadane's from i to i+n , send indices
                            ans = Math.max(ans, mss(tmp, i, Math.min(size - 1, i + n - 1)));
                        }
                    }
                }
            }

            sb.append(ans + "\n");
        }

        System.out.print(sb);
        ed = System.currentTimeMillis();
        System.err.println("Time Taken = " + (ed - st) + " ms");
    }

    static int mss(int[] a, int st, int ed) {

//        System.out.println("TMP ARRAY---------");
//        System.out.println(Arrays.toString(a));
//        System.out.println("------------------");
//        System.out.println("start =" + st + " end=" + ed);
//        System.out.println();


        int curr = 0, ans = Integer.MIN_VALUE;
        for (int i = st; i <= ed; i++) {
            curr += a[i];
            ans = Math.max(ans, curr);
            if (curr < 0)
                curr = 0;

        }

        return ans;
    }
}
