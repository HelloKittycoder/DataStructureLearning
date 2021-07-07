package com.kittycoder.datastructure.hashtab;

/**
 * Created by shucheng on 2021/6/5 19:45
 * 哈希表代码实现（结合课程思路自己写的）
 */
public class MyHashTabDemo {
    private MyEmpLinkedList[] myEmpLinkedListArray;
    private int size;

    public static void main(String[] args) {
        MyHashTabDemo h = new MyHashTabDemo(5);
        MyEmp myEmp = new MyEmp(1, "张三");
        h.add(myEmp);
        myEmp = new MyEmp(2, "李四");
        h.add(myEmp);
        myEmp = new MyEmp(3, "王五");
        h.add(myEmp);
        myEmp = new MyEmp(4, "赵六");
        h.add(myEmp);
        myEmp = new MyEmp(5, "田七");
        h.add(myEmp);
        myEmp = new MyEmp(6, "吴八");
        h.add(myEmp);
        h.list();

        MyEmp findMyEmp = h.findById(6);
        System.out.println("findById  " + findMyEmp);
    }

    public MyHashTabDemo(int size) {
        this.myEmpLinkedListArray = new MyEmpLinkedList[size];
        this.size = size;
    }

    public void add(MyEmp myEmp) {
        int hash = hash(myEmp.getId());
        MyEmpLinkedList myEmpLinkedList = myEmpLinkedListArray[hash];
        if (myEmpLinkedList == null) {
            myEmpLinkedListArray[hash] = new MyEmpLinkedList();
            myEmpLinkedList = myEmpLinkedListArray[hash];
        }
        myEmpLinkedList.add(myEmp);
    }

    public void list() {
        for (MyEmpLinkedList myEmpLinkedList : myEmpLinkedListArray) {
            if (myEmpLinkedList != null) {
                myEmpLinkedList.list();
            }
        }
    }

    public MyEmp findById(int id) {
        int hash = hash(id);
        MyEmpLinkedList myEmpLinkedList = myEmpLinkedListArray[hash];
        if (myEmpLinkedList == null) {
            return null;
        }
        return myEmpLinkedList.findById(id);
    }

    public int hash(int id) {
        return id % size;
    }
}

class MyEmpLinkedList {
    private MyEmp head;

    public void add(MyEmp myEmp) {
        if (head == null) {
            head = myEmp;
            return;
        }
        MyEmp curMyEmp = head;
        while (true) {
            if (curMyEmp.getNext() == null) {
                break;
            }
            curMyEmp = curMyEmp.getNext();
        }
        curMyEmp.setNext(myEmp);
    }

    public void list() {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        MyEmp curMyEmp = head;
        while (true) {
            System.out.printf("==>id=%d name=%s", curMyEmp.getId(), curMyEmp.getName());
            if (curMyEmp.getNext() == null) {
                break;
            }
            curMyEmp = curMyEmp.getNext();
        }
        System.out.println();
    }

    public MyEmp findById(int id) {
        if (head == null) {
            return null;
        }
        MyEmp curMyEmp = head;
        while (true) {
            if (curMyEmp.getId() == id) {
                return curMyEmp;
            }
            if (curMyEmp.getNext() == null) {
                break;
            }
            curMyEmp = curMyEmp.getNext();
        }
        return null;
    }
}

class MyEmp {
    private int id;
    private String name;
    private MyEmp next;

    public MyEmp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setNext(MyEmp next) {
        this.next = next;
    }

    public MyEmp getNext() {
        return next;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' + "}";
    }
}
