package com.handl.java.experience.算法_排序_查找;

/**
 * Created by handl on 2017/12/1.
 */
public class MaoPaoPaiXu {
    public static void main(String[] args) {
        int [] is = {32,67,12,21,9,76,44,25,18,3};
        int fori = 0;
        int forin = 0;
        for (int i = 0; i<is.length;i++){
            for (int j = is.length-1;j>i;j--){
                System.out.println("内循环次数="+(++forin));
                if (is[j-1] > is [j]){
                    int temp = is[j-1];
                    is[j-1] = is[j];
                    is[j] = temp;
                }
            }
            forin = 0;
            fori++;
        }
        for (int i : is)
            System.out.print(i+",");
        System.out.println();
        System.out.println("循环次数="+fori);
    }
}
