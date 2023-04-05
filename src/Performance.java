import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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

        Scanner in;
        try { in = new Scanner(new File(filename)); }
        catch (FileNotFoundException e) {
            System.out.println("Error reading " + filename + "."); 
            return null;
        }

        while (in.hasNextLine()) {

            String line = in.nextLine();
            list.add(new Pair<String, String>(
                line.substring(0, 5), line.substring(6)
            ));
        }
        in.close();
        return list.toArray(new Pair[list.size()]);
    }

    public void testHashMap(double loadFactor) {
        int tableSize = (int) (set.length / loadFactor),
            collisionCounter = 0, successfulProbeCounter = 0, unsuccessfulProbeCounter = 0;
        MyHashTable<String, String> map = new MyHashTable<>((int) (set.length / loadFactor));
        
        long startTime = System.currentTimeMillis(); // start stopwatch
        for (Pair<String, String> pair : set) collisionCounter += map.putWithCollision(pair.key, pair.value);
        long buildTime = System.currentTimeMillis() - startTime; // stop stopwatch
        
        startTime = System.currentTimeMillis();
        for (Pair<String, String> pair : successfulSearches) successfulProbeCounter += map.getWithProbes(pair.key);
        long successfulSearchTime = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        for (Pair<String, String> pair : unsuccessfulSearches) unsuccessfulProbeCounter += map.getWithProbes(pair.key);
        long unsuccessfulSearchTime = System.currentTimeMillis() - startTime;

        PrintWriter out;
        try { out = new PrintWriter(new File("report-map-" + loadFactor + ".txt")); }
        catch (FileNotFoundException e) {
            System.out.println("Error creating file for record."); 
            return; 
        }
        
        out.println(String.format(
            """
                Hash type: Open hashing
                Hash function: Default string hashcode

                Records: %s
                Table size: %s
                Load factor: %s
                
                Average insertion time: %s ms
                Insertion collisions: %s
                Collisions per insertion: %s%%
                
                Average time for successful searches: %s ms
                Probes per successful search: %s
                
                Average time for unsuccessful searches: %s ms
                Probes per unsuccessful search: %s
            """, 
            set.length, 
            tableSize, 
            loadFactor, 
            (double) buildTime / set.length, 
            collisionCounter, 
            100 * (double) collisionCounter / set.length,
            (double) successfulSearchTime / successfulSearches.length,
            (double) successfulProbeCounter / successfulSearches.length,
            (double) unsuccessfulSearchTime / unsuccessfulSearches.length,
            (double) unsuccessfulProbeCounter / unsuccessfulSearches.length
        ));
        out.close();
    }

    public static void main(String[] args) {
        Performance performance = new Performance();
        performance.testHashMap(0.5);
        performance.testHashMap(0.75);
        performance.testHashMap(0.9);
    }
}
