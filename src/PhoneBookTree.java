public class PhoneBookTree<K extends Comparable<K>, V> implements Measurable<K, V> {
    
    class Entry {
        K key;
        V value;
        Entry left, right;
        
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
    }}

    private Entry root = null;
    private int size = 0;
    
    public int size() { return size; }

    private V put(Entry node, K key, V value) {
        int compare = key.compareTo(node.key);
        if (compare == 0) {
            V old = node.value;
            node.value = value;
            return old;
        }
        if (compare < 0) {
            if (node.left == null) {
                node.left = new Entry(key, value);
                size++;
                return null;
            }
            return put(node.left, key, value);
        }
        if (node.right == null) {
            node.right = new Entry(key, value);
            size++;
            return null;
        }
        return put(node.right, key, value);
    }

    public V put(K key, V value) {
        if (root == null) {
            root = new Entry(key, value);
            size++;
            return null;
        }
        return put(root, key, value);
    }

    private int putWithCollision(Entry node, K key, V value) {
        int compare = key.compareTo(node.key);
        if (compare == 0) {
            node.value = value;
            return 0;
        }
        if (compare < 0) {
            if (node.left == null) {
                node.left = new Entry(key, value);
                size++;
                return 1;
            }
            return 1 + putWithCollision(node.left, key, value);
        }
        if (node.right == null) {
            node.right = new Entry(key, value);
            size++;
            return 1;
        }
        return 1 + putWithCollision(node.right, key, value);
    }

    public int putWithCollision(K key, V value) {
        if (root == null) {
            root = new Entry(key, value);
            size++;
            return 0;
        }
        return putWithCollision(root, key, value);
    }

    public V get(K key) {
        Entry node = root;
        while (node != null) {
            int compare = key.compareTo(node.key);
            if (compare == 0) return node.value;
            node = compare < 0 ? node.left : node.right;
        }
        return null;
    }

    public int getWithProbes(K key) {
        Entry node = root;
        int probeCounter = 1;
        while (node != null) {
            int compare = key.compareTo(node.key);
            if (compare == 0) return probeCounter;
            probeCounter++;
            node = compare < 0 ? node.left : node.right;
        }
        return probeCounter;
    }

    public V remove(K key) {
        Entry node = root;
        Entry parent = null;
        while (node != null) {
            int compare = key.compareTo(node.key);
            if (compare == 0) {
                V old = node.value;
                if (node.left == null && node.right == null) {
                    if (parent == null) root = null;
                    else if (parent.left == node) parent.left = null;
                    else parent.right = null;
                } 
                else if (node.left == null) {
                    if (parent == null) root = node.right;
                    else if (parent.left == node) parent.left = node.right;
                    else parent.right = node.right;
                } 
                else if (node.right == null) {
                    if (parent == null) root = node.left;
                    else if (parent.left == node) parent.left = node.left;
                    else parent.right = node.left;
                } 
                else {
                    Entry successor = node.right;
                    Entry successorParent = node;

                    while (successor.left != null) {
                        successorParent = successor;
                        successor = successor.left;
                    }
                    node.key = successor.key;
                    node.value = successor.value;
                    
                    if (successorParent.left == successor) successorParent.left = successor.right;
                    else successorParent.right = successor.right;
                }
                size--;
                return old;
            }
            parent = node;
            if (compare < 0) node = node.left;
            else node = node.right;
        }
        return null;
    }

    public int removeWithCollision(K key) {
        Entry node = root;
        Entry parent = null;
        int collisionCounter = 0;
        while (node != null) {
            int compare = key.compareTo(node.key);
            if (compare == 0) {
                if (node.left == null && node.right == null) {
                    if (parent == null) root = null;
                    else if (parent.left == node) parent.left = null;
                    else parent.right = null;
                } 
                else if (node.left == null) {
                    if (parent == null) root = node.right;
                    else if (parent.left == node) parent.left = node.right;
                    else parent.right = node.right;
                } 
                else if (node.right == null) {
                    if (parent == null) root = node.left;
                    else if (parent.left == node) parent.left = node.left;
                    else parent.right = node.left;
                } 
                else {
                    Entry successor = node.right;
                    Entry successorParent = node;

                    while (successor.left != null) {
                        successorParent = successor;
                        successor = successor.left;
                    }
                    node.key = successor.key;
                    node.value = successor.value;
                    
                    if (successorParent.left == successor) successorParent.left = successor.right;
                    else successorParent.right = successor.right;
                }
                size--;
                return collisionCounter;
            }
            collisionCounter++;
            parent = node;
            if (compare < 0) node = node.left;
            else node = node.right;
        }
        return collisionCounter;
    }
}
