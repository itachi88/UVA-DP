package UVA;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Stack;

public class UVA836_LargestSubmatrix {
    static long st, end;
    static int[][] a, ps;

    public static void main(String[] args) throws Exception {
        st = System.currentTimeMillis();
        Scanner sc = new Scanner(new FileInputStream("/home/itachi/codechef/Assignments _GFG _sessions/src/UVA/in.txt"));
//      Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            String s = sc.next();
            int n = s.length();
            a = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++)
                    a[i][j] = s.charAt(j) - '0';

                if (i != n - 1)
                    s = sc.next();
            }

//            for (int i = 0; i < n; i++)
//                System.out.println(Arrays.toString(a[i]));
            ps = new int[n][n];

            for (int i = 0; i < n; i++)
                ps[0][i] = a[0][i];

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (a[i][j] != 0) {
                        ps[i][j] = a[i][j] + ps[i - 1][j];
                    }
                }
            }

//            for (int i = 0; i < n; i++)
//                System.out.println(Arrays.toString(ps[i]));

            StringBuilder sb = new StringBuilder();
            int ans = Integer.MIN_VALUE;

            for (int i = 0; i < n; i++) {
                ans = Math.max(ans, mah(ps[i]));
            }

            sb.append(ans + "\n\n");

            System.out.print(sb.toString());

        }


        end = System.currentTimeMillis();
        System.err.println("Time taken =" + (end - st) + " ms");
    }

    // max rectangle area in histogram
    static int mah(int[] a) {
        int n = a.length;
        Stack<Pair> st = new Stack<>();
        st.push(new Pair(a[0], 0));

        int curr = 1;
        int ans = -1;

        while (curr < n) {
            if (st.isEmpty()) {
                st.push(new Pair(a[curr], curr));
                continue;
            }

            if (a[curr] >= st.peek().ht) {
                st.push(new Pair(a[curr], curr));
            } else {
                Pair p = null;
                while (!st.isEmpty() && a[curr] < st.peek().ht) {
                    p = st.pop();
                    int ar = p.ht * (curr - p.idx);
                    ans = Math.max(ans, ar);
                }

                st.push(new Pair(a[curr], p.idx));
            }

            curr++;
        }

        while (!st.isEmpty()) {
            ans = Math.max(ans, st.peek().ht * (n - st.pop().idx));
        }

        return ans;
    }

    private static class Pair {
        int ht;
        int idx;

        public Pair(int ht, int idx) {
            this.ht = ht;
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "ht=" + ht +
                    ", idx=" + idx +
                    '}';
        }
    }
}
