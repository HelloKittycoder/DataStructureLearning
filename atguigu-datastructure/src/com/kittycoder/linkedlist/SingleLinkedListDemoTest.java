package com.kittycoder.linkedlist;

import org.junit.Test;

/**
 * Created by shucheng on 2019/12/27 23:13
 */
public class SingleLinkedListDemoTest {

    @Test
    public void testAdd() {
        // 先创建节点
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");

        // 创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        // 向链表添加数据
        singleLinkedList.add(heroNode1);
        singleLinkedList.add(heroNode2);
        singleLinkedList.add(heroNode3);
        singleLinkedList.add(heroNode4);

        // 显示链表数据
        singleLinkedList.list();
    }

    // 测试addByOrder或addByOrder2
    @Test
    public void testAddByOrder() {
        // 先创建节点
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");

        // 创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder2(heroNode4);
        singleLinkedList.addByOrder2(heroNode1);
        singleLinkedList.addByOrder2(heroNode3);
        singleLinkedList.addByOrder2(heroNode2);

        /*for (int i = 0; i < 1000; i++) {
            int  no = (int) (Math.random() * i);
            HeroNode h = new HeroNode(no, "张三" + no, "昵称" + no);
            singleLinkedList.addByOrder2(h);
        }*/

        // 显示链表数据
        singleLinkedList.list();
    }

    @Test
    public void testUpdate() {
        // 先创建节点
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");

        // 创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder2(heroNode4);
        singleLinkedList.addByOrder2(heroNode1);
        singleLinkedList.addByOrder2(heroNode3);
        singleLinkedList.addByOrder2(heroNode2);

        singleLinkedList.list();
        // 修改
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        // HeroNode newHeroNode = new HeroNode(5, "小卢", "玉麒麟~~");
        singleLinkedList.update(newHeroNode);

        System.out.println("修改后的链表情况");
        singleLinkedList.list();
    }
}
