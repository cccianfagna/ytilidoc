package com.ytilidoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * - No empty, zero index array A of N integers.
 * - Examples:
 *      A[0] = 4
 *      A[1] = 6
 *      A[2] = 2
 *      A[3] = 2
 *      A[4] = 6
 *      A[5] = 6
 *      A[6] = 1
 *      The given function returns 4
 *
 *      A[0] = 2
 *      A[1] = 2
 *      ..
 *      ..
 *      ..
 *      A[49999] = 2
 *      A[50000] = 2
 *
 *      A[K] = 2 , 0 <= K <= 50000
 *
 *      The given function returns 50000
 *
 *      The Best solution I have found is using a map to collect all the indexes
 *      that belong to the same vale. Then I iterate the keys of that map for getting the list of indexes,
 *      here what I did was to calculate the abs between the first and last element (because indexes are in order and
 *      the difference between the first and last one would be the bigger one). So I have the bigger abs difference for each
 *      value then I get the max of all of them.
 *
 *      Last solution4 is improved it terms of space due to is not using a list or map to keep all the indexes, we use
 *      a pair with the min and max indes seen.
 *
 */
public class ArrayABSCalculation {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        isTrue("new int[] {4,6,2,2,6,6,1} == 4",
                solution(new int[] {4,6,2,2,6,6,1}) == 4);
        System.out.println("Time taken solution: " + (System.currentTimeMillis() - startTime));


        startTime = System.currentTimeMillis();
        isTrue("new int[] {4,6,2,2,6,6,1} == 4",
                solution2(new int[] {4,6,2,2,6,6,1}) == 4);
        System.out.println("Time taken solution2: " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        isTrue("new int[] {4,6,2,2,6,6,1} == 4",
                solution3(new int[] {4,6,2,2,6,6,1}) == 4);
        System.out.println("Time taken solution3: " + (System.currentTimeMillis() - startTime));


        startTime = System.currentTimeMillis();
        isTrue("new int[] {4,6,2,2,6,6,1} == 4",
                solution4(new int[] {4,6,2,2,6,6,1}) == 4);
        System.out.println("Time taken solution4: " + (System.currentTimeMillis() - startTime));


        System.out.println();





        int[] integers2 = new int[50001];
        IntStream.rangeClosed(0, 50000).forEach(i -> integers2[i] = 2);

        startTime = System.currentTimeMillis();
        isTrue("IntStream.rangeClosed(0, 50001).toArray() == 50000",
                solution(integers2) == 50000);
        System.out.println("Time taken solution: " + (System.currentTimeMillis() - startTime));

        int[] integers22 = new int[50001];
        IntStream.rangeClosed(0, 50000).forEach(i -> integers22[i] = 2);

        startTime = System.currentTimeMillis();
        isTrue("IntStream.rangeClosed(0, 50001).toArray() == 50000",
                solution2(integers22) == 50000);
        System.out.println("Time taken solution2: " + (System.currentTimeMillis() - startTime));

        int[] integers222 = new int[50001];
        IntStream.rangeClosed(0, 50000).forEach(i -> integers222[i] = 2);

        startTime = System.currentTimeMillis();
        isTrue("IntStream.rangeClosed(0, 50001).toArray() == 50000",
                solution3(integers222) == 50000);
        System.out.println("Time taken solution3: " + (System.currentTimeMillis() - startTime));


        int[] integers2222 = new int[50001];
        IntStream.rangeClosed(0, 50000).forEach(i -> integers2222[i] = 2);

        startTime = System.currentTimeMillis();
        isTrue("IntStream.rangeClosed(0, 50001).toArray() == 50000",
                solution4(integers2222) == 50000);
        System.out.println("Time taken solution4: " + (System.currentTimeMillis() - startTime));



        System.out.println();

        int[] integers3 = new int[100001];
        IntStream.rangeClosed(0, 100000).forEach(i -> integers3[i] = 2);

        startTime = System.currentTimeMillis();
        isTrue("IntStream.rangeClosed(0, 50001).toArray() == 50000",
                solution(integers3) == 100000);
        System.out.println("Time taken solution: " + (System.currentTimeMillis() - startTime));



        int[] integers33 = new int[100001];
        IntStream.rangeClosed(0, 100000).forEach(i -> integers33[i] = 2);
        startTime = System.currentTimeMillis();
        isTrue("IntStream.rangeClosed(0, 50001).toArray() == 50000",
                solution2(integers33) == 100000);
        System.out.println("Time taken solution2: " + (System.currentTimeMillis() - startTime));


        int[] integers333 = new int[100001];
        IntStream.rangeClosed(0, 100000).forEach(i -> integers333[i] = 2);
        startTime = System.currentTimeMillis();
        isTrue("IntStream.rangeClosed(0, 50001).toArray() == 50000",
                solution3(integers333) == 100000);
        System.out.println("Time taken solution3: " + (System.currentTimeMillis() - startTime));



        int[] integers3333 = new int[100001];
        IntStream.rangeClosed(0, 100000).forEach(i -> integers3333[i] = 2);
        startTime = System.currentTimeMillis();
        isTrue("IntStream.rangeClosed(0, 50001).toArray() == 50000",
                solution4(integers3333) == 100000);
        System.out.println("Time taken solution4: " + (System.currentTimeMillis() - startTime));

        System.out.println();

    }

    private static int solution4(int[] A) {
        Map<Integer, Pair> valueToIndexes = new HashMap<>();
        int length = A.length;
        Pair pair;
        for (int i = 0; i < length; i++) {
            if (!valueToIndexes.containsKey(A[i])) {
                valueToIndexes.put(A[i], new Pair(i, i));
            } else {
                pair = valueToIndexes.get(A[i]);
                valueToIndexes.put(A[i], new Pair(pair.minIdx(), i));
            }
        }

        return valueToIndexes.keySet().stream().map(key -> {
            Pair p = valueToIndexes.get(key);
            if (p.minIdx() == p.maxIdx()) {
                return 0;
            }
            return Math.abs(p.minIdx() - p.maxIdx());
        }).reduce(1, Integer::max);
    }

    private static int solution3(int[] A) {
        Map<Integer, List<Integer>>  valueToIndexes = new HashMap<>();
        int length = A.length;
        List<Integer> indexes;
        for (int i = 0; i < length; i++) {
            if (!valueToIndexes.containsKey(A[i])) {
                indexes = new ArrayList<>();
                indexes.add(i);
                valueToIndexes.put(A[i], indexes);
            } else {
                indexes = valueToIndexes.get(A[i]);
                indexes.add(i);
            }
        }

        return valueToIndexes.keySet().stream().map(key -> {
            List<Integer> idxs = valueToIndexes.get(key);
            if (idxs.size() == 1) {
                return 0;
            }
            return Math.abs(idxs.get(0) - idxs.get(idxs.size() - 1));
        }).reduce(1, Integer::max);
    }


    private static int solution2(int[] A) {
        int lenght = A.length;
        int result = 0;
        for (int i = 0; i < lenght; i++) {
            for(int j = i + 1; j < lenght; j++) {
                if (A[i] == A[j]) {
                    result = Math.max(result, Math.abs(i - j));
                }
            }
        }
        return result;
    }

    private static int solution(int[] A) {
        int N = A.length;
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (A[i] == A[j]) {
                    result = Math.max(result, Math.abs(i - j));
                }
            }
        }
        return result;
    }

    private static void isTrue(String expression, boolean trueValue) {
        if (!trueValue) {
            throw new RuntimeException("NOT TRUE expression: " + expression);
        }
    }

    private static record Pair(int minIdx, int maxIdx){};
}
