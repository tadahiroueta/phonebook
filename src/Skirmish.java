public class Skirmish {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Person person = new Person("John");
        PhoneNumber number = new PhoneNumber("123-456-7890");
        phoneBook.put(person, number);
        System.out.println(phoneBook.get(person));
        System.out.println(phoneBook.remove(person));
        System.out.println(phoneBook.get(person));

        // more tests
        phoneBook.put(new Person("A"), new PhoneNumber("111-111-1111"));
        phoneBook.put(new Person("B"), new PhoneNumber("222-222-2222"));
        phoneBook.put(new Person("C"), new PhoneNumber("333-333-3333"));
        phoneBook.put(new Person("D"), new PhoneNumber("444-444-4444"));
        phoneBook.put(new Person("E"), new PhoneNumber("555-555-5555"));
        phoneBook.put(new Person("F"), new PhoneNumber("666-666-6666"));
        phoneBook.put(new Person("G"), new PhoneNumber("777-777-7777"));
        phoneBook.put(new Person("H"), new PhoneNumber("888-888-8888"));
        phoneBook.put(new Person("I"), new PhoneNumber("999-999-9999"));
        phoneBook.put(new Person("J"), new PhoneNumber("000-000-0000"));
        phoneBook.put(new Person("K"), new PhoneNumber("101-010-1010"));
        phoneBook.put(new Person("L"), new PhoneNumber("111-111-1111"));
        phoneBook.put(new Person("M"), new PhoneNumber("121-212-1212"));
        phoneBook.put(new Person("N"), new PhoneNumber("131-313-1313"));
        phoneBook.put(new Person("O"), new PhoneNumber("141-414-1414"));
        phoneBook.put(new Person("P"), new PhoneNumber("151-515-1515"));
        phoneBook.put(new Person("Q"), new PhoneNumber("161-616-1616"));
        phoneBook.put(new Person("R"), new PhoneNumber("171-717-1717"));
        phoneBook.put(new Person("S"), new PhoneNumber("181-818-1818"));
        phoneBook.put(new Person("T"), new PhoneNumber("191-919-1919"));
        phoneBook.put(new Person("U"), new PhoneNumber("202-020-2020"));
        phoneBook.put(new Person("V"), new PhoneNumber("212-121-2121"));
        phoneBook.put(new Person("W"), new PhoneNumber("222-222-2222"));
        phoneBook.put(new Person("X"), new PhoneNumber("232-323-2323"));
        phoneBook.put(new Person("Y"), new PhoneNumber("242-424-2424"));
        phoneBook.put(new Person("Z"), new PhoneNumber("252-525-2525"));
        System.out.println(phoneBook);

        // now add entries to cause collisions
        phoneBook.put(new Person("AA"), new PhoneNumber("111-111-1111"));
        phoneBook.put(new Person("BB"), new PhoneNumber("222-222-2222"));
        phoneBook.put(new Person("CC"), new PhoneNumber("333-333-3333"));
        phoneBook.put(new Person("DD"), new PhoneNumber("444-444-4444"));
        phoneBook.put(new Person("EE"), new PhoneNumber("555-555-5555"));
        phoneBook.put(new Person("FF"), new PhoneNumber("666-666-6666"));
        phoneBook.put(new Person("GG"), new PhoneNumber("777-777-7777"));
        phoneBook.put(new Person("HH"), new PhoneNumber("888-888-8888"));
        phoneBook.put(new Person("II"), new PhoneNumber("999-999-9999"));
        phoneBook.put(new Person("JJ"), new PhoneNumber("000-000-0000"));
        phoneBook.put(new Person("KK"), new PhoneNumber("101-010-1010"));
        phoneBook.put(new Person("LL"), new PhoneNumber("111-111-1111"));
        phoneBook.put(new Person("MM"), new PhoneNumber("121-212-1212"));
        phoneBook.put(new Person("NN"), new PhoneNumber("131-313-1313"));
        phoneBook.put(new Person("OO"), new PhoneNumber("141-414-1414"));
        phoneBook.put(new Person("PP"), new PhoneNumber("151-515-1515"));
        phoneBook.put(new Person("QQ"), new PhoneNumber("161-616-1616"));
        phoneBook.put(new Person("RR"), new PhoneNumber("171-717-1717"));
        phoneBook.put(new Person("SS"), new PhoneNumber("181-818-1818"));
        phoneBook.put(new Person("TT"), new PhoneNumber("191-919-1919"));
        phoneBook.put(new Person("UU"), new PhoneNumber("202-020-2020"));
        phoneBook.put(new Person("VV"), new PhoneNumber("212-121-2121"));
        phoneBook.put(new Person("WW"), new PhoneNumber("222-222-2222"));
        phoneBook.put(new Person("XX"), new PhoneNumber("232-323-2323"));
        phoneBook.put(new Person("YY"), new PhoneNumber("242-424-2424"));
        phoneBook.put(new Person("ZZ"), new PhoneNumber("252-525-2525"));
        System.out.println(phoneBook);
    }
}