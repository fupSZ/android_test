package com.algorithm.test;

/**
 * Created by fup on 2017/10/24.
 */
public class ArrayTest {

    public static boolean isArrayOrdered(int a[], int length) {
        Boolean isAscend = null;//升序
        for(int i=0; i<length-1; i++) {
            if(isAscend != null) {
                if(isAscend) {
                    if(a[i] > a[i+1]) {
                        return false;
                    }
                } else {
                    if(a[i] < a[i+1]) {
                        return false;
                    }
                }
            } else {
                if(a[i] > a[i +1]) {
                    isAscend = false;
                } else if(a[i] < a[i+1]) {
                    isAscend = true;
                }
            }
        }

        return true;
    }

    public static void printArray(int[] array, int length) {
        for(int i=0; i<length; i++) {
            System.out.print("[" + i + "]:"+array[i] + "\n");
        }
    }

    //交换i、j的位置
    public static void swap(int a[], int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
