package com.wjjzst.ads.first_stage.learn.map;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * @Author: Wjj
 * @Date: 2019/5/23 11:12
 * @desc:
 */
public class HashMap<K, V> implements Map<K, V> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private int size;
    private Node<K, V>[] table;
    private static final int DEFAULT_CAPACITY = 1 << 4;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    public HashMap() {
        table = new Node[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
    }

    @Override
    public V put(K key, V value) {
        resize();
        int index = index(key);
        Node<K, V> root = table[index];
        if (root == null) {
            root = createNode(key, value, null);
            table[index] = root;
            size++;
            fixAfterPut(root);
            return null;
        }
        // 添加新的节点到红黑树上
        // 根节点不为空时候
        Node<K, V> parent; //找到父节点
        Node<K, V> node = root;
        int cmp;
        K k1 = key;
        int h1 = hash(k1);
        Node<K, V> result;
        boolean searched = false;//是否已经搜索过
        do {
            parent = node;
            K k2 = node.key;
            int h2 = node.hash;
            if (h1 > h2) {
                cmp = 1;
            } else if (h1 < h2) {
                cmp = -1;
            } else if (Objects.equals(k1, k2)) {
                cmp = 0;
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {// 此处相等不一定key相等 要舍掉等于0的时候 0的时候也得遍历左右子树
            } else if (searched) { //searched ==true; 扫描过一次了都不存在 直接跟节点比较内存地址
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            } else {//没有扫描过
                if (node.left != null && (result = node(node.left, k1)) != null  //左边能找到
                        || node.right != null && (result = node(node.right, k1)) != null) { //或者右边能找到
                    //已经存在了这个key
                    node = result; //实际要覆盖的是result;不是最开始的node
                    cmp = 0;
                } else { // 不存在这个key 比较hashCode值
                    searched = true;
                    cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
                }
            }
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                V oldValue = node.value;
                node.key = key;
                node.value = value;
                node.hash = h1; // 可以不写
                return oldValue;
            }
        } while (node != null);
        //看看插入到父节点的哪个位置
        Node<K, V> newNode = createNode(key, value, parent);
        if (cmp < 0) {
            parent.left = newNode;
        } else if (cmp > 0) {
            parent.right = newNode;
        }
        size++;
        fixAfterPut(newNode); //新添加节点之后处理
        return null;
    }

    protected Node<K, V> createNode(K key, V value, Node<K, V> parent) {
        return new Node<>(key, value, parent);
    }

    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return node != null ? node.value : null;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    protected V remove(Node<K, V> node) {
        Node<K, V> willNode = node;
        if (node == null) {
            return null;
        }
        size--;
        V oldValue = node.value;
        // 度为2的节点
        if (node.hasTwoChildren()) {
            // 找到后继节点
            Node<K, V> successor = successor(node);
            // 后继节点的值覆盖原本度为2的节点
            node.key = successor.key;
            node.value = successor.value;
            node.hash = successor.hash;  // hash也要覆盖
            // 删除后继节点
            node = successor;
        }
        // 删除度为0,1的节点的子节点
        Node<K, V> replacement = node.left != null ? node.left : node.right;
        int index = index(node);
        // 度为1的节点替代节点必定存在
        if (replacement != null) {
            // 取代节点的父节点指向原本节点的父节点
            replacement.parent = node.parent; // 根节点parent==null
            // 取决于当前节点是父节点的左右  取代节点才能插入上去
            // 度为1的节点是跟节点
            if (node.parent == null) {
                table[index] = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
            fixAfterRemove(replacement);
        } else if (node.parent == null) {// 度为0的节点  根节点
            table[index] = null;
        } else { // 度为0的节点 其他节点
            if (node == node.parent.left) {
                // node.parent.left = replacement; //此时replacement = null
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            fixAfterRemove(node);
        }
        // 虽然没有节点指向node 但是node仍然指向他的父节点 node.parent = replacement.parent
        // node删掉了 node.parent高度就会改变就需要平衡
        // fixAfterRemove(node); //删了replacement 改变的是node 需要调整的也是node
        // 交给子类去实现
        afterRemove(willNode,node);
        return oldValue;
    }

    protected void afterRemove(Node<K, V> willNode, Node<K, V> removeNode) {

    }

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if (size == 0) {
            return false;
        }
        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                continue;
            }
            queue.offer(table[i]);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (Objects.equals(value, node.value)) {
                    return true;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (size == 0 || visitor == null) {
            return;
        }
        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                continue;
            }
            queue.offer(table[i]);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (visitor.visit(node.key, node.value)) {
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
    }

    private Node<K, V> node(K key) {
        Node<K, V> root = table[index(key)];
        return root == null ? null : node(root, key);
    }

    private Node<K, V> node(Node<K, V> node, K k1) {
        int h1 = hash(k1); //node可能为空
        Node<K, V> result;
        int cmp;
        while (node != null) {
            K k2 = node.key;
            int h2 = node.hash; // 经过扰动计算过的
            //先比较hash值
            if (h1 > h2) {
                node = node.right;
            } else if (h1 < h2) {
                node = node.left;
            } else if (Objects.equals(k1, k2)) {
                return node;
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
                node = cmp > 0 ? node.right : node.left;
                // hash值相等,不具备可比较性,也不equals
            } else if (node.right != null && (result = node(node.right, k1)) != null) { //从右子树中扫描
                return result;

            } else { //右边扫描不到,只能往左边查找
                node = node.left;// 减少递归调用

            }
           /* else if (node.left != null && (result = node(node.left, k1)) != null) { //从左子树中扫描
                return result;

            } else {
                return null;
            }*/
        }
        return null;
    }

    /*private Node<K, V> node1(K key) {
        Node<K, V> node = table[index(key)];
        int h1 = key == null ? 0 : key.hashCode(); //node可能为空
        while (node != null) {
            int cmp = compare(key, node.key, h1, node.hash);
            if (cmp == 0) {
                return node;
            }
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            }
        }
        return null;
    }*/

    private void resize() {
        if (size / table.length <= DEFAULT_LOAD_FACTOR) {
            return;
        }
        System.out.println("表扩容:" + table.length + "->" + (table.length << 1));
        Node<K, V>[] oldTable = table;
        table = new Node[oldTable.length << 1];// 扩容为2倍
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] == null) {
                continue;
            }
            Queue<Node<K, V>> queue = new LinkedList<>();
            queue.offer(oldTable[i]);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                // 挪动
                moveNode(node);
            }

        }

    }

    private void moveNode(Node<K, V> newNode) {
        // 重置
        newNode.parent = null;
        newNode.left = null;
        newNode.right = null;
        newNode.color = RED; //新节点认为是红色
        // 取出index位置的红黑树节点
        int index = index(newNode);
        Node<K, V> root = table[index];
        if (root == null) {
            root = newNode;
            table[index] = root;
            // size++; 挪动size不用++
            fixAfterPut(root);
            return;
        }
        // 添加新的节点到红黑树上
        // 根节点不为空时候
        Node<K, V> parent; //找到父节点
        Node<K, V> node = root;
        int cmp;
        K k1 = newNode.key;
        int h1 = newNode.hash;
        // Node<K, V> result;
        // boolean searched = false;//是否已经搜索过
        do {
            parent = node;
            K k2 = node.key;
            int h2 = node.hash;
            if (h1 > h2) {
                cmp = 1;
            } else if (h1 < h2) {
                cmp = -1;
            }
            /*else if (Objects.equals(k1, k2)) {
                cmp = 0;
            }*/ // 旧的红黑树上绝对没有equals相等的node key
            else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {// 此处相等不一定key相等 要舍掉等于0的时候 0的时候也得遍历左右子树
            } else {
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            }
            /*else if (searched) { //searched ==true; 扫描过一次了都不存在 直接跟节点比较内存地址     // 旧的红黑树上绝对没有equals相等的node key
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            } else {//没有扫描过
                if (node.left != null && (result = node(node.left, k1)) != null  //左边能找到
                        || node.right != null && (result = node(node.right, k1)) != null) { //或者右边能找到
                    //已经存在了这个key
                    node = result; //实际要覆盖的是result;不是最开始的node
                    cmp = 0;
                } else { // 不存在这个key 比较hashCode值
                    searched = true;
                    cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
                }
            }*/
            if (cmp < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            //cmp绝对不等于0
           /*else {
                V oldValue = node.value;
                node.key = key;
                node.value = value;
                node.hash = h1; // 可以不写
                return oldValue;
            }*/
        } while (node != null);
        //看看插入到父节点的哪个位置
        // Node<K, V> newNode = new Node<>(key, value, parent);
        newNode.parent = parent;
        if (cmp < 0) {
            parent.left = newNode;
        } else if (cmp > 0) {
            parent.right = newNode;
        }
        fixAfterPut(newNode); //新添加节点之后处理

    }

    private int index(K key) {
        return hash(key) & (table.length - 1);
    }

    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        int hash = key.hashCode();
        return hash ^ (hash >>> 16);
    }

    private int index(Node<K, V> node) {
        return node.hash & (table.length - 1);
    }

    /*private int compare(K k1, K k2, int h1, int h2) {
        // 比较hash值
        int result = h1 - h2;
        if (result != 0) {
            return result;
        }
        //  比较eqauls
        if (Objects.equals(k1, k2)) {   // 两个都为空这里就返回0了
            return 0;
        }
        // hash值相等但是不是equals;
        // 比较类名
        if (k1 != null && k2 != null) {
            String k1Cls = k1.getClass().getName();
            String k2Cls = k2.getClass().getName();
            result = k1Cls.compareTo(k2Cls);
            if (result != 0) {
                return result;
            }
            // 同一种类型 并且具备可比较性
            if (k1 instanceof Comparable) {
                return ((Comparable) k1).compareTo(k2);
            }
        }
        // 同一种类型但是不具备可比较性
        // k1 不为null k2 为null
        // k1为null k2 为null
        return System.identityHashCode(k1) - System.identityHashCode(k2);
    }*/

    // 找后驱节点
    private Node<K, V> successor(Node<K, V> node) {
        if (node == null) {
            return null;
        }
        // 后驱节点在左子树当中(left.right.right.right.right...)
        Node<K, V> p = node.right;
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
            table[index(grandparent)] = parent; // grandparent,parent,child在同一个红黑树下 他们的key的hashCode都是一样的
        }
        // 更新child为parent
        if (child != null) {
            child.parent = grandparent;
        }
        // 更新grandparent的parent
        grandparent.parent = parent;

    }

    private void fixAfterPut(Node<K, V> node) {
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
            fixAfterPut(grandparent);
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


    private void fixAfterRemove(Node<K, V> node) {
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
                    fixAfterRemove(parent);
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
                    fixAfterRemove(parent);
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


    protected static class Node<K, V> {
        private int hash;
        protected K key;
        protected V value;
        private Node<K, V> left;
        private Node<K, V> right;
        private Node<K, V> parent;
        private boolean color = RED;

        protected Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            int hash = key == null ? 0 : key.hashCode();
            this.hash = hash ^ (hash >>> 16);// 扰动计算
            this.value = value;
            this.parent = parent;
        }

        private boolean isLeaf() {
            return left == null && right == null;
        }

        private boolean hasTwoChildren() {
            return left != null && right != null;
        }

        //判断自己是不是父节点的左子树
        private boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        //判断自己是不是父节点的左子树
        private boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        // 获取兄弟节点
        private Node<K, V> sibling() {
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
