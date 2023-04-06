public interface Measurable<K, V> {
    int size();
    V put(K key, V value);
    int putWithCollision(K key, V value);
    V get(K key);
    int getWithProbes(K key);
    V remove(K key);
    int removeWithCollision(K key);

    // you see, I thought this was great for performance tests...
    // but now I realise that the tests were really meant to compare hashmaps...
    // but I've already written it, so I might as well leave it here
}