package com.handl.java.experience.算法_排序_查找;

/**
 * Created by handl on 2017/12/1.
 */
public class MaoPaoMySelfTwoPaiXu {
    public static void main(String[] args) {
        int [] is = {32,67,12,21,9,76,44,25,18,3};


        for (int i = 0; i<is.length;i++){
            int min = is[i];
            int index = i;
            for (int j = i+1;j<is.length;j++){
                if (min > is [j]){
                    min = is[j];
                    index = j;
                }
            }
            is[index] = is[i];
            is[i] = min;
        }

        for (int i : is)
            System.out.print(i+",");

    }
}
