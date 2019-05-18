package com.wjjzst.learn.tree;

/**
 * @Author: Wjj
 * @Date: 2019/5/10 0:43
 * @desc:
 */
public class RBTree<E> extends BBSTree<E> {
    private static boolean RED = false;
    private static boolean BLACK = true;


    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;
        // 添加的是根节点  或者上溢到了根节点
        if (parent == null) {
            black(node);
            return;
        }
        // 如果父节点是黑色的则不用管 所有红黑树的性质都满足
        if (isBlack(parent)) {
            return;
        }
        // 叔父节点
        Node<E> uncle = parent.sibling();
        // 祖父节点 祖父节点最终终会变成红色
        Node<E> grandparent = red(parent.parent);
        // 如果叔父节点是红色 则说明原来的节点有三个值 新加的元素后发生上溢现象 grandparent上去
        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            //把祖父节点染成红色并当作新添加的节点
            afterAdd(grandparent);
            return;
        }
        //叔父节点不是红色  需要旋转
        if (parent.isLeftChild()) {
            if (node.isLeftChild()) { // LL
                black(parent);
            } else { // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grandparent);
        } else { //R
            if (node.isLeftChild()) { //RL
                black(node);
                rotateRight(parent);
            } else { //RR
                black(parent);
            }
            rotateLeft(grandparent);
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        // 如果被删除的节点是红色的则不用做任何处理
        if (isRed(node)) {
            return;
        }
        // 如果用于取代node的replacement节点是红色的,则染成黑色即可
        if (isRed(node)) {
            black(node);
            return;
        }
        Node<E> parent = node.parent;
        // 删除的是根节点
        if (parent == null) {
            return;
        }

        // 删除的是黑色叶子节点   // 删除的是叶子节点 replacement必然是空的
        // 判断被删除的node是左还是右
        // 此时node的parent已经完全不指向node了 指向的是replacement
        // TODO 待理解
        // 判断被删除的node是左还是右
        boolean left = parent.left == null;
        // 不能根据下面这种方法获取兄弟节点 parent已经完全不指向node了
        // Node<E> sibling = node.sibling();
        Node<E> sibling = left ? parent.right : parent.left;
        if (left) { //被删除的节点在左边,兄弟节点在右边
            if (isRed(sibling)) { //兄弟节点是红色 兄弟节点染成黑色 父节点染成红色 对父节点左旋 使兄弟节点左节点成为自己的兄弟节点
                black(sibling);
                red(parent);
                rotateLeft(parent);
                sibling = parent.right;
            }

            // 兄弟节点必然是黑色的 空的也算作是黑节点
            if (isBlack(sibling.right) && isBlack(sibling.left)) {  //兄弟左右都是黑的 没有节点可以借,父节点要向下节点与兄弟节点合并
                boolean parentBlack = isBlack(parent); // 父节点与兄弟节点都为黑色的时候  先记录下来 再染色 再递归的
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟至少有一个红子节点,想兄弟节点借元素
                // 兄弟的节点的右边是黑色,兄弟先进行旋转
                if (isBlack(sibling.right)) {    // 即兄弟节点右节点为null 有左节点的时候
                    rotateRight(sibling);
                    // sibling = sibling.left;  // 原本兄弟的左节点要变为兄弟节点  变换之后sibling的left已经是空的了
                    sibling = parent.right; // 原本兄弟的左节点要变为兄弟节点
                }
                color(sibling,colorOf(parent)); // 兄弟节点继承父节点的颜色
                black(sibling.right); // 新的 sibling的右节点就是之前的sibling
                black(parent); // 父节点变成黑色  因为父节点代替调整到被删除节点的位置(被删除的时黑色的)
                rotateLeft(parent); // 父节点左旋转
            }
        } else { // 被删除逇节点在右边,兄弟节点在左边
            if (isRed(sibling)) { //兄弟节点是红色 兄弟节点染成黑色 父节点染成红色 对父节点右旋 使兄弟节点右节点成为自己的兄弟节点
                black(sibling);
                red(parent);
                rotateRight(parent);
                sibling = parent.left;
            }

            // 兄弟节点必然是黑色的 空的也算作是黑节点
            if (isBlack(sibling.left) && isBlack(sibling.right)) {  //兄弟左右都是黑的 即两边都没有红节点 没有节点可以借,父节点要向下节点与兄弟节点合并
                boolean parentBlack = isBlack(parent); // 父节点与兄弟节点都为黑色的时候  先记录下来 再染色 再递归的
                black(parent);
                red(sibling);
                if (parentBlack) {
                    // TODO 待理解
                    afterRemove(parent);
                }
            } else { // 兄弟至少有一个红子节点,想兄弟节点借元素
                // 兄弟的节点的左边是黑色,兄弟先进行旋转
                if (isBlack(sibling.left)) {    // 即兄弟节点左节点为null 有右节点的时候
                    rotateLeft(sibling);
                    // sibling = sibling.right;  // 原本兄弟的右节点要变为兄弟节点  变换之后sibling的right已经是空的了
                    sibling = parent.left; // 原本兄弟的右节点要变为兄弟节点
                }
                color(sibling,colorOf(parent)); // 兄弟节点继承父节点的颜色
                black(sibling.left); // 新的 sibling的左节点就是之前的sibling
                black(parent); // 父节点变成黑色  因为父节点代替调整到被删除节点的位置(被删除的时黑色的)
                rotateRight(parent); // 父节点右旋转
            }
        }
    }

    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>) node).color;
    }

    private RBNode<E> color(Node<E> node, boolean color) {
        if (node != null) {
            ((RBNode<E>) node).color = color;
        }
        return (RBNode<E>) node;
    }

    private RBNode<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    private RBNode<E> red(Node<E> node) {
        return color(node, RED);
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }

    private static class RBNode<E> extends Node<E> {

        public boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }


    }

    @Override
    public Object string(Object node) {
        RBNode<E> rbNode = (RBNode<E>) node;
        StringBuilder sb = new StringBuilder();
        if (rbNode.color == RED) {
            sb.append("R_");
        }
        sb.append(rbNode.element).append("_p(");
        if (rbNode.parent == null) {
            sb.append("null");
        } else {
            sb.append((rbNode.parent.element));
        }
        sb.append(")");
        return sb.toString();
    }
}
