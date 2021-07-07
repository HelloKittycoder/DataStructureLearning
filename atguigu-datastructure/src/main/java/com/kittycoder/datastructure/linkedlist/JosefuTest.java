package com.kittycoder.datastructure.linkedlist;

import org.junit.Test;

/**
 * Created by shucheng on 2019/12/30 0:07
 */
public class JosefuTest {

    @Test
    public void testAddBoy() {
        Josefu j = new Josefu();
        j.addBoy(5);
        j.showBoy();
    }

    // 测试countBoy和countBoy2
    @Test
    public void testCountBoy() {
        Josefu j = new Josefu();
        j.addBoy(5);
        j.countBoy(1, 2, 5);

        Josefu j2 = new Josefu();
        j2.addBoy(125);
        j2.countBoy(10, 20, 125);
    }
}
