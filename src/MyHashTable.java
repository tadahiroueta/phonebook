public class MyHashTable<K, V> implements Measurable<K, V> {

    class Entry {
        K key;
        V value;
        Entry next;

        public Entry(K key, V phone) {
            this.key = key;
            this.value = phone;
    }}

    private int tableSize = 100;

    private Object[] table = new Object[tableSize];
    private int size = 0;

    public MyHashTable() {}

    public MyHashTable(int tableSize) {
        this.tableSize = tableSize; 
        table = new Object[tableSize];
    }

    public int size() { return size; }

    @Override
    public V put(K key, V value) {
        int hash = Math.abs(key.hashCode() % table.length);
        @SuppressWarnings("unchecked")
        Entry node = (Entry) table[hash];

        // empty slot
        if (node == null) {
            table[hash] = new Entry(key, value);
            size++;
            return null;
        }
        
        while (true) {
            // update
            if (node.key.equals(key)) {
                V old = node.value;
                node.value = value;
                return old;
            }
            // end of chain
            if (node.next == null) {
                node.next = new Entry(key, value);
                size++;
                return null;
            }
            node = node.next;
    }}

    /** Returns number of collisions */
    public int putWithCollision(K key, V value) {
        int hash = Math.abs(key.hashCode() % table.length);
        @SuppressWarnings("unchecked")
        Entry node = (Entry) table[hash];
        int collisionCounter = 0;

        // empty slot
        if (node == null) {
            table[hash] = new Entry(key, value);
            size++;
            return collisionCounter;
        }

        while (true) {
            // update
            if (node.key.equals(key)) {
                V old = node.value;
                node.value = value;
                return collisionCounter;
            }

            collisionCounter++;

            // end of chain
            if (node.next == null) {
                node.next = new Entry(key, value);
                size++;
                return collisionCounter;
            }
            node = node.next;
    }}

    public V get(K key) {
        int hash = Math.abs(key.hashCode() % table.length);
        @SuppressWarnings("unchecked")
        Entry node = (Entry) table[hash];
       
        // empty slot
        if (node == null) return null;
        
        while (true) {
            if (node.key.equals(key)) return node.value; // found
            if (node.next == null) return null; // end of chain
            node = node.next;
    }}

    /** Returns number of probes - nodes checked */
    public int getWithProbes(K key) {
        int hash = Math.abs(key.hashCode() % table.length);
        @SuppressWarnings("unchecked")
        Entry node = (Entry) table[hash];
        int probeCounter = 1;
       
        // empty slot
        if (node == null) return probeCounter;
        
        while (true) {
            if (node.key.equals(key)) return probeCounter; // found
            probeCounter++;

            if (node.next == null) return probeCounter; // end of chain
            node = node.next;
    }}

    public V remove(K key) { 
        int hash = Math.abs(key.hashCode() % table.length);
        @SuppressWarnings("unchecked")
        Entry node = (Entry) table[hash];

        if (node == null) return null;
        if (node.key.equals(key)) {
            V old = node.value;
            table[hash] = node.next;
            size--;
            return old;
        }
        if (node.next == null) return null;

        while (true) {
            if (node.next.key.equals(key)) {
                V old = node.next.value;
                node.next = node.next.next;
                size--;
                return old;
            }
            if (node.next.next == null) return null;
            node = node.next;
    }}

    /** Returns number of collisions */
    public int removeWithCollision(K key) { 
        int hash = Math.abs(key.hashCode() % table.length);
        @SuppressWarnings("unchecked")
        Entry node = (Entry) table[hash];
        int collisionCounter = 0;

        if (node == null) return collisionCounter;
        if (node.key.equals(key)) {
            table[hash] = node.next;
            size--;
            return collisionCounter;
        }

        collisionCounter++;
        if (node.next == null) return collisionCounter;

        while (true) {
            if (node.next.key.equals(key)) {
                node.next = node.next.next;
                size--;
                return collisionCounter;
            }
            collisionCounter++;
            
            if (node.next.next == null) return collisionCounter;
            node = node.next;
    }}

    @Override
    public String toString() {
        // print table
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            @SuppressWarnings("unchecked")
            Entry node = (Entry) table[i];
            while (node != null) {
                sb.append(node.key).append(":").append(node.value).append(", ");
                node = node.next;
            }
        }
        return "[" + sb.toString().substring(0, sb.length() - 2) + "]";
    }
}
