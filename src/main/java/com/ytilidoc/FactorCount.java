package com.ytilidoc;

/**
 * A positive integer D is a factor of a positive integer N if there exists an integer M such that N = D * M.
 *
 * For example, 6 is a factor of 24, because M = 4 satisfies the above condition (24 = 6 * 4).
 *
 * Write a function:
 *
 * class Solution { public int solution(int N); }
 *
 * that, given a positive integer N, returns the number of its factors.
 *
 * For example, given N = 24, the function should return 8, because 24 has 8 factors, namely 1, 2, 3, 4, 6, 8, 12, 24. There are no other factors of 24.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [1..2,147,483,647].
 */
public class FactorCount {

    public static void main(String[] args) {
        System.out.println(solution(2147483647));
    }

    private static long solution(long N) {
        long count = 0;
        long i = 1;
        while (i * i < N) {
            if (N % i == 0) {
                count = count + 2;
            }
            i = i + 1;
        }
        if (i * i == N) {
            count = count + 1;
        }
        return count;
    }
}
