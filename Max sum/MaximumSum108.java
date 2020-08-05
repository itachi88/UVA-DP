package UVA;

import java.io.FileInputStream;
import java.util.Scanner;

//MaximumSum108
public class MaximumSum108 {
    static long start, end;

    public static void main(String[] args) throws Exception {
        start = System.currentTimeMillis();
        Scanner sc = new Scanner(new FileInputStream("/home/itachi/codechef/Assignments _GFG _sessions/src/UVA/in.txt"));
//        Scanner sc = new Scanner(System.in);

        StringBuilder sb = new StringBuilder();
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[][] a = new int[n][n];


            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++)
                    a[i][j] = sc.nextInt();
            }

//
//        System.out.println("print Array");
//        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(a[i]));
//        }
//        System.out.println("----printed Array----");

            int ans = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) { // r1
                for (int j = i; j < n; j++) { //r2
                    int[] v = new int[n];
                    for (int k = 0; k < n; k++) { // choosing a column
                        for (int l = i; l <= j; l++) { // for the chosen column count the vertical sum within r1 and r2
                            v[k] += a[l][k];
                        }
                    }
                    // use kadane's
//                    System.out.println(Arrays.toString(v));
                    ans = Math.max(ans, mss(v));
                }
            }
            sb.append(ans + "\n");
        }
        System.out.print(sb);
        end = System.currentTimeMillis();
        System.err.println("Time taken = " + (end - start) + " ms");
    }

    static int mss(int[] a) {
        int n = a.length;
        int curr = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            curr += a[i];
            max = Math.max(max, curr);
            if (curr < 0) {
                curr = 0;
            }
        }

        return max;
    }
}
