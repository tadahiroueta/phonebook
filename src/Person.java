public class Person {
    protected String name;

    public Person(String name) { this.name = name; }

    public int hashCode() { return name.hashCode(); }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person)) return false;
        
        return name.equals(((Person) obj).name);
    }

    @Override
    public String toString() { return name; }
}
