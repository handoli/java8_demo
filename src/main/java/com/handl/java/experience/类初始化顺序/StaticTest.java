package com.handl.java.experience.类初始化顺序;


/**
 * Created by handl on 2017/10/2.
 *
 * 1、初始化父类的静态变量，静态代码块，初始化的顺序按照出现顺序。
 2、初始化子类的静态变量，静态代码块。
 3、初始化父类的成员变量。
 4、执行父类的构造函数。
 5、初始化子类的成员变量。
 6、构造代码块创建对象时执行。
 7、执行子类的构造函数。
 执行的结果为：
 2
 3
 a=100,b=0
 1
 4
 产生这个结果的原因的关键在这一句话：
 static StaticTest st = new StaticTest();
 st变量的引用是本类的实例，因此在实例化st变量时，将实例初始化嵌入到静态初始化中。
 因为这一句放在静态初始化的开头，所以static int b=112没有被调用，输出的b=0，同时，
 输出1也在2和3后面。在对象的初始化时，也是先初始化环境变量，再执行构造函数，a的值为100。
 *
 *
 */
public class StaticTest {
    public static void main(String args[]){
        staticFunction();
    }
    static StaticTest st = new StaticTest();
    static{
        System.out.println("1");
    }
    StaticTest(){
        System.out.println("3");
        System.out.println("a="+a+" b="+b);
    }
    public static void staticFunction(){
        System.out.println("4");
    }
    {
        System.out.println("2");
    }
    int a=100;
    static int b=112;
}

