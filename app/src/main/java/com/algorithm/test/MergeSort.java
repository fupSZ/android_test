package com.algorithm.test;

/**
 * Created by fup on 2017/10/24.
 *
 */
public class MergeSort implements ISort{
    @Override
    public void sort(int[] a, int length, boolean isAscend) {
        IntPointer pointer = new IntPointer(a, 0 ,length);
        sortDown(pointer, isAscend);
    }

    //自低向上
    private int[] sortUp(int[] a, int length, boolean isAscend) {
//        for(int i=0; i<)
        return null;
    }

    //递归向下
    private void sortDown(IntPointer array, boolean isAscend) {
        if(array.getLength() <= 1) {
            return;
        }
        int leftLen = array.getLength() /2;
        int rightLen = array.getLength() - leftLen;

        IntPointer left = array.newPoint(0, leftLen);
        IntPointer right = array.newPoint(leftLen, rightLen);

        sortDown(left, isAscend);
        sortDown(right, isAscend);

        merge(left, right, isAscend);
    }

    private static void merge(IntPointer left, IntPointer right, /*int[] left, int leftLen, int[] right, int rightLen,*/ boolean isAscend) {
        int leghtSum = left.getLength() + right.getLength();
        int res[] = new int[leghtSum];
        int leftIndex = 0;
        int rightIndex = 0;
        for(int i =0; i<leghtSum; i++) {
            if(leftIndex >= left.getLength()) {//左侧已完成
                res[i] = right.get(rightIndex++);
            } else if(rightIndex >= right.getLength()) {
                res[i] = left.get(leftIndex++);
            } else {
                if(isAscend) {
                    if(left.get(leftIndex) <= right.get(rightIndex)) {
                        res[i] = left.get(leftIndex++);
                    } else {
                        res[i] = right.get(rightIndex++);
                    }
                } else {
                    if(left.get(leftIndex) <= right.get(rightIndex)) {
                        res[i] = right.get(rightIndex++);
                    } else {
                        res[i] = left.get(leftIndex++);
                    }
                }
            }
        }
        for(int i=0; i<leghtSum; i++) {
            left.setPos(i, res[i]);
        }
    }

    public static void main(String[] argv) {
        int a[] = new int[8];
        for(int i=0; i<8; i++) {
            a[i] = i;
        }
//        int[] b = new int[4];
//        System.arraycopy(a, 4, b, 0, 4);

//        merge(a, 4, b, 4);
        ArrayTest.printArray(a, a.length);
        new MergeSort().sort(a, 8, false);
        ArrayTest.printArray(a, a.length);
    }
}
