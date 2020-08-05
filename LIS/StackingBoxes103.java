package Chelper_codes_gen;

import FastIO.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StackingBoxes103 {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        Integer numBoxes;
        Integer dimension;

        try {
            while ((numBoxes = in.readInt()) != null && (dimension = in.readInt()) != null) {
                Box[] box = new Box[numBoxes];

                for (int i = 0; i < numBoxes; i++) {
                    int[] dims = new int[dimension];
                    for (int j = 0; j < dimension; j++)
                        dims[j] = in.readInt();
                    Arrays.sort(dims);
                    box[i] = new Box(dims, i + 1);
                }

                 /*arrange the boxes to apply LIS later
                for (int i = 0; i < box.length; i++)
                    System.out.println(box[i]);*/

                Arrays.sort(box);

                /*System.out.println("After arrangement");
                for (int i = 0; i < box.length; i++)
                    System.out.println(box[i]);*/

                List<Integer> lis = new ArrayList<>();
//                List<Integer> curr;
                int ans = 0; //LIS length
                int[] dp = new int[numBoxes];
                Arrays.fill(dp, 1);

                int[] parent = new int[numBoxes];
                for (int i = 0; i < numBoxes; i++)
                    parent[i] = i;

                for (int i = 0; i < numBoxes; i++) {
//                    curr = new ArrayList<>();
                    for (int j = 0; j < i; j++) {
                        if (allDimensionsBigger(box[i], box[j])) { // increasing subsequence (j inside i)

                            if (dp[i] < dp[j] + 1) {
                                dp[i] = dp[j] + 1;
//                                curr.add(box[j].id);
                                parent[i] = j;
                            }
                        }
                    }

                    if (ans < dp[i]) {
                        ans = dp[i];
                        lis.clear();
                        int ch = i;
                        int par = parent[i];

                        while (ch != par) {
                            lis.add(box[ch].id);
                            ch = par;
                            par = parent[ch];
                        }

                        lis.add(box[ch].id);
                        Collections.reverse(lis);
                    }


                    /*//at this point I have computed LIS ending at i
                    // compare with previous LIS and update lis list accordingly

//                    curr.add(box[i].id);

//                    if (ans < curr.size()) {
//
//                        //update the ans as it is the new LIS length
//                        ans = Math.max(ans, curr.size());
//                        //add to the lis list
//                        lis.clear();
//                        lis.addAll(curr);
//                    }*/

                }

                /*System.out.println("dp array");
                for (int i = 0; i < dp.length; i++)
                    System.out.print(dp[i] + " ");
                System.out.println();*/

                StringBuilder sb = new StringBuilder();
                sb.append(ans + "\n");
                for (int x : lis)
                    sb.append(x + " ");
                System.out.println(sb);
            }
        } catch (Exception e) {
            System.exit(0);
        }
    }

    private boolean allDimensionsBigger(Box b1, Box b2) {
        int n = b1.dims.length;
//        System.out.println("dimension check");
//        System.out.println(b1.id + " --- " + b2.id);
        for (int i = 0; i < n; i++) {
            if (b2.dims[i] >= b1.dims[i]) {
//                System.out.println(false);
                return false;
            }
        }
//        System.out.println(true);
        return true;
    }

    private static class Box implements Comparable<Box> {
        int[] dims;
        int id;

        public Box(int[] dims, int id) {
            this.dims = dims;
            this.id = id;
        }

        public String array() {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            for (int x : dims)
                sb.append(x + " ");
            sb.append("}");
            return sb.toString();
        }

        @Override
        public String toString() {
            return "box id = " + this.id + " dims = " + array();
        }

        @Override
        public int compareTo(Box o) {
            for (int i = 0; i < dims.length; i++) {
                //if atleast one dim fails for this.box, then it's bigger
                if (this.dims[i] < o.dims[i])
                    return -1;
                if (this.dims[i] > o.dims[i])
                    return 1;
            }
            return 1;
        }
    }
}
