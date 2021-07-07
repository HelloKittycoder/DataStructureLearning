package com.kittycoder.datastructure.linkedlist;

import org.junit.Test;

/**
 * Created by shucheng on 2019/12/29 21:51
 */
public class MyJosefuTest {

    @Test
    public void test() {
        MyJosepfu j = new MyJosepfu();
        j.add(new JsNode(2));
        j.add(new JsNode(3));
        j.add(new JsNode(4));
        j.add(new JsNode(5));
        j.list();

        j.del(1);
        j.del(2);
        j.del(3);
        j.del(4);
        j.del(5);
        System.out.println("删除数据后的链表");
        j.list();
    }

    private MyJosepfu generateList() {
        MyJosepfu j = new MyJosepfu();
        j.add(new JsNode(2));
        j.add(new JsNode(3));
        j.add(new JsNode(4));
        j.add(new JsNode(5));
        return j;
    }

    // 显示出队次序
    // int i=1;
    // int interval=2;
    @Test
    public void dequeueNum() {
        MyJosepfu j = generateList();
        j.printDequeueNum();
    }
}
