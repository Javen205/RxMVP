package com.javen.rxmvp.bean;

import java.util.List;

/**
 * Created by Javen on 2016/8/18.
 */
public class JuHeDream {

    /**
     * id : 873e943d1bcb40cd4b289e0809803343
     * title : 黄金 金子
     * des : 梦见黄金，预示会遭遇挫折。梦见有人送黄金给自己，可能会蒙受损失。女人梦见丢了黄金，预示添置新首饰。
     * list : ["梦见黄金，预示会遭遇挫折。梦见有人送黄金给自己，可能会蒙受损失。女人梦见丢了黄金，预示添置新首饰。","另外，如果梦见金银制的杯、盆、工具等，都预示要结婚，或在需要下赌注的事情上，会有好运气。","梦见金衣服、金布，预示会得到荣誉和声望。","梦见自己在寻找黄金，预示会通过自己的努力，改善处境，取得成功。","梦见炼金，或是淘金，采金，则是提醒你要提高警惕，擦亮眼睛，不要以貌取人。","梦见挖出金子，或打开金库，预示你会有意外之财。","梦见藏起金子，提醒你要好好保护自己的利益，必要的话"]
     */

    private String id;
    private String title;
    private String des;
    private List<String> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "JuHeDream{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", des='" + des + '\'' +
                ", list=" + list +
                '}';
    }
}
