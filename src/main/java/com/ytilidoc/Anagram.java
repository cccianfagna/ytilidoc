package com.ytilidoc;

import java.util.HashMap;
import java.util.Map;

/**
 * Two strings are given, each representing one or more words.
 * There are no punctuation characters in these strings.
 * Words are separated by single whitespace character.
 * The task is to write a function which tests whether these strings are anagrams.
 * Two strings are anagrams if they are not equal, but use exactly the same rearranged set of letters.
 * Letter casing and blank space characters should be ignored.
 * Function should return value true if two strings are anagrams and value false otherwise.
 *
 * Examples: Words "license" and "silence" are anagrams and function should return true.
 * "William Shakespeare" is anagram with "I am a weakish speller".
 */
public class Anagram {

    public static void main(String[] args) {
        String first = "license";
        String second = "silence";
        isTrue(first + " and " + second + " are anagrams", isAnagram(first, second));

        first = "William Shakespeare";
        second = "I am a weakish speller";
        isTrue(first + " and " + second + " are anagrams", isAnagram(first, second));
        isTrue(second + " and " + first + " are anagrams", isAnagram(second, first));

        first = "WILLIAM SHAKESPEARE";
        second = "I am a weakish speller";
        isTrue(first + " and " + second + " are anagrams", isAnagram(first, second));
        isTrue(second + " and " + first + " are anagrams", isAnagram(second, first));

        first = "William Shakespeare";
        second = "William Shakespeare";
        isTrue("'" + first + "' and '" + second + "' are not anagrams", !isAnagram(first, second));

        first = "WILLIAM Shakespeare";
        second = "william SHAKESPEARE";
        isTrue("'" + first + "' and '" + second + "' are not anagrams", !isAnagram(first, second));

        first = "william shakespeare";
        second = "WILLIAM SHAKESPEARE";
        isTrue("'" + first + "' and '" + second + "' are not anagrams", !isAnagram(first, second));
    }

    private static boolean isAnagram(String firstSentence, String secondSentence) {
        if (firstSentence.equalsIgnoreCase(secondSentence)) {
            return false;
        }
        int maxLength = Math.max(firstSentence.length(), secondSentence.length());
        Map<String, Integer> characterCount = new HashMap<>();
        for (int i = 0; i < maxLength; i++) {
            if (firstSentence.length() > i && !String.valueOf(firstSentence.charAt(i)).isBlank()) {
                String characterInFirstSentence = ((Character) firstSentence.charAt(i)).toString().toLowerCase();
                if (!characterCount.containsKey(characterInFirstSentence)) {
                    characterCount.put(characterInFirstSentence, 1);
                } else {
                    characterCount.put(characterInFirstSentence,
                            characterCount.get(characterInFirstSentence) + 1);
                }
            }
            if (secondSentence.length() > i && !String.valueOf(secondSentence.charAt(i)).isBlank()) {
                String characterInSecondSentence = ((Character) secondSentence.charAt(i)).toString().toLowerCase();
                if (!characterCount.containsKey(characterInSecondSentence)) {
                    characterCount.put(characterInSecondSentence, -1);
                } else {
                    characterCount.put(characterInSecondSentence,
                            characterCount.get(characterInSecondSentence) - 1);
                }
            }
        }
        return characterCount.values().stream().mapToInt(Integer::intValue).sum() == 0;
    }

    private static void isTrue(String expression, boolean trueValue) {
        if (!trueValue) {
            throw new RuntimeException("NOT TRUE expression: " + expression);
        }
    }
}
