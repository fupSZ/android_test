package com.algorithm.test;

import java.util.Random;

/**
 * Created by fup on 2017/10/24.
 */
public class DataGenerate {

    public static void main(String[] argv) {
//        generateRandomArray(1000, 0, 10000);
        int array[] = generateRandomArray(10000, 0, 1000000);
        ArrayTest.printArray(array, array.length);

        MergeSort sort = new MergeSort();
        array = sort.sort(array, array.length, true);

        System.out.print("----after sort-----\n");

        ArrayTest.printArray(array, array.length);

        System.out.print("the array is order:" + ArrayTest.isArrayOrdered(array, array.length));
    }
    //产生随机数组
    //length：长度；min，max最大值最小值
    public static int[] generateRandomArray(int length, int min, int max) {
        int array[] = new int[length];

        Random random = new Random();
        for(int i=0; i<length; i++) {
            array[i] = random.nextInt(max)%(max - min + 1) + min;
        }

        return array;
    }

    //产生大量重复数据的数组
    //differentNum 不相同数据的个数
    public static int[] generateNearlyIdenticalArray(int length, int differentNum) {
        int array[] = new int[length];

        Random random = new Random();
        for(int i=0; i<length; i++) {
            array[i] = random.nextInt(differentNum);
        }

        return array;
    }

    //产生近似顺序的随机数组
    //dotNum：不顺序的点，为偶数
    public static int[] generateNearlyRandomArray(int length, int dotNum) {
        int array[] = new int[length];

        Random random = new Random();
        for(int i=0; i<length; i++) {
            array[i] = i;
        }
        for(int i =0; i<dotNum/2; i++) {
            int left = random.nextInt(length);
            int right = random.nextInt(length);
            if(left == right) {
                i--;
            } else {
                ArrayTest.swap(array, left, right);
            }
        }

        for(int i=0; i<length; i++) {
            if(array[i] != i) {
                System.out.print("[" + i + "]:"+array[i] + " is not in pos\n");
            } else {
                System.out.print("[" + i + "]:"+array[i] + "\n");
            }
        }

        return array;
    }


}
