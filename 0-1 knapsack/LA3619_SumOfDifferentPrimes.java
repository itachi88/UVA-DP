package UVA;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

//LA3619_SumOfDifferentPrimes
public class LA3619_SumOfDifferentPrimes {
    static ArrayList<Integer> primes;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileInputStream("/home/itachi/codechef/Assignments _GFG _sessions/src/UVA/in.txt"));
//        Scanner sc = new Scanner(System.in);


        primes = new ArrayList<>();
        for (int i = 2; i <= 1120; i++)
            if (isPrime(i))
                primes.add(i);

//        System.out.println(primes);

        dp = new int[primes.size()][1121][15];
        for (int i = 0; i < dp.length; i++)
            for (int j = 0; j < dp[0].length; j++)
                for (int kk = 0; kk < dp[0][0].length; kk++)
                    dp[i][j][kk] = -1;


        StringBuilder sb = new StringBuilder();
        while (true) {
            int n = sc.nextInt(), k = sc.nextInt();
            if (n == 0 && k == 0)
                break;

            int ans = ks(0, n, k);
            sb.append(ans + "\n");
        }

        System.out.print(sb);
    }

    static int ks(int curr, int rem, int moves) { // state depends on curr weight , curr index and remaining moves

        if (rem < 0 || curr >= primes.size())
            return 0;

        if (moves == 0) { // it's one way if we manage to use up k primes and create a sum
            if (rem == 0)
                return 1;
            return 0;
        }

//        System.out.println("curr = " + curr + " rem = " + rem + " moves = " + moves);
        if (dp[curr][rem][moves] != -1) {
//            System.out.println("in");
            return dp[curr][rem][moves];
        }

        int take = 0;

        if (primes.get(curr) <= rem)
            take = ks(curr + 1, rem - primes.get(curr), moves - 1);

        int leave = ks(curr + 1, rem, moves);

        return dp[curr][rem][moves] = take + leave;

    }

    static boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0)
                return false;

        return true;
    }
}
