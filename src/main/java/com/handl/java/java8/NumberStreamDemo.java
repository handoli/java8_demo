package com.handl.java.java8;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 数值流：原始类型流，避免了装箱成本
 * 原始类型流:IntStream 、 DoubleStream 和LongStream，分别将流中的元素特化为 int 、 long 和 double ，从而避免了暗含的装箱成本
 *
 * 每个接口都带来了进行常用数值归约的新方法，比如对数值流求和的 sum ，找到最大元素的 max 。
 * 此外还有在必要时再把它们转换回对象流的方法。要记住的是，这些特化的原因并不在
 * 于流的复杂性，而是装箱造成的复杂性——即类似 int 和 Integer 之间的效率差异
 */
public class NumberStreamDemo {

    public static void main(String[] args) {
        List<MhxySy> list = MhxySy.list;

        //映射到数值流,将流转换为特化版本的常用方法是 mapToInt 、 mapToDouble 和 mapToLong
        IntStream mapToIntStream = list.stream()
                .mapToInt(mhxysy -> mhxysy.getScore());
        mapToIntStream.forEach(i -> System.out.println(i));

        //要把原始流转换成一般对象流，可以使用 boxed 方法,此时int类型封装成Integer类型
        IntStream mapToIntStream1 = list.stream()
                .mapToInt(mhxysy -> mhxysy.getScore());
        Stream<Integer> mapToStream = mapToIntStream1.boxed();

        //对于三种原始流特化，也分别有一个 Optional 原始类
        //型特化版本： OptionalInt 、 OptionalDouble 和 OptionalLong
        IntStream mapToIntStream2 = list.stream()
                .mapToInt(mhxysy -> mhxysy.getScore());
        OptionalInt optionalInt = mapToIntStream2.max();
        System.out.println(optionalInt.getAsInt());

        //数值范围:假设你想要生成1和100之间的所有数字。
        //Java 8引入了两个可以用于 IntStream 和 LongStream 的静态方法,range 和 rangeClosed
        //这两个方法都是第一个参数接受起始值，第二个参数接受结束值。
        //但range 是不包含结束值的，而 rangeClosed 则包含结束值

        IntStream.range(1,100).forEach(i-> System.out.println(i));
        IntStream.rangeClosed(1,100).forEach(i-> System.out.println(i));
    }
}
