package com.algorithm.test;

/**
 * Created by fup on 2017/10/26.
 */
public class ChooseSort implements ISort {

    public static void main(String[] argv) {
        int a[] = {10, 9, 5, 11, 13, 2, 2, 9, 5};
        ISort sort = new ChooseSort();
        sort.sort(a, a.length, true);

        for(int i=0; i<a.length; i++) {
            System.out.print("" + a[i] + "\n");
        }
    }

    @Override
    public void sort(int[] a, int length, boolean isAscend) {
        int len = a.length;
        for(int i=0; i<len-1; i++) {
            int pos = findPos(a, i+1, isAscend);
            if(isAscend) {
                if(a[i] > a[pos]) {
                    swap(a, i, pos);
                }
            } else {
                if(a[i] < a[pos]) {
                    swap(a, i, pos);
                }
            }
        }
    }

    private void swap(int[] a, int indexA, int indexB) {
        int tmp;
        tmp = a[indexA];
        a[indexA] = a[indexB];
        a[indexB] = tmp;
    }

    //如果是升序，就寻找最小值
    private int findPos(int[] a, int beginPos, boolean isAscend) {
        if(beginPos >= a.length) {
            throw new IndexOutOfBoundsException("out of bounds");
        }
        if(beginPos == a.length -1) {//最后一个
            return beginPos;
        }
        int index = beginPos;
        for(int i=beginPos+1; i<a.length; i++) {
            if(isAscend) {
                if(a[index] > a[i]) {
                    index = i;
                }
            } else {
                if(a[index] < a[i]) {
                    index = i;
                }
            }
        }

        return index;
    }
}
