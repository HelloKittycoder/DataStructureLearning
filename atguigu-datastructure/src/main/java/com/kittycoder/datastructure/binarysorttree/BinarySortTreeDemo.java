package com.kittycoder.datastructure.binarysorttree;

/**
 * Created by shucheng on 2021/6/26 22:47
 * 二叉排序树
 */
public class BinarySortTreeDemo {
}

class BinarySortTree {
    private Node root;

    // 查找指定节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    // 查找指定节点的父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * 编写方法：
     * 1.返回以node为根节点的二叉排序树的最小值的节点
     * 2.删除node为根节点的二叉排序树的最小值的节点
     * @param node 传入的节点（当做二叉排序树的根节点）
     * @return 返回以node为根节点的二叉排序树的最小值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        // 循环的查找左子节点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        // 这时target就指向了最小值对应的节点
        // 删除最小值对应的节点
        delNode(target.value);
        return target.value;
    }

    // 删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            // 1.需要先去找要删除的节点 targetNode
            Node targetNode = search(value);
            // 如果没有找到要删除的节点
            if (targetNode == null) {
                return;
            }
            // 如果我们发现这棵二叉树只有一个节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            // 去找到targetNode的父节点
            Node parent = searchParent(value);
            // 如果要删除的节点是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                // 判断targetNode是父节点的左子节点，还是右子节点
                if (parent.left != null && parent.left.value == value) { // 是左子节点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) { // 是右子节点
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) { // 删除有两棵子树的节点
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else { // 删除只有一棵子树的节点
                // 下面没有完全按课程里的代码来，因为课程里的代码可能会出NPE错误
                if (targetNode.left != null) { // 如果该节点只有左子树
                    if (parent != null) {
                        if (parent.left != null && parent.left.value == value) {
                            // 如果targetNode是parent的左子节点
                            parent.left = targetNode.left;
                        } else if (parent.right != null && parent.right.value == value) {
                            // 如果targetNode是parent的右子节点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else { // 如果该节点只有右子树
                    if (parent != null) {
                        if (parent.left != null && parent.left.value == value) {
                            // 如果targetNode是parent的左子节点
                            parent.left = targetNode.right;
                        } else if (parent.right != null && parent.right.value == value) {
                            // 如果targetNode是parent的右子节点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }

    // 添加节点的方法
    public void add(Node node) {
        if (root == null) {
            // 如果root为空，则直接让root指向node
            root = node;
        } else {
            root.add(node);
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (root == null) {
            System.out.println("二叉排序树为空，无法遍历");
            return;
        }
        root.infixOrder();
    }
}

// 创建Node节点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 查找要删除的节点
     * @param value 希望删除的节点的值
     * @return 如果找到返回该节点，否则返回null
     */
    public Node search(int value) {
        if (value == this.value) { // 找到就是该节点
            return this;
        } else if (value < this.value) { // 如果查找的值小于当前节点的值，向左子树递归查找
            // 如果左子节点为空
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else { // 如果查找的值大于当前节点的值，向右子树递归查找
            // 如果右子节点为空
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除节点的父节点
     * @param value 要找到的节点的值
     * @return 返回要删除节点的父节点，如果没有就返回null
     */
    public Node searchParent(int value) {
        // 如果当前节点就是要删除的节点的父节点，就返回
        if ((this.left != null && this.left.value == value)
                || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            // 如果查找的值小于当前节点的值，并且当前节点的左子节点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value); // 向左子树递归查找
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value); // 向右子树递归查找
            } else {
                return null;
            }
        }
    }

    /**
     * 添加节点
     * 递归的形式添加节点，注意需要满足二叉排序树的要求
     * @param node
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }

        // 判断传入节点的值，和当前子树的根节点的值的关系
        if (node.value < this.value) {
            // 添加的节点的值小于当前节点的值
            // 如果当前节点左子节点为null
            if (this.left == null) {
                this.left = node;
            } else {
                // 递归的向左子树添加
                this.left.add(node);
            }
        } else {
            // 添加的节点的值大于或等于当前节点的值
            if (this.right == null) {
                this.right = node;
            } else {
                // 递归的向右子树添加
                this.right.add(node);
            }
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}