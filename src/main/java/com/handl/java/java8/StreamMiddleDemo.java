package com.handl.java.java8;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * 中间操作(filter map limit可以连成一条流水线)
 * 终端操作(collect触发流水线执行并关闭它)
 *
 *  一个数据源（如集合）来执行一个查询；
 *  一个中间操作链，形成一条流的流水线；
 *  一个终端操作，执行流水线，并能生成结果
 */
public class StreamMiddleDemo {


    public static void main(String[] args) {
        //数据源（集合）
        List<MhxySy> list = MhxySy.list;

        /**
         * 中间操作，
         * 过滤：filter（）
         * 排序：sorted（）
         * 去重：distinct（），只有int ，long，double的实现
         * 截流：limit（n），取前n个元素
         * 跳过：skip（n），前n个不要，其它都要，与limit互补
         * 映射：map（）
         * 扁平化映射：flatMap（）
         */

        //谓词筛选:filter方法，接受一个谓词（一个返回boolean 的函数）作为参数，并返回一个包括所有符合谓词的元素的流。
        List<MhxySy> filterList = list.stream()
                .filter(mhxySy -> mhxySy.getGrade() > 60)
                .collect(Collectors.toList());

        filterList.forEach(mhxySy -> System.out.println(mhxySy));

        //排序
        List<MhxySy> sortedList = list.stream()
                .sorted(comparing(mhxySy -> mhxySy.getScore()))
                .collect(Collectors.toList());

        sortedList.forEach(mhxySy -> System.out.println(mhxySy.toString()));

        //去重:distinct方法,返回一个元素各异（根据流所生成元素的hashCode 和 equals 方法实现）的流。
        Arrays.asList(1,2,3,4,5,6,2,3,4,5).stream()
                .distinct()
                .forEach(integer -> System.out.println(integer));

        //截短流:limit(n)方法,返回一个不超过给定长度的流。所需的长度作为参数传递给limit,如果流是有序的，则最多会返回前 n 个元素
        Arrays.asList(1,2,3,4,5,6,2,3,4,5).stream()
                .limit(3)
                .forEach(integer -> System.out.println(integer));

        //跳过元素:skip(n) 方法，返回一个扔掉了前n个元素的流。如果流中元素不足n个，则返回一个空流。limit(n)和skip(n)是互补的
        Arrays.asList(1,2,3,4,5,6,2,3,4,5).stream()
                .skip(3)
                .forEach(integer -> System.out.println(integer));

        //映射:map方法,对流中每一个元素应用函数,接受一个函数作为参数。这个函数会被应用到每个元素上，并将其映射成一个新的元素
        List<Map<String,String>> mapList = list.stream()
                .map(mhxySy -> {
                    Map<String,String> m = new HashMap<>();
                    Integer grade = mhxySy.getGrade();
                    m.put(mhxySy.getName(),grade > 89 ? "神威" : grade > 69 ? "勇武" : "精锐");
                    return m;
                }).collect(Collectors.toList());

        mapList.forEach(map -> {
            for (String key : map.keySet())
                System.out.println(key+",,,"+map.get(key));
        });


        //流的扁平化处理： flatMap方法
        //使用 flatMap 方法的效果是，各个数组并不是分别映射成一个流，而是映射成流的内
        //容。所有使用 map(Arrays::stream) 时生成的单个流都被合并起来，即扁平化为一个流
        List<String> rootList = Arrays.asList("java in action".split(" "));
        List<String> flatMap =
                rootList.stream()
                        .map(s -> s.split(""))
                        .flatMap(Arrays::stream)
                        .distinct()
                        .collect(Collectors.toList());
        flatMap.forEach(s -> System.out.println(s));

    }
}


