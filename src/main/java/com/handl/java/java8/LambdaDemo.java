package com.handl.java.java8;

import java.util.ArrayList;
import java.util.List;

public class LambdaDemo {
    //使用lamdba

    //1.定义一个方法接受需要处理的数据和处理的行为（函数式接口）
    public static List<Apple> filter(List<Apple> apples ,AppleLamdba<Boolean,Apple> lamdba){

        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples){
            //默认方法
            lamdba.say(apple);
            if (lamdba.action(apple))
                list.add(apple);
        }
        return list;
    }

    public static void main(String[] args) {
        //2.组装数据
        List<Apple> apples = new ArrayList(){{
            add(new Apple(15.6,1));
            add(new Apple(5.6,2));
            add(new Apple(13.6,3));
            add(new Apple(51.6,4));
            add(new Apple(1.6,5));
            add(new Apple(13.6,6));
        }};

        //3.调用filetr方法，传入数据和lambda
        List<Apple> appleList = filter(apples, apple -> apple.getWeight() > 10);

        appleList.forEach(apple -> System.out.println(apple.getWeight()));


    }
}


/**
 * 函数式接口：只有一个方法的接口
 * @FunctionalInterface表示接口是一个函数式接口，不写也没关系
 * 接口中可以有默认方法
 */

@FunctionalInterface
interface AppleLamdba<T,R>{
    T action(R r);

    default void say(Apple apple){
        System.out.println("重量=" + apple.getWeight());
    }
}

/**
 * 定义一个Apple类
 */
class Apple{
    double weight;
    Integer id;

    public Apple(double weight,Integer id) {
        this.weight = weight;
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public Integer getId() {
        return id;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", id=" + id +
                '}';
    }
}