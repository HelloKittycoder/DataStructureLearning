package com.kittycoder.datastructure.linkedlist;

import org.junit.Test;

/**
 * Created by shucheng on 2019/12/29 0:14
 */
public class DoubleLinkedListTest {

    private DoubleLinkedList generateList() {
        HeroNode2 h1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 h2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 h3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 h4 = new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(h1);
        doubleLinkedList.add(h2);
        doubleLinkedList.add(h3);
        doubleLinkedList.add(h4);
        return doubleLinkedList;
    }

    @Test
    public void testAdd(){
        DoubleLinkedList doubleLinkedList = generateList();
        doubleLinkedList.add(new HeroNode2(5, "花和尚", "鲁智深"));
        doubleLinkedList.list();
    }

    @Test
    public void testUpdate(){
        DoubleLinkedList doubleLinkedList = generateList();
        doubleLinkedList.list();

        doubleLinkedList.update(new HeroNode2(2, "小卢", "玉麒麟~~"));
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();
    }

    @Test
    public void testDel(){
        DoubleLinkedList doubleLinkedList = generateList();
        doubleLinkedList.list();

        doubleLinkedList.del(4);
        System.out.println("删除后的链表情况");
        doubleLinkedList.list();
    }

    @Test
    public void testAddByOrder() {
        HeroNode2 h1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 h2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 h3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 h4 = new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addByOrder(h2);
        doubleLinkedList.addByOrder(h1);
        doubleLinkedList.addByOrder(h4);
        doubleLinkedList.addByOrder(h3);
        doubleLinkedList.list();
    }
}
