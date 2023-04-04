public class Person {
    protected String name;

    public Person(String name) { this.name = name; }

    public int hashCode(int tableSize) { return name.hashCode() % tableSize; }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person)) return false;
        
        return name.equals(((Person) obj).name);
    }

    @Override
    public String toString() { return name; }
}
