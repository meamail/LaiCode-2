import java.util.Arrays;
/**
 * HashTable的实现
 *
 * <p>
 * 1. default constructor 调用含参constructor
 * 2. hash() 用 & 让符号位取0， 其他位保留，生成非0 hashcode
 * 3. 使用external chaining, 每次都往表头加
 */

/**
 * Implementation of HashTable
 * supported operations
 * <p>
 * size()
 * isEmpty()
 * clear()
 * put(K key, V value)
 * get(K key)
 * remove(K key)
 * containsKey(K key)
 * containsValue(V value)
 * auto-resizing
 */

public class HashTable<K, V> {

    class Node<K, V> {
        private K key;
        private V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }


    }

    private Node<K, V>[] array;
    private int size;
    private float loadFactor;
    public static final float LOAD_FACTOR = 0.75f;
    public static final int DEFAULT_CAPACITY = 11;


    // constructor

    public HashTable(int capacity, float loadFactor) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be >=0");

        }

        this.loadFactor = loadFactor;
        this.array = (Node<K, V>[]) new Node[size];// no generic array creation

    }

    public HashTable() {
        this(DEFAULT_CAPACITY, LOAD_FACTOR);

    }


    public int size() {
        return size;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    //remove all the key, value pair in the map
    public void clear() {
        Arrays.fill(null, array);
        size = 0;
    }

    // check if the map contains the key
    public boolean containsKey(K key) {
        int index = getIndex(key);
        Node<K, V> node = array[index];
        while (node != null) {
            if (equalsKey(node.key, key)) return true;
        }
        return false;

    }

    // returns whether the map contains the value
    public boolean containsValue(V value) {
        for (Node<K, V> n : array) {
            while (n != null) {
                if (equalsValue(n.value, value)) return true;
                n = n.next;
            }


        }
        return false;


    }

    // associate the value with the key in the map
    // returns the previous value; if the map does not contains the key, return null
    public V put(K key, V value) {

        int index = getIndex(key);
        Node<K, V> curr = array[index];
        while (curr != null) {
            if (equalsKey(curr.key, key)) {
                // update the value
                V prevValue = curr.value;
                curr.value = value;
                return prevValue;
            }
            curr = curr.next;

        }
        // add new node at the head of the array[index]
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = array[index];
        array[index] = newNode;
        size++;
        if (needResizing()) resize();
        return null;
    }

    public V get(K key) {

        int index = getIndex(key);
        Node<K, V> curr = array[index];
        while (curr != null) {
            if (equalsKey(curr.key, key)) return curr.value;
            curr = curr.next;
        }
        return null;

    }

    // remove the key-value pair in the map;
    // returns the previous value of the key; if the map does not contain the key, return null
    public V remove(K key) {
        int index = getIndex(key);
        Node<K, V> curr = array[index];
        Node<K, V> prev = null;
        while (curr != null) {
            if (equalsKey(curr.key, key)) {
                V prevValue = curr.value;
                if (prev != null) prev.next = curr.next;
                else array[index] = curr.next;
                size--;
                return prevValue;

            }
            prev = curr;
            curr = curr.next;
        }
        return null;
    }

    //return a non-negative hashCode for the key
    private int hash(K key) {
        if (key == null) return 0;
        /**
         *return key.hashCode() >= 0? key.hashCode : - key.hashCode();
         *也可以返回非负数，但是注意 当key = Integer.MIN_VALUE 时，取绝对值会overflow！
         */


        return key.hashCode() & 0x7fffffff;

    }

    // return the index of the key in the array
    private int getIndex(K key) {
        return hash(key) % array.length;
    }

    // return whether two keys are equal
    private boolean equalsKey(K k1, K k2) {
        if (k1 == k2) return true;    // deal with NPE
        return k1 != null && k1.equals(k2);
    }

    // return whether two values are equal
    private boolean equalsValue(V v1, V v2) {
        if (v1 == v2) return true;
        return v1 != null && v1.equals(v2);
    }

    private boolean needResizing() {
        return size * 1.0f / array.length >= loadFactor;
    }

    //double array size and rehashing the key-value pairs
    private void resize() {

        Node<K, V>[] newArray = (Node<K, V>[]) new Node[array.length * 2];
        for (Node<K, V> n : array) {
            int index = hash(n.key) % newArray.length;
            n.next = array[index];// insert the node at the head
            array[index] = n;
        }

        array = newArray;

    }


}
