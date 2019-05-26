/**
 * Create by Administrator on 2019/5/24
 * 数组中查询元素和修改元素
 */
public class Array {

    private int[] data; // 数组容量（data.length）
    private int size; // 数组实际存放的元素数量

    // 构造函数，传入数组的容量capacity构造Array
    public Array(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    // 无参构造，默认数组的容量capacity=10
    public Array() {
        this(10);
    }

    // 获取数组中的元素个数
    public int getSize() {
        return size;
    }

    // 获取数组的容量
    public int getCapacity() {
        return data.length;
    }

    // 返回数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 向数组末尾添加一个元素
    public void addLast(int e) {
        /*if (size == data.length)
            throw new IllegalArgumentException("AddLast failed. Array is full.");
        data[size] = e;
        size++;
        // 上面两行可以简写为 data[size++] = e*/

        add(size, e);
    }

    // 向数组开头添加一个元素
    public void addFirst(int e) {
        add(0, e);
    }

    // 在第index个位置插入一个新元素e
    public void add(int index, int e) {
        if (size == data.length)
            throw new IllegalArgumentException("AddLast failed. Array is full.");

        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size");

        for (int i = size - 1; i >= index; i--)
            data[i + 1] = data[i];

        data[index] = e;

        size++;
    }

    // 获取index索引位置的元素
    public int get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        return data[index];
    }

    // 修改index索引位置的元素为e
    public void set(int index, int e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        data[index] = e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }
}
