import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Performance {
    
    class Pair<K,V> {
        K key;
        V value;
        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final Set<Pair<String, String>> 
        set = scan("Large Data Set.txt"), 
        successfulSearches = scan("Successful Search Records.txt"), 
        unsuccessfulSearches = scan("Unsuccessful Search Records.txt"); 

    private Set<Pair<String, String>> scan(String filename) {
        Set<Pair<String, String>> set = new HashSet<>();

        Scanner in;
        try { in = new Scanner(new File(filename)); }
        catch (FileNotFoundException e) {
            System.out.println("Error reading " + filename + "."); 
            return null;
        }

        while (in.hasNextLine()) {

            String line = in.nextLine();
            set.add(new Pair<String, String>(
                line.substring(0, 5), line.substring(6)
            ));
        }
        in.close();
        return set;
    }

    private long[] getStats(Measurable<String, String> structure) {
        long[] stats = new long[6]; // first 3 are times, last 3 are counters
        stats[1] = stats[3] = stats[5] = 0;

        long startTime = System.currentTimeMillis();
        for (Pair<String, String> pair : set) stats[0] += structure.putWithCollision(pair.key, pair.value);
        stats[1] = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        for (Pair<String, String> pair : successfulSearches) stats[2] += structure.getWithProbes(pair.key);
        stats[3] = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        for (Pair<String, String> pair : unsuccessfulSearches) stats[4] += structure.getWithProbes(pair.key);
        stats[5] = System.currentTimeMillis() - startTime;

        return stats;
    }

    private String getMapReport(double loadFactor) {
        int tableSize = (int) (set.size() / loadFactor);
        
        long[] stats = getStats(new MyHashTable<>(tableSize));
        
        return String.format(
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
            set.size(), 
            tableSize, 
            loadFactor, 
            (double) stats[1] / set.size(), 
            stats[0], 
            100 * (double) stats[0] / set.size(),
            (double) stats[3] / successfulSearches.size(),
            (double) stats[2] / successfulSearches.size(),
            (double) stats[5] / unsuccessfulSearches.size(),
            (double) stats[4] / unsuccessfulSearches.size()
        );
    }

    private String getTreeReport() {
        long[] stats = getStats(new PhoneBookTree<>());
        return String.format(
            """
                Data structure: Binary search tree
                Compare to function: Default string compare to

                Records: %s
                
                Average insertion time: %s ms
                Insertion collisions: %s
                Collisions per insertion: %s%%
                
                Average time for successful searches: %s ms
                Probes per successful search: %s
                
                Average time for unsuccessful searches: %s ms
                Probes per unsuccessful search: %s



            """,
            set.size(),
            (double) stats[1] / set.size(),
            stats[0],
            100 * (double) stats[0] / set.size(),
            (double) stats[3] / successfulSearches.size(),
            (double) stats[2] / successfulSearches.size(),
            (double) stats[5] / unsuccessfulSearches.size(),
            (double) stats[4] / unsuccessfulSearches.size()
        );
    }

    private String getTreeMapReport(double loadFactor) {
        int tableSize = (int) (set.size() / loadFactor);
        long[] stats = getStats(new TreeTable<>(tableSize));
        return String.format(
            """
                Data structure: Hash table with binary search trees
                Compare to function: Default string compare to

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
            set.size(),
            tableSize,
            loadFactor,
            (double) stats[1] / set.size(),
            stats[0],
            100 * (double) stats[0] / set.size(),
            (double) stats[3] / successfulSearches.size(),
            (double) stats[2] / successfulSearches.size(),
            (double) stats[5] / unsuccessfulSearches.size(),
            (double) stats[4] / unsuccessfulSearches.size()
        );
    }

    public void saveReports() {        
        PrintWriter out;
        try { out = new PrintWriter(new File("report.txt")); }
        catch (FileNotFoundException e) {
            System.out.println("Error creating file for record."); 
            return; 
        }

        double[] loadFactors = new double[] { .5, .75, .9 };

        for (double loadFactor : loadFactors) out.println(getMapReport(loadFactor));
        out.println(getTreeReport());
        for (double loadFactor : loadFactors) out.println(getTreeMapReport(loadFactor));
        
        out.close();
    }    

    public static void main(String[] args) {
        new Performance().saveReports();
    }
}
