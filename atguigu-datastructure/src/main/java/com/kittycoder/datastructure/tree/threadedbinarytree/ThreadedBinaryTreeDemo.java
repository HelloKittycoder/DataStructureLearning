package com.kittycoder.datastructure.tree.threadedbinarytree;

/**
 * Created by shucheng on 2021/6/8 21:30
 * 线索化二叉树
 */
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        // 测试中序线索二叉树的功能
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        // 二叉树，后面我们要递归创建，现在简单处理，先手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        // 测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        // 测试：用10号节点来测试
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10号节点的前驱节点是=" + leftNode); // 3
        System.out.println("10号节点的后继节点是=" + rightNode); // 1

        // 当线索化二叉树后，不能再使用原来的遍历方法
        // threadedBinaryTree.infixOrder();
        System.out.println("使用线索化的方式遍历 线索化二叉树");
        threadedBinaryTree.threadedList(); // 8,3,10,1,14,6
    }
}

// 定义ThreadedBinaryTree 实现了线索化功能的二叉树
class ThreadedBinaryTree {
    private HeroNode root;

    // 为了实现线索化，需要创建指向当前节点的前驱节点的指针
    // 在递归进行线索化时，pre总是保留前一个节点
    private HeroNode pre;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void threadedNodes() {
        this.threadedNodes(root);
    }

    // 遍历中序线索二叉树的方法
    public void threadedList() {
        // 定义一个遍历，存储当前遍历的节点，从root开始
        HeroNode node = root;
        while (node != null) {
            // 循环的找到leftType==1的节点，第一个找到的就是8节点
            // 后面随着遍历而变化，因为当leftType==1时，说明该节点是按照线索化
            // 处理后的有效节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }

            // 打印当前这个节点
            System.out.println(node);
            // 如果当前节点的右指针指向的是后继节点，就一直输出
            while (node.getRightType() == 1) {
                // 获取到当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            // 替换这个遍历的节点
            node = node.getRight();
        }
    }

    /**
     * 编写对二叉树进行中序线索化的方法
     * @param node 就是当前需要线索化的节点
     */
    public void threadedNodes(HeroNode node) {
        // 如果node==null，不能线索化
        if (node == null) {
            return;
        }
        // （一）先线索化左子树
        threadedNodes(node.getLeft());
        // （二）线索化当前节点[有难度]
        // 以8节点来理解
        // 8结点的.left = null , 8结点的.leftType = 1
        if (node.getLeft() == null) {
            // 让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            // 修改当前节点的左指针的类型，指向前驱节点
            node.setLeftType(1);
        }
        // 处理后继节点
        if (pre != null && pre.getRight() == null) {
            // 让前驱节点的右边指向当前节点
            pre.setRight(node);
            // 修改前驱节点的右指针类型
            pre.setRightType(1);
        }
        //!!! 每处理一个结点后，让当前结点是下一个结点的前驱结点
        pre = node;
        //(三)再线索化右子树
        threadedNodes(node.getRight());
    }

    // 前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    // 中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    // 后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    // 删除节点
    public void delNode(int no) {
        if (root != null) {
            // 如果只有一个root节点，这里立即判断root是不是就是要删除的节点
            if (root.getNo() == no) {
                root = null;
            } else {
                // 递归删除
                root.delNode(no);
            }
        } else {
            System.out.println("空树，不能删除~");
        }
    }
}

// 先创建 HeroNode 节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //说明
    //1. 如果leftType == 0 表示指向的是左子树, 如果 1 则表示指向前驱结点
    //2. 如果rightType == 0 表示指向是右子树, 如果 1表示指向后继结点
    private int leftType;
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    // 前序遍历
    public void preOrder() {
        // 先输出父节点
        System.out.println(this);
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归向左子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    // 中序遍历
    public void infixOrder() {
        // 递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        // 输出父节点
        System.out.println(this);
        // 递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    // 后序遍历
    public void postOrder() {
        // 递归向左子树后序遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        // 递归向右子树后序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        // 输出父节点
        System.out.println(this);
    }

    // 前序遍历查找
    public HeroNode preOrderSearch(int no) {
        // System.out.println("HeroNode#preOrderSearch compare");
        // 比较当前节点是不是
        if (this.no == no) {
            return this;
        }

        /**
         * lsc注：下面把HeroNode单独提出作为变量是关键
         * 如果左子树递归没找到，则返回null，找到则直接返回；
         * 如果右子树递归，不管是否找到都要进行返回
         */
        // 1.否则判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
        // 2.如果左递归前序查找，找到节点，则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) { // 说明我们左子树找到
            return resNode;
        }
        // 1.左递归前序查找，找到节点，则返回，否则继续判断
        // 2.当前节点的右子节点是否为空，如果不空，则继续向右递归前序查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    // 中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        // 判断当前节点的左子节点是否为空，如果不为空，则递归中序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        // System.out.println("HeroNode#infixOrderSearch compare");
        // 如果找到，则返回，如果没有找到，就和当前节点比较，如果是则返回当前节点
        if (this.no == no) {
            return this;
        }

        // 否则继续进行右递归的中序查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    // 后序遍历查找
    public HeroNode postOrderSearch(int no) {
        // 判断当前节点的左子节点是否为空，如果不为空，则递归后序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        // 说明在左子树找到
        if (resNode != null) {
            return resNode;
        }

        // 如果左子树没有找到，则向右子树递归进行后序遍历查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        // System.out.println("HeroNode#postOrderSearch compare");
        // 如果左右子树都没有找到，就比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        // 下面这行直接写 return null; 也行
        return resNode;
    }

    // 递归删除节点
    // 1.如果删除的节点是叶子节点，则删除该节点
    // 2.如果删除的节点是非叶子节点，则删除该子树
    public void delNode(int no) {
        // 思路
        /**
         * 1.因为我们的二叉树是单向的，所以我们是判断当前节点的子节点是否需要删除节点，而不能判断当前这个节点是不是需要删除节点
         * 2.如果当前节点的左子节点不为空，并且左子节点就是要删除的节点，就将this.left=null；并且就返回（结束递归删除）
         * 3.如果当前节点的右子节点不为空，并且右子节点就是要删除的节点，就将this.right=null；并且就返回（结束递归删除）
         * 4.如果第2和第3步没有删除节点，那么我们就需要向左子树进行递归删除
         * 5.如果第4步也没有删除节点，则应当向右子树进行递归删除
         */
        // 2.如果当前节点的左子节点不为空，并且左子节点就是要删除的节点，就将this.left=null；并且就返回（结束递归删除）
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        // 3.如果当前节点的右子节点不为空，并且右子节点就是要删除的节点，就将this.right=null；并且就返回（结束递归删除）
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        // 4.我们就需要向左子树进行递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        // 5.向右子树进行递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
