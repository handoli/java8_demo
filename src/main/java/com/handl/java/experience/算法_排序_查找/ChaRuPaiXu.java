package com.handl.java.experience.算法_排序_查找;

/**
 * Created by handl on 2017/12/1.
 */
public class ChaRuPaiXu {
    public static void main(String[] args) {
        int [] a = {32,67,12,21,9,76,44,25,18,3};
        int n = a.length;
        int i,j,tmp;
        for (i = 1; i < n; i++) {
            tmp = a[i];
            for (j = i - 1; j >= 0 && a[j] > tmp; j--) {
                a[j+1] = a[j];
            }
            a[j+1] = tmp;
        }

        for (int ii : a)
            System.out.print(ii+",");
    }
}
