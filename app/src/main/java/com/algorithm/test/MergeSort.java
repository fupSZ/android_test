package com.algorithm.test;

/**
 * Created by fup on 2017/10/24.
 *
 */
public class MergeSort implements ISort{
    @Override
    public int[] sort(int[] a, int length, boolean isAscend) {
        return sortDown(a, length, isAscend);
    }

    //自低向上
    private int[] sortUp(int[] a, int length, boolean isAscend) {
//        for(int i=0; i<)
        return null;
    }

    //递归向下
    private int[] sortDown(int[] a, int length, boolean isAscend) {
        if(length <= 1) {
            return a;
        }
        int leftLen = length /2;
        int rightLen = length - leftLen;

        int[] left = new int[leftLen];
        int[] right = new int[rightLen];
        System.arraycopy(a, 0, left, 0, leftLen);
        System.arraycopy(a, leftLen, right, 0, rightLen);

        left = sortDown(left, leftLen, isAscend);
        right = sortDown(right, rightLen, isAscend);

        return merge(left, leftLen, right, rightLen, isAscend);
    }

    private static int[] merge(int[] left, int leftLen, int[] right, int rightLen, boolean isAscend) {
         int res[] = new int[leftLen + rightLen];
        int leftIndex = 0;
        int rightIndex = 0;
        for(int i =0; i<leftLen + rightLen; i++) {
            if(leftIndex >= leftLen) {//左侧已完成
                res[i] = right[rightIndex++];
            } else if(rightIndex >= rightLen) {
                res[i] = left[leftIndex++];
            } else {
                if(isAscend) {
                    if(left[leftIndex] <= right[rightIndex]) {
                        res[i] = left[leftIndex++];
                    } else {
                        res[i] = right[rightIndex++];
                    }
                } else {
                    if(left[leftIndex] <= right[rightIndex]) {
                        res[i] = right[rightIndex++];
                    } else {
                        res[i] = left[leftIndex++];
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] argv) {
        int a[] = new int[8];
        for(int i=0; i<8; i++) {
            a[i] = i;
        }
        int[] b = new int[4];
        System.arraycopy(a, 4, b, 0, 4);

//        merge(a, 4, b, 4);
    }
}
