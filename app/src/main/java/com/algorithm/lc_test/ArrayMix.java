package com.algorithm.lc_test;

import java.util.HashMap;
import java.util.HashSet;

public class ArrayMix {
    public static void main(String[] argv) {

    }

    public static int[] mix(int[] a, int[] b) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for(int tmp : a) {
            hashMap.put(tmp, tmp);
        }
        int res[] = new int[Math.min(a.length, b.length)];

        int index;
        for(int k : b) {
            if(hashMap.containsKey())
        }
    }
}
