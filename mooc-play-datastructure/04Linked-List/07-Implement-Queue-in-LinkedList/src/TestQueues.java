import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by shucheng on 2019-6-6 下午 23:01
 * 数组队列和循环队列的比较
 */
public class TestQueues {

    // 测试使用q运行opCount个enqueue和dequeue操作所需要的时间，单位：秒
    private static double testQueue(Queue<Integer> q, int opCount) {

        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++)
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        for (int i = 0; i < opCount; i++)
            q.dequeue();

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    // 一次性测试
    @Test
    public void test1() {

        int opCount = 100000;

        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue, time: " + time1 + " s");

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testQueue(loopQueue, opCount);
        System.out.println("LoopQueue, time: " + time2 + " s");

        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        double time3 = testQueue(linkedListQueue, opCount);
        System.out.println("LinkedListQueue, time: " + time3 + " s");
    }

    // 多次求平均数
    @Test
    public void test2() {
        testScheme(10, 100000);
    }

    public void testScheme(int testCount, int opCount) {
        double[] timeArr1 = new double[testCount];
        for (int i = 0; i < testCount; i++) {
            ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
            timeArr1[i] = testQueue(arrayQueue, opCount);
        }

        System.out.println("ArrayQueue, time: " + average(timeArr1) + " s");

        double[] timeArr2 = new double[testCount];
        for (int i = 0; i < testCount; i++) {
            LoopQueue<Integer> loopQueue = new LoopQueue<>();
            timeArr2[i] = testQueue(loopQueue, opCount);
        }
        System.out.println("LoopQueue, time: " + average(timeArr2) + " s");

        double[] timeArr3 = new double[testCount];
        for (int i = 0; i < testCount; i++) {
            LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
            timeArr3[i] = testQueue(linkedListQueue, opCount);
        }
        System.out.println("LinkedListQueue, time: " + average(timeArr3) + " s");
    }

    // 使用jdk8的lamda表达式求平均值
    private double average(double[] arr) {
        return Arrays.stream(arr).average().getAsDouble();
    }
}
