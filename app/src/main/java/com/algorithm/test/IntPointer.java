package com.algorithm.test;

/**
 * Created by fup on 2017/12/25.
 * java模拟c指针
 * 没有处理成模板，因为模板不能使用基础数据类型
 */
public class IntPointer {
    int[] array;
    int index;//起点
    int arrayLen;

    public IntPointer(int[] array, int index, int arrayLen) {
        this.array = array;
        this.index = index;
        this.arrayLen = arrayLen;
    }

    public IntPointer newPoint(int start, int arrayLen) {
        return new IntPointer(array, index + start, arrayLen);
    }
//    public int[] getArray() {
//        return array;
//    }
//
//    public int getIndex() {
//        return index;
//    }

    public int getLength() {
        return arrayLen;
    }

    public int get(int pos) {
        return array[index + pos];
    }

    public void setPos(int pos, int value) {
        array[index + pos] = value;
    }
}
