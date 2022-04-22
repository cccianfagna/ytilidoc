package com.ytilidoc;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * An array A consisting of N different integers is given. The array contains integers in the range [1..(N + 1)], which means that exactly one element is missing.
 *
 * Your goal is to find that missing element.
 *
 * Write a function:
 *
 * class Solution { public int solution(int[] A); }
 *
 * that, given an array A, returns the value of the missing element.
 *
 * For example, given array A such that:
 *
 *   A[0] = 2
 *   A[1] = 3
 *   A[2] = 1
 *   A[3] = 5
 * the function should return 4, as it is the missing element.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [0..100,000];
 * the elements of A are all distinct;
 * each element of array A is an integer within the range [1..(N + 1)].
 */
public class MissingNumberInArray {
    public static void main(String[] args) {

        int[] array = IntStream.rangeClosed(2, 100001).map(i -> i).toArray();

     System.out.println(
        solution(array)
     );
    }

    private static int solution(int[] A) {
        Map<Integer, Integer> integers = new HashMap<>();

        for (int i = 0; i < A.length; i++) {
            integers.put(A[i], 1);
        }

        for (int i = 1; i <= A.length + 1; i++) {
            if (!integers.containsKey(i)) {
                return i;
            }
        }
        return A.length + 1;
    }
}
