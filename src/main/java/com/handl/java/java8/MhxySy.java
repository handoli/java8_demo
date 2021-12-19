package com.handl.java.java8;

import java.util.ArrayList;
import java.util.List;

class MhxySy{
    String name;
    Integer grade;
    Integer score;
    String sects;
    String des;

    public static List<MhxySy> list;

    static {
        list = new ArrayList<>();
        list.add(new MhxySy("凡凡呀",69,41000,"月宫"));
        list.add(new MhxySy("辰月",69,11000,"女儿"));
        list.add(new MhxySy("大漂亮",69,4000,"花生"));
        list.add(new MhxySy("人鱼线",69,6000,"魔王"));
        list.add(new MhxySy("小蛮腰",69,7000,"地府"));
        list.add(new MhxySy("十二楼",109,51000,"大唐"));
        list.add(new MhxySy("红中",49,10000,"狮驼"));
        list.add(new MhxySy("北凉",79,9000,"普陀"));
        list.add(new MhxySy("东心",59,7500,"魔王"));
        list.add(new MhxySy("往下跳",100,31000,"花生"));
    }

    public MhxySy(String name, Integer grade, Integer score, String sects) {
        this.name = name;
        this.grade = grade;
        this.score = score;
        this.sects = sects;
    }

    public String getName() {
        return name;
    }

    public Integer getGrade() {
        return grade;
    }

    public Integer getScore() {
        return score;
    }

    public String getSects() {
        return sects;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "MhxySy{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                ", score=" + score +
                ", sects='" + sects + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
