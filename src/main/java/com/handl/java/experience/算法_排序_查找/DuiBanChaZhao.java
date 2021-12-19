package com.handl.java.experience.算法_排序_查找;

/**
 * Created by handl on 2017/11/30.
 */
public class DuiBanChaZhao {
    public static void main(String[] args) {
        int[] is = {0,1,3,7,14,17,19,22,25,28,32,54,62,77,81,99};
        //          0 1 2 3  4  5  6  7  8  9 10 11 12 13 14 15

        int f = 0;
        int l = is.length-1;

        int count = 81;

        int flag = 0;
        while (f <= l){
            int m = (f+l)/2;  //对半查找法
//            int m = (l-f)*(count-is[f])/(is[l]-is[f])+f;  //插值查找
            if (count < is[m]) {
                l = m - 1;
            }
            else if (count > is[m]) {
                f = m + 1;
            }
            else {
                System.out.println("下标为" + m+",结果是"+is[m]);
                break;
            }
            System.out.println("f="+f+",l="+l+",m="+m);
            flag++;
        }
        System.out.println("查找次数="+flag);
    }
}
