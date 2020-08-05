package UVA;

import java.io.FileInputStream;
import java.util.Scanner;

//UVA507_JillRidesAgain
public class UVA507_JillRidesAgain {
    static int[] a;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileInputStream("/home/itachi/codechef/Assignments _GFG _sessions/src/UVA/in.txt"));
//        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        int rid = 1;
        while (t-- > 0) {
            int r = sc.nextInt();
            if (r <= 0) {
                sb.append("Route " + rid++ + " has no nice parts\n");
                continue;
            }

            a = new int[r - 1];

            for (int i = 0; i < r - 1; i++)
                a[i] = sc.nextInt();

            int n = a.length;
            int ans = Integer.MIN_VALUE, sum = 0;
            int st = 0, ed = 0, sf = 0, endtmp = 0;
            int maxpl = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {

                sum += a[i];
                if (ans < sum) {
                    ans = sum;
                    sf = st;
                    ed = i + 1;
                    maxpl = ed - st + 1; // -1 ?
                } else if (ans == sum) {
                    endtmp = i + 1;
                    if (endtmp - st + 1 > maxpl) { // -1 ?
                        sf = st;
                        ed = endtmp;
                        maxpl = endtmp - st + 1; // -1 ?

                    }
                }

                if (sum < 0) {
                    sum = 0;
                    st = i + 1;
                }

            }

            if (ans <= 0)
                sb.append("Route " + rid++ + " has no nice parts\n");
            else
                sb.append("The nicest part of route " + rid++ + " is between stops " + (sf + 1) + " and " + (ed + 1) + "\n");
        }

        System.out.print(sb);
    }

}
