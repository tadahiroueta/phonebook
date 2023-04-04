import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Performance {
    
    class Pair<K,V> {
        K key;
        V value;
        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final Pair<String, String>[] 
        set = scan("Large Data Set.txt"), 
        successfulSearches = scan("Successful Search Records.txt"), 
        unsuccessfulSearches = scan("Unsuccessful Search Records.txt"); 

    private Pair<String, String>[] scan(String filename) {
        List<Pair<String, String>> list = new ArrayList<>();

        Scanner scanner = new Scanner(filename);
        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();
            list.add(new Pair<String, String>(
                line.substring(0, 5), line.substring(6)
            ));
        }
        scanner.close();
        return list.toArray(new Pair[list.size()]);
    }

    public void testHashMap(double loadFactor) {
        MyHashTable<String, String> map = new MyHashTable<>();
        int collisionCounter = 0;
        long startTime = System.currentTimeMillis(); // start stopwatch
        for (Pair<String, String> pair : set) {
            if (map.put(pair.key, pair.value) != null) {
                
            }
        }
        
    }
}
