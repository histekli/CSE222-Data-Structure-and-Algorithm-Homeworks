package src.spellchecker;

public class TestRunner {
    public static void main(String[] args) {
        // Test GTUHashMap
        System.out.println("Testing GTUHashMap...");
        GTUHashMap<String, Integer> map = new GTUHashMap<>(10);
        map.put("one", 1);
        map.put("two", 2);
        System.out.println("Value for 'one': " + map.get("one"));
        System.out.println("Value for 'two': " + map.get("two"));
        map.remove("one");
        System.out.println("Contains 'one' after removal: " + map.containsKey("one"));

        // Test GTUHashSet
        System.out.println("\nTesting GTUHashSet...");
        GTUHashSet<String> set = new GTUHashSet<>(10);
        set.add("apple");
        set.add("banana");
        System.out.println("Contains 'apple': " + set.contains("apple"));
        set.remove("apple");
        System.out.println("Contains 'apple' after removal: " + set.contains("apple"));

        System.out.println("\nAll tests completed!");
    }
}