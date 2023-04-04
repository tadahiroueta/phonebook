/**
 * Run some basic tests on your MyHashTable class
 * 
 * The setUp() method runs one time prior to any @Test
 * 
 * Tests run in numerical order
 * 
 * Feel free to add your own or add print statements to check values (or use the debugger)
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) //run methods in numerical order
public class MyHashTableTester
{
	static final String PATH = ""; //change if your data files are stored in another directory
	
	static MyHashTable<Person, Integer> table;
	
	// This code executes once and only once before an @Test method runs.
	// Good for initialization.
	@BeforeClass
	public static void setUp() {
		table = new MyHashTable<>();
	}
	
	@Test
	public void test00_putNoPreviousValue() {
		assertTrue("Adding Mary should return null", table.put(new Person("Mary"), 80) == null);
	}

	@Test
	public void test01_putWithPreviousValue() {
		assertTrue("Replacing Mary should return previously mapped value (80)", table.put(new Person("Mary"), 50) == 80); //return prev. value
		assertTrue("Should return Mary's new age (50)", table.get(new Person("Mary")) == 50);                             //return new value
	}
	
	@Test
	public void test02_getNoCollision() {
		Person jim = new Person("Jim");
		table.put(jim, 20);
		assertTrue("Jim should be mapped to 20", 20 == table.get(jim));
	}

	@Test
	public void test03_getDoesntExist() {
		assertEquals("Bob doesn't exist in map (return null)", null, table.get(new Person("Bob")));
	}
	
	@Test
	public void test04_getWithCollision() {
		//this should generate a collision if you're using String's hashCode method to
		//  hash the Person.  Uncomment the print statement if you're skeptical
//		System.out.println("Siblings".hashCode() + ", " + "Teheran".hashCode());
		assertTrue("Your Person.java needs to use the String class' hashCode method", "Siblings".hashCode() == new Person("Siblings").hashCode());
		table.put(new Person("Siblings"), 60);
		table.put(new Person("Teheran"), 70);
		assertTrue("Should return value of second item in linked list (70)", 70 == table.get(new Person("Teheran")));
	}

	@Test
	public void test05_removeExistsNoCollision() {
		Person charlie = new Person("Charlie");
		table.put(charlie, 30);
		assertTrue("Removing Charlie returns mapped value (30)", table.remove(charlie) == 30);
		assertTrue("Getting Charlie should now return null", table.get(charlie) == null);
	}
	
	@Test
	public void test06_removeDoesntExistNoCollision() {
		assertTrue("Attempting to remove Bob should return null", table.remove(new Person("Bob")) == null);
	}
	
	@Test
	public void test07_removeExistsWithCollisionFirstElementFirst() {
		//first element remove
		assertTrue("Removing first item in collision list should return 60", table.remove(new Person("Siblings")) == 60);
		assertTrue("Now Siblings should not exist in table", table.remove(new Person("Siblings")) == null);
		assertTrue("Teheran should still be in table", table.get(new Person("Teheran")) != null);
	}
	
	@Test
	public void test08_removeExistsWithCollisionSecondElementFirst() {
		table = new MyHashTable<>();
		
		//this should generate a collision if you're using String's hashCode method to
		//  hash the Person.  Uncomment the print statement if you're skeptical
		//    (need to put them back in since they were previously removed)
		table.put(new Person("Siblings"), 60);
		table.put(new Person("Teheran"), 70);
		
		//second element remove
		assertTrue("Removing second item in collision list should return 70", table.remove(new Person("Teheran")) == 70);
		assertTrue("Now Teheran should not exist in table", table.remove(new Person("Teheran")) == null);
		assertTrue("Siblings should still be in table", table.get(new Person("Siblings")) != null);
	}
	
	@Test
	public void test09_removeBothCollidingElements() {
		//this should generate a collision if you're using String's hashCode method to
		//  hash the Person.  Uncomment the print statement if you're skeptical
		//    (need to put them back in since they were previously removed)
		table.put(new Person("Siblings"), 60);
		table.put(new Person("Teheran"), 70);
		
		//second element remove
		assertTrue("Removing second item in collision list should return 70", table.remove(new Person("Teheran")) == 70);
		assertTrue("Siblings should not exist in table", table.remove(new Person("Teheran")) == null);

		//first element remove
		assertTrue("Removing first item in collision list should return 60", table.remove(new Person("Siblings")) == 60);
		assertTrue("Siblings should not exist in table", table.remove(new Person("Siblings")) == null);
	}
	
	@Test
	public void test10_removeDoesntExistWithCollision() {
		table = new MyHashTable<>();
		
		table.put(new Person("Siblings"), 60);
		//create a collision but without putting the colliding element in the list
		//  to check if your table walks the entire list
		assertTrue("Should not find the Teheran person", table.remove(new Person("Teheran")) == null);
	}
	
	@Test
	public void test11_testSizeEmpty() {
		table = new MyHashTable<>();
		assertTrue("Size should be 0", table.size() == 0);
	}
	
	@Test
	public void test12_testSizeOnePut() {
		table.put(new Person("Jill"), 90);
		assertTrue("Size should be 1", table.size() == 1);
		
		table.put(new Person("Jill"), 100);
		assertTrue("Size should STILL be 1", table.size() == 1);
	}
	
	@Test
	public void test13_testSizeOnePutOneRemove() {
		table.put(new Person("Jill"), 90);
		
		table.remove(new Person("Jill"));
		
		assertTrue("Size should be 0", table.size() == 0);
	}
	
	@Test
	public void test14_testSizeMultiplePuts() {
		table.put(new Person("Jill"),   90);
		table.put(new Person("Ronnie"), 100);
		table.put(new Person("Westyn"), 110);
		
		assertTrue("Size should be 3", table.size() == 3);
	}
	
	/*
	 * This test may take some time to run, uncomment if you want to check the performance
	 * of your hash table.  Note that it will require an extra method be written to work
	 */
//	@Test
//	public void test15_testRuntimeComparedToList() {
//		System.out.println("RUNNING BIG-O TESTS");
//		
//		final int size = 1_000_000;
//		
//		for (int i = 0; i < size; i++) {
//			table.put(new Person("" + i), i);
//		}
//
//		double start = System.currentTimeMillis();
//		
//		table.get(new Person("" + size/2));
//		
//		System.out.println("table lookup: " + (System.currentTimeMillis() - start) + "ms");
//		
//		List<Person> list = new ArrayList<>(size);
//		
//	    //you will need to implement the keySet method (similar to java.util.HashMap)
//		list.addAll(table.keySet()); //keySet should return a Set<K>, where K is a Person in this case
//
//		start = System.currentTimeMillis();
//		
//		list.contains(new Person("" + size/2));
//		
//		System.out.println("list contains: " + (System.currentTimeMillis() - start) + "ms");
//	}
}