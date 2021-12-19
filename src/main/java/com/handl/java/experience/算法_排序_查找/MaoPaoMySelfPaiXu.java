package com.handl.java.experience.算法_排序_查找;

/**
 * Created by handl on 2017/12/1.
 */
public class MaoPaoMySelfPaiXu {
    public static void main(String[] args) {
        int [] is = {32,67,12,21,9,76,44,25,18,3};

        int iflag = is.length;
        int fori = 0;
        int forin = 0;

        for (int i = 0; i<iflag;i++){
            for (int j = 0;j<iflag-1;j++){
                System.out.println("内循环次数="+(++forin));
                if (is[j] > is [j+1]){
                    int temp = is[j];
                    is[j] = is[j+1];
                    is[j+1] = temp;
                }
            }
            iflag--;
            fori++;
            forin = 0;
        }

        for (int i : is)
            System.out.print(i+",");
        System.out.println();
        System.out.println("循环次数="+fori);
    }
}
