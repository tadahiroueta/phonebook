public class MyHashTable<K, V> {

    class Entry {
        K key;
        V value;
        Entry next;

        public Entry(K key, V phone) {
            this.key = key;
            this.value = phone;
    }}

    protected static final int TABLE_SIZE = 100;

    private Object[] table = new Object[TABLE_SIZE];
    private int size = 0;

    public MyHashTable() {}
    public MyHashTable(int size) { table = new Object[size]; }

    public V put(K key, V value) {
        int hash = key.hashCode() % table.length;
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

    public V get(K key) {
        int hash = key.hashCode() % table.length;
        Entry node = (Entry) table[hash];
       
        // empty slot
        if (node == null) return null;
        
        while (true) {
            if (node.key.equals(key)) return node.value; // found
            if (node.next == null) return null; // end of chain
            node = node.next;
    }}

    public V remove(K key) { 
        int hash = key.hashCode() % table.length;
        Entry node = (Entry) table[hash];

        if (node == null) return null;
        if (node.key.equals(key)) {
            V old = node.value;
            table[hash] = node.next;
            size--;
            return old;
        }

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

    public int size() { return size; }

    @Override
    public String toString() {
        // print table
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            Entry node = (Entry) table[i];
            while (node != null) {
                sb.append(node.key).append(":").append(node.value).append(", ");
                node = node.next;
            }
        }
        return "[" + sb.toString().substring(0, sb.length() - 2) + "]";
    }
}
