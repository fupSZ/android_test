package com.algorithm.lc_test;

public class ChooseOccusOnce {
    public static void main(String[] argv) {

        int a[] = {1,2,3,4,1,2,3,4,10};
        int res = occusOnce(a);

        System.out.print("res:" + res);
    }

    public static int occusOnce(int[] a) {
        int len = a.length;
        int res = a[0];
        for(int i=1; i<len; i++) {
            res = res ^ a[i];
        }

        return res;
    }
}
