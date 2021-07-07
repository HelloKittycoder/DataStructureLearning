package com.kittycoder.datastructure.tree.question;

/**
 * Created by shucheng on 2021/6/7 12:09
 * 二叉树删除节点课后思考题（结合课程内容自己写的）
 * 如果要删除的节点是非叶子节点，现在我们不希望将该非叶子节点为根节点的子树删除，需要指定规则，假如规则如下：
 * 1）如果该非叶子节点A只有一个子节点B，则子节点B替代节点A
 * 2）如果该非叶子节点A有左子节点B和右子节点C，则让左子节点B替代节点A
 */
public class BinaryTreeQuestion {

    public static void main(String[] args) {
        // 先需要创建一棵二叉树
        BinaryTree binaryTree = new BinaryTree();
        // 创建需要的节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        // 说明，我们先手动创建该二叉树，后面我们学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        // node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        // 测试删除节点
        System.out.println("删除前，前序遍历");
        binaryTree.preOrder();
        // 当3下面只有一个左子节点5时，1,2,3,5->1,2,5
        // 当3下面只有一个右子节点4时，1,2,3,4->1,2,4
        // 当3下面左子节点为5、右子节点为4时，1,2,3,5,4->1,2,5,4
        binaryTree.delNode(3);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder();
    }
}

// 定义 BinaryTree 二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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
            if (this.left.left == null && this.left.right == null) {
                // 如果是叶子节点
                this.left = null;
                return;
            } else if (this.left.left != null && this.left.right != null) {
                // 如果不是叶子节点，且有两个子节点，则让左子节点上升为父节点
                HeroNode oldLeft = this.left;
                this.left.left.right = oldLeft.right;
                this.left = oldLeft.left;
                oldLeft.right = null;
            } else {
                // 如果不是叶子节点，且只有一个子节点，则让子节点上升为父节点
                if (this.left.left != null) {
                    this.left = this.left.left;
                }
                if (this.left.right != null) {
                    this.left = this.left.right;
                }
            }
        }
        // 3.如果当前节点的右子节点不为空，并且右子节点就是要删除的节点，就将this.right=null；并且就返回（结束递归删除）
        if (this.right != null && this.right.no == no) {
            if (this.right.left == null && this.right.right == null) {
                // 如果是叶子节点
                this.right = null;
                return;
            } else if (this.right.left != null && this.right.right != null) {
                // 如果不是叶子节点，且有两个子节点，则让左子节点上升为父节点
                HeroNode oldRight = this.right;
                this.right.left.right = oldRight.right;
                this.right = oldRight.left;
                oldRight.right = null;
            } else {
                // 如果不是叶子节点，且只有一个子节点，则让子节点上升为父节点
                if (this.right.left != null) {
                    this.right = this.right.left;
                }
                if (this.right.right != null) {
                    this.right = this.right.right;
                }
            }
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

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
