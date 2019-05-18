package com.wjjzst.learn.tree;


import com.wjjzst.learn.tree.printer.BinaryTreeInfo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @Author: Wjj
 * @Date: 2019/5/6 12:17
 */
public class MyBinaryTree<E> implements BinaryTreeInfo {
    protected int size;
    protected Node<E> root;

    public int size() {
        return size;
    }

    public boolean isEmpyt() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }


    /**
     * 前序遍历
     */
    public void preorderTraversal() {
        preorderTraversal(root);
    }


    private void preorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.element);
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    public void preorder(Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        preorder(root, visitor);
    }


    private void preorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop()) {
            return;
        }
        visitor.visitWrap(node.element);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    /**
     * 中序遍历
     */
    public void inorderTraversal() {
        inorderTraversal(root);
    }


    private void inorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left);
        System.out.println(node.element);
        inorderTraversal(node.right);
    }

    public void inorder(Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        inorder(root, visitor);
    }


    private void inorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop()) {
            return;
        }
        inorder(node.left, visitor);
        visitor.visitWrap(node.element);
        inorder(node.right, visitor);
    }

    /**
     * 后序遍历
     */
    public void postorderTraversal() {
        postorderTraversal(root);
    }


    private void postorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.println(node.element);
    }

    public void postorder(Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        postorder(root, visitor);
    }


    private void postorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop()) {
            return;
        }
        postorder(node.left, visitor);
        postorder(node.right, visitor);
        visitor.visitWrap(node.element);
    }

    public void levelOrderTraversal() {
        /*if (root == null) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            System.out.println(node.element);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }*/
        levelOrder(new Visitor<E>() {
            @Override
            public boolean visit(E element) {
                System.out.println(element);
                return false;
            }
        });

    }

    public void levelOrder(Visitor<E> visitor) {
        if (root == null) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (visitor.visit(node.element)) {
                return;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    // 找前驱节点
    protected Node<E> predesessor(Node<E> node) {
        if (node == null) {
            return null;
        }
        // 前驱节点在左子树当中(left.right.right.right.right...)
        Node<E> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }
        // 从父节点,祖父节点中寻找前驱节点
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        // node = node.parent.right 跳出来
        return node.parent;
    }

    // 找后驱节点
    protected Node<E> successor(Node<E> node) {
        if (node == null) {
            return null;
        }
        // 后驱节点在左子树当中(left.right.right.right.right...)
        Node<E> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
        // 从父节点,祖父节点中寻找后驱节点
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        // node = node.parent.left 跳出来
        return node.parent;
    }

    /**
     * 树的高度
     *
     * @return
     */

    public int height() {
        if (root == null) {
            return 0;
        }
        int height = 0;
        //存储着每一层的元素数量
        int levelSize = 1; //默认第一层有1个 跟不为空
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            levelSize--;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        if (levelSize == 0) {
            levelSize = queue.size();
            height++;
        }
        return height2(root);
    }

    /**
     * 任何一个节点的高度
     *
     * @param node
     * @return
     */
    public int height2(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height2(node.left), height2(node.right));
    }

    public boolean isComplete() {
        if (root == null) {
            return false;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean leaf = false; //要求后面节点是叶子的标志
        // 保证层序节点所有的都能遍历到
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) {
                return false;
            }
            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) { //左空右不空
                // node.left == null && node.right != null;
                return false;
            }

            if (node.right != null) {
                queue.offer(node.right);
            } else {
                // node.left != null && node.right == null;
                // node.left == null && node.right == null;
                leaf = true;
            }
        }
        return true;
    }

    public boolean isComplete1() {
        if (root == null) {
            return false;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean leaf = false; //要求后面节点是叶子的标志
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) {
                return false;
            }
            if (node.hasTwoChildren()) {
                queue.offer(node.left);
                queue.offer(node.right);
            } else if (node.left == null && node.right != null) {
                return false;
            } else {  //后面的节点都应该全是叶子节点 如果不是叶子节点则返回false
                leaf = true;
                if (node.left != null) {
                    queue.offer(node.left);
                }
            }
        }
        return true;
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        StringBuilder sb = new StringBuilder();
        sb.append(((Node<E>) node).element).append("_p(");
        if (((Node<E>) node).parent == null) {
            sb.append("null");
        } else {
            sb.append(((Node<E>) node).parent.element);
        }
        sb.append(")");
        return sb.toString();
    }


    public interface Visitor<E> {
        Map flag = new HashMap();

        boolean visit(E element);

        default void visitWrap(E element) {
            if (stop()) {
                return;
            }
            if (visit(element)) {
                flag.put("flag", true);
            }
        }

        default boolean stop() {
            return flag.get("flag") != null;
        }
    }

    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }

    protected static class Node<E> {
        protected E element;
        protected Node<E> left;
        protected Node<E> right;
        protected Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        //判断自己是不是父节点的左子树
        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        //判断自己是不是父节点的左子树
        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        // 获取兄弟节点
        public Node<E> sibling() {
            if (isLeftChild()) {
                return parent.right;
            } else if (isRightChild()) {
                return parent.left;
            } else {
                return null;
            }
        }
    }

}
