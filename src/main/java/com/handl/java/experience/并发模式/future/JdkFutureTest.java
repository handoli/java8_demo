package com.handl.java.experience.并发模式.future;

import java.util.concurrent.*;

/**
 * Created by handl on 2017/10/21.
 */
public class JdkFutureTest {
    public String name;
    public static void main(String[] args) {

        //线程管理器
        ExecutorService executorService = Executors.newFixedThreadPool(10);

       FutureTask<JdkFutureTest> futureTestFutureTask = new FutureTask<JdkFutureTest>(
               new Callable<JdkFutureTest>() {
                   @Override
                   public JdkFutureTest call() throws Exception {
                       JdkFutureTest jdkFutureTest = new JdkFutureTest();
                       jdkFutureTest.name="jjjj";
                       Thread.sleep(10000);
                       return jdkFutureTest;
                   }
               }
       );

       Callable<JdkFutureTest> callable = new Callable<JdkFutureTest>() {
           @Override
           public JdkFutureTest call() throws Exception {
               JdkFutureTest jdkFutureTest = new JdkFutureTest();
               jdkFutureTest.name="jjjj";
               Thread.sleep(10000);
               return jdkFutureTest;
           }
       };

        //通过sumbit执行可以回去到线程执行结果，就是Future
//        executorService.submit(futureTestFutureTask);
        Future<JdkFutureTest> future = executorService.submit(callable);
        //获取真实结果
        try {
            System.out.println("操作其他事情。。。。");
            System.out.println("查看结果。。。");
//            JdkFutureTest a = futureTestFutureTask.get();
            JdkFutureTest a = future.get();
            System.out.println(a.name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
