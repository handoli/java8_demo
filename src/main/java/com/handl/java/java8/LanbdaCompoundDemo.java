package com.handl.java.java8;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * lambda复合用法
 */
public class LanbdaCompoundDemo {

    public static void main(String[] args) {
        //数据源（集合）
        List<MhxySy> list = MhxySy.list;

        //复合lambda表达式,Lambda表达式的Comparator、Function和Predicate都提供了进行复合的方法

        //1.比较器复合,使用Comparator的静态方法
        //逆序：reversed()
        //比较链：thenComparing（），第一个条件相同时在使用thenComparing（）条件
        List<MhxySy> bijiaoqi = list.stream()
                .sorted(comparing(MhxySy::getScore)
                        .reversed()
                        .thenComparing(MhxySy::getGrade))
                .collect(Collectors.toList());
        bijiaoqi.forEach(mhxySy -> System.out.println(mhxySy.toString()));

        System.out.println("谓词复合------");
        //2谓词复合：可以重用已有的Predicate来创建更复杂的谓词
        //包括三个方法:negate(!)、and(&&)和or(||)

        //筛选评分小于40000并且等级大于69的数据
        Predicate<MhxySy> weici1 = (MhxySy mhxySy)-> mhxySy.getScore() >= 40000;
        Predicate<MhxySy> weici2 = (MhxySy mhxySy)-> mhxySy.getGrade() >69;
        //取反变成小于40000评分
        List<MhxySy> weiciList = list.stream()
                .filter(weici1.negate().and(weici2)) //多谓词复合操作
                .collect(Collectors.toList());
        weiciList.forEach(mhxySy -> System.out.println(mhxySy.toString()));


        System.out.println("函数复合------");
        //3.函数复合：把Function接口所代表的Lambda表达式复合起来
        //Function接口为此配 了andThen和compose两个默认方法，它们都会返回Function的一个实例

        //创建Function lambda表达式
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;

        //andThen方法会返回一个函数，它先对输入应用一个给定函数，再对输出应用另一个函数
        Function<Integer,Integer> h1 = f.andThen(g);
        System.out.println(h1.apply(1));
        //先把给定的函数用作compose的参数里面给的那个函数，然后再把函数本身用于结果
        Function<Integer,Integer> h2 = f.compose(g);
        System.out.println(h2.apply(1));
    }
}
