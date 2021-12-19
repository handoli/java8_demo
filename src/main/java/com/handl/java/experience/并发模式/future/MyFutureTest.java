package com.handl.java.experience.并发模式.future;

/**
 * Created by handl on 2017/10/21.
 */
public class MyFutureTest {
    public static void main(String[] args) {
        //向客户端发送请求，拿到虚拟对象
        Client client = new Client();
        FutureData data = (FutureData) client.request();
        System.out.println(data.toString());
        //提取真是对象
        if (data.realData == null)
            System.out.println("现在还是空的啊");

        //阻塞获取
        String realData = data.getResult();
        System.out.println(realData);

    }

}

//Client
class Client{

    public Data request(){
        //构建虚拟数据对象，用来及时返回
        final FutureData data = new FutureData();
        //开启线程去获取真实对象，并把结果放入虚拟数据对象
        new Thread(new Runnable() {
            public void run() {
                //构建真实对象并放入虚拟数据对象
                RealData data1 = new RealData();
                data.setRealData(data1);
            }
        }).start();

        //即可返回虚拟对象
        return data;
    }

}

interface Data{
    String getResult();
}

//伪造对象
class FutureData implements Data {
    //持有真实对象引用
    public RealData realData = null;
    boolean b = false; //线程间标识
    //因为真实对象在构建后才能获取，所以要做同步
    public synchronized void setRealData(RealData data){
        if (b)
            return;
        this.realData = data;
        b = true;
        notifyAll(); //唤醒获取对象线程
    }

    public synchronized String getResult() {

            try {
                while (!b){
                    wait(); //没有数据，等待
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
       return realData.str;
    }

    @Override
    public String toString() {
        return "假的";
    }
}

//真实对象
class RealData implements Data {
    public String str = "123";
    public RealData() {
        try {
            //模拟大对象创建，需要时间
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getResult() {
        return str;
    }

    @Override
    public String toString() {
        return "真的"+str;
    }
}
