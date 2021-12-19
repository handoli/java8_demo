package com.handl.java.java8;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 终端操作时，传递给 collect方法的参数是 Collector(收集器) 接口的一个实现
 *
 * 收集器用作高级归约：
 * 用收集器可以简洁而灵活地定义 collect 用来生成结果集合的标准。对流调用
 * collect 方法将对流中的元素触发一个归约操作（由 Collector 来参数化）
 *
 * Comparator:  比较器函数接口
 */
public class StreamCollectorDemo {

    public static void main(String[] args) {

        /**
         * 数据源（集合）
         */
        List<MhxySy> list = MhxySy.list;

        /**
         * 数据源（数据集合Integer）
         */
        List<Integer> integerList = Arrays.asList(35,65,2,4,21,98,64,32,55,27,90,44);


        //1.归约(求元素个数)操作:Collectors的counting()方法
        long count1 = integerList.stream().collect(Collectors.counting());
        //直接使用Stream API的count()方法，效果相同
        long count2 = integerList.stream().count();
        System.out.println("count1="+count1+",count2="+count2);

        //2.最大值 Collectors.maxBy 和 最小值 Collectors.minBy
        Optional<Integer> max = integerList.stream()
                .collect(Collectors.maxBy((a,b)-> a>b?1:a<b?-1:0));
        Optional<Integer> min = integerList.stream()
                .collect(Collectors.minBy((a,b)-> a>b?1:a<b?-1:0));
        System.out.println("max="+max.get()+",min="+min.get());
        //直接使用Stream API的max()和min()方法，效果相同
        Optional<Integer> maxValue = integerList.stream()
                .max((a,b)-> a>b?1:a<b?-1:0);
        Optional<Integer> minValue = integerList.stream()
                .min((a,b)-> a>b?1:a<b?-1:0);
        System.out.println("maxValue="+maxValue.get()+",minValue="+minValue.get());

        //3.汇总(求和)：Collectors的summingInt()方法
        //Collectors.summingLong 和 Collectors.summingDouble 方法的作用完全一样
        int sum = integerList.stream().collect(Collectors.summingInt(i -> i));
        System.out.println("sum="+sum);

        //4.平均值:Collectors的averagingInt方法
        //对应的 averagingLong 和averagingDouble 可以计算Long和Double数值的平均数
        double ave = integerList.stream().collect(Collectors.averagingInt(i -> i));
        System.out.println("ave="+ave);

        //5.当同时需要 最大值、最小值、归约、平均值时，使用 summarizingInt 工厂方法返回的收集器，
        // 这个收集器会把所有这些信息收集到一个叫作IntSummaryStatistics 的类里，它提供了方便的取值（getter）方法来访问结果
        //对应的 summarizingLong 和 summarizingDouble 工厂方法有相关的LongSummaryStatistics 和 DoubleSummaryStatistics 类型，适用于收集的属性是原始类型 long 或double 的情况
        IntSummaryStatistics result = integerList.stream()
                .collect(Collectors.summarizingInt(i->i));
        System.out.println(result);

        //6.连接字符串：Collectors的joining方法
        //joining 工厂方法返回的收集器会把对流中每一个对象应用 toString 方法得到的所有字符串连接成一个字符串，
        // joining 在内部使用 StringBuilder 来把生成的字符串逐个追加起来
        String joinStr = list.stream().map(mhxySy -> mhxySy.getName()).collect(Collectors.joining());
        System.out.println("join="+joinStr);
        //joining 方法有一个重载版本可以接受元素之间的分界符：
        String joinString = list.stream().map(mhxySy -> mhxySy.getSects()).collect(Collectors.joining(", "));
        System.out.println("join="+joinString);

        //7.广义的归约汇总： Collectors的reducing方法,reducing方法可以完成归约、最大值、最小值、汇总、字符串连接等操作
        //reducing方法有三个重载版本

        //求和
        //一个参数，没有默认值，返回Optional
        Optional<Integer> r1 = integerList.stream().collect(Collectors.reducing((a,b)-> a+b));
        //两个参数，有默认值，返回int
        int r2 = integerList.stream().collect(Collectors.reducing(0,(a,b)->a+b));
        //三个参数，有默认值，有转换表达式，返回int
        int r3 = integerList.stream().collect(Collectors.reducing(0,i->(i*i),(a,b)->a+b));
        System.out.println("r1="+r1.get()+",r2="+r2+",r3="+r3);

        //求最大值/最小值
        Optional<Integer> max1 = integerList.stream().collect(Collectors.reducing(Integer::max));
        int max2 = integerList.stream().collect(Collectors.reducing(0,Integer::max));
        int max3 = integerList.stream().collect(Collectors.reducing(0,i->(i*i),Integer::max));
        System.out.println("max1="+max1.get()+",max2="+max2+",max3="+max3);


        //8.分组：Collectors的groupingBy方法
        //分组函数返回的值作为映射的键，把流中所有具有这个分类值的项目的列表作为对应的映射值
        Map<String,List<MhxySy>> mhxySyMap = list.stream()
                .collect(Collectors.groupingBy(MhxySy::getSects));
        mhxySyMap.forEach((k,v)-> System.out.println("key="+k+",value="+v.toString()));

        System.out.println("------");
        //多级分组groupingBy(分类函数，二级分组函数[groupingBy(分类函数)])
        Map<String,Map<Integer,List<MhxySy>>> maps = list.stream()
                .collect(Collectors.groupingBy(MhxySy::getSects,
                Collectors.groupingBy(MhxySy::getGrade)));
        maps.forEach((k,v)-> {
            System.out.println("key="+k);
            v.forEach((k1,v1)-> System.out.println("key="+k1+",value="+v1.toString()));
        });

        System.out.println("------");
        //按子组收集数据：groupingBy(分类函数，子组数据汇总操作函数)
        //传递给第一个 groupingBy 的第二个收集器可以是任何类型
        Map<String,Long> mapCount = list.stream()
                .collect(Collectors.groupingBy(MhxySy::getSects, Collectors.counting()));
        mapCount.forEach((k,v)-> System.out.println("key="+k+",value="+v));

        System.out.println("------");
        //9.分区：Collectors的partitioningBy方法,在使用上和分组方法groupingBy一样，
        //分区是分组的特殊情况：由一个谓词（返回一个布尔值的函数）作为分类函数，它称分区函数。
        // 分区函数返回一个布尔值，这意味着得到的分组 Map 的键类型是 Boolean ，
        //于是它最多可以分为两组—— true 是一组， false 是一组
        //也分为基础的分区函数，还可以多级的分区，以及分区结果转换
        Map<Boolean,List<MhxySy>> partitionMap = list.stream()
                .collect(Collectors.partitioningBy(mhxySy -> mhxySy.getScore() > 40000));
        partitionMap.forEach((k,v)-> System.out.println("key="+k+",value="+v.toString()));

        System.out.println("------");
        //多级分区
        Map<Boolean,Map<Boolean,List<MhxySy>>> partitionMaps = list.stream()
                .collect(Collectors.partitioningBy(mhxySy -> mhxySy.getScore() > 40000,
                        Collectors.partitioningBy(mhxySy -> mhxySy.getGrade() > 69)));
        partitionMaps.forEach((k,v)->{
            System.out.println("key="+k);
            v.forEach((k1,v1)-> System.out.println("key="+k1+",value="+v1.toString()));
        });

        System.out.println("------");
        //值转换/处理
        Map<Boolean,MhxySy> toMap = list.stream()
                .collect(Collectors.partitioningBy(mhxySy -> mhxySy.getScore() > 40000,
                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(MhxySy::getScore)),
                                Optional::get)
                ));

        toMap.forEach((k,v)-> System.out.println("key="+k+",value="+v.toString()));

    }
}
