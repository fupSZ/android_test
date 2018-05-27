package com.androidtest.fup.dextest;

/**
 * Created by wuxin on 2018/3/24.
 */

public class TaxTest {

    public static void main(String[] argv) {
        float res = TaxActivity.getYearAfterTax(180000);

        System.out.print("res:" + res);
    }
}
