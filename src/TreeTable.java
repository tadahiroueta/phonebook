public class TreeTable<K extends Comparable<K>, V> implements Measurable<K, V> {
    private int bucketNumber = 100, size = 0;
    private PhoneBookTree<K, V>[] table = new PhoneBookTree[bucketNumber];

    public TreeTable() {}
    
    public TreeTable(int tableSize) {
        this.bucketNumber = tableSize;
        table = new PhoneBookTree[tableSize];
    }

    @Override
    public int size() { return size; }

    @Override
    public V put(K key, V value) {
        int hash = Math.abs(key.hashCode() % table.length);
        if (table[hash] == null) {
            table[hash] = new PhoneBookTree<K, V>();
            size++;
        }
        return table[hash].put(key, value);
    }

    @Override
    public int putWithCollision(K key, V value) {
        int hash = Math.abs(key.hashCode() % table.length);
        if (table[hash] == null) {
            table[hash] = new PhoneBookTree<K, V>();
            size++;
        }
        return table[hash].putWithCollision(key, value);
    }

    @Override
    public V get(K key) {
        int hash = Math.abs(key.hashCode() % table.length);
        if (table[hash] == null) return null;
        return table[hash].get(key);
    }

    @Override
    public int getWithProbes(K key) {
        int hash = Math.abs(key.hashCode() % table.length);
        if (table[hash] == null) return 0;
        return table[hash].getWithProbes(key);
    }

    @Override
    public V remove(K key) {
        int hash = Math.abs(key.hashCode() % table.length);
        if (table[hash] == null) return null;
        V value = table[hash].remove(key);

        if (table[hash].size() == 0) {
            table[hash] = null;
            size--;
        }
        return value;
    }

    @Override
    public int removeWithCollision(K key) {
        int hash = Math.abs(key.hashCode() % table.length);
        if (table[hash] == null) return 0;
        int collision = table[hash].removeWithCollision(key);

        if (table[hash].size() == 0) {
            table[hash] = null;
            size--;
        }
        return collision;
    }
}
