package com.handl.java.experience.并发模式.master_worker;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class JavaForkJoinTest extends RecursiveTask<Integer> {
    private static final int THREAD_HOLD = 2; //限定分割任务大小
    private int start,end;
    public JavaForkJoinTest(int start, int end){
        this.start = start; this.end = end;
    }
    @Override
    protected Integer compute() {
        int sum = 0;

        if((end - start) <= THREAD_HOLD){////如果任务足够小就计算
            for(int i=start;i<=end;i++)
                sum += i;
        }else{
            int middle = (start + end) / 2;
            JavaForkJoinTest left = new JavaForkJoinTest(start,middle);
            JavaForkJoinTest right = new JavaForkJoinTest(middle+1,end);
            left.fork();//执行子任务
            right.fork();//执行子任务
            int lResult = left.join();//获取子任务结果
            int rResult = right.join();//获取子任务结果
            sum = lResult + rResult;
        }
        return sum;
    }
    public static void main(String[] args){
        ForkJoinPool pool = new ForkJoinPool(); //使用ForkJoinPool来调用任务
        JavaForkJoinTest task = new JavaForkJoinTest(1,4);
        Future<Integer> result = pool.submit(task);//使用ForkJoinPool来调用任务
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}