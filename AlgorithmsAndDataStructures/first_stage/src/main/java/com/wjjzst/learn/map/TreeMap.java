package com.wjjzst.learn.map;


import java.util.Comparator;

/**
 * @Author: Wjj
 * @Date: 2019/5/20 0:38
 * @desc:
 */
public class TreeMap<K, V> implements Map<K, V> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private int size;
    private Node<K, V> root;

    private Comparator comparator;

    public TreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    public TreeMap() {

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return false;
    }


    public void clear() {

    }


    public V put(K key, V value) {
        keyNotNullCheck(key);
        // 根节点为空是
        if (root == null) {
            root = new Node<>(key, value, null);
            size++;
            afterPut(root);//新添加节点之后处理
            return null;
        }
        // 根节点不为空时候
        Node<K, V> parent = root; //找到父节点
        Node<K, V> node = root;
        int cmp = 0;
        while (node != null) {
            cmp = compare(key, node.key);
            parent = node;
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                node.key = key;
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
        }
        Node<K, V> newNode = new Node<>(key, value, parent);
        if (cmp < 0) {
            parent.left = newNode;
        } else if (cmp > 0) {
            parent.right = newNode;
        }
        size++;
        afterPut(newNode); //新添加节点之后处理
        return null;
    }


    public V get(K key) {
        return null;
    }


    public V remove(K key) {
        return null;
    }


    public boolean containsKey(K key) {
        return false;
    }


    public boolean containsValue(V value) {
        return false;
    }


    public void traversal(Visitor<K, V> visitor) {

    }

    //左旋转
    private void rotateLeft(Node<K, V> grandparent) {
        Node<K, V> parent = grandparent.right;
        Node<K, V> child = parent.left;
        grandparent.right = child;
        parent.left = grandparent;
        afterRotate(grandparent, parent, child);
    }

    //右旋转
    private void rotateRight(Node<K, V> grandparent) {
        Node<K, V> parent = grandparent.left;
        Node<K, V> child = parent.right;
        grandparent.left = child;
        parent.right = grandparent;
        afterRotate(grandparent, parent, child);
    }

    // 更新完之后做的事情
    private void afterRotate(Node<K, V> grandparent, Node<K, V> parent, Node<K, V> child) {
        // 新子树根节点代替原子树根节点
        parent.parent = grandparent.parent;
        if (grandparent.isLeftChild()) {
            grandparent.parent.left = parent;
        } else if (grandparent.isRightChild()) {
            grandparent.parent.right = parent;
        } else {// 根节点情况
            root = parent;
        }
        // 更新child为parent
        if (child != null) {
            child.parent = grandparent;
        }
        // 更新grandparent的parent
        grandparent.parent = parent;

    }

    private void afterPut(Node<K, V> node) {
        Node<K, V> parent = node.parent;
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
        Node<K, V> uncle = parent.sibling();
        // 祖父节点 祖父节点最终终会变成红色
        Node<K, V> grandparent = red(parent.parent);
        // 如果叔父节点是红色 则说明原来的节点有三个值 新加的元素后发生上溢现象 grandparent上去
        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            //把祖父节点染成红色并当作新添加的节点
            afterPut(grandparent);
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


    private void afterRemove(Node<K, V> node) {
        // 如果被删除的节点是红色的则不用做任何处理
        // if (isRed(node)) {
        //    return;
        //}
        // 如果用于取代node的replacement节点是红色的,则染成黑色即可
        if (isRed(node)) {
            black(node);
            return;
        }
        Node<K, V> parent = node.parent;
        // 删除的是根节点
        if (parent == null) {
            return;
        }

        // 删除的是黑色叶子节点   // 删除的是叶子节点 replacement必然是空的
        // 判断被删除的node是左还是右
        // 此时node的parent已经完全不指向node了 指向的是replacement
        // TODO 待理解
        // 判断被删除的node是左还是右
        boolean left = parent.left == null || node.isLeftChild();
        // 不能根据下面这种方法获取兄弟节点 parent已经完全不指向node了
        // Node<K,V> sibling = node.sibling();
        Node<K, V> sibling = left ? parent.right : parent.left;
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
                color(sibling, colorOf(parent)); // 兄弟节点继承父节点的颜色
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
                color(sibling, colorOf(parent)); // 兄弟节点继承父节点的颜色
                black(sibling.left); // 新的 sibling的左节点就是之前的sibling
                black(parent); // 父节点变成黑色  因为父节点代替调整到被删除节点的位置(被删除的时黑色的)
                rotateRight(parent); // 父节点右旋转
            }
        }
    }

    private int compare(K k1, K k2) {
        // 如果有传进来有比较
        if (comparator != null) {
            return comparator.compare(k1, k2);
        }
        return ((Comparable<K>) k1).compareTo(k2);
    }

    private void keyNotNullCheck(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
    }

    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }

    private boolean colorOf(Node<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    private Node<K, V> color(Node<K, V> node, boolean color) {
        if (node != null) {
            node.color = color;
        }
        return node;
    }

    private Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }

    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }


    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;
        private Node<K, V> parent;
        public boolean color = RED;

        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            this.value = value;
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
        public Node<K, V> sibling() {
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
