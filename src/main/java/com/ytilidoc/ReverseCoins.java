package com.ytilidoc;

public class ReverseCoins {

    public static void main(String[] args) {
        System.out.println(coins(10, new int[]{1,2,3,4,5,6,7,8,9,10}));
    }

    private static int coins(int n, int[] coins) {
        int result = 0;
        for (int i = 1; i < n ; i++) {
            int k = i;
            while (k < n) {
                coins[k] = (coins[k] + 1) % 2;
                k = k + i;
            }
            result = result + coins[i];
        }
        return result;
    }
}
