package com.handl.java.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 中间操作(filter map limit可以连成一条流水线)
 * 终端操作(collect触发流水线执行并关闭它)
 *
 *  一个数据源（如集合）来执行一个查询；
 *  一个中间操作链，形成一条流的流水线；
 *  一个终端操作，执行流水线，并能生成结果
 */
public class StreamTerminalDemo {

    public static void main(String[] args) {

        //数据源（集合）
        List<MhxySy> list = MhxySy.list;

        /**
         * 收集与归约： Stream 接口的 collect和 reduce 方法区别
         *  reduce 方法旨在把两个值结合起来生成一个新值，它是一个不可变的归约。
         *  collect 方法的设计就是要改变容器，从而累积要输出的结果
         */

        /**
         * 终端操作：collect收集方法,方法的参数是 Collector 接口的一个实现，如toList()
         * 把结果放到list或者set等等
         */
        //collect，收集流中间操作的结果,例如：收集评分大于40000的数据
        List<MhxySy> collectList = list.stream().filter(mhxySy -> mhxySy.getScore() > 40000)
                .collect(Collectors.toList());
        collectList.forEach(mhxySy -> System.out.println(mhxySy.toString()));

        /**
         * 终端操作：归约reduce
         * 求和/求最大/最小
         */
        List<Integer> integers = Arrays.asList(6,3,23,64,80,1,36);

        //求和,第一个参数是初始值
        Integer sum = integers.stream().reduce(0,(i1,i2)->i1 + i2);
        System.out.println(sum);

        //求最大
        Optional<Integer> max = integers.stream().reduce(Integer::max);
        System.out.println(max.get());
        //求最小
        Optional<Integer> min = integers.stream().reduce(Integer::min);
        System.out.println(min.get());


        /**
         * 终端操作，匹配
         */

        //检查谓词是否至少匹配一个元素:anyMatch 方法
        boolean anyMacthBool = list.stream()
                .anyMatch(mhxySy -> mhxySy.getSects() == "月宫");
        System.out.println("anyMacth="+anyMacthBool);

        //检查谓词是否匹配所有元素:allMatch 方法
        boolean allMacthBool = list.stream()
                .allMatch(mhxySy -> mhxySy.getSects() == "月宫");
        System.out.println("allMacth="+allMacthBool);

        //检查谓词是否都不匹配所有元素：noneMatch方法
        boolean noneMacthBool = list.stream()
                .noneMatch(mhxySy -> mhxySy.getSects() == "月宫");
        System.out.println("noneMacth="+noneMacthBool);


        /**
         * 终端操作：查找
         */

        //查找第一个元素:findFirst 方法
        Optional<MhxySy> findFirstData = list.stream().findFirst();
        System.out.println(findFirstData.get().toString());

        //查找流中的任意元素：findAny 方法,返回当前流中的任意元素
        //findAny用于并行流
        Optional<MhxySy> findAnyData = list.parallelStream().findAny();
        System.out.println(findAnyData.get().toString());

    }
}
