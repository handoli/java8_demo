package com.handl.java.experience.算法_排序_查找;

/**
 * Created by handl on 2017/12/1.
 */
public class KuaiSuPaiXu {

    public static void main(String[] args) {
        int [] is = {32,67,12,21,9,76,44,25,18,3};
        int lo = 0;
        int hi = is.length-1;
        sort(is,lo,hi);
        for (int i : is)
            System.out.print(i+",");
    }

    public static int partition(int []array,int lo,int hi){
        //固定的切分方式
        int key=array[lo];
        while(lo<hi){
            while(array[hi]>=key&&hi>lo){//从后半部分向前扫描
                hi--;
            }
            array[lo]=array[hi];
            while(array[lo]<=key&&hi>lo){//从前半部分向后扫描
                lo++;
            }
            array[hi]=array[lo];
        }
        array[hi]=key;
        return hi;
    }

    public static void sort(int[] array,int lo ,int hi){
        if(lo>=hi){
            return ;
        }
        int index=partition(array,lo,hi);
        sort(array,lo,index-1);
        sort(array,index+1,hi);
    }
}
