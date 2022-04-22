package com.ytilidoc;

import java.util.HashMap;
import java.util.Map;

public class FizzFuzz {

    public static void main(String[] args) {

        System.out.println(fizzfuzz(0,0).equals(new HashMap<>()));
        System.out.println(fizzfuzz(0,1).equals(new HashMap<>()));
        System.out.println(fizzfuzz(3,3).equals(Map.of(3, "fizz")));
        System.out.println(fizzfuzz(5,5).equals(Map.of(5, "fuzz")));
        System.out.println(fizzfuzz(15,15).equals(Map.of(15, "fizzfuzz")));
        System.out.println(fizzfuzz(0,3).equals(Map.of(3, "fizz")));
        System.out.println(fizzfuzz(0,4).equals(Map.of(3, "fizz")));
        System.out.println(fizzfuzz(0,5).equals(
                Map.of(
                        3, "fizz",
                        5, "fuzz"
                ))
        );
        System.out.println(fizzfuzz(0,6).equals(
                Map.of(
                        3, "fizz",
                        5, "fuzz",
                        6, "fizz"
                ))
        );
        System.out.println(fizzfuzz(0,15).equals(
                Map.of(
                        3, "fizz",
                        5, "fuzz",
                        6, "fizz",
                        9, "fizz",
                        10, "fuzz",
                        12, "fizz",
                        15, "fizzfuzz"
                ))
        );
        System.out.println(fizzfuzz(-15,-0).equals(
                Map.of(
                        -3, "fizz",
                        -5, "fuzz",
                        -6, "fizz",
                        -9, "fizz",
                        -10, "fuzz",
                        -12, "fizz",
                        -15, "fizzfuzz"
                ))
        );
    }

    private static Map<Integer, String> fizzfuzz(int from, int to) {
        Map<Integer, String> result = new HashMap<>();
        if (from == to && from == 0) {
            return result;
        }
        for (int i = from; i <= to; i++) {
            if (i != 0) {
                String r = i % 3 == 0 ? "Fizz" : "";
                r = i % 5 == 0 ? r + "Fuzz" : r;
                if (!r.isBlank()) {
                    result.put(i, r);
                }
            }
        }
        return result;
    }
}
