package com.kittycoder.datastructure.huffmancode;

import java.io.*;
import java.util.*;

/**
 * Created by shucheng on 2021/6/16 21:50
 * 数据压缩-赫夫曼编码
 * 数据解压-赫夫曼解码
 * 使用赫夫曼编码压缩、解压文件
 */
public class HuffmanCode {
    public static void main(String[] args) throws Exception {
        // 测试压缩文件
        File srcFile = new File(HuffmanCode.class.getResource("src.bmp").toURI());
        File destFile = new File(srcFile.getParent(), "dest.zip");
        zipFile(srcFile.getAbsolutePath(), destFile.getAbsolutePath());

        // 测试解压文件
        File originalFile = new File(srcFile.getParent(), "original.bmp");
        unzipFile(destFile.getAbsolutePath(), originalFile.getAbsolutePath());

        /*String content = "i like like like java do you like a java";
        byte[] huffmanCodeBytes = huffmanZip(content.getBytes());
        System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes));

        byte[] decodeHuffmanBytes = decode(huffmanCodeMap, huffmanCodeBytes);
        System.out.println("原来的字符串=" + new String(decodeHuffmanBytes)); // i like like like java do you like a java*/

        //如何将 数据进行压缩
        // 分步过程
        /*String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes(); // 40

        List<Node> nodes = getNodes(contentBytes);
        System.out.println("nodes=" + nodes);

        // 创建赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        // huffmanTreeRoot.preOrder();
        getCodes(huffmanTreeRoot);
        System.out.println("生成的赫夫曼编码表=" + huffmanCodeMap);

        // 将赫夫曼编码压缩成字节数组
        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodeMap);
        System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes)); // 17*/
    }

    /**
     * 编写一个方法，完成对压缩文件的解压
     * @param zipFile 准备解压的文件
     * @param unzipFile 将文件解压到哪个位置
     */
    public static void unzipFile(String zipFile, String unzipFile) {
        // 声明输入流
        InputStream is = null;
        // 声明一个对象输入流
        ObjectInputStream ois = null;
        // 声明文件的输出流
        FileOutputStream os = null;
        try {
            // 创建文件输入流
            is = new FileInputStream(zipFile);
            // 创建一个和is关联的对象
            ois = new ObjectInputStream(is);

            // 读取byte数组，经过赫夫曼编码后的byte数组
            byte[] huffmanCodeBytes = (byte[]) ois.readObject();
            // 读取赫夫曼编码表
            Map<Byte, String> huffmanCodeMap = (Map<Byte, String>) ois.readObject();

            // 解码（将赫夫曼编码后的byte数组解码成源文件的byte数组）
            byte[] bytes = decode(huffmanCodeMap, huffmanCodeBytes);
            os = new FileOutputStream(unzipFile);
            // 将byte数组写到目标文件
            os.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 编写方法，将一个文件进行压缩
     * @param srcFile 你传入的希望压缩的文件的全路径
     * @param destFile 我们压缩后的文件的全路径
     */
    public static void zipFile(String srcFile, String destFile) {
        // 声明文件的输入流
        FileInputStream is = null;
        // 声明输出流
        FileOutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            // 创建文件的输入流
            is = new FileInputStream(srcFile);

            // 创建一个和源文件大小一样的byte数组
            byte[] bytes = new byte[is.available()];
            // 读取文件
            is.read(bytes);
            // 使用赫夫曼编码对源文件的byte数组进行压缩
            byte[] huffmanCodeBytes = huffmanZip(bytes);

            // 创建文件的输出流，存放压缩文件
            os = new FileOutputStream(destFile);
            // 创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            // 把赫夫曼编码后的byte数组写入压缩文件
            oos.writeObject(huffmanCodeBytes);
            // 这里我们以对象流的方式写入赫夫曼编码表，是为了以后我们恢复源文件使用
            // 注意一定要把赫夫曼编码表写入压缩文件
            oos.writeObject(huffmanCodeMap);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 编写一个方法，完成对压缩数据的解码
     * @param huffmanCodeMap 赫夫曼编码表map
     * @param huffmanBytes 经过赫夫曼编码后得到的字节数组
     * @return 原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodeMap, byte[] huffmanBytes) {
        // 1.先得到huffmanBytes对应的二进制字符串，形如 1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            // 判断是否为最后一个字节，如果是最后一个字节的话，则不需要补成8位
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        // System.out.println(stringBuilder);
        // 把字符串按照指定的赫夫曼编码进行解码
        /**
         * 把huffmanCodeMap的key、value对调，因为需要根据编码找到对应的字符，
         * 原本是 a->100，现在要变成 100->a
         */
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodeMap.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        // 创建一个集合，存放解析出来的byte
        List<Byte> list = new ArrayList<>();
        // i可以理解成索引，扫描stringBuilder
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1; // 小的计数器
            boolean flag = true;
            Byte b = null;

            while (flag) {
                // 1010100010111...
                // 递增的取出key 1
                String key = stringBuilder.substring(i, i + count);// i不动，让count移动，直到匹配到一个字符
                b = map.get(key);
                if (b == null) { // 说明没有匹配到
                    count++;
                } else {
                    // 匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count; // i直接移动count个位置
        }
        // 当for循环结束后，list中就存放了所有的字符（"i like like like java do you like a java"）对应的字节
        // 将List<Byte>转换成byte[]并返回
        byte[] resultBytes = new byte[list.size()];
        for (int i = 0; i < resultBytes.length; i++) {
            resultBytes[i] = list.get(i);
        }
        return resultBytes;
    }

    /**
     * 将一个byte转成一个二进制的字符串
     * 举例：-88->10101000，-90->10100110，28->11100（数组的最后一个数）
     * 结合byte[] [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
     * 还有 "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * 可以知道
     *
     * @param flag 标识是否需要补高位，如果是true，表示需要补高位；如果是false，表示不补（如果是最后一个字节，无需补高位）
     * @param b 传入的byte
     * @return 该b对应的二进制字符串（注意是按补码返回）
     */
    private static String byteToBitString(boolean flag, byte b) {
        // 使用变量保存b
        int temp = b; // 将b转成int
        // 如果是正数，我们还需要补高位
        if (flag) {
            /**
             * 按位或 256
             * 举例：1 0000 0000  | 0000 0001 = 1 0000 0001
             */
            temp |= 256;
        }

        // 返回的是temp对应的二进制的补码
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * 使用赫夫曼编码对字节数组进行压缩
     * @param bytes 原始的字符串对应的byte[]
     * @return
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //根据 nodes 创建赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //根据 赫夫曼树创建对应的赫夫曼编码
        Map<Byte, String> huffmanCodeMap = getCodes(huffmanTreeRoot);
        //根据生成的赫夫曼编码，得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodeMap);
        return huffmanCodeBytes;
    }

    //1. 将赫夫曼编码表存放在 Map<Byte,String> 形式
    //   生成的赫夫曼编码表{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
    static Map<Byte, String> huffmanCodeMap = new HashMap<>();
    //2. 在生成赫夫曼编码表示，需要去拼接路径, 定义一个StringBuilder 存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 将字符串对应的byte[]，通过生成的赫夫曼编码表，返回一个赫夫曼编码压缩后的byte[]
     * @param bytes 原始的字符串对应的byte[]
     * @param huffmanCodeMap 生成的赫夫曼编码表
     * @return 返回赫夫曼编码处理后的byte[]
     *
     * 举例： String content = "i like like like java do you like a java"; -> byte[] contentBytes = content.getBytes();
     * 返回的是字符串
     * "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * -> 对应的 byte[] huffmanCodeBytes  ，即 8位对应一个 byte,放入到 huffmanCodeBytes
     * 补码=原码取反+1，原码=补码-1，再取反
     * huffmanCodeBytes[0] =  10101000(补码) => byte  [推导  10101000=> 10101000 - 1 => 10100111(反码)=> 11011000(原码)= -88 ]
     * huffmanCodeBytes[1] = -88，
     * 最终结果为：
     * [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodeMap) {
        // 1.利用huffmanCodeMap将bytes转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 遍历bytes
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodeMap.get(b));
        }
        // System.out.println("测试 stringBuilder~~~=" + stringBuilder.toString());
        // 将 "1010100010111111110..." 转成byte[]
        // 统计返回byte[] huffmanCodeBytes 长度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        // 存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0; // 记录是第几个byte
        // 因为是每8位对应一个byte，所以步长+8
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) { // 不够8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            // 将strByte转成一个byte，放到huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    // 为了调用方便，我们重载getCodes方法
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        getCodes(root, "", stringBuilder);
        return huffmanCodeMap;

        // 上面的代码和下面这段是等价的
        /*if(root == null) {
            return null;
        }
        //处理root的左子树
        getCodes(root.left, "0", stringBuilder);
        //处理root的右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodeMap;*/
    }

    /**
     * 获取赫夫曼树所有叶子节点的编码并放入huffmanCodeMap中
     * @param node 传入节点
     * @param code 路径：左子节点是0，右子节点是1
     * @param stringBuilder 用于拼接路径
     * @return
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        // 将code加到stringBuilder2
        stringBuilder2.append(code);
        if (node != null) { // 如果node==null不处理
            // 判断当前node是叶子节点还是非叶子节点
            if (node.data == null) { // 非叶子节点
                // 递归处理
                // 向左递归
                getCodes(node.left, "0", stringBuilder2);
                // 向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else { // 说明是一个叶子节点
                // 就表示找到某个叶子节点的最后
                huffmanCodeMap.put(node.data, stringBuilder2.toString());
            }
        }
    }

    // 前序遍历
    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("赫夫曼树为空");
        }
    }

    /**
     * 根据原始字节数组生成节点对象列表
     * @param bytes 接收的字节数组
     * @return 返回的就是List形式 [Node[date=97,weight=5], Node[date=32,weight=9]......],
     */
    private static List<Node> getNodes(byte[] bytes) {
        // 创建一个ArrayList
        List<Node> nodes = new ArrayList<>();
        // 遍历bytes，统计每一个byte出现的次数 key是byte，value是byte出现的次数
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) { // Map还没有这个字符数据，第一次
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        // 把每一个键值对转成Node对象，并加到nodes集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    // 通过List<Node>创建对应的赫夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            // 排序，从小到大
            Collections.sort(nodes);
            // 取出第一棵最小的二叉树
            Node leftNode = nodes.get(0);
            // 取出第二棵最小的二叉树
            Node rightNode = nodes.get(1);
            // 创建一棵新的二叉树，它的根节点 没有data，只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            // 将已经处理的两棵二叉树从nodes中删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 将新的二叉树，加到nodes中
            nodes.add(parent);
        }
        // nodes 最后的节点，就是赫夫曼树的根节点
        return nodes.get(0);
    }
}

// 创建Node，带数据和权值
class Node implements Comparable<Node> {
    Byte data; // 存放数据（字符）本身，比如：'a'->97，' '->32
    int weight; // 权值，表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
