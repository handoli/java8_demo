package com.handl.java.experience.并发模式.生产消费模式;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by handl on 2017/10/22.
 */
public class ProducerConsumer {
    public static void main(String[] args) {
        BlockingDeque<String> blockingDeque = new LinkedBlockingDeque<>(1000);
        //线程管理器
        ExecutorService service = Executors.newFixedThreadPool(6);
        //添加生产者
        service.execute(new Producer(blockingDeque,"生产一一一一一号"));
        service.execute(new Producer(blockingDeque,"生产二二二二二号"));
        service.execute(new Producer(blockingDeque,"生产三三三三三号"));
        //添加消费者
        service.execute(new Consumer(blockingDeque,"消费一号"));
        service.execute(new Consumer(blockingDeque,"消费二号"));
        service.execute(new Consumer(blockingDeque,"消费三号"));

        service.shutdown();
        if (service.isTerminated())
            System.exit(0);
    }
}

//生产者
class Producer implements Runnable{

    BlockingDeque<String> blockingDeque;
    String name;
    int i;
    public Producer(BlockingDeque<String> blockingDeque,String name) {
        this.blockingDeque = blockingDeque;
        this.name = name;
    }

    @Override
    public void run() {
       while (true){
           if (i >= 100)
               break;
           i++;
           String s = "烧鸭子";
           blockingDeque.add(s);
           System.out.println("name = "+name+",做了"+i+"只鸭子");
//           try {
//               Thread.sleep(500);
//           } catch (InterruptedException e) {
//               e.printStackTrace();
//           }
       }
    }
}

class Consumer implements Runnable{

    BlockingDeque<String> blockingDeque;
    String name;

    public Consumer(BlockingDeque<String> blockingDeque, String name) {
        this.blockingDeque = blockingDeque;
        this.name = name;
    }

    @Override
    public void run() {
        String s = null;
        int i= 0;
        try {
            while (true){
                if ( (s= blockingDeque.take())!=null){
                    i++;
                    System.out.println("name = "+name+",吃了"+i+"只鸭子----"+s);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
