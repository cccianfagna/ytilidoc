package com.ytilidoc;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given two strings X and Y. The task is to find the length of the longest subsequence of string X which is a substring in sequence Y.
 * Examples:
 * <p>
 * <p>
 * Input : X = "ABCD",  Y = "BACDBDCD"
 * Output : 3
 * "ACD" is longest subsequence of X which
 * is substring of Y.
 * <p>
 * Input : X = "A",  Y = "A"
 * Output : 1
 * <p>
 * A subsequence of a string is a sequence of chars formed by removing zero or more chars from the string.
 * Example, the subsequences of "abc" are a, b, c, ab, ac, bc,  abc
 * <p>
 * A substring is a contiguous segment of one or more of a string's chars, example
 * substring of abc are a, b, c,ab, bc, abc
 * <p>
 * Given 2 strings x and y, determine the length of the longest subsequence that is also a
 * substring of y.
 * <p>
 * Examples
 * x= abcd
 * y= abdc
 * result abd length 3
 * <p>
 * x=abc
 * y=aedace
 * result ac length 2
 * <p>
 * <p>
 * length of X and Y >= 1 and <= 2000
 */
public class LongestSubSeqInSubString {

    public static void main(String[] args) {
        String subSequenceString = "abc";
        String subString = "aedace";
        long startTime = System.currentTimeMillis();
        System.out.println(bruteForceSolution(subSequenceString, subString));
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));

        System.out.println();

        startTime = System.currentTimeMillis();
        System.out.println(recursiveSolution(subSequenceString, subString));
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));

        System.out.println();

        int[][] cache = new int[subSequenceString.length() + 1][subString.length() + 1];
        IntStream.range(0, subSequenceString.length() + 1).forEach(i ->
                IntStream.range(0, subString.length() + 1).forEach(j ->
                        cache[i][j] = -1));

        startTime = System.currentTimeMillis();
        System.out.println(recursiveSolutionWithMemoization(cache, subSequenceString, subString));
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));
    }

    private static int recursiveSolutionWithMemoization(int[][] cache, String subSequenceString, String subString) {
        int lengthSubSeqString = subSequenceString.length();
        int lengthSubString = subString.length();
        int maxLength = 0;
        for (int i = 0; i <= lengthSubString; i++) {
            maxLength = Math.max(maxLength,
                    maxLengthSubSequenceSubStringWithMemoization(cache, subSequenceString, subString, lengthSubSeqString, i));
        }
        return maxLength;
    }

    private static int maxLengthSubSequenceSubStringWithMemoization(int [][] cache, String subSequenceString, String subString, int subSeqIndex, int subStringIndex) {
        if (subSeqIndex == 0 || subStringIndex == 0) {
            return 0;
        }
        int cacheValue = cache[subSeqIndex][subStringIndex];
        if (cacheValue != -1) {
            return cacheValue;
        }
        if (subSequenceString.charAt(subSeqIndex - 1) == subString.charAt(subStringIndex - 1)) {
            return cache[subSeqIndex][subStringIndex] =
                    1 + maxLengthSubSequenceSubStringWithMemoization(cache, subSequenceString, subString,
                            subSeqIndex - 1, subStringIndex - 1);
        }
        return cache[subSeqIndex][subStringIndex] = maxLengthSubSequenceSubStringWithMemoization(cache, subSequenceString, subString,
                subSeqIndex - 1, subStringIndex);
    }

    private static int recursiveSolution(String subSequenceString, String subString) {
        int lengthSubSeqString = subSequenceString.length();
        int lengthSubString = subString.length();
        int maxLength = 0;
        for (int i = 0; i <= lengthSubString; i++) {
            maxLength = Math.max(maxLength,
                    maxLengthSubSequenceSubString(subSequenceString, subString, lengthSubSeqString, i));
        }
        return maxLength;
    }

    private static int maxLengthSubSequenceSubString(String subSequenceString, String subString, int subSeqIndex, int subStringIndex) {
        if (subSeqIndex == 0 || subStringIndex == 0) {
            return 0;
        }
        if (subSequenceString.charAt(subSeqIndex - 1) == subString.charAt(subStringIndex - 1)) {
            return 1 +
                    maxLengthSubSequenceSubString(subSequenceString, subString,
                            subSeqIndex - 1, subStringIndex - 1);
        }
        return maxLengthSubSequenceSubString(subSequenceString, subString,
                        subSeqIndex - 1, subStringIndex);
    }

    private static int bruteForceSolution(String subSequenceString, String subString) {
        Set<String> result = subSequencesOf(subSequenceString);
        result.retainAll(subStringsOf(subString));
        return result.stream().
                map(String::length).
                reduce(Math::max).orElseThrow();
    }

    private static Set<String> subStringsOf(String s) {
        Set<String> subStringSet = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                for (int k = s.length(); j < k; k--) {
                    subStringSet.add(s.substring(j, k));
                }
            }
        }
        return subStringSet;
    }

    private static Set<String> subSequencesOf(String s) {
        Set<String> subSequencesSet = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < i + 1; j++) {
                if (subSequencesSet.isEmpty()) {
                    subSequencesSet.add(s.substring(i, j + 1));
                } else {
                    String character = s.substring(i, j + 1);
                    subSequencesSet.addAll(
                            subSequencesSet.stream().
                                    map(subSequence -> subSequence + character).
                                    collect(Collectors.toSet())
                    );
                    subSequencesSet.add(character);
                }
            }
        }
        return subSequencesSet;
    }
}
