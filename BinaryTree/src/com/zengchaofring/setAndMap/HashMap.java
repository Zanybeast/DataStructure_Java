package com.zengchaofring.setAndMap;

import com.zengchaofring.tools.printer.BinaryTreeInfo;
import com.zengchaofring.tools.printer.BinaryTrees;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * @ClassName HashMap
 * @Description TODO
 * @Author carl
 * @Date 2020/9/25 02:11
 * @Version 1.0
 **/
public class HashMap<K, V> implements Map<K, V> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private int size;
    private static final int DEFAULT_CAPACITY = 1 << 4;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private Node<K, V>[] table;

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
        if (size == 0) return;
        size = 0;
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

        Node<K, V> parent = root;
        Node<K, V> node = root;
        int cmp = 0;
        K k1 = key;
        int h1 = hash(k1);
        Node<K, V> result = null;
        boolean searched = false;   //是否已搜索过这个key了
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
                    && k1 instanceof Comparable
                    && k1.getClass() == k2.getClass()
                    && (cmp = ((Comparable)k1).compareTo(k2)) != 0) {
                //如果类型相等而且对象具备可比较性，不做任何事情，直接跳到后面
                //该处判断条件已经计算出两key的比较结果了
            } else if (searched) {
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            } else {    //扫描开始，在此之前searched不会被置为true
                if (node.left != null && (result = node(node.left, k1)) != null
                        || (node.right != null && (result = node(node.right, k1)) != null)) {
                    //如果以上条件满足，说明该key已经存在
                    node = result;
                    cmp = 0;
                } else {
                    //不存在这个key，所有扫描完成，将比较内存地址大小
                    searched = true;
                    cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
                }
            }

            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                V oldValue = node.value;
                node.key = key;
                node.value = value;
                node.hash = h1;
                return oldValue;
            }

        } while (node != null);

        //判断插入到父节点哪边
        Node<K, V> newNode = createNode(key, value, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }

        size++;

        fixAfterPut(newNode);
        return null;
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

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if (size == 0) return false;
        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;

            queue.offer(table[i]);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (Objects.equals(value, node.value)) return true;

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
        if (size == 0 || visitor == null) return;

        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;

            queue.offer(table[i]);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (visitor.visit(node.key, node.value)) return;

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
    }

    public void print() {
        if (size == 0) return;

        for (int i = 0; i < table.length; i++) {
            final Node<K, V> root = table[i];
            System.out.println("【index = " + i + "】");
            BinaryTrees.println(new BinaryTreeInfo() {
                @Override
                public Object root() {
                    return root;
                }

                @Override
                public Object left(Object node) {
                    return ((Node<K, V>)node).left;
                }

                @Override
                public Object right(Object node) {
                    return ((Node<K, V>)node).right;
                }

                @Override
                public Object string(Object node) {
                    return node;
                }
            });
            System.out.println("-------------------------------");
        }
    }

    /******************************
     * 辅助实现
     ******************************/
    /*
     * @Author carl
     * @Description 删除节点的实现
     * @Date 19:48 2020/9/25
     * @Param [node]
     * @Return V
     **/
    protected V remove(Node<K, V> node) {
        if (node == null) return null;

        Node<K, V> willNode = node;
        size--;

        V oldValue = node.value;

        if (node.hasTwoChildren()) {
            Node<K, V> s = successor(node);
            node.key = s.key;
            node.value = s.value;
            node.hash = s.hash;

            node = s;
        }

        Node<K, V> replacement = node.left != null ? node.left : node.right;
        int index = index(node);
        if (replacement != null) {
            replacement.parent = node.parent;
            if (node.parent == null) {
                table[index] = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }

            fixAfterRemove(replacement);
        } else if (node.parent == null) {
            table[index] = null;
        } else {
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }

            fixAfterRemove(node);
        }

        afterRemove(willNode, node);

        return oldValue;
    }
    /*
     * @Author carl
     * @Description 放置元素后调整红黑树
     * @Date 19:48 2020/9/25
     * @Param [node]
     * @Return void
     **/
    private void fixAfterPut(Node<K, V> node) {
        Node<K, V> parent = node.parent;
        //添加的是根结点 或 上溢到达了根结点
        if (parent == null) {
            black(node);
            return;
        }

        if(isBlack(parent)) return;

        Node<K, V> uncle = parent.sibling();
        Node<K, V> grand = red(parent.parent);
        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            fixAfterPut(grand);
            return;
        }

        if (parent.isLeftChild()) {
            if (node.isLeftChild()) {
                black(parent);
            } else {
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else {
            if (node.isLeftChild()) {
                black(node);
                rotateRight(parent);
            } else
                black(parent);
            rotateLeft(grand);
        }


    }

    private void fixAfterRemove(Node<K, V> node) {
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<K, V> parent = node.parent;
        if (parent == null) return;

        //来到这里，删除的节点是黑色节点，而且删除后造成下溢
        boolean left = parent.left == null || node.isLeftChild();
        Node<K, V> sibling = left ? parent.right : parent.left;
        if (left) { //被删除节点在左边，兄弟节点在右边
            if (isRed(sibling)) {   //兄弟节点是红色
                black(sibling);
                red(parent);
                rotateLeft(parent);

                sibling = parent.right;
            }

            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                boolean parentBlack  =isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {  //如果父节点是黑色仍然造成下溢，循环调用该方法直至修复完所有引起下溢的节点
                    fixAfterRemove(parent);
                }
            } else {    //兄弟节点至少有 1 个红色子节点，向兄弟节点借元素
                //兄弟节点的右边是黑色，兄弟要先旋转
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }

                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }
        } else {
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.left;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    fixAfterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }

                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }
    }

    protected void afterRemove(Node<K, V> willNode, Node<K, V> removedNode) { }

    /*
     * @Author carl
     * @Description 根据key查找节点
     * @Date 19:46 2020/9/25
     * @Param [key]
     * @Return com.zengchaofring.setAndMap.HashMap.Node<K,V>
     **/
    private Node<K, V> node(K key) {
        Node<K, V> root = table[index(key)];
        return root == null ? null : node(root, key);
    }

    /*
     * @Author carl
     * @Description 用以比较红黑树节点是否与要查找的key相等
     * 前提条件是两个key不具备可比较性
     * @Date 19:01 2020/9/25
     * @Param [node, k1]
     * @Return com.zengchaofring.setAndMap.HashMap.Node<K,V>
     **/
    private Node<K, V> node(Node<K, V> node, K k1)  {
        int h1 = hash(k1);

        Node<K, V> result = null;
        int cmp = 0;
        while (node != null) {
            K k2 = node.key;
            int h2 = node.hash;

            if (h1 > h2) {
                node = node.right;
            } else if (h1 < h2) {
                node = node.left;
            } else if (Objects.equals(k1, k2)) {
                return node;
            } else if (k1 != null && k2 != null
                    && k1 instanceof Comparable
                    && k1.getClass() == k2.getClass()
                    && (cmp = ((Comparable)k1).compareTo(k2)) != 0) {
                node = cmp > 0 ? node.right : node.left;
            } else if (node.right != null && (result = node(node.right, k1)) != null) {     //递归查找，从右边开始找起
                return result;
            } else {
                node = node.left;
            }
        }

        return null;
    }

    private void resize() {
        //装填因子 <= 0.75
        if (size / table.length <= DEFAULT_LOAD_FACTOR) return;

        Node<K, V>[] oldTable = table;
        table = new Node[oldTable.length << 1];

        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] == null) continue;

            queue.offer(oldTable[i]);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);

                moveNode(node);
            }
        }
    }

    private void moveNode(Node<K, V> newNode) {
        newNode.parent = null;
        newNode.left = null;
        newNode.right = null;
        newNode.color = RED;

        int index = index(newNode);
        Node<K, V> root = table[index];
        if (root == null) {
            root = newNode;
            table[index] = root;
            fixAfterPut(root);
            return;
        }

        //添加新的节点到红黑树上
        Node<K, V> parent = root;
        Node<K, V> node = root;
        int cmp = 0;
        K k1 = newNode.key;
        int h1 = newNode.hash;
        do {
            parent = node;
            K k2 = node.key;
            int h2 = node.hash;
            if (h1 > h2) {
                cmp = 1;
            } else if (h1 < h2) {
                cmp = -1;
            } else if (k1 != null && k2 != null
                    && k1 instanceof Comparable
                    && k1.getClass() == k2.getClass()
                    && (cmp = ((Comparable)k1).compareTo(k2)) != 0) {
            } else {
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            }

            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            }
        } while (node != null);

        // 看看插入到父节点的哪个位置
        newNode.parent = parent;
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }

        // 新添加节点之后的处理
        fixAfterPut(newNode);
    }

    private int index(K key) {
        //由key计算出来的放在哈希表中桶的索引
        return hash(key) & (table.length - 1);
    }

    private int hash(K key) {
        if (key == null) return 0;
        int hash = key.hashCode();
        return hash ^ (hash >>> 16);
    }

    private int index(Node<K, V> node) {
        //根据node自身的key的哈希值计算该node属于table中的哪个桶（数组索引）
        return node.hash & (table.length - 1);
    }

    /******************************
     * 红黑树相关方法
     ******************************/
    private Node<K, V> successor(Node<K, V> node) {
        if (node == null) return null;

        Node<K, V> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        return node.parent;
    }

    private void rotateLeft(Node<K, V> grand) {
        Node<K, V> parent = grand.right;
        Node<K, V> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    private void rotateRight(Node<K, V> grand) {
        Node<K, V> parent = grand.left;
        Node<K, V> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    private void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {
        // 让parent称为子树的根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else { // grand是root节点
            table[index(grand)] = parent;
        }

        // 更新child的parent
        if (child != null) {
            child.parent = grand;
        }

        // 更新grand的parent
        grand.parent = parent;
    }

    private Node<K, V> color(Node<K, V> node, boolean color) {
        if (node == null) return node;
        node.color = color;
        return node;
    }

    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }

    private Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }

    private boolean colorOf(Node<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }

    /*
     * @Author carl
     * @Description 特有类，存储在哈希表中桶的红黑树节点
     * @Date 03:18 2020/9/25
     **/
    protected Node<K, V> createNode(K key, V value, Node<K, V> parent) {
        return new Node<>(key, value, parent);
    }

    private static class Node<K, V> {
        int hash;   //该哈希值是由key计算出来的哈希值
        K key;
        V value;
        boolean color = RED;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;

        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            int hash = key == null ? 0 : key.hashCode();
            this.hash = hash ^ (hash >>> 16);
            this.value = value;
            this.parent = parent;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        public Node<K, V> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }

            if (isRightChild()) {
                return parent.left;
            }

            return null;
        }

        @Override
        public String toString() {
            return "Node_" + key + "_" + value;
        }
    }
}
