package UVA;

import java.io.FileInputStream;
import java.util.Scanner;

//UVA10651_PebbleSolitaire
public class UVA10651_PebbleSolitaire {
    static char[] c;
//    static int opt1, opt2; // min pebbles we can get from curr configuration

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileInputStream("/home/itachi/codechef/Assignments _GFG _sessions/src/UVA/in.txt"));
//        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            c = sc.next().toCharArray();
            int mask = 0;
//            opt1 = 12;
//            opt2 = 12;

            for (int i = 0; i < 12; i++) {
                if (c[i] == 'o')
                    mask |= 1 << (11 - i);
            }

//            System.out.println(Integer.toBinaryString(mask));
            sb.append(dfs(mask) + "\n");

        }

        System.out.print(sb);
    }

    // -o----ooo---
    // -o---o--o--- OR
    // -o----o--o-- 3

    static int dfs(int mask) {
        int opt1, opt2;
        opt1 = opt2 = bits(mask);
//        System.out.println("===array====");
//        System.out.println(Arrays.toString(c));
//        System.out.println("==========");

//        System.out.println("MASK =" + Integer.toBinaryString(mask));
        for (int i = 0; i < 12; i++) {
            // opt1 - 110
            if (i - 1 > 0 && c[i - 2] == 'o' && c[i - 1] == 'o' && c[i] == '-') {
                //change array
                c[i - 2] = '-';
                c[i - 1] = '-';
                c[i] = 'o';

                int tmp = 7 << (11 - i); // handle negative i = 10
//                System.out.println("110tmp = " + Integer.toBinaryString(tmp));
//                System.out.println(Integer.toBinaryString(tmp ^ mask));
//                int bits = bits(dfs(mask ^ tmp));
                int bits = dfs(mask ^ tmp);
//                System.out.println("mask110 =" + Integer.toBinaryString(mask) + " bits =" + bits);
                opt1 = Math.min(opt1, bits);

                //revert array

                c[i - 2] = 'o';
                c[i - 1] = 'o';
                c[i] = '-';

            }

            // opt2 - 011

            if (i + 1 < 12 && i + 2 < 12 && c[i] == '-' && c[i + 1] == 'o' && c[i + 2] == 'o') {

                c[i + 2] = '-';
                c[i + 1] = '-';
                c[i] = 'o';

                int tmp = 7 << (9 - i);
//                System.out.println("011tmp = " + Integer.toBinaryString(tmp));
//                int bits = bits(dfs(mask ^ tmp));
//                System.out.println(Integer.toBinaryString(tmp ^ mask));
                int bits = dfs(mask ^ tmp);
//                System.out.println("mask011 =" + Integer.toBinaryString(mask) + " bits =" + bits);
                opt2 = Math.min(opt2, bits);

                c[i + 2] = 'o';
                c[i + 1] = 'o';
                c[i] = '-';
            }
        }

        return Math.min(opt1, opt2);
    }

    static int bits(int n) {
        int ans = 0;
        while (n > 0) {
            if ((n & 1) == 1)
                ans++;
            n >>= 1;
        }
        return ans;
    }
}
