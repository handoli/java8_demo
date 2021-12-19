package com.handl.java.experience.并发模式.master_worker;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * Created by handl on 2017/10/21.
 */
public class MasterWorkerTest {

    public static void main(String[] args) {
        //获取当前可用cpu核数
        int in = Runtime.getRuntime().availableProcessors();
        System.out.println(in);
        Master master = new Master(new PushWorker(),in);
        for (int i = 0;i<1000;i++)
            master.submit(i);
        master.start();
        System.out.println(master.getRusult());

    }
}

//Master进程
class Master{
    //任务队列,用protected方便在同一包下访问，不用public防止对外暴露
    protected Queue<Object> workerQueue = new ConcurrentLinkedQueue<Object>();
    //结果集合
    protected Map<String,Object> resultMap = new ConcurrentHashMap<>();

    //创建的worker进程数量
    int count;
    //具体的Worker逻辑对象
    Worker worker;
    //线程管理器
    ExecutorService executorService;
    //构造器的逻辑操作
    public Master(Worker worker, int i) {
        //给Worker设置任务队列
        worker.setWorkerQueue(workerQueue);
        //给Worker设置结果集
        worker.setResultQueue(resultMap);
        this.count = i;
        this.worker = worker;
        executorService = Executors.newFixedThreadPool(i);

    }

    //添加任务
    public void submit(Object object){
        workerQueue.add(object);
    }

    //判断worker是否都已经完成
    public boolean isDone(){
        return executorService.isTerminated();
    }

    //开启任务
    public void start(){
        //概念:new Thread是创建新的线程，可以把同一个任务附着到不同线程
        //但一个线程不能启动多次
        //这里直接使用了线程管理器方式
        for (int i=0;i<count;i++)
            executorService.submit(worker);
        //不能再添加任务到线程管理器
        executorService.shutdown();
    }

    //返回结果
    public Object getRusult(){
        int re = 0;
        while (resultMap.size() > 0 || !isDone()){
            for (String s : resultMap.keySet()){
                if (s != null){
                    int i = (int) resultMap.get(s);
                    re+=i;
                    resultMap.remove(s);
                }else
                    break;
            }
        }
        return re;
    }
}

//Worker工作进程，实现Runnable，是一个线程任务
abstract class Worker implements Callable<Object>{
    //任务队列
    Queue<Object> workerQueue;
    //结果队列
    Map<String,Object> resultMap;

    public void setWorkerQueue(Queue<Object> workerQueue) {
        this.workerQueue = workerQueue;
    }

    public void setResultQueue(Map<String, Object> resultQueue) {
        this.resultMap = resultQueue;
    }

    //逻辑Worker方法（模板方法）
    abstract Object handl(Object object);

    //任务方法
    @Override
    public Object call() throws Exception {
        while (true){
            //从队列中获取任务
            Object obj = workerQueue.poll();
            //队列中任务执行完成跳出方法
            if (obj == null)
                break;
            //把结果保存到结果集合
            resultMap.put(Integer.toString(obj.hashCode()),
                    handl(obj));
            System.out.println(Thread.currentThread().getName());
        }
        return null;
    }
}

//真正的逻辑Worker实现
class PushWorker extends Worker {

    //重写handl逻辑方法
    @Override
    Object handl(Object object) {
        Integer i = (Integer) object;
        return i * i * i;
    }
}
