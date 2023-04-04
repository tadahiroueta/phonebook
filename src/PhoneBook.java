public class PhoneBook implements IMap {

    class Entry {
        Person person;
        PhoneNumber phoneNumber;
        Entry next;

        public Entry(Person person, PhoneNumber phone) {
            this.person = person;
            this.phoneNumber = phone;
    }}

    private int tableSize = 100;

    private Entry[] table = new Entry[tableSize];
    private int size = 0;

    public PhoneBook() {}
    
    public PhoneBook(int tableSize) { 
        this.tableSize = tableSize;
        table = new Entry[tableSize];
    }

    public PhoneNumber put(Person key, PhoneNumber value) {
        int hash = Math.abs(key.hashCode() % table.length);
        Entry node = table[hash];

        // empty slot
        if (node == null) {
            table[hash] = new Entry(key, value);
            size++;
            return null;
        }
        
        while (true) {
            // update
            if (node.person.equals(key)) {
                PhoneNumber old = node.phoneNumber;
                node.phoneNumber = value;
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

    public PhoneNumber get(Person key) {
        int hash = Math.abs(key.hashCode() % table.length);
        Entry node = table[hash];
       
        // empty slot
        if (node == null) return null;
        
        while (true) {
            if (node.person.equals(key)) return node.phoneNumber; // found
            if (node.next == null) return null; // end of chain
            node = node.next;
    }}

    public PhoneNumber remove(Person key) { 
        int hash = Math.abs(key.hashCode() % table.length);
        Entry node = table[hash];

        if (node == null) return null;
        if (node.person.equals(key)) {
            PhoneNumber old = node.phoneNumber;
            table[hash] = node.next;
            size--;
            return old;
        }
        if (node.next == null) return null;

        while (true) {
            if (node.next.person.equals(key)) {
                PhoneNumber old = node.next.phoneNumber;
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
            Entry node = table[i];
            while (node != null) {
                sb.append(node.person).append(":").append(node.phoneNumber).append(", ");
                node = node.next;
            }
        }
        return "[" + sb.toString().substring(0, sb.length() - 2) + "]";
    }
}
